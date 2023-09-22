package com.d103.newreka.hottopic.dto;

import com.d103.newreka.hottopic.domain.KeyWord;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
public class ArticleDto {
    Long articleId;
    String title;
    String company;
    String link;
    LocalDateTime time;
    KeyWord keyWordId;

}
