package com.ideabank.web.idea.dto;

import com.ideabank.web.domain.idea.Idea;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class IdeaResponseDto {

    private long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdDate;
//    private boolean isDeleted;

    public IdeaResponseDto(Idea idea) {
        this.id = idea.getId();
        this.title = idea.getTitle();
        this.author = idea.getAuthor();
        this.content = idea.getContent();
        this.createdDate = idea.getCreatedDate();
    }
}
