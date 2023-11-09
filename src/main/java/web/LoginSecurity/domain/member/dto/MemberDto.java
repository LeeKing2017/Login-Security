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
    public static class LoginRequest {
        private String email;
        private String password;
        private String reCaptchaResponse;

        @Builder
        public LoginRequest(String email, String password, String reCaptchaResponse) {
            this.email = email;
            this.password = password;
            this.reCaptchaResponse = reCaptchaResponse;
        }
    }

    @Getter
    @NoArgsConstructor
    @ToString
    public static class LoginResponse {
        private boolean status;
        private String errMsg;

        public LoginResponse(boolean status, String errMsg) {
            this.status = status;
            this.errMsg = errMsg;
        }

        public LoginResponse(boolean status) {
            this.status = status;
        }
    }
}
