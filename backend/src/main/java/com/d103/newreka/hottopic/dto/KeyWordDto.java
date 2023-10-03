package com.d103.newreka.hottopic.dto;

import com.d103.newreka.hottopic.domain.KeyWord;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
// @NoArgsConstructor
@AllArgsConstructor
@Data
public class KeyWordDto {

	private Long keyWordId;
	private String name;
	private String summary;
	private String category;
	private Long timeId;

	public static KeyWordDto fromEntity(KeyWord keyWord) {
		return KeyWordDto.builder()
			.keyWordId(keyWord.getKeyWordId())
			.name(keyWord.getName())
			.summary(keyWord.getSummary())
			.category(keyWord.getCategory())
			.timeId(keyWord.getTime().getTimeId())
			.build();
	}

}
