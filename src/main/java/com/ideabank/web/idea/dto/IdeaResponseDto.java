package com.ideabank.web.idea.dto;

import com.ideabank.web.comment.dto.CommentDto;
import com.ideabank.web.domain.comment.Comment;
import com.ideabank.web.domain.idea.Idea;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class IdeaResponseDto {

    private long ideaId;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdDate;
    private List<CommentDto> comments;

    public IdeaResponseDto(Idea idea) {
        this.ideaId = idea.getIdeaId();
        this.title = idea.getTitle();
        this.author = idea.getAuthor();
        this.content = idea.getContent();
        this.createdDate = idea.getCreatedDate();
    }

    public IdeaResponseDto(Idea idea, List<Comment> comments) {
        this(idea);
        this.comments = comments.stream()
                .map(CommentDto::new)
                .collect(Collectors.toList());
    }

}
