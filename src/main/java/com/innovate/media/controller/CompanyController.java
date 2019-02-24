package com.innovate.media.controller;

import com.innovate.media.constant.ConstantMessage;
import com.innovate.media.constant.ResultConstant;
import com.innovate.media.domain.Client;
import com.innovate.media.domain.Company;
import com.innovate.media.domain.Postion;
import com.innovate.media.domain.WorkCategory;
import com.innovate.media.repository.CompanyRepository;
import com.innovate.media.utils.BaseResult;
import com.innovate.media.utils.ChangeResultTypeUtils;
import com.innovate.media.utils.FormatBeanUtils;
import com.innovate.media.utils.PageBean;
import javafx.geometry.Pos;
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
public class CompanyController {
    @Autowired
    private CompanyRepository companyRepository;

    @PostMapping(path = "/getAllCompanyList")
    public PageBean getPage(@RequestParam("page")int pageNum, @RequestParam("rows")int pageLimit,
                            @RequestParam("order")String order){
        Pageable pageable = PageRequest.of(pageNum - 1, pageLimit, Sort.Direction.ASC, order);
        Page<Company> page = companyRepository.findAll(pageable);
        PageBean pageBean = new PageBean();
        pageBean.setTotal(page.getNumberOfElements());
        List<Map> list = ChangeResultTypeUtils.changeCompanyResultType(page.getContent());
        pageBean.setRows(list);
        return pageBean;
    }

    @PostMapping("/getSimpleCompany")
    public Map getSimpleCompany(@RequestParam("id")Long id){
        Optional<Company> optional = companyRepository.findById(id);
        Map map = null;
        try{
            map = FormatBeanUtils.formatBean(optional.get());
        }catch (Exception e){
            return null;
        }
        return map;
    }

    @PostMapping("/addCompany")
    public BaseResult addCompany(@RequestParam Map<String, String> requestMap){
         Company company = new Company();
        company.setCompanyName(requestMap.get("company_name"));
        company.setLogo(requestMap.get("logo"));
        company.setDescription(requestMap.get("description"));
        company.setDetailAddress(requestMap.get("detail_address"));
        company.setCompanyLabel(requestMap.get("company_label"));
        company.setScale(Integer.valueOf(requestMap.get("scale")));
        company.setChinaId(Integer.valueOf(requestMap.get("china_id")));
        Company company1 = companyRepository.save(company);
        if(company1 != null){
            return BaseResult.result(ResultConstant.SUCCESS, ConstantMessage.ADD_SUCCESS_MESSAGE);
        }else{
            return BaseResult.result(ResultConstant.SAVE_FAIL, ConstantMessage.ADD_FAILED_MESSAGE);
        }
    }

    @PostMapping("/updateCompany")
    public BaseResult updateCompany(@RequestParam Map<String, String> requestMap){
        Company company = new Company();
        company.setId(Long.valueOf(requestMap.get("id")));
        company.setCompanyName(requestMap.get("company_name"));
        company.setLogo(requestMap.get("logo"));
        company.setDescription(requestMap.get("description"));
        company.setDetailAddress(requestMap.get("detail_address"));
        company.setCompanyLabel(requestMap.get("company_label"));
        company.setScale(Integer.valueOf(requestMap.get("scale")));
        company.setChinaId(Integer.valueOf(requestMap.get("china_id")));
        Company company1 = companyRepository.save(company);
        if(company1 != null){
            return BaseResult.result(ResultConstant.SUCCESS, ConstantMessage.UPDATE_SUCCESS_MESSAGE);
        }else{
            return BaseResult.result(ResultConstant.UPDATE_FAIL, ConstantMessage.UPDATE_FAILED_MESSAGE);
        }
    }
    @PostMapping("/deleteCompany")
    public BaseResult deleteCompany(@RequestParam("id")Long id){
        companyRepository.deleteById(id);
        return BaseResult.result(ResultConstant.SUCCESS, ConstantMessage.DELETE_SUCCESS_MESSAGE);
    }
}
