package com.innovate.media.controller;

import com.innovate.media.domain.AdminLog;
import com.innovate.media.repository.AdminLogRepository;
import com.innovate.media.utils.ChangeResultTypeUtils;
import com.innovate.media.utils.PageBean;
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

@RestController
public class AdminLogController {
    @Autowired
    private AdminLogRepository adminLogRepository;
    @PostMapping("/getAllAdminLogList")
    public PageBean getPage(@RequestParam("page")int pageNum, @RequestParam("rows")int pageLimit,
                            @RequestParam("order")String order){
        Pageable pageable = PageRequest.of(pageNum - 1, pageLimit, Sort.Direction.ASC, order);
        Page<AdminLog> page =  adminLogRepository.findAll(pageable);
        PageBean pageBean = new PageBean();
        pageBean.setTotal(page.getNumberOfElements());
        List<Map> list = ChangeResultTypeUtils.changeAdminLogResultType(page.getContent());
        pageBean.setRows(list);
        return pageBean;
    }
}
