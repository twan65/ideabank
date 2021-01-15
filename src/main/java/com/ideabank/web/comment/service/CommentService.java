package com.ideabank.web.comment.service;

import com.ideabank.web.comment.dto.CommentSaveRequestDto;
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
     * @param requestDto コメント登録情報
     * @return コメントID
     */
    @Transactional
    public Long save(CommentSaveRequestDto requestDto) {
        return commentRepository.save(requestDto.toEntity()).getCommentId();
    }
}
