package com.d103.newreka.hottopic.repo;

import com.d103.newreka.hottopic.domain.Article;
import com.d103.newreka.hottopic.dto.ArticleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Long> {

    List<Article> findAllByKeyWord_keyWordId(Long id);

}
