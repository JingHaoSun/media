package com.innovate.media.controller;

import com.innovate.media.constant.ConstantMessage;
import com.innovate.media.constant.ResultConstant;
import com.innovate.media.domain.Admin;
import com.innovate.media.domain.AdminLog;
import com.innovate.media.domain.WorkCategory;
import com.innovate.media.repository.AdminLogRepository;
import com.innovate.media.repository.AdminRepository;
import com.innovate.media.utils.BaseResult;
import com.innovate.media.utils.ChangeResultTypeUtils;
import com.innovate.media.utils.FormatBeanUtils;
import com.innovate.media.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

@RestController
public class AdminController {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    AdminLogRepository adminLogRepository;

    @PostMapping("/addAdmin")
    public BaseResult addAdmin(@RequestParam("user_name")String name, @RequestParam("password")String password
                          ){
        Admin admin = adminRepository.save(new Admin(name,password));
        if(admin != null){
            return BaseResult.result(ResultConstant.SUCCESS, ConstantMessage.ADD_SUCCESS_MESSAGE);
        }else{
            return BaseResult.result(ResultConstant.SAVE_FAIL, ConstantMessage.ADD_FAILED_MESSAGE);
        }
    }
    @PostMapping("/deleteAdmin")
    public BaseResult deleteAdmin(@RequestParam("id")Long id){
        adminRepository.deleteById(id);
        return BaseResult.result(ResultConstant.SUCCESS, ConstantMessage.DELETE_SUCCESS_MESSAGE);
    }
    @PostMapping("/updateAdmin")
    public BaseResult upadte(@RequestParam Map<String, String> requestMap){
        Admin admin = new Admin();
        admin.setId(Long.valueOf(requestMap.get("id")));
        admin.setUserName(requestMap.get("user_name"));
        admin.setPassword(requestMap.get("password"));
        Admin adminl = adminRepository.save(admin);
        if(adminl != null){
            return BaseResult.result(ResultConstant.SUCCESS, ConstantMessage.UPDATE_SUCCESS_MESSAGE);
        }else{
            return BaseResult.result(ResultConstant.UPDATE_FAIL, ConstantMessage.UPDATE_FAILED_MESSAGE);
        }
    }
    @PostMapping("/getSingleAdmin")
    public Map getSingleAdmin(@RequestParam("id")Long id){
        Optional<Admin> optional = adminRepository.findById(id);
        Map map = null;
        try{
            map = FormatBeanUtils.formatBean(optional.get());
        }catch (Exception e){
            return null;
        }
        return map;
    }
    @PostMapping("/updateAdminPassword")
    public BaseResult updateAdminPassword(@RequestParam("id")Long id, @RequestParam("password")String password){
        int admin = adminRepository.updateAdminPassword(id, password);
        if(admin != 0){
            return BaseResult.result(ResultConstant.SUCCESS, ConstantMessage.UPDATE_SUCCESS_MESSAGE);
        }else{
            return BaseResult.result(ResultConstant.UPDATE_FAIL, ConstantMessage.UPDATE_FAILED_MESSAGE);
        }
    }

    @PostMapping("/updateCurrentAdminPassword")
    public BaseResult updateCurrentAdminPassword(@RequestParam("password")String password, HttpSession httpSession){
        Long id = (Long)httpSession.getAttribute("id");
        if(id == null || StringUtils.isEmpty(password)){
            return BaseResult.result(ResultConstant.UPDATE_FAIL,ConstantMessage.UPDATE_FAILED_MESSAGE);
        }
        int admin = adminRepository.updateAdminPassword(id, password);
        if(admin != 0){
            return BaseResult.result(ResultConstant.SUCCESS, ConstantMessage.UPDATE_SUCCESS_MESSAGE);
        }else{
            return BaseResult.result(ResultConstant.UPDATE_FAIL, ConstantMessage.UPDATE_FAILED_MESSAGE);
        }
    }

