package com.d103.newreka.hottopic.dto;

import com.d103.newreka.hottopic.domain.Article;
import com.d103.newreka.hottopic.domain.KeyWord;
import com.d103.newreka.hottopic.domain.Time;
import com.d103.newreka.quiz.domain.Quiz;
import com.d103.newreka.quiz.dto.QuizDto;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Builder
@Getter
@Setter
@Data
public class KeyWordDto {

    Long keyWordId;
    String name;
    String summary;
    String category;
    Long timeId;

    public static KeyWordDto fromEntity(KeyWord keyWord){
        return KeyWordDto.builder()
                .keyWordId(keyWord.getKeyWordId())
                .name(keyWord.getName())
                .summary(keyWord.getSummary())
                .category(keyWord.getCategory())
                .timeId(keyWord.getTime().getTimeId())
                .build();
    }

}
