package com.d103.newreka.hottopic.dto;

import com.d103.newreka.hottopic.domain.Article;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ArticleDto {
    private Long articleId;
    private String title;
    private String company;
    private String link;
    private String thumbnail;
    private String category;
    private LocalDateTime time;
    private Long keyWordId;
    private String content;

    public static ArticleDto fromEntity(Article article) {
        return ArticleDto.builder()
                .articleId(article.getArticleId())
                .title(article.getTitle())
                .company(article.getCompany())
                .link(article.getLink())
                .category(article.getCategory())
                .thumbnail(article.getImgLink())
                .time(article.getTime())
                .content(article.getContent())
                .keyWordId(article.getKeyWord().getKeyWordId())
                .build();
    }

}
