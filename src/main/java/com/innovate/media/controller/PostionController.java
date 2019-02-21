package com.innovate.media.controller;

import com.innovate.media.constant.ConstantMessage;
import com.innovate.media.constant.ResultConstant;
import com.innovate.media.domain.Postion;
import com.innovate.media.domain.WorkCategory;
import com.innovate.media.repository.PostionRepository;
import com.innovate.media.utils.BaseResult;
import javafx.geometry.Pos;
import org.omg.CORBA.BAD_CONTEXT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
public class PostionController {
    @Autowired
    private PostionRepository postionRepository;
    @PostMapping(path = "/getPostion")
    public Page<Postion> getPage(@RequestParam("pageNum")int pageNum, @RequestParam("pageLimit")int pageLimit){
        Pageable pageable = PageRequest.of(pageNum - 1, pageLimit);
        return postionRepository.findAll(pageable);
    }
    @PostMapping("/getSimplePostion")
    public Optional<Postion> getSimplePostion(@RequestParam("id")Long id){
        return postionRepository.findById(id);
    }
    @PostMapping("/addPostion")
    public BaseResult addPostion(@RequestParam Map<String, String> requestMap){
        Postion postion = new Postion();
        postion.setClient_id(Integer.valueOf(requestMap.get("client_id")));
        postion.setContact(requestMap.get("contact"));
        postion.setPostion_name(requestMap.get("postion_name"));
        postion.setSalary(Integer.valueOf(requestMap.get("salary")));
        postion.setWorkage(Integer.valueOf(requestMap.get("workage")));
        postion.setDescription(requestMap.get("description"));
        postion.setRecruitment(Integer.valueOf(requestMap.get("recruitment")));
        postion.setPostion_label(requestMap.get("postion_label"));
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
        postion.setClient_id(Integer.valueOf(requestMap.get("client_id")));
        postion.setContact(requestMap.get("contact"));
        postion.setPostion_name(requestMap.get("postion_name"));
        postion.setSalary(Integer.valueOf(requestMap.get("salary")));
        postion.setWorkage(Integer.valueOf(requestMap.get("workage")));
        postion.setDescription(requestMap.get("description"));
        postion.setRecruitment(Integer.valueOf(requestMap.get("recruitment")));
        postion.setPostion_label(requestMap.get("postion_label"));
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
