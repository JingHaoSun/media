package com.innovate.media.controller;

import com.innovate.media.constant.ConstantMessage;
import com.innovate.media.constant.ResultConstant;
import com.innovate.media.domain.*;
import com.innovate.media.repository.ChinaRepository;
import com.innovate.media.utils.BaseResult;
import com.innovate.media.utils.ChangeResultTypeUtils;
import com.innovate.media.utils.FormatBeanUtils;
import com.innovate.media.utils.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ChinaController {
    @Autowired
    private ChinaRepository chinaRepository;
    @PostMapping(path = "/getChinaList")
    public PageBean getPage(@RequestParam("page")int pageNum, @RequestParam("rows")int pageLimit,
                            @RequestParam("order")String order){
        Pageable pageable = PageRequest.of(pageNum - 1, pageLimit, Sort.Direction.ASC, order);
        Page<China> page = chinaRepository.findAll(pageable);
        PageBean pageBean = new PageBean();
        pageBean.setTotal(page.getNumberOfElements());
        List<Map> list = ChangeResultTypeUtils.changeChinaResultType(page.getContent());
        pageBean.setRows(list);
        return pageBean;
    }
    @PostMapping(value="/addOrUpdateChina")
    public BaseResult addChina(@RequestParam Map<String, String> requestMap){
        China china = new China();
        china.setName(requestMap.get("name"));
        china.setPid(Integer.valueOf(requestMap.get("pid")));
        china.setId(Long.valueOf(requestMap.get("id")));
        China china1 = chinaRepository.save(china);
        if(china1 != null){
            return BaseResult.result(ResultConstant.SUCCESS, ConstantMessage.ADD_SUCCESS_MESSAGE);
        }else{
            return BaseResult.result(ResultConstant.SAVE_FAIL, ConstantMessage.ADD_FAILED_MESSAGE);
        }
    }
    @PostMapping("/deleteChina")
    public BaseResult deleteChina(@RequestParam("id")Long id){
        chinaRepository.deleteById(id);
        return BaseResult.result(ResultConstant.SUCCESS, ConstantMessage.DELETE_SUCCESS_MESSAGE);
    }

    @PostMapping("/getSimpleChina")
    public Map getSimpleChina(@RequestParam("id")Long id){
        Optional<China> optional = chinaRepository.findById(id);
        Map map = null;
        try{
            map = FormatBeanUtils.formatBean(optional.get());
        }catch (Exception e){
            return null;
        }
        return map;
    }
}
