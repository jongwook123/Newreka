package com.d103.newreka.user.repo;

import com.d103.newreka.quiz.domain.Quiz;
import com.d103.newreka.user.domain.QuizState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizStateRepo extends JpaRepository<QuizState, Long> {

    List<QuizState> findByUser_IdAndCategory(Long id, String category);
}
