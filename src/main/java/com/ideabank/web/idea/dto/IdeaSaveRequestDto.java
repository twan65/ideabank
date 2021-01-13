package com.ideabank.web.idea.dto;

import com.ideabank.web.domain.idea.Idea;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class IdeaSaveRequestDto {

    private String title;
    private String content;

    @Builder
    public IdeaSaveRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Idea toEntity() {
        return Idea.builder()
                .title(title)
                .content(content)
                .author("") // TODO: ログイン情報から作成者を追加
                .build();
    }
}
