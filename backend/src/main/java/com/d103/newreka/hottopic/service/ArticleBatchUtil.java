package com.d103.newreka.hottopic.service;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.d103.newreka.hottopic.domain.KeyWord;
import com.d103.newreka.hottopic.dto.ArticleDto;
import com.d103.newreka.hottopic.repo.KeyWordRepo;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ArticleBatchUtil {

	private final NewsService newsService;
	private final ArticleService articleService;
	private final KeyWordRepo keyWordRepo;

	private static final Logger logger = LoggerFactory.getLogger(ArticleBatchUtil.class);

	@Scheduled(cron = "0 2/10 * * * ?")
	public void insertData() {
		try {
			List<KeyWord> keywordList = keyWordRepo.findTop10ByOrderByKeyWordIdDesc();

			// 순서가 반대로 불러와져서 있어서 뒤집기
			Collections.reverse(keywordList);

			for (KeyWord k : keywordList) {
				String keyword = k.getName();
				List<ArticleDto> articleDtos = newsService.searchWithClusters(keyword);

				// 헤드라인 뉴스 선정
				ArticleDto headLineArticle = articleDtos.get(0);

				// 여기 !!!!!!!

				// 키워드에 카테고리 저장
				k.setCategory(headLineArticle.getCategory());
				keyWordRepo.save(k);

				// 연관 뉴스 저장
				for (ArticleDto articleDto : articleDtos) {
					articleDto.setKeyWordId(k.getKeyWordId());
					System.out.println(articleDto);
					articleService.saveArticle(articleDto);
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

}
