package com.innovate.media.controller;

import com.innovate.media.constant.ConstantMessage;
import com.innovate.media.constant.ResultConstant;
import com.innovate.media.domain.ClientOccupation;
import com.innovate.media.domain.WorkCategory;
import com.innovate.media.repository.WorkCategoryRepository;
import com.innovate.media.utils.BaseResult;
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
public class WorkCategoryController {
    @Autowired
    private WorkCategoryRepository workCategoryRepository;
    @PostMapping(path = "/getWorkCategory")
    public Page<WorkCategory> getPage(@RequestParam("pageNum")int pageNum, @RequestParam("pageLimit")int pageLimit){
        Pageable pageable = PageRequest.of(pageNum - 1, pageLimit);
        return workCategoryRepository.findAll(pageable);
    }
    @PostMapping("/getSimpleWorkCategory")
    public Optional<WorkCategory> getSimpleWorkCategory(@RequestParam("id")Long id){
        return workCategoryRepository.findById(id);
    }
    @PostMapping("/addWorkCategory")
    public BaseResult addWorkCategory(@RequestParam Map<String, String> requestMap){
        WorkCategory workCategory = new WorkCategory();
        workCategory.setCategory_name(requestMap.get("category_name"));
        WorkCategory workCategory1 = workCategoryRepository.save(workCategory);
        if(workCategory1 != null){
            return BaseResult.result(ResultConstant.SUCCESS, ConstantMessage.ADD_SUCCESS_MESSAGE);
        }else{
            return BaseResult.result(ResultConstant.SAVE_FAIL, ConstantMessage.ADD_FAILED_MESSAGE);
        }
    }
    @PostMapping("/updateWorkCategory")
    public BaseResult updateWorkCategory(@RequestParam Map<String, String> requestMap){
        WorkCategory workCategory = new WorkCategory();
        workCategory.setId(Long.valueOf(requestMap.get("id")));
        workCategory.setCategory_name(requestMap.get("category_name"));
        WorkCategory workCategory1 = workCategoryRepository.save(workCategory);
        if(workCategory1 != null){
            return BaseResult.result(ResultConstant.SUCCESS, ConstantMessage.UPDATE_SUCCESS_MESSAGE);
        }else{
            return BaseResult.result(ResultConstant.UPDATE_FAIL, ConstantMessage.UPDATE_FAILED_MESSAGE);
        }
    }
    @PostMapping("/deleteWorkCategory")
    public BaseResult deleteWorkCategory(@RequestParam("id")Long id){
        workCategoryRepository.deleteById(id);
        return BaseResult.result(ResultConstant.SUCCESS, ConstantMessage.DELETE_SUCCESS_MESSAGE);
    }
}
