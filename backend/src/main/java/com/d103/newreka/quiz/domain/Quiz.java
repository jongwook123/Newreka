package com.d103.newreka.quiz.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.d103.newreka.hottopic.domain.KeyWord;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Quiz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "quiz_id", nullable = false)
	private Long quizId;

	@Column(nullable = false, columnDefinition = "varchar(100)")
	private String title;

	@Column(nullable = false, columnDefinition = "varchar(20)")
	private String answer1;
	@Column(nullable = false, columnDefinition = "varchar(20)")
	private String answer2;
	@Column(nullable = false, columnDefinition = "varchar(20)")
	private String answer3;
	@Column(nullable = false, columnDefinition = "varchar(20)")
	private String answer4;

	@ManyToOne
	@JoinColumn(name = "keyWord_id", nullable = false)
	private KeyWord keyWordId;

}
