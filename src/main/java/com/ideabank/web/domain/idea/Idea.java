package com.ideabank.web.domain.idea;

import com.ideabank.web.domain.BaseTimeEntity;
import com.ideabank.web.domain.comment.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Where(clause = "is_deleted = false")
public class Idea extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idea_id")
    private long ideaId;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "idea_id")
    private List<Comment> comments;

    @Builder
    public Idea(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.isDeleted = false;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    /** アイデアEntityを取得後に呼び出すと論理削除される。 */
    public void logicalDelete() {
        this.isDeleted = true;
    }
}
