package com.d103.newreka.hottopic.dto;

import com.d103.newreka.hottopic.domain.Article;
import com.d103.newreka.hottopic.domain.KeyWord;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Builder
@Getter
@Setter
@Data
public class ArticleDto {
    Long articleId;
    String title;
    String company;
    String link;
    String thumbnail;
    LocalDateTime time;
    Long keyWordId;


    public static ArticleDto fromEntity(Article article){
        return ArticleDto.builder()
                .articleId(article.getArticleId())
                .title(article.getTitle())
                .company(article.getCompany())
                .link(article.getLink())
                .thumbnail(article.getThumbnail())
                .time(article.getTime())
                .keyWordId(article.getKeyWord().getKeyWordId())
                .build();
    }

}
