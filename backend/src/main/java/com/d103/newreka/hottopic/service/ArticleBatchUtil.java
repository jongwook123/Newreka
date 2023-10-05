package com.d103.newreka.hottopic.service;

import com.d103.newreka.hottopic.domain.KeyWord;
import com.d103.newreka.hottopic.dto.ArticleDto;
import com.d103.newreka.hottopic.repo.KeyWordRepo;
import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.Encoding;
import com.knuddels.jtokkit.api.EncodingRegistry;
import com.knuddels.jtokkit.api.ModelType;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.RequiredArgsConstructor;
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

import java.io.IOException;
import java.util.Collections;
import java.util.List;

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
    private static CloseableHttpClient httpClient = HttpClients.createDefault();

    @Scheduled(cron = "0 1/10 * * * ?") // 1분, 11분, 21분, ... 단위로 스케줄러 등록
    public void insertData() {
        try {
            List<KeyWord> keywordList = keyWordRepo.findTop10ByOrderByKeyWordIdDesc();
            Collections.reverse(keywordList); // 순서가 반대로 불러와져서 있어서 뒤집기

            for (KeyWord k : keywordList) {
                String keyword = k.getName();
                List<ArticleDto> articleDtos = newsService.searchWithClusters(keyword);

                // 헤드라인 뉴스 선정
                ArticleDto headLineArticle = null;
                int count = 0;
                for (int i = 0; i < 5 && count == 0; i++) {
                    if (articleDtos.get(i).getContent().length() <= 2000) {
                        headLineArticle = articleDtos.get(i);
                        count++;
                    }
                }

                if (count == 0) {
                    headLineArticle = articleDtos.get(0);
                    headLineArticle.setContent(headLineArticle.getContent().substring(0, 1998));
                }

                String summary = getSummary(headLineArticle.getContent());
                k.setSummary(summary);
                k.setCategory(headLineArticle.getCategory());
                keyWordRepo.save(k); // 키워드 업데이트


                // 연관 뉴스 저장
                for (ArticleDto articleDto : articleDtos) {
                    articleDto.setKeyWordId(k.getKeyWordId());
                    articleService.saveArticle(articleDto);
                }
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    private String getSummary(String content) throws IOException {
        HttpPost httpPost = new HttpPost(url);

        httpPost.setHeader("Accept", "application/json;UTF-8");
        httpPost.setHeader("Content-Type", "application/json;UTF-8");
        httpPost.setHeader("X-NCP-APIGW-API-KEY-ID", client_id);
        httpPost.setHeader("X-NCP-APIGW-API-KEY", client_secret);

        JSONObject documentObj = new JSONObject();
        documentObj.put("content", content);

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

        HttpEntity et = response.getEntity();
        String resultContent = EntityUtils.toString(et, "UTF-8");
//        System.out.println(resultContent);
        resultContent = resultContent.substring(12, resultContent.length() - 2);
        String[] a = resultContent.split("\\\\n");
        resultContent = a[0] + " " + a[1];

        a = resultContent.split("\\\\\"");
        String result = a[0];
        for (int i = 1; i < a.length; i++) {
            result += a[i];
        }

        return result;

    }


    static public int getTokenSize(String text) {
        EncodingRegistry registry = Encodings.newDefaultEncodingRegistry();
        Encoding enc = registry.getEncodingForModel(ModelType.GPT_3_5_TURBO);

        List<Integer> encoded = enc.encode(text);
        return encoded.size();
    }

}


//				"        \"content\": \""+context+"다른말은 하지말고 이 뉴스 내용을 이용해서 객관식 퀴즈 3개 (보기는 4개)와 정답을 알려주세요. 양식은 json형식으로 quiz 1, option 1, 2, 3, 4, answer, quiz 2, option 1, 2, 3, 4, answer, quiz 3, option 1, 2, 3, 4, answer 이렇게 보내주세요.\""+"\n" +
//"        \"content\": \""+context+"다른말은 하지말고 이 뉴스 내용을 이용해서 '\\n1. 문제1:내용\\n보기:\\na) 1\\nb) 2\\nc) 3\\nd) 4\\n\\n정답: ? \\n2. 문제2:내용\\n보기:\\na) 1\\nb) 2\\nc) 3\\nd) 4\\n\\n정답: ? \\n3. 문제3:내용\\n보기:\\na) 1\\nb) 2\\nc) 3\\nd) 4\\n\\n정답: ? ' 이 양식으로 객관식퀴즈를 3개 내고 정답을 알려줘\""+"\n" +