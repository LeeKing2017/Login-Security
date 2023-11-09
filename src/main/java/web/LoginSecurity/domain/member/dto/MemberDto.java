package web.LoginSecurity.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class MemberDto {

    @Getter
    @NoArgsConstructor
    @ToString
    public static class CreateMember{
        private String email;
        private String password;
        private String nickname;

        @Builder
        public CreateMember(String email, String password, String nickname) {
            this.email = email;
            this.password = password;
            this.nickname = nickname;
        }
    }

    @Getter
    @NoArgsConstructor
    @ToString
    public static class LoginMember{
        private String email;
        private String password;

        @Builder
        public LoginMember(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }
}
