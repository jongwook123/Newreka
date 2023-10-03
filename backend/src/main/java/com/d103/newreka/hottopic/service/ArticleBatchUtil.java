package com.d103.newreka.hottopic.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
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
	private static String client_id = "hevtdy8yus";
	private static String client_secret = "11vHPak7llNgpKSm3wx5S7U8Vlsw1zUnZPyWptvh";
	private static String url = "https://naveropenapi.apigw.ntruss.com/text-summary/v1/summarize";
	@Scheduled(cron = "0 0/10 * * * ?")
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

				//요약
				CloseableHttpClient httpClient = HttpClients.createDefault();

				HttpPost httpPost = new HttpPost(url);
				httpPost.setHeader("Accept", "application/json;UTF-8");
				httpPost.setHeader("Content-Type", "application/json;UTF-8");
				httpPost.setHeader("X-NCP-APIGW-API-KEY-ID", client_id);
				httpPost.setHeader("X-NCP-APIGW-API-KEY", client_secret);

				JSONObject documentObj = new JSONObject();
				documentObj.put("content", headLineArticle.getContent());

				JSONObject optionObj = new JSONObject();
				optionObj.put("language", "ko");
				optionObj.put("model", "news");
				optionObj.put("tone", 2);
				optionObj.put("summaryCount", 2);

				JSONObject mainObj = new JSONObject();

				mainObj.put("document", documentObj);
				mainObj.put("option", optionObj);

				StringEntity entity = new StringEntity(mainObj.toString(), ContentType.APPLICATION_JSON);
				httpPost.setEntity(entity);

				CloseableHttpResponse response = httpClient.execute(httpPost);
				//200이면 에러
				//int rescode = response.statusCode();

				HttpEntity et = response.getEntity();
				String content = EntityUtils.toString(et, "UTF-8");

				k.setSummary(content);
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
