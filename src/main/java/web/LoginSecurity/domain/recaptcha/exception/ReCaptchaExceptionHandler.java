package web.LoginSecurity.domain.recaptcha.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import web.LoginSecurity.domain.member.dto.MemberDto;

@RestControllerAdvice
public class ReCaptchaExceptionHandler {
    @ExceptionHandler
    public MemberDto.LoginResponse reCaptchaException(ReCaptchaErrorException e){
        return new MemberDto.LoginResponse(false, e.getMessage());
    }
}
