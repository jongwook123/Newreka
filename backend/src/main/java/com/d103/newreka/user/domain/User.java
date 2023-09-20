package com.d103.newreka.user.domain;

import javax.persistence.*;

import com.d103.newreka.quizStats.domain.QuizStats;
import com.d103.newreka.typingStats.domain.TypingStats;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity // 이 클래스는 엔티티를 나타냄을 명시
@NoArgsConstructor // 생성자 표시 안해도 있다고 해줌
@AllArgsConstructor // 이것도 비슷한걸걸 ? ㅋ
@Getter // 게터를 원래 밑에 명시해야하는데 이게 대체
@Setter // 세터도 동일
@ToString // tostring 도 동일
@Builder
public class User {

	@Id // primary key // PK임을 명시하기 위한 어노테이션
	// Auto Increment임을 나타내기 위해서 GeneratedValue 사용
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//자바에서는 id로 사용하고 db에서는 user_id로 사용하기 위해 name 사용
	// nullable은 DB에서 NOt null 인지 null 인지
	// nullable = false 는 즉 Not null 이란 소리임
	@Column(name = "user_id", nullable = false)
	private Long id; // Long이 db에서 bigint임
	//erd의 유저 식별번호

//	@Column(name = "login_id", nullable = false, columnDefinition = "varchar(30)")
//	private String loginId;

	@Column(nullable = false, columnDefinition = "varchar(30)")
	private String password;

	@Column(nullable = false, columnDefinition = "varchar(20)")
	private String nickname;

	@Column(nullable = false, columnDefinition = "varchar(30)")
	private String email;

	@Column(nullable = false, columnDefinition = "tinyint")
	private Byte status;

	@Column(nullable = false, columnDefinition = "varchar(30)")
	private String name;

	@Builder.Default
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<QuizStats> userQuiz = new ArrayList<>();

	@Builder.Default
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<TypingStats> userTyping = new ArrayList<>();

}
