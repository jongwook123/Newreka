package com.d103.newreka.quiz.repo;

import com.d103.newreka.quiz.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepo extends JpaRepository<Quiz, Long> {


}
