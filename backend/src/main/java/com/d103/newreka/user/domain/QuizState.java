package com.d103.newreka.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.d103.newreka.hottopic.domain.KeyWord;

import com.d103.newreka.quiz.domain.Quiz;
import com.d103.newreka.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class QuizState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sate_id", nullable = false)
    private Long stateId;

    @Column(nullable = false, columnDefinition = "varchar(30)")
    private String category;
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime createTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "keyWord_id", nullable = false)
    private KeyWord keyWord;
}
