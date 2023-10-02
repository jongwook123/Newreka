package com.d103.newreka.hottopic.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "article_Id", nullable = false)
	private Long articleId;

	@Column(nullable = false, columnDefinition = "varchar(100)")
	private String title;
	@Column(nullable = false, columnDefinition = "varchar(30)")
	private String company;
	@Column(nullable = false, columnDefinition = "varchar(255)")
	private String link;
	@Column(nullable = false, columnDefinition = "varchar(255)")
	private String imgLink;
	@Column(columnDefinition = "varchar(30)")
	private String category;
	@Column(nullable = false, columnDefinition = "text")
	private String content;
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime time;

	@ManyToOne
	@JoinColumn(name = "keyWord_id", nullable = false)
	private KeyWord keyWord;
}