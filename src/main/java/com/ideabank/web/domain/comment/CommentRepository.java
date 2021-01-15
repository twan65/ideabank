package com.ideabank.web.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Commentの基本CRUD生成
 * Comment関連のSQLはこのインタフェースに定義する。
 * CommentのEntityクラスの同じパッケージ内で管理する必要がある。
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
