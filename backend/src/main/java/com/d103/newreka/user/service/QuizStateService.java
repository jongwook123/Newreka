package com.d103.newreka.user.service;

import com.d103.newreka.quiz.repo.QuizRepo;
import com.d103.newreka.user.domain.QuizState;
import com.d103.newreka.user.dto.QuizStateDto;
import com.d103.newreka.user.repo.QuizStateRepo;
import com.d103.newreka.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuizStateService {

    private final QuizStateRepo quizStateRepo;
    private final UserRepository userRepository;
    private final QuizRepo quizRepo;

    @Transactional
    public void saveQuizState(QuizStateDto quizStateDto){
        QuizState quizState = QuizState.builder()
                .stateId(quizStateDto.getStateId())
                .category(quizStateDto.getCategory())
                .createTime(quizStateDto.getCreateTime())
                .user(userRepository.getReferenceById(quizStateDto.getUser()))
                .quiz(quizRepo.getReferenceById(quizStateDto.getQuiz()))
                .build();
        quizStateRepo.save(quizState);
    }

}
