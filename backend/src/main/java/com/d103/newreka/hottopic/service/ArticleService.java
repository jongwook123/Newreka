package com.d103.newreka.hottopic.service;

import com.d103.newreka.hottopic.domain.Article;
import com.d103.newreka.hottopic.domain.KeyWord;
import com.d103.newreka.hottopic.domain.Time;
import com.d103.newreka.hottopic.dto.ArticleDto;
import com.d103.newreka.hottopic.repo.ArticleRepo;
import com.d103.newreka.hottopic.repo.KeyWordRepo;
import com.d103.newreka.hottopic.repo.TimeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepo articleRepo;
    private final KeyWordRepo keyWordRepo;
    @Transactional
    public void saveArticle(ArticleDto articleDto){
        KeyWord keyWord = keyWordRepo.getReferenceById(articleDto.getKeyWordId());
        Article article = Article.builder()
                .articleId(articleDto.getArticleId())
                .title(articleDto.getTitle())
                .company(articleDto.getCompany())
                .link(articleDto.getLink())
                .time(articleDto.getTime())
                .keyWord(keyWord)
                .build();

        articleRepo.save(article);
    }


}
