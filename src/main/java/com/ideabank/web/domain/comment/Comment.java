package com.ideabank.web.domain.comment;

import com.ideabank.web.domain.BaseTimeEntity;
import com.ideabank.web.domain.idea.Idea;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Where(clause = "is_deleted = false")
public class Comment extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "comment_id")
  private long commentId;

  // TODO: FK設定が必要なのかを検討
  @Column(name = "idea_id")
  private long ideaId;

  @Column(columnDefinition = "TEXT", nullable = false)
  private String comment;

  private String author;

  @Column(name = "is_deleted", nullable = false)
  private boolean isDeleted;

  @Builder
  public Comment(String comment, String author, long ideaId) {
    this.ideaId = ideaId;
    this.comment = comment;
    this.author = author;
  }

  public void update(String comment) {
    this.comment = comment;
  }

  public void logicalDelete() {
    this.isDeleted = true;
  }
}
