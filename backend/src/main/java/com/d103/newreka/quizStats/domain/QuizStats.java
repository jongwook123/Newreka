package com.d103.newreka.quizStats.domain;

import javax.persistence.*;

import com.d103.newreka.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class QuizStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stats_id", nullable = false)
    private Long id;

    @Column(nullable = false, columnDefinition = "varchar(30)")
    private String category;

    @Column(nullable = false, columnDefinition = "timestamp")
    private LocalDateTime createtime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
