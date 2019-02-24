package com.innovate.media.controller;

import com.innovate.media.constant.ConstantMessage;
import com.innovate.media.constant.ResultConstant;
import com.innovate.media.domain.Admin;
import com.innovate.media.domain.China;
import com.innovate.media.domain.Client;
import com.innovate.media.repository.ClientRepository;
import com.innovate.media.service.ClientService;
import com.innovate.media.utils.BaseResult;
import com.innovate.media.utils.ChangeResultTypeUtils;
import com.innovate.media.utils.FormatBeanUtils;
import com.innovate.media.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public PageBean getPage(@RequestParam("page")int pageNum, @RequestParam("rows")int pageLimit,
                            @RequestParam("order")String order){
        Pageable pageable = PageRequest.of(pageNum - 1, pageLimit, Sort.Direction.ASC, order);
        Page<Client> page = clientRepository.findAll(pageable);
        PageBean pageBean = new PageBean();
        pageBean.setTotal(page.getNumberOfElements());
        List<Map> list = ChangeResultTypeUtils.changeClientResultType(page.getContent());
        pageBean.setRows(list);
        return pageBean;
    }

    @PostMapping("/addClient")
    public BaseResult addAdmin(@RequestParam Map<String, String> requestMap){
        Client client = new Client();
        client.setClientName(requestMap.get("client_name"));
        client.setPassword(requestMap.get("password"));
        client.setMailBox(requestMap.get("mail_box"));
        client.setRealName(requestMap.get("real_name"));
        client.setMobile(requestMap.get("mobile"));
        client.setCityId(Integer.valueOf(requestMap.get("city_id")));
        client.setOccupationId(Integer.valueOf(requestMap.get("occupation_id")));
        client.setCompanyId(Integer.valueOf(requestMap.get("company_id")));
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
        client.setClientName(requestMap.get("client_name"));
        client.setPassword(requestMap.get("password"));
        client.setMailBox(requestMap.get("mail_box"));
        client.setRealName(requestMap.get("real_name"));
        client.setMobile(requestMap.get("mobile"));
        client.setCityId(Integer.valueOf(requestMap.get("city_id")));
        client.setOccupationId(Integer.valueOf(requestMap.get("occupation_id")));
        client.setCompanyId(Integer.valueOf(requestMap.get("company_id")));
        Client client1 = clientRepository.save(client);
            if(client1 != null){
            return BaseResult.result(ResultConstant.SUCCESS, ConstantMessage.UPDATE_SUCCESS_MESSAGE);
        }else{
            return BaseResult.result(ResultConstant.UPDATE_FAIL, ConstantMessage.UPDATE_FAILED_MESSAGE);
        }
    }

    @PostMapping("/getSimpleClient")
    public Map getSimpleClient(@RequestParam("id")Long id){
        Optional<Client> optional = clientRepository.findById(id);
        Map map = null;
        try{
            map = FormatBeanUtils.formatBean(optional.get());
        }catch (Exception e){
            return null;
        }
        return map;
    }
}

