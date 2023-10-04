package com.d103.newreka.quiz.dto;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class QuizCompareDto {

	private Long quizId1;
	private int quiz1answer;

	private Long quizId2;
	private int quiz2answer;

	private Long quizId3;
	private int quiz3answer;

	public List<Long> getQuizIdList() {
		return Arrays.asList(quizId1, quizId2, quizId3);
	}

	public List<Integer> getAnswerList() {
		return Arrays.asList(quiz1answer, quiz2answer, quiz3answer);
	}

}
