package com.d103.newreka.quiz.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.d103.newreka.hottopic.domain.KeyWord;
import com.d103.newreka.user.domain.QuizState;

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

	@Column(nullable = false, columnDefinition = "int")
	private int collectAnswer;

	@ManyToOne
	@JoinColumn(name = "keyWord_id", nullable = false)
	private KeyWord keyword;

	@Builder.Default
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "quiz", cascade = CascadeType.ALL)
	private List<QuizState> quizStates = new ArrayList<>();

}