    @PostMapping("/getAllAdminList")
    public PageBean getPage(@RequestParam("page")int pageNum, @RequestParam("rows")int pageLimit,
                            @RequestParam("order")String order, @RequestParam("user_name")String user_name){
        Pageable pageable = PageRequest.of(pageNum - 1, pageLimit, Sort.Direction.ASC, order);
        Page<Admin> page =  adminRepository.findAllByUserName(user_name, pageable);
        PageBean pageBean = new PageBean();
        pageBean.setTotal(page.getNumberOfElements());
        List<Map> list = ChangeResultTypeUtils.changeAdminResultType(page.getContent());
        pageBean.setRows(list);
        return pageBean;
    }


    @GetMapping("/login")
    public BaseResult login(@RequestParam("user_name")String name,
                            @RequestParam("password")String password,
                            HttpServletRequest request) throws ParseException {
        if(StringUtils.isEmpty(name)){
            /* 用户名为空*/
            return BaseResult.result(ResultConstant.USERNAME_EMPTY,ConstantMessage.USERNAME_EMPTY_MESSAGE);
        }else if(StringUtils.isEmpty(password)){
            /* 密码为空*/
            return BaseResult.result(ResultConstant.PASSWORD_EMPTY,ConstantMessage.PASSWORD_EMPTY_MESSAGE);
        }else {
            /* 处理登录逻辑*/

            return checkLogin(name, password,request);
        }
    }
    public BaseResult checkLogin(String userName, String password, HttpServletRequest request) throws ParseException {
        Admin admin = adminRepository.findAdminByUserNameAndPassword(userName, password);
        if(admin!=null){
            String lastIp = request.getRemoteAddr();
            HttpSession session = request.getSession();
            session.setAttribute("id", admin.getId());
            session.setAttribute("name", userName);
            /**
             * 时间
             */
            Timestamp lastTime = new java.sql.Timestamp(new Date().getTime());
            System.out.println(new java.sql.Timestamp(new Date().getTime()));
            admin.setLastTime(lastTime);
            admin.setLastIp(lastIp);
            AdminLog adminLog = new AdminLog(admin.getId(), lastIp, lastTime);
            adminLogRepository.save(adminLog);
            adminRepository.save(admin);
            return BaseResult.result(ResultConstant.SUCCESS, ConstantMessage.LOGIN_SUCCESS_MESSAGE);
        }else{
            return BaseResult.result(ResultConstant.NO_SUCH_USER, ConstantMessage.NO_SUCH_USER_MESSAGE);
        }
    }

    @GetMapping("/logout")
    public BaseResult logOut(HttpSession httpSession){
        Long sessionId =(Long) httpSession.getAttribute("id");
        String sessionName = (String)httpSession.getAttribute("name");
        if(sessionId == null && sessionName == null){
            /* 管理员已登出*/
            return BaseResult.result(ResultConstant.LOGOUT,ConstantMessage.LOGOUT_MESSAGE);
        }
        /* 登出*/
        httpSession.removeAttribute("id");
        httpSession.removeAttribute("name");
        return BaseResult.result(ResultConstant.SUCCESS,ConstantMessage.LOUGOU_SUCCESS_MESSAGE);
    }
    @GetMapping("/checkLoginStatus")
    public Object checkStatus(HttpSession httpSession){
        Long id = (Long) httpSession.getAttribute("id");
        String name = (String) httpSession.getAttribute("name");
        if(name!=null && id != null){
            Map map = new LinkedHashMap(3);
            Map data = new HashMap();
            data.put("user_name", name);
            map.put("status_code", ResultConstant.SUCCESS);
            map.put("message", ConstantMessage.LOGIN_SUCCESS_MESSAGE);
            map.put("data", data);
            return map;
        }
        return BaseResult.result(ResultConstant.LOGOUT,ConstantMessage.LOGOUT_MESSAGE);
    }
}
