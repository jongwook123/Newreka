package com.d103.newreka.hottopic.service;

import com.d103.newreka.hottopic.domain.Article;
import com.d103.newreka.hottopic.domain.KeyWord;
import com.d103.newreka.hottopic.dto.ArticleDto;
import com.d103.newreka.hottopic.repo.ArticleRepo;
import com.d103.newreka.hottopic.repo.KeyWordRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepo articleRepo;
    private final KeyWordRepo keyWordRepo;
    private final NewsService newsService;

    @Transactional
    public void saveArticle(ArticleDto articleDto) {
        KeyWord keyWord = keyWordRepo.getReferenceById(articleDto.getKeyWordId());
        Article article = Article.builder()
                .articleId(articleDto.getArticleId())
                .title(articleDto.getTitle())
                .company(articleDto.getCompany())
                .link(articleDto.getLink())
                .imgLink(articleDto.getThumbnail())
                .time(articleDto.getTime())
                .category((articleDto.getCategory()))
                .content(articleDto.getContent())
                .keyWord(keyWord)
                .build();

        articleRepo.save(article);
    }

    public List<Article> getArticleList(Long keywordId) throws IOException, ParseException {
        List<Article> result = articleRepo.findTop5ByKeyWord_keyWordIdOrderByArticleIdDesc(keywordId);

        // URL 기준으로 중복 제거
        List<Article> distinctArticles = result.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Article::getLink))),
                        ArrayList::new
                ));

        Collections.reverse(distinctArticles);

        return distinctArticles;
    }

}
