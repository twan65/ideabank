package com.ideabank.web.idea.service;

import com.ideabank.web.domain.idea.IdeaRepository;
import com.ideabank.web.idea.dto.IdeaListResponseDto;
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
    public List<IdeaListResponseDto> findAll() {
        return ideaRepository.findAll().stream()
                .map(IdeaListResponseDto::new)
                .collect(Collectors.toList());
    }
}
