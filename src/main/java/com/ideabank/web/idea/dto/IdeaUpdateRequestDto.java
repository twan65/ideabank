package com.ideabank.web.idea.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class IdeaUpdateRequestDto {

    private String title;
    private String content;

    @Builder
    public IdeaUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
