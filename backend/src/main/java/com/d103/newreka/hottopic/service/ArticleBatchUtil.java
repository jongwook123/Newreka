package com.d103.newreka.hottopic.service;

import com.d103.newreka.hottopic.domain.KeyWord;
import com.d103.newreka.hottopic.dto.ArticleDto;
import com.d103.newreka.hottopic.repo.KeyWordRepo;
import com.d103.newreka.quiz.domain.Quiz;
import com.d103.newreka.quiz.repo.QuizRepo;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ArticleBatchUtil {

    private final NewsService newsService;
    private final ArticleService articleService;
    private final KeyWordRepo keyWordRepo;
    private final QuizRepo quizRepo;

    private static final Logger logger = LoggerFactory.getLogger(ArticleBatchUtil.class);
    private static String client_id = "hevtdy8yus";
    private static String client_secret = "11vHPak7llNgpKSm3wx5S7U8Vlsw1zUnZPyWptvh";
    private static String url = "https://naveropenapi.apigw.ntruss.com/text-summary/v1/summarize";
    private static String url2 = "https://api.openai.com/v1/chat/completions";
    private static String gpt = "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";
    private static CloseableHttpClient httpClient = HttpClients.createDefault();

    @Scheduled(cron = "0 6/10 * * * ?") // 1분, 11분, 21분, ... 단위로 스케줄러 등록
    public void insertData() {
        try {
            List<KeyWord> keywordList = keyWordRepo.findTop10ByOrderByKeyWordIdDesc();
            Collections.reverse(keywordList); // 순서가 반대로 불러와져서 있어서 뒤집기

            for (KeyWord k : keywordList) {
                String keyword = k.getName();
                List<ArticleDto> articleDtos = newsService.searchWithClusters(keyword);
                Quiz quiz = new Quiz();

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
                ArrayList<String> list = getQuiz(headLineArticle.getContent());
				quiz.setTitle(list.get(0));
				quiz.setAnswer1(list.get(1));
				quiz.setAnswer2(list.get(2));
				quiz.setAnswer3(list.get(3));
				quiz.setAnswer4(list.get(4));
				quiz.setCorrectAnswer(list.get(5));
				quiz.setKeyword(k);
				quizRepo.save(quiz);
				quiz.setTitle(list.get(6));
				quiz.setAnswer1(list.get(7));
				quiz.setAnswer2(list.get(8));
				quiz.setAnswer3(list.get(9));
				quiz.setAnswer4(list.get(10));
				quiz.setCorrectAnswer(list.get(11));
				quizRepo.save(quiz);
				quiz.setTitle(list.get(12));
				quiz.setAnswer1(list.get(13));
				quiz.setAnswer2(list.get(14));
				quiz.setAnswer3(list.get(15));
				quiz.setAnswer4(list.get(16));
				quiz.setCorrectAnswer(list.get(17));
				quizRepo.save(quiz);
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

//	public static void main(String[] args) throws IOException {
////		int a = getTokenSize("더불어민주당 전라북도 지역 국회의원들은 정부의 새만금 SOC 예산 삭감은 전북을 잼버리 파행 희생양으로 삼은 것이라고 강하게 반발하며 규탄의 의미로 삭발식을 진행했습니다.민주당 김윤덕 의원을 비롯한 전북 의원들과 당원·지지자들은 오늘(7일) 국회 본청 앞에서 윤석열 정부 새만금 SOC 예산 삭감 규탄대회를 열었습니다.삭발에 참여한 김성주 의원은 정부가 잼버리 파행 책임을 전북에 모두 떠넘기고 있다면서, 새만금 예산의 부활을 위해 전체주의에 맞서 싸우겠다고 밝혔습니다.한병도 의원은 다음 주 기획재정부 앞에서 예산 삭감을 규탄하며 별도 삭발식을 진행할 예정입니다.※ '당신의 제보가 뉴스가 됩니다'[카카오톡] YTN 검색해 채널 추가[전화] 02-398-8585[메일] social@ytn.co.kr");
//		String s ="전쟁 중인 우크라이나에 무단 입국했던 이근 전 대위가 무면허 운전으로 입건됐습니다.경기 수원남부경찰서는 어제(6일) 이 씨를 도로교통법 위반 혐의로 입건했습니다.이 씨는 어제 오후 6시쯤 총포화약법 위반 혐의로 조사를 받기 위해 경찰서를 찾았다가 경찰관이 운전자를 확인하는 과정에서 무면허 운전 사실이 드러났습니다.이 씨는 지난해 7월 서울 시내에서 차를 운전하다가 오토바이와 사고를 낸 뒤 구호 조치 없이 현장을 떠난 혐의로 수사를 받아 현재 면허가 취소된 상태입니다.또, 지난달 17일엔 우크라이나 무단 입국 혐의와 뺑소니 혐의로 서울중앙지방법원에서 징역 1년 6개월에 집행유예 3년을 선고받았습니다.※ '당신의 제보가 뉴스가 됩니다'[카카오톡] YTN 검색해 채널 추가[전화] 02-398-8585[메일] social@ytn.co.kr";
////		System.out.println(a);
////		String  sub = "좋습니다. 여러분을 위해 객관식 퀴즈를 준비해보겠습니다. 아래의 세 가지 질문에 대한 정답도 함께 제시해드릴게요.\n\n1. 이근 전 대위가 어떤 혐의로 경찰서에서 입건되었나요?\na) 도로교통법 위반\nb) 총포화약법 위반\nc) 무면허 운전\nd) 뺑소니 혐의\n정답: c) 무면허 운전\n\n2. 이근 전 대위는 왜 면허가 취소된 상태인가요?\na) 차량 사고로 인한 구호 조치 미발동\nb) 우크라이나 무단 입국 혐의\nc) 뺑소니 혐의\nd) 서울 시내에서의 사고 사건\n정답: a) 차량 사고로 인한 구호 조치 미발동\n\n3. 이근 전 대위는 최근 어떤 혐의로 선고를 받았고, 어떤 판결이 내려졌나요?\na) 우크라이나 무단 입국 혐의 / 징역 1년 6개월, 집행유예 3년\nb) 총포화약법 위반 혐의 / 징역 1년 6개월, 집행유예 3년\nc) 뺑소니 혐의 / 징역 1년 6개월, 집행유예 3년\nd) 도로교통법 위반 혐의 / 징역 1년 6개월, 집행유예 3년\n정답: a) 우크라이나 무단 입국 혐의 / 징역 1년 6개월, 집행유예 3년\n\n위의 질문과 정답을 확인하셨습니까? 추가적인 도움이 필요하다면 언제든지 말씀해주세요.";
//		String ss = getQuiz(s);
//		System.out.println(ss);
//		String[] qu = ss.split("\\\\n");
//		for(String i : qu){
//			System.out.println(i);
//		}
//		ArrayList<String> a = new ArrayList<>();
//		for(String i : qu){
//			if(i.length()>3) {
//				if(i.charAt(1) == '문'){
//					String[] b = i.split(": ");
//					a.add(b[1]);
//				}
//				else if (i.charAt(1) == '.') {
//					String[] b = i.split(" ");
//					a.add(b[1]);
//				}
//				else if(i.charAt(1) == '답'){
//					String[] b = i.split(": ");
//					if(b[1].charAt(0)=='A'||b[1].charAt(0)=='B'||b[1].charAt(0)=='C'||b[1].charAt(0)=='D')
//					a.add(b[1].charAt(0)+"");
//					else{
//						a.add(b[1]);
//					}
//				}
//			}
//		}
//		for(int i=0;i<a.size();i++){
//			System.out.println(a.get(i));
//		}

//		for(int i=0;i< qu.length;i++){
//			if(qu[i].length()>=4){
//				qu[i]=qu[i].substring(3);
//			}
//			if(i==7||i==14||i==21){
//				qu[i]=qu[i].substring(1,2);
//			}
//		}
//
//	}

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

        return resultContent.substring(11, resultContent.length() - 2);
    }

    static private ArrayList<String> getQuiz(String article) throws IOException {

        // "를 '로 변경
        String context = article.replace("\"", "'");

//        System.out.println("아래 context");
//        System.out.println(context);
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
                "        \"content\": \"기사를 기반으로 보기가 4개인 객관식 퀴즈3개를 답과함께 만들어줘 형식을 꼭 지켜줘 띄어쓰기도 지켜 \\n 기사:" + context + "\\n\\nQuiz 1:\\n\\n질문:\\n\\n보기\\n\\nA.\\n\\nB.\\n\\nC.\\n\\nD.\\n\\n정답\\n\\nQuiz 2:\\n\\n질문: \\n\\n보기:\\n\\nA. \\n\\nB. \\n\\nC. \\n\\nD. \\n\\n정답:\\n\\nQuiz 3:\\n\\n질문: \\n\\n보기:\\n\\nA. \\n\\nB. \\n\\nC. \\n\\nD. \\n\\n정답: \"" + "\n" +
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

        CloseableHttpResponse response = httpClient.execute(httpPost);

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
                    String[] b = i.split(" ");
                    a.add(b[1]);
                } else if (i.charAt(1) == '답') {
                    String[] b = i.split(": ");
                    if (b[1].charAt(0) == 'A' || b[1].charAt(0) == 'B' || b[1].charAt(0) == 'C' || b[1].charAt(0) == 'D')
                        a.add(b[1].charAt(0) + "");
                    else {
                        a.add(b[1]);
                    }
                }
            }
        }

        return a;
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