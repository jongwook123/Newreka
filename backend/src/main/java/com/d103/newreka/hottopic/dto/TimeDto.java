package com.d103.newreka.hottopic.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
@Data
public class TimeDto {

    Long timeId;
    LocalDateTime time;

}
