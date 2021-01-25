package com.ideabank.web.comment.service;

import com.ideabank.web.comment.dto.CommentSaveRequestDto;
import com.ideabank.web.comment.dto.CommentUpdateRequestDto;
import com.ideabank.web.domain.comment.Comment;
import com.ideabank.web.domain.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

  private final CommentRepository commentRepository;

  /**
   * コメント登録を行う。
   *
   * @param requestDto コメント登録情報
   * @return コメントID
   */
  @Transactional
  public Long save(CommentSaveRequestDto requestDto) {
    return commentRepository.save(requestDto.toEntity()).getCommentId();
  }

  /**
   * コメント更新を行う。
   * @param commentId コメントID
   * @param requestDto コメント
   * @return コメントID
   */
  @Transactional
  public Long update(Long commentId, CommentUpdateRequestDto requestDto) {
    Comment comment =
        commentRepository
            .findById(commentId)
            .orElseThrow(() -> new IllegalArgumentException("該当のコメントがありません。コメントID=" + commentId));

    comment.update(requestDto.getComment());

    return commentId;
  }

  /**
   * コメント削除を行う。
   *
   * @param commentId コメントID
   * @return コメントID
   */
  @Transactional
  public Long delete(Long commentId) {
    Comment comment =
        commentRepository
            .findById(commentId)
            .orElseThrow(() -> new IllegalArgumentException("該当のコメントがありません。コメントID=" + commentId));

    commentRepository.delete(comment);

    return commentId;
  }
}
