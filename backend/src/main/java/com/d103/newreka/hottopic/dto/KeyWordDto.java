package com.d103.newreka.hottopic.dto;

import com.d103.newreka.hottopic.domain.Article;
import com.d103.newreka.hottopic.domain.Time;
import com.d103.newreka.quiz.domain.Quiz;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
public class KeyWordDto {

    Long keyWordId;
    String name;
    String summary;
    String category;
    Long timeId;

}
