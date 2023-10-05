package com.d103.newreka.user.dto;

import com.d103.newreka.user.domain.Scrap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScrapDto {

	Long scrapId;
	// String link;
	// String category;
	// LocalDateTime createTime;
	// String thumbnail;
	Long user;
	Long keyWord;
	Long article;

	public static ScrapDto fromEntity(Scrap scrap) {
		//        TeamRepo teamRepo = null;
		//        StadiumRepo stadiumRepo = null;
		// UserRepository userRepository = null;
		return ScrapDto.builder()
			.scrapId(scrap.getScrapId())
			// .createTime(scrap.getCreateTime())
			.user(scrap.getUserId().getId())
			.keyWord(scrap.getKeyWordId().getKeyWordId())
			.article(scrap.getArticleId().getArticleId())
			.build();
	}
}
