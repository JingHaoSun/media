package com.innovate.media.controller;

import com.innovate.media.constant.ConstantMessage;
import com.innovate.media.constant.ResultConstant;
import com.innovate.media.domain.Work;
import com.innovate.media.domain.WorkCategory;
import com.innovate.media.repository.WorkRepository;
import com.innovate.media.utils.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLOutput;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@RestController
public class WorkController {
    @Autowired
    private WorkRepository workRepository;
    @PostMapping(path = "/getAllWork")
    public Page<Work> getPage(@RequestParam("pageNum")int pageNum, @RequestParam("pageLimit")int pageLimit){
        Pageable pageable = PageRequest.of(pageNum - 1, pageLimit);
        return workRepository.findAll(pageable);
    }
    @PostMapping("/getSimpleWork")
    public Optional<Work> getSimpleWork(@RequestParam("id")Long id){
        return workRepository.findById(id);
    }

    @PostMapping("/addWork")
    public BaseResult addWork(@RequestParam Map<String, String> requestMap) throws ParseException {
        Work work = new Work();
        work.setWork_name(requestMap.get("work_name"));
        work.setDescription(requestMap.get("description"));
        work.setPicture(requestMap.get("picture"));
        work.setWork_label(requestMap.get("work_label"));
        work.setVideo(requestMap.get("video"));
        work.setCover(requestMap.get("cover"));
        work.setComment(requestMap.get("comment"));
        work.setClient_id(Integer.valueOf(requestMap.get("client_id")));
        work.setCategory_id(Integer.valueOf(requestMap.get("category_id")));
        work.setCollect(Integer.valueOf(requestMap.get("collect")));
        work.setLikes(Integer.valueOf(requestMap.get("likes")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dstr = requestMap.get("release_time");
        java.util.Date d = sdf.parse(dstr);
        work.setRelease_time(new java.sql.Date(d.getTime()));
        System.out.println(new java.sql.Date(d.getTime()));
        Work work1 = workRepository.save(work);
        if(work1 != null){
            return BaseResult.result(ResultConstant.SUCCESS, ConstantMessage.ADD_SUCCESS_MESSAGE);
        }else{
            return BaseResult.result(ResultConstant.SAVE_FAIL, ConstantMessage.ADD_FAILED_MESSAGE);
        }
    }

    @PostMapping("/updateWork")
    public BaseResult updateWork(@RequestParam Map<String, String> requestMap) throws ParseException {
        Work work = new Work();
        work.setId(Long.valueOf(requestMap.get("id")));
        work.setWork_name(requestMap.get("work_name"));
        work.setDescription(requestMap.get("description"));
        work.setPicture(requestMap.get("picture"));
        work.setWork_label(requestMap.get("work_label"));
        work.setVideo(requestMap.get("video"));
        work.setCover(requestMap.get("cover"));
        work.setComment(requestMap.get("comment"));
        work.setClient_id(Integer.valueOf(requestMap.get("client_id")));
        work.setCategory_id(Integer.valueOf(requestMap.get("category_id")));
        work.setCollect(Integer.valueOf(requestMap.get("collect")));
        work.setLikes(Integer.valueOf(requestMap.get("likes")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dstr = requestMap.get("release_time");
        java.util.Date d = sdf.parse(dstr);
        work.setRelease_time(new java.sql.Date(d.getTime()));
        Work work1 = workRepository.save(work);
        if(work1 != null){
            return BaseResult.result(ResultConstant.SUCCESS, ConstantMessage.UPDATE_SUCCESS_MESSAGE);
        }else{
            return BaseResult.result(ResultConstant.UPDATE_FAIL, ConstantMessage.UPDATE_FAILED_MESSAGE);
        }
    }
    @PostMapping("/deleteWork")
    public BaseResult deleteWork(@RequestParam("id")Long id){
        workRepository.deleteById(id);
        return BaseResult.result(ResultConstant.SUCCESS, ConstantMessage.DELETE_SUCCESS_MESSAGE);
    }
}
