package com.d103.newreka.user.domain;

import com.d103.newreka.hottopic.domain.Article;
import com.d103.newreka.hottopic.domain.KeyWord;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scrap_id", nullable = false)
    private Long scrapId;

    // @Column(nullable = false, columnDefinition = "varchar(50)")
    // private String link;
    //
    // @Column(nullable = false, columnDefinition = "varchar(30)")
    // private String category;

    @Column(name = "create_time", nullable = false, columnDefinition = "datetime")
    private LocalDateTime createTime;

    // @Column(nullable = false, columnDefinition = "varchar(100)")
    // private String thumbnail;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @ManyToOne
    @JoinColumn(name = "keyword_id", nullable = false)
    private KeyWord keyWordId;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article articleId;

}
