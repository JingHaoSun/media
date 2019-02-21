package com.innovate.media.controller;

import com.innovate.media.constant.ConstantMessage;
import com.innovate.media.constant.ResultConstant;
import com.innovate.media.domain.Client;
import com.innovate.media.domain.ClientOccupation;
import com.innovate.media.domain.Company;
import com.innovate.media.repository.ClientOccupationRepository;
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
public class ClientOccupationController {
    @Autowired
    private ClientOccupationRepository clientOccupationRepository;

    @PostMapping(path = "/getClientOccupation")
    public Page<ClientOccupation> getPage(@RequestParam("pageNum")int pageNum, @RequestParam("pageLimit")int pageLimit){
        Pageable pageable = PageRequest.of(pageNum - 1, pageLimit);
        return clientOccupationRepository.findAll(pageable);
    }
    @PostMapping("/getSimpleClientOccupation")
    public Optional<ClientOccupation> getSimpleClientOccupation(@RequestParam("id")Long id){
        return clientOccupationRepository.findById(id);
    }
    @PostMapping("/addClientOccupation")
    public BaseResult addClientOccupation(@RequestParam Map<String, String> requestMap){
        ClientOccupation clientOccupation = new ClientOccupation();
        clientOccupation.setOccupation_name(requestMap.get("occupation_name"));
        ClientOccupation clientOccupation1 = clientOccupationRepository.save(clientOccupation);
        if(clientOccupation1 != null){
            return BaseResult.result(ResultConstant.SUCCESS, ConstantMessage.ADD_SUCCESS_MESSAGE);
        }else{
            return BaseResult.result(ResultConstant.SAVE_FAIL, ConstantMessage.ADD_FAILED_MESSAGE);
        }
    }
    @PostMapping("/updateClientOccupation")
    public BaseResult updateClientOccupation(@RequestParam Map<String, String> requestMap){
        ClientOccupation clientOccupation = new ClientOccupation();
        clientOccupation.setId(Long.valueOf(requestMap.get("id")));
        clientOccupation.setOccupation_name(requestMap.get("occupation_name"));
        ClientOccupation clientOccupation1 = clientOccupationRepository.save(clientOccupation);
        if(clientOccupation1 != null){
            return BaseResult.result(ResultConstant.SUCCESS, ConstantMessage.UPDATE_SUCCESS_MESSAGE);
        }else{
            return BaseResult.result(ResultConstant.UPDATE_FAIL, ConstantMessage.UPDATE_FAILED_MESSAGE);
        }
    }
    @PostMapping("/deleteClientOccupation")
    public BaseResult deleteClientOccupation(@RequestParam("id")Long id){
        clientOccupationRepository.deleteById(id);
        return BaseResult.result(ResultConstant.SUCCESS, ConstantMessage.DELETE_SUCCESS_MESSAGE);
    }
}
