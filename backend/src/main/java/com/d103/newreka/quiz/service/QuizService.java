package com.d103.newreka.quiz.service;

import com.d103.newreka.hottopic.domain.Article;
import com.d103.newreka.hottopic.domain.KeyWord;
import com.d103.newreka.hottopic.repo.KeyWordRepo;
import com.d103.newreka.quiz.domain.Quiz;
import com.d103.newreka.quiz.dto.QuizCompareDto;
import com.d103.newreka.quiz.dto.QuizDto;
import com.d103.newreka.quiz.repo.QuizRepo;
import com.d103.newreka.user.domain.QuizState;
import com.d103.newreka.user.domain.Scrap;
import com.d103.newreka.user.domain.User;
import com.d103.newreka.user.dto.QuizStateDto;
import com.d103.newreka.user.repo.QuizStateRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepo quizRepo;
    private final QuizStateRepo quizStateRepo;
    private final KeyWordRepo keyWordRepo;

    @Transactional
    public void saveQuiz(QuizDto quizDto) {
        KeyWord keyWord = keyWordRepo.getReferenceById(quizDto.getKeyword());
        Quiz quiz = Quiz.builder()
                .title(quizDto.getTitle())
                .answer1(quizDto.getAnswer1())
                .answer2(quizDto.getAnswer2())
                .answer3(quizDto.getAnswer3())
                .answer4(quizDto.getAnswer4())
                .correctAnswer(quizDto.getCorrectAnswer())
                .keyword(keyWord)
                .build();
        quizRepo.save(quiz);
    }

    @Transactional
    public List<Quiz> getQuizeList(Long keyword) {
        return quizRepo.findByKeyword_keyWordId(keyword);
    }

    /**
     * 퀴즈 정답 비교 후 정답시 true, 오답시 false
     */
    @Transactional
    public String compareAnswer(QuizCompareDto quizCompareDto, User user) {

        // 퀴즈 정보 저장
        List<Long> quizIdList = quizCompareDto.getQuizIdList();
        List<Integer> userAnswerList = quizCompareDto.getAnswerList();

        KeyWord keyword = new KeyWord();

        for (int i = 0; i < quizIdList.size(); i++) {

            Long quizId = quizIdList.get(i);
            int userAnswer = userAnswerList.get(i);

            Quiz quiz = quizRepo.findById(quizId)
                    .orElseThrow(() -> new NoSuchElementException("No Quiz found with id: " + quizId));

            keyword = quiz.getKeyword();
            System.out.println(keyword.getKeyWordId());
            System.out.println(user.getId());

            if(i==0){
                QuizState check = quizStateRepo.findByKeyWord_keyWordIdAndUser_Id(keyword.getKeyWordId(), user.getId());

                System.out.println(check);

                if(check!=null){
                    return "already solved";
                }
            }

            int realAnswer = quiz.getCorrectAnswer();

            if (userAnswer != realAnswer) {
                return "false";
            }
        }
        System.out.println("문제 비교는 다 끝남");
        
        LocalDateTime now = LocalDateTime.now();

        QuizState quizState = QuizState.builder()
                        .keyWord(keyword)
                                .user(user)
                                        .category(keyword.getCategory())
                                                .createTime(LocalDateTime.now())
                                                        .build();

        quizStateRepo.save(quizState);

        return "true";
    }

}
