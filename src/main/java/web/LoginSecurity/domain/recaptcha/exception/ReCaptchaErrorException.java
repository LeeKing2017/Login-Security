package web.LoginSecurity.domain.recaptcha.exception;

public class ReCaptchaErrorException extends RuntimeException{
    public ReCaptchaErrorException(String message) {
        super(message);
    }
}
