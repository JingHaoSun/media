package com.innovate.media.controller;

import com.innovate.media.constant.ConstantMessage;
import com.innovate.media.constant.ResultConstant;
import com.innovate.media.domain.Admin;
import com.innovate.media.domain.Client;
import com.innovate.media.repository.ClientRepository;
import com.innovate.media.service.ClientService;
import com.innovate.media.utils.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ClientController {

    @Autowired
    ClientRepository clientRepository;
    @PostMapping(path = "/getAllClientList")
    public Page<Client> getPage(@RequestParam("pageNum")int pageNum, @RequestParam("pageLimit")int pageLimit){
        Pageable pageable = PageRequest.of(pageNum - 1, pageLimit);
        return clientRepository.findAll(pageable);
    }

    @PostMapping("/addClient")
    public BaseResult addAdmin(@RequestParam Map<String, String> requestMap){
        Client client = new Client();
        client.setClient_name(requestMap.get("client_name"));
        client.setPassword(requestMap.get("password"));
        client.setMailBox(requestMap.get("mail_box"));
        client.setReal_name(requestMap.get("real_name"));
        client.setMobile(requestMap.get("mobile"));
        client.setCity_id(Integer.valueOf(requestMap.get("city_id")));
        client.setOccupation_id(Integer.valueOf(requestMap.get("occupation_id")));
        client.setCompany_id(Integer.valueOf(requestMap.get("company_id")));
        Client clientl = clientRepository.save(client);
        if(clientl != null){
            return BaseResult.result(ResultConstant.SUCCESS, ConstantMessage.ADD_SUCCESS_MESSAGE);
        }else{
            return BaseResult.result(ResultConstant.SAVE_FAIL, ConstantMessage.ADD_FAILED_MESSAGE);
        }
    }

    @PostMapping("/deleteClient")
    public BaseResult deleteClient(@RequestParam("id")Long id){
        clientRepository.deleteById(id);
        return BaseResult.result(ResultConstant.SUCCESS, ConstantMessage.DELETE_SUCCESS_MESSAGE);
    }

    @PostMapping("/updateClient")
    public BaseResult updateClient(@RequestParam Map<String, String> requestMap){
        Client client = new Client();
        client.setId(Long.valueOf(requestMap.get("id")));
        client.setClient_name(requestMap.get("client_name"));
        client.setPassword(requestMap.get("password"));
        client.setMailBox(requestMap.get("mail_box"));
        client.setReal_name(requestMap.get("real_name"));
        client.setMobile(requestMap.get("mobile"));
        client.setCity_id(Integer.valueOf(requestMap.get("city_id")));
        client.setOccupation_id(Integer.valueOf(requestMap.get("occupation_id")));
        client.setCompany_id(Integer.valueOf(requestMap.get("company_id")));
        Client client1 = clientRepository.save(client);
        if(client1 != null){
            return BaseResult.result(ResultConstant.SUCCESS, ConstantMessage.UPDATE_SUCCESS_MESSAGE);
        }else{
            return BaseResult.result(ResultConstant.UPDATE_FAIL, ConstantMessage.UPDATE_FAILED_MESSAGE);
        }
    }

    @PostMapping("/findClient")
    public Optional<Client> findAdmin(@RequestParam("id")Long id){
        return clientRepository.findById(id);
    }


}

