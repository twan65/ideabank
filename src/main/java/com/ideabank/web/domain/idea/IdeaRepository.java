package com.ideabank.web.domain.idea;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Ideaの基本CRUD生成
 * Idea関連のSQLはこのインタフェースに定義する。
 * IdeaのEntityクラスの同じパッケージ内で管理する必要がある。
 */
public interface IdeaRepository extends JpaRepository<Idea, Long> {
}
