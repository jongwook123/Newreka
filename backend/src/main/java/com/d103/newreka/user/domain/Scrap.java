package com.d103.newreka.user.domain;

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
	private Long id;

	@Column(nullable = false, columnDefinition = "varchar(50)")
	private String link;

	@Column(nullable = false, columnDefinition = "varchar(30)")
	private String category;

	@Column(nullable = false, columnDefinition = "varchar(200)")
	private String keyword;

	@Column(name = "create_time", nullable = false, columnDefinition = "date")
	private LocalDateTime createTime;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User userId;
}
