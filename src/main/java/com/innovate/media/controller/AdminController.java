package com.innovate.media.controller;

import com.innovate.media.constant.ConstantMessage;
import com.innovate.media.constant.ResultConstant;
import com.innovate.media.domain.Admin;
import com.innovate.media.repository.AdminRepository;
import com.innovate.media.utils.BaseResult;
import org.omg.PortableInterceptor.AdapterManagerIdHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class AdminController {
    @Autowired
    AdminRepository adminRepository;


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
        admin.setId(Integer.parseInt(requestMap.get("id")));
        admin.setUserName(requestMap.get("user_name"));
        admin.setPassword(requestMap.get("password"));
        Admin adminl = adminRepository.save(admin);
        if(adminl != null){
            return BaseResult.result(ResultConstant.SUCCESS, ConstantMessage.UPDATE_SUCCESS_MESSAGE);
        }else{
            return BaseResult.result(ResultConstant.UPDATE_FAIL, ConstantMessage.UPDATE_FAILED_MESSAGE);
        }
    }
    @PostMapping("/findAdmin")
    public Optional<Admin> findAdmin(@RequestParam("id")Long id){
        return adminRepository.findById(id);
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

    @PostMapping("/getAllAdminList")
    public Page<Admin> getPage(@RequestParam("pageNum")int pageNum, @RequestParam("pageLimit")int pageLimit){
        Pageable pageable = PageRequest.of(pageNum - 1, pageLimit);
        return adminRepository.findAll(pageable);
    }
    @PostMapping("/login")
    public BaseResult login(@RequestParam("user_name")String name,
                            @RequestParam("password")String password,
                            HttpServletRequest request){
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
    public BaseResult checkLogin(String userName, String password, HttpServletRequest request){
        Admin admin = adminRepository.findByUserNameAndPassword(userName, password);
        if(admin!=null){
            return BaseResult.result(ResultConstant.SUCCESS, ConstantMessage.LOGIN_SUCCESS_MESSAGE);
        }else{
            return BaseResult.result(ResultConstant.NO_SUCH_USER, ConstantMessage.NO_SUCH_USER_MESSAGE);
        }
    }
}
