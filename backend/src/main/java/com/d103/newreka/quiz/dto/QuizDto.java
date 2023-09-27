package com.d103.newreka.quiz.dto;

import com.d103.newreka.hottopic.domain.KeyWord;
import com.d103.newreka.quiz.domain.Quiz;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
@Builder
@Getter
@Data
@AllArgsConstructor
public class QuizDto {
    Long quizId;
    String title;
    String answer1;
    String answer2;
    String answer3;
    String answer4;
    Long keyword;

    public static QuizDto fromEntity(Quiz quiz){
//        TeamRepo teamRepo = null;
//        StadiumRepo stadiumRepo = null;
        return QuizDto.builder()
                .quizId(quiz.getQuizId())
                .title(quiz.getTitle())
                .answer1(quiz.getAnswer1())
                .answer2(quiz.getAnswer2())
                .answer3(quiz.getAnswer3())
                .answer4(quiz.getAnswer4())
                .keyword(quiz.getKeyword().getKeyWordId())
                .build();
    }

}




