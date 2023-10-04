package com.d103.newreka.quiz.dto;

import com.d103.newreka.quiz.domain.Quiz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {
	private Long quizId;
	private String title;
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	private int collectAnswer;
	private Long keyword;

	public static QuizDto fromEntity(Quiz quiz) {
		//        TeamRepo teamRepo = null;
		//        StadiumRepo stadiumRepo = null;
		return QuizDto.builder()
			.quizId(quiz.getQuizId())
			.title(quiz.getTitle())
			.answer1(quiz.getAnswer1())
			.answer2(quiz.getAnswer2())
			.answer3(quiz.getAnswer3())
			.answer4(quiz.getAnswer4())
			.collectAnswer(quiz.getCollectAnswer())
			.keyword(quiz.getKeyword().getKeyWordId())
			.build();
	}

}




