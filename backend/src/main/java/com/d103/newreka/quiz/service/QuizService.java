package com.d103.newreka.quiz.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d103.newreka.hottopic.domain.KeyWord;
import com.d103.newreka.hottopic.repo.KeyWordRepo;
import com.d103.newreka.quiz.domain.Quiz;
import com.d103.newreka.quiz.dto.QuizCompareDto;
import com.d103.newreka.quiz.dto.QuizDto;
import com.d103.newreka.quiz.repo.QuizRepo;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuizService {

	private final QuizRepo quizRepo;
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
			.collectAnswer(quizDto.getCollectAnswer())
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
	public boolean compareAnswer(QuizCompareDto quizCompareDto) {

		// 퀴즈 정보 저장
		List<Long> quizIdList = quizCompareDto.getQuizIdList();
		List<Integer> userAnswerList = quizCompareDto.getAnswerList();

		for (int i = 0; i < quizIdList.size(); i++) {

			Long quizId = quizIdList.get(i);
			int userAnswer = userAnswerList.get(i);

			Quiz quiz = quizRepo.findById(quizId)
				.orElseThrow(() -> new NoSuchElementException("No Quiz found with id: " + quizId));
			int realAnswer = quiz.getCollectAnswer();

			if (userAnswer != realAnswer) {
				return false;
			}

		}

		return true;
	}
}
