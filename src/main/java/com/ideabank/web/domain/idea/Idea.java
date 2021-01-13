package com.ideabank.web.domain.idea;

import com.ideabank.web.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Where(clause = "is_deleted = false")
public class Idea extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Builder
    public Idea(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
