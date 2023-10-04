package com.d103.newreka.hottopic.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.d103.newreka.hottopic.domain.Article;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Long> {
	List<Article> findAllByKeyWord_keyWordId(Long id);
}
