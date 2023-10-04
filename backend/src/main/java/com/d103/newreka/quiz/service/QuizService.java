package com.d103.newreka.quiz.service;

import com.d103.newreka.hottopic.domain.KeyWord;
import com.d103.newreka.hottopic.repo.KeyWordRepo;
import com.d103.newreka.quiz.domain.Quiz;
import com.d103.newreka.quiz.dto.QuizDto;
import com.d103.newreka.quiz.repo.QuizRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepo quizRepo;
    private final KeyWordRepo keyWordRepo;
    @Transactional
    public void saveQuiz(QuizDto quizDto){
        KeyWord keyWord =  keyWordRepo.getReferenceById(quizDto.getKeyword());
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
    public List<Quiz> getQuizeList(Long keyword){
        return quizRepo.findByKeyword_keyWordId(keyword);
    }
}
