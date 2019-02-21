package com.innovate.media.repository;

import com.innovate.media.domain.CommentReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentReplyRepository extends JpaRepository<CommentReply, Long> {
    
}
