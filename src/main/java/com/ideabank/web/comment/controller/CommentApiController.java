package com.ideabank.web.comment.controller;

import com.ideabank.web.comment.dto.CommentSaveRequestDto;
import com.ideabank.web.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommentApiController {

  private final CommentService commentService;

  /**
   * コメント登録を行う。
   *
   * @param requestDto コメント詳細
   * @return コメントID
   */
  @PostMapping("/api/v1/comment")
  public Long save(@RequestBody CommentSaveRequestDto requestDto) {
    return commentService.save(requestDto);
  }
}
