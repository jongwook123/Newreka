package com.d103.newreka.hottopic.repo;

import com.d103.newreka.hottopic.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Long> {

}
