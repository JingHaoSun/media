package com.innovate.media.controller;

import com.innovate.media.constant.ConstantMessage;
import com.innovate.media.constant.ResultConstant;
import com.innovate.media.domain.Postion;
import com.innovate.media.domain.WorkCategory;
import com.innovate.media.repository.PostionRepository;
import com.innovate.media.utils.BaseResult;
import com.innovate.media.utils.ChangeResultTypeUtils;
import com.innovate.media.utils.FormatBeanUtils;
import com.innovate.media.utils.PageBean;
import javafx.geometry.Pos;
import org.omg.CORBA.BAD_CONTEXT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class PostionController {
    @Autowired
    private PostionRepository postionRepository;
    @PostMapping(path = "/getPostionList")
    public PageBean getPage(@RequestParam("page")int pageNum, @RequestParam("rows")int pageLimit,
                            @RequestParam("order")String order){
        Pageable pageable = PageRequest.of(pageNum - 1, pageLimit, Sort.Direction.ASC, order);
        Page<Postion> page = postionRepository.findAll(pageable);
        PageBean pageBean = new PageBean();
        pageBean.setTotal(page.getNumberOfElements());
        List<Map> list = ChangeResultTypeUtils.changePostionResultType(page.getContent());
        pageBean.setRows(list);
        return pageBean;
    }
    @PostMapping("/getSimplePostion")
    public Map getSimplePostion(@RequestParam("id")Long id){
        Optional<Postion> optional = postionRepository.findById(id);
        Map map = null;
        try{
            map = FormatBeanUtils.formatBean(optional.get());
        }catch (Exception e){
            return null;
        }
        return map;
    }
    @PostMapping("/addPostion")
    public BaseResult addPostion(@RequestParam Map<String, String> requestMap){
        Postion postion = new Postion();
        postion.setClientId(Integer.valueOf(requestMap.get("client_id")));
        postion.setContact(requestMap.get("contact"));
        postion.setPostionName(requestMap.get("postion_name"));
        postion.setSalary(Integer.valueOf(requestMap.get("salary")));
        postion.setWorkage(Integer.valueOf(requestMap.get("workage")));
        postion.setDescription(requestMap.get("description"));
        postion.setRecruitment(Integer.valueOf(requestMap.get("recruitment")));
        postion.setPostionLabel(requestMap.get("postion_label"));
        postion.setPhone(requestMap.get("phone"));
        Postion postion1 = postionRepository.save(postion);
        if(postion1 != null){
            return BaseResult.result(ResultConstant.SUCCESS, ConstantMessage.ADD_SUCCESS_MESSAGE);
        }else{
            return BaseResult.result(ResultConstant.SAVE_FAIL, ConstantMessage.ADD_FAILED_MESSAGE);
        }
    }
    @PostMapping("/updatePostion")
    public BaseResult updatePostion(@RequestParam Map<String, String> requestMap){
        Postion postion = new Postion();
        postion.setId(Long.valueOf(requestMap.get("id")));
        postion.setClientId(Integer.valueOf(requestMap.get("client_id")));
        postion.setContact(requestMap.get("contact"));
        postion.setPostionName(requestMap.get("postion_name"));
        postion.setSalary(Integer.valueOf(requestMap.get("salary")));
        postion.setWorkage(Integer.valueOf(requestMap.get("workage")));
        postion.setDescription(requestMap.get("description"));
        postion.setRecruitment(Integer.valueOf(requestMap.get("recruitment")));
        postion.setPostionLabel(requestMap.get("postion_label"));
        postion.setPhone(requestMap.get("phone"));
        Postion postion1 = postionRepository.save(postion);
        if(postion1 != null){
            return BaseResult.result(ResultConstant.SUCCESS, ConstantMessage.UPDATE_SUCCESS_MESSAGE);
        }else{
            return BaseResult.result(ResultConstant.UPDATE_FAIL, ConstantMessage.UPDATE_FAILED_MESSAGE);
        }
    }
    @PostMapping("/deletePostion")
    public BaseResult deletePostion(@RequestParam("id")Long id){
        postionRepository.deleteById(id);
        return BaseResult.result(ResultConstant.SUCCESS, ConstantMessage.DELETE_SUCCESS_MESSAGE);
    }
}
