package com.ideabank.web.comment.dto;

import com.ideabank.web.domain.comment.Comment;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentSaveRequestDto {

  private long ideaId;
  private String comment;
  private String author;


  @Builder
  public CommentSaveRequestDto(String comment, String author, long ideaId) {
    this.ideaId = ideaId;
    this.comment = comment;
    this.author = author;
  }

  public Comment toEntity() {
    return Comment.builder()
            .ideaId(this.ideaId)
            .comment(this.comment)
            .author(this.author)
            .build();
  }
}
