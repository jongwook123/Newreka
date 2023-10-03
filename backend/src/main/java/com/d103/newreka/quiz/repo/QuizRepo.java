package com.d103.newreka.quiz.repo;

import com.d103.newreka.quiz.domain.Quiz;
import com.d103.newreka.quiz.dto.QuizDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QuizRepo extends JpaRepository<Quiz, Long> {

    List<Quiz> findByKeyword_keyWordId(Long id);
}
