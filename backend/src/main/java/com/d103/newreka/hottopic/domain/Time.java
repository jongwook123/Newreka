package com.d103.newreka.hottopic.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Time {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "time_id", nullable = false)
	private Long timeId;

	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime time;

	@Builder.Default
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "time", cascade = CascadeType.ALL)
	private List<KeyWord> keyWords = new ArrayList<>();

}
