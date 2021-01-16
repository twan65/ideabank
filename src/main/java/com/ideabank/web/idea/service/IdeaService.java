package com.ideabank.web.idea.service;

import com.ideabank.web.domain.comment.Comment;
import com.ideabank.web.domain.comment.CommentRepository;
import com.ideabank.web.domain.idea.Idea;
import com.ideabank.web.domain.idea.IdeaRepository;
import com.ideabank.web.idea.dto.IdeaResponseDto;
import com.ideabank.web.idea.dto.IdeaSaveRequestDto;
import com.ideabank.web.idea.dto.IdeaUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class IdeaService {

  private final IdeaRepository ideaRepository;

  /**
   * 全アイデアを取得する。
   *
   * @return 全てのアイデア
   */
  @Transactional(readOnly = true)
  public List<IdeaResponseDto> findAll() {
    return ideaRepository.findAll().stream().map(IdeaResponseDto::new).collect(Collectors.toList());
  }

  /**
   * アイデアを1件取得する。
   *
   * @param id 記事ID
   * @return アイデア詳細
   */
  @Transactional(readOnly = true)
  public IdeaResponseDto findById(Long id) {
    Idea idea =
        ideaRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("該当アイデアがありません。Id=" + id));

    return new IdeaResponseDto(idea, idea.getComments());
  }

  /**
   * アイデアを登録する。
   *
   * @param requestDto アイデア情報
   * @return アイデアID
   */
  @Transactional
  public Long save(IdeaSaveRequestDto requestDto) {
    return ideaRepository.save(requestDto.toEntity()).getIdeaId();
  }

  /**
   * アイデアを更新する。
   *
   * @param id アイデアID
   * @param requestDto アイデア詳細情報
   * @return アイデアID
   */
  @Transactional
  public Long update(Long id, IdeaUpdateRequestDto requestDto) {

    Idea idea =
        ideaRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("該当のアイデアがありません。Id=" + id));

    // 特にSQLを投げなくてもJPAの永続性コンテキストのため、Updateされる。（Dirty checking）
    idea.update(requestDto.getTitle(), requestDto.getContent());

    // 関係テーブルであるコメントテーブルも削除を行う。
    idea.getComments().forEach(Comment::logicalDelete);

    return id;
  }

  /**
   * アイデアidに該当するアイデアの論理削除を行う。
   *
   * @param id アイデアID
   * @return アイデアID
   */
  @Transactional
  public Long delete(Long id) {
    Idea idea =
        ideaRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("該当のアイデアがありません。Id=" + id));

    idea.logicalDelete();

    return id;
  }
}
