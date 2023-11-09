package web.LoginSecurity.global.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import web.LoginSecurity.domain.member.dto.MemberDto;

@RestControllerAdvice
public class MemberExceptionHandler {
    @ExceptionHandler(NotFoundMemberException.class)
    public MemberDto.LoginResponse notFoundMemberExceptionHandler(NotFoundMemberException e){
        return new MemberDto.LoginResponse(false, e.getMessage());
    }
}
