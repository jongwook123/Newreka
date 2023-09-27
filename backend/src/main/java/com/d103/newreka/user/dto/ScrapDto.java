package com.d103.newreka.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
@Data
@AllArgsConstructor
public class ScrapDto {

    Long scrapId;
    String link;
    String category;
    LocalDateTime createTime;
    String thumbnail;
    Long user;
    Long keyWrod;
}
