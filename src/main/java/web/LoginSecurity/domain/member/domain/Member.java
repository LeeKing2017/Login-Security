package web.LoginSecurity.domain.member.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long memberId;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NICK_NAME")
    private String nickname;

    @CreatedDate
    private LocalDateTime createTime;

    public Member toEntity(String email, String password, String nickname){
        return Member.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .build();
    }

    @Builder
    public Member(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }
}
