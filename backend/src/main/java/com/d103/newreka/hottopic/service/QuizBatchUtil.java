package com.d103.newreka.hottopic.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.d103.newreka.hottopic.domain.Article;
import com.d103.newreka.hottopic.domain.KeyWord;
import com.d103.newreka.hottopic.repo.KeyWordRepo;
import com.d103.newreka.quiz.domain.Quiz;
import com.d103.newreka.quiz.repo.QuizRepo;
import com.nimbusds.jose.shaded.json.JSONObject;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class QuizBatchUtil {

	private final QuizRepo quizRepo;
	private final ArticleService articleService;
	private final KeyWordRepo keyWordRepo;
	private static final Logger logger = LoggerFactory.getLogger(QuizBatchUtil.class);

	private static String url2 = "https://api.openai.com/v1/chat/completions";
	private static String gpt = "yourKey";
	private static CloseableHttpClient httpClient = null;

	static List<KeyWord> keywordList = new ArrayList<>();
	static List<String> headLineArticleList = new ArrayList<>();

	// 일시 중지
	// @Scheduled(cron = "40 1/10 * * * ?")
	public void setArticle() throws IOException, ParseException {

		this.keywordList = keyWordRepo.findTop10ByOrderByKeyWordIdDesc();
		Collections.reverse(keywordList); // 순서가 반대로 불러와져서 있어서 뒤집기

		for (KeyWord k : keywordList) {
			try {
				//            System.out.println(k.getName());
				List<Article> articles = articleService.getArticleList(k.getKeyWordId());

				// 헤드라인 뉴스 선정
				String headLineArticle = null;
				int count = 0;
				for (int i = 0; i < 5 && count == 0; i++) {
					if (articles.get(i).getContent().length() <= 2000) {
						headLineArticle = articles.get(i).getContent();
						count++;
					}
				}

				if (count == 0) {
					headLineArticle = articles.get(0).getContent().substring(0, 1998);
				}

				//            System.out.println(headLineArticle);
				headLineArticleList.add(headLineArticle);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}

	}

	// 일시 중지
	// @Scheduled(cron = "50 1/10 * * * ?")
	public void insertQ() throws IOException {
		for (int i = 0; i < 10; i++) {
			try {
				if (!headLineArticleList.isEmpty()) {
					insertQuiz(keywordList.get(i), headLineArticleList.get(i));
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	public void insertQuiz(KeyWord k, String article) throws IOException {

		ArrayList<String> list = getQuiz(article);

		if (list == null) {
			return;
		}

		Quiz quiz1 = new Quiz();
		Quiz quiz2 = new Quiz();
		Quiz quiz3 = new Quiz();

		quiz1.setTitle(list.get(0));
		quiz1.setAnswer1(list.get(1));
		quiz1.setAnswer2(list.get(2));
		quiz1.setAnswer3(list.get(3));
		quiz1.setAnswer4(list.get(4));
		quiz1.setCorrectAnswer(Integer.valueOf(list.get(5)));
		quiz1.setKeyword(k);
		quizRepo.save(quiz1);

		quiz2.setTitle(list.get(6));
		quiz2.setAnswer1(list.get(7));
		quiz2.setAnswer2(list.get(8));
		quiz2.setAnswer3(list.get(9));
		quiz2.setAnswer4(list.get(10));
		quiz2.setCorrectAnswer(Integer.valueOf(list.get(11)));
		quiz2.setKeyword(k);
		quizRepo.save(quiz2);

		quiz3.setTitle(list.get(12));
		quiz3.setAnswer1(list.get(13));
		quiz3.setAnswer2(list.get(14));
		quiz3.setAnswer3(list.get(15));
		quiz3.setAnswer4(list.get(16));
		quiz3.setCorrectAnswer(Integer.valueOf(list.get(17)));
		quiz3.setKeyword(k);
		quizRepo.save(quiz3);
	}

	static private ArrayList<String> getQuiz(String article) throws IOException {

		// "를 '로 변경
		String context = article.replace("\"", "'");

		HttpPost httpPost = new HttpPost(url2);

		httpPost.setHeader("Content-Type", "application/json");
		httpPost.setHeader("Authorization", "Bearer " + gpt);

		JSONObject messageObj = new JSONObject();
		messageObj.put("role", "system");
		messageObj.put("content", "You are a helpful assistant.");

		JSONObject messageObj1 = new JSONObject();
		messageObj1.put("role", "user");
		messageObj1.put("content", context);

		//		messageObj.put("content",context+"\n 이 뉴스 내용을 이용해서 객관식퀴즈를 내고 정답을 알려줘");
		String t = "{\n" +
			"    \"model\": \"gpt-3.5-turbo\",\n" +
			"    \"messages\": [\n" +
			"      {\n" +
			"        \"role\": \"system\",\n" +
			"        \"content\": \"You are a helpful assistant.\"\n" +
			"      },\n" +
			"      {\n" +
			"        \"role\": \"user\",\n" +
			"        \"content\": \"기사를 기반으로 보기가 4개인 객관식 퀴즈3개를 답과함께 만들어줘 형식을 꼭 지켜줘 띄어쓰기도 지켜 \\n 기사:" + context
			+ "\\n\\nQuiz 1:\\n\\n질문:\\n\\n보기\\n\\nA.\\n\\nB.\\n\\nC.\\n\\nD.\\n\\n정답\\n\\nQuiz 2:\\n\\n질문: \\n\\n보기:\\n\\nA. \\n\\nB. \\n\\nC. \\n\\nD. \\n\\n정답:\\n\\nQuiz 3:\\n\\n질문: \\n\\n보기:\\n\\nA. \\n\\nB. \\n\\nC. \\n\\nD. \\n\\n정답: \""
			+ "\n" +
			"      }\n" +
			"    ]\n" +
			"  }";

		//        System.out.println(t);
		//		System.out.println(t);
		JSONObject mainObj = new JSONObject();
		mainObj.put("model", "gpt-3.5-turbo");
		mainObj.put("message", messageObj);
		mainObj.put("message", t);
		//		StringEntity entity = new StringEntity(mainObj.toString(), ContentType.APPLICATION_JSON);
		StringEntity entity = new StringEntity(t, ContentType.APPLICATION_JSON);
		//		System.out.println(mainObj.toString());
		httpPost.setEntity(entity);

		int timeout = 40; // 40초 지나면 세션 만료
		RequestConfig config = RequestConfig.custom()
			.setConnectTimeout(timeout * 1000)
			.setConnectionRequestTimeout(timeout * 1000)
			.setSocketTimeout(timeout * 1000).build();

		httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();

		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpPost);
		} catch (SocketTimeoutException e) {
			logger.error("요청이 타임아웃되었습니다.");
			return null;
		}

		HttpEntity et = response.getEntity();
		InputStream is = et.getContent();

		//		String resultContent = EntityUtils.toString(et, "UTF-8");
		//		System.out.println(resultContent);
		BufferedReader r = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		String line = null;
		for (int i = 0; i < 11; i++) {
			line = r.readLine().toString();
			//            System.out.println(line);
		}

		String[] qu = line.split("\\\\n");
		//        for (String i : qu) {
		//            System.out.println(i);
		//        }
		ArrayList<String> a = new ArrayList<>();
		for (String i : qu) {
			if (i.length() > 3) {
				if (i.charAt(1) == '문') {
					String[] b = i.split(": ");
					a.add(b[1]);
				} else if (i.charAt(1) == '.') {
					String[] b = i.split("\\. ");
					a.add(b[1]);
				} else if (i.charAt(1) == '답') {
					String[] b = i.split(": ");
					if (b[1].charAt(0) == 'A')
						a.add("1");
					else if (b[1].charAt(0) == 'B')
						a.add("2");
					else if (b[1].charAt(0) == 'C')
						a.add("3");
					else if (b[1].charAt(0) == 'D')
						a.add("4");
					else {
						a.add("11");
					}
				}
			}
		}

		return a;
	}
}
