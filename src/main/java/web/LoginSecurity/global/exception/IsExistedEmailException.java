package web.LoginSecurity.global.exception;

public class IsExistedEmailException extends RuntimeException{
    public IsExistedEmailException(String message) {
        super(message);
    }
}
