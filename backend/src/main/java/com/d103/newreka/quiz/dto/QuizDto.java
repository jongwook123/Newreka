package com.d103.newreka.quiz.dto;

import com.d103.newreka.hottopic.domain.KeyWord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

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
    String keyWordId;
}
