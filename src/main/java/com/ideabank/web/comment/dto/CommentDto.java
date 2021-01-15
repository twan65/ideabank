package com.ideabank.web.comment.dto;

import com.ideabank.web.domain.comment.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentDto {

    private long commentId;
    private long ideaId;
    private String comment;
    private String author;
    private LocalDateTime modifiedDate;

    public CommentDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.ideaId = comment.getIdeaId();
        this.comment = comment.getComment();
        this.author = comment.getAuthor();
        this.modifiedDate = comment.getModifiedDate();
    }
}
