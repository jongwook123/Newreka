package com.d103.newreka.user.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.d103.newreka.hottopic.domain.Article;
import com.d103.newreka.hottopic.domain.KeyWord;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Scrap {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "scrap_id", nullable = false)
	private Long scrapId;

	// @Column(nullable = false, columnDefinition = "varchar(50)")
	// private String link;
	//
	// @Column(nullable = false, columnDefinition = "varchar(30)")
	// private String category;

	@Column(name = "create_time", nullable = false, columnDefinition = "datetime")
	private LocalDateTime createTime;

	// @Column(nullable = false, columnDefinition = "varchar(100)")
	// private String thumbnail;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User userId;

	@ManyToOne
	@JoinColumn(name = "keyword_id", nullable = false)
	private KeyWord keyWordId;

	@ManyToOne
	@JoinColumn(name = "article_id", nullable = false)
	private Article articleId;

}
