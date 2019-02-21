package com.innovate.media.controller;

import com.innovate.media.constant.ConstantMessage;
import com.innovate.media.constant.ResultConstant;
import com.innovate.media.domain.Comment;
import com.innovate.media.domain.CommentReply;
import com.innovate.media.repository.CommentReplyRepository;
import com.innovate.media.utils.BaseResult;
import com.sun.org.apache.xpath.internal.operations.Bool;
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
public class CommentReplyController {
    @Autowired
    private CommentReplyRepository commentReplyRepository;
    @PostMapping(path = "/getCommentReplyList")
    public Page<CommentReply> getPage(@RequestParam("pageNum")int pageNum, @RequestParam("pageLimit")int pageLimit){
        Pageable pageable = PageRequest.of(pageNum - 1, pageLimit);
        return commentReplyRepository.findAll(pageable);
    }
    @PostMapping("/addCommentReply")
    public BaseResult addCommentReply(@RequestParam Map<String, String> requestMap) throws ParseException {
        CommentReply commentReply = new CommentReply();
        commentReply.setComment_id(Integer.valueOf(requestMap.get("comment_id")));
        commentReply.setReply_id(Integer.valueOf(requestMap.get("reply_id")));
        commentReply.setTo_uid(Integer.valueOf(requestMap.get("to_uid")));
        commentReply.setFrom_uid(Integer.valueOf(requestMap.get("from_uid")));
        commentReply.setContent(requestMap.get("content"));
        commentReply.setReply_type(Boolean.valueOf(requestMap.get("reply_type")));
        String str = requestMap.get("comment_time");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = sdf.parse(str);
        commentReply.setComment_time(new java.sql.Timestamp(date.getTime()));
        CommentReply commentReply1 = commentReplyRepository.save(commentReply);
        if(commentReply1 != null){
            return BaseResult.result(ResultConstant.SUCCESS, ConstantMessage.ADD_SUCCESS_MESSAGE);
        }else{
            return BaseResult.result(ResultConstant.SAVE_FAIL, ConstantMessage.ADD_FAILED_MESSAGE);
        }
    }
    @PostMapping("/deleteCommentReply")
    public BaseResult deleteComment(@RequestParam("id")Long id){
        commentReplyRepository.deleteById(id);
        return BaseResult.result(ResultConstant.SUCCESS, ConstantMessage.DELETE_SUCCESS_MESSAGE);
    }
}
