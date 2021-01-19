package com.ideabank.web.comment.controller;

import com.ideabank.web.comment.dto.CommentSaveRequestDto;
import com.ideabank.web.comment.dto.CommentUpdateRequestDto;
import com.ideabank.web.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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


  //  @PutMapping("/api/v1/comment/{id}")
  //  public Long update(
  //      @PathVariable("id") Long commentId, @RequestBody CommentUpdateRequestDto requestDto) {
  //    return commentService.update(commentId, requestDto);
  //  }

  /**
   * コメント削除を行う。
   * @param commentId コメントID
   * @return コメントID
   */
  @DeleteMapping("/api/v1/comment/{id}")
  public Long delete(@PathVariable("id") Long commentId) {
    return commentService.delete(commentId);
  }
}
