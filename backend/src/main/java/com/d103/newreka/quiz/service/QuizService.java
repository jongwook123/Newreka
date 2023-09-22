package com.d103.newreka.quiz.service;

import com.d103.newreka.quiz.domain.Quiz;
import com.d103.newreka.quiz.dto.QuizDto;
import com.d103.newreka.quiz.repo.QuizRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepo quizRepo;

    //키워드만들고 키워드명넣게 바꿔야함
    public void saveQuiz(QuizDto quizDto){
        Quiz quiz = Quiz.builder()
                .title(quizDto.getTitle())
                .answer1(quizDto.getAnswer1())
                .answer2(quizDto.getAnswer2())
                .answer3(quizDto.getAnswer3())
                .answer4(quizDto.getAnswer4())
//                .keyWordId(quizDto.getKeyWordId())
                .build();
        quizRepo.save(quiz);
    }

}
