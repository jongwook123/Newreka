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
public class QuizStateDto {

    Long stateId;
    String category;
    LocalDateTime createTime;
    Long user;
    Long keyWord;

}
