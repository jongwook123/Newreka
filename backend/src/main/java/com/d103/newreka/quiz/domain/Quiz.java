package com.d103.newreka.quiz.domain;

import com.d103.newreka.hottopic.domain.KeyWord;
import com.d103.newreka.user.domain.QuizState;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id", nullable = false)
    private Long quizId;

    @Column(nullable = false, columnDefinition = "varchar(200)")
    private String title;

    @Column(nullable = false, columnDefinition = "varchar(100)")
    private String answer1;
    @Column(nullable = false, columnDefinition = "varchar(100)")
    private String answer2;
    @Column(nullable = false, columnDefinition = "varchar(100)")
    private String answer3;
    @Column(nullable = false, columnDefinition = "varchar(100)")
    private String answer4;
    @Column(nullable = false, columnDefinition = "int")
    private Integer correctAnswer;

    @ManyToOne
    @JoinColumn(name = "keyWord_id", nullable = false)
    private KeyWord keyword;


}
