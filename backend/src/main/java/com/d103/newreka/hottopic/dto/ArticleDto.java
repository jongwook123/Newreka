package com.d103.newreka.hottopic.dto;

import java.time.LocalDateTime;

import com.d103.newreka.hottopic.domain.Article;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@Data
@ToString
public class ArticleDto {
	Long articleId;
	String title;
	String company;
	String link;
	String thumbnail;
	LocalDateTime time;
	Long keyWordId;
	String content;

	public static ArticleDto fromEntity(Article article) {
		return ArticleDto.builder()
			.articleId(article.getArticleId())
			.title(article.getTitle())
			.company(article.getCompany())
			.link(article.getLink())
			.thumbnail(article.getImgLink())
			.time(article.getTime())
			.content(article.getContent())
			.keyWordId(article.getKeyWord().getKeyWordId())
			.build();
	}

}
