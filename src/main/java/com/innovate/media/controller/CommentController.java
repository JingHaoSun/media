package com.innovate.media.controller;

import com.innovate.media.constant.ConstantMessage;
import com.innovate.media.constant.ResultConstant;
import com.innovate.media.domain.Comment;
import com.innovate.media.domain.WorkCategory;
import com.innovate.media.repository.CommentRepository;
import com.innovate.media.utils.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

@RestController
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;
    @PostMapping(path = "/getCommentList")
    public Page<Comment> getPage(@RequestParam("pageNum")int pageNum, @RequestParam("pageLimit")int pageLimit){
        Pageable pageable = PageRequest.of(pageNum - 1, pageLimit);
        return commentRepository.findAll(pageable);
    }
    @PostMapping("/addComment")
    public BaseResult addComment(@RequestParam Map<String, String> requestMap) throws ParseException {
        Comment comment = new Comment();
        comment.setWork_id(Integer.valueOf(requestMap.get("work_id")));
        comment.setFrom_uid(Integer.valueOf(requestMap.get("from_uid")));
        comment.setContent(requestMap.get("content"));
        String str = requestMap.get("comment_time");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = sdf.parse(str);
        System.out.println(new java.sql.Timestamp(date.getTime()));
        comment.setComment_time(new java.sql.Timestamp(date.getTime()));
        Comment comment1 = commentRepository.save(comment);
        if(comment1 != null){
            return BaseResult.result(ResultConstant.SUCCESS, ConstantMessage.ADD_SUCCESS_MESSAGE);
        }else{
            return BaseResult.result(ResultConstant.SAVE_FAIL, ConstantMessage.ADD_FAILED_MESSAGE);
        }
    }
    @PostMapping("/deleteComment")
    public BaseResult deleteComment(@RequestParam("id")Long id){
        commentRepository.deleteById(id);
        return BaseResult.result(ResultConstant.SUCCESS, ConstantMessage.DELETE_SUCCESS_MESSAGE);
    }
}
