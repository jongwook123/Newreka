package com.d103.newreka.user.dto;

import com.d103.newreka.hottopic.domain.Article;
import com.d103.newreka.hottopic.domain.KeyWord;
import com.d103.newreka.hottopic.dto.ArticleDto;
import com.d103.newreka.hottopic.dto.KeyWordDto;
import com.d103.newreka.hottopic.repo.KeyWordRepo;
import com.d103.newreka.user.domain.Scrap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScrapLoadDto {

    Long scrapId;
    KeyWordDto keyWord;
    ArticleDto article;

    public static ScrapLoadDto fromEntity(Scrap scrap) {
//        KeyWordDto keyWordDto = null;
        return ScrapLoadDto.builder()
                .scrapId(scrap.getScrapId())
                .keyWord(KeyWordDto.fromEntity(scrap.getKeyWordId()))
                .article(ArticleDto.fromEntity(scrap.getArticleId()))
                .build();
    }
}
