package com.d103.newreka.hottopic.domain;

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

import com.d103.newreka.quiz.domain.Quiz;
import com.d103.newreka.user.domain.Scrap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class KeyWord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "keyWord_id", nullable = false)
	private Long keyWordId;
	@Column(nullable = false, columnDefinition = "varchar(30)")
	private String name;
	@Column(columnDefinition = "text")
	private String summary;
	@Column(columnDefinition = "varchar(30)")
	private String category;

	@ManyToOne
	@JoinColumn(name = "time_id", nullable = false)
	private Time time;

	@Builder.Default
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "keyWord", cascade = CascadeType.ALL)
	private List<Article> articles = new ArrayList<>();

	@Builder.Default
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "keyword", cascade = CascadeType.ALL)
	private List<Quiz> quizs = new ArrayList<>();

	@Builder.Default
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "keyWordId", cascade = CascadeType.ALL)
	private List<Scrap> scraps = new ArrayList<>();

}
