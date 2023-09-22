package com.d103.newreka.hottopic.repo;

import com.d103.newreka.hottopic.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepo extends JpaRepository<Article, Long> {

}
