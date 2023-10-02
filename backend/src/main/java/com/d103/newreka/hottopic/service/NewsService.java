package com.d103.newreka.hottopic.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.d103.newreka.hottopic.dto.ArticleDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class NewsService {

	private final RestHighLevelClient client;

	private static final Logger logger = LoggerFactory.getLogger(NewsService.class);

	public NewsService(
		RestHighLevelClient client) {
		this.client = client;
	}

	public List<ArticleDto> searchWithClusters(String keyword) throws IOException, ParseException {
		Request request = getRequest(keyword);

		Response response = client.getLowLevelClient().performRequest(request);

		int statusCode = response.getStatusLine().getStatusCode();
		String jsonResponse = EntityUtils.toString(response.getEntity());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode rootNode = objectMapper.readTree(jsonResponse);

		JsonNode hitsNode = rootNode.path("hits").path("hits");

		List<ArticleDto> articleDtos = new ArrayList<>();
		Set<String> existingUrls = new HashSet<>();

		for (JsonNode hit : hitsNode) {
			JsonNode source = hit.path("_source");

			String url = source.path("url").asText();

			if (existingUrls.contains(url)) {
				continue;
			}

			ArticleDto articleDto = ArticleDto.builder()
				.title(source.path("title").asText())
				.company(source.path("news_comp").asText())
				.link(source.path("url").asText())
				.thumbnail(source.path("img_url").asText())
				.category(source.path("category").asText())
				.time(LocalDateTime.now())
				.content(source.path("content").asText())
				.build();

			String timeStr = source.get("time").textValue();
			Date date;
			try {
				date = formatter.parse(timeStr);
				LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
				articleDto.setTime(localDateTime);
			} catch (ParseException e) {
				logger.error(e.getMessage(), e);
			}

			existingUrls.add(url);
			articleDtos.add(articleDto);

			if (existingUrls.size() == 5) {
				break;
			}
		}

		return articleDtos;
	}

	private static Request getRequest(String keyword) {
		String indexName = calculateDateOfIndex();

		Request request = new Request("POST", "/" + indexName + "/_search_with_clusters");

		String jsonRequestBody = "{"
			+ "\"search_request\": {"
			+ "\"_source\": [\"url\", \"title\", \"content\", \"img_url\", \"category\", \"time\", \"news_comp\"],"
			+ "\"query\": {\"match\": {\"content\": \"" + keyword + "\"}},"
			+ "\"size\": 20},"
			+ "\"query_hint\": \"" + keyword + "\","
			+ "\"field_mapping\": {\"title\":[\"_source.title\"], \"content\":[\"_source.content\"]},"
			+ "\"algorithm\":\"Lingo\""
			+ " }";

		request.setJsonEntity(jsonRequestBody);
		return request;
	}

	private static String calculateDateOfIndex() {
		LocalDateTime now = LocalDateTime.now();
		LocalDate today = now.toLocalDate();

		if (now.toLocalTime().isBefore(LocalTime.of(3, 30))) {
			LocalDate yesterday = today.minusDays(1);

			return formatIndexName(today) + "," + formatIndexName(yesterday);
		} else {
			return formatIndexName(today);
		}
	}

	private static String formatIndexName(LocalDate date) {
		return "news-" + date.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
	}

}
