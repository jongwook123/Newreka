package com.d103.newreka.typingStats.domain;

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
public class TypingStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stats_id", nullable = false)
    private Long id; // Long이 db에서 bigint임

    @Column(nullable = false, columnDefinition = "varchar(30)")
    private String category;

    @Column(nullable = false)
    private Integer maxspeed;

    @Column(nullable = false)
    private Integer avgspeed;

    @Column(nullable = false)
    private Integer accuracy;

    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime createtime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
