package com.example.springDev.repository;

import com.example.springDev.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Long> {

    @Query(value = "SELECT * FROM article WHERE id = :articleId", nativeQuery = true)
    Article findByArticleId(@Param("articleId") Long articleId);


}
