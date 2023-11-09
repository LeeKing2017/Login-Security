package web.LoginSecurity.domain.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class SignUpViewController {
    @GetMapping("/login")
    public String loginView(){return "login";};

    @GetMapping("/member")
    public String signUpView(){
        return "signUpForm";
    }
}
