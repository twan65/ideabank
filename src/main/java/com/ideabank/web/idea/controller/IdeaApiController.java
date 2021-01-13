package com.ideabank.web.idea.controller;

import com.ideabank.web.idea.dto.IdeaSaveRequestDto;
import com.ideabank.web.idea.service.IdeaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/api/v1")
public class IdeaApiController {

    private final IdeaService ideaService;

    /**
     * アイデアを登録する。
     *
     * @param requestDto アイデア情報
     * @return アイデアID
     */
    @PostMapping("/idea")
    public Long save(@RequestBody IdeaSaveRequestDto requestDto) {
        return ideaService.save(requestDto);
    }
}
