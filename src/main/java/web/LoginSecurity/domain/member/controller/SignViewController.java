package web.LoginSecurity.domain.member.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class SignViewController {
    @Value("${google.recaptcha.key.site}")
    private String siteKey;

    @GetMapping("/login")
    public String loginView(Model model){
        model.addAttribute("siteKey", siteKey);
        return "login";
    }

    @GetMapping("/member")
    public String signUpView(){
        return "signUpForm";
    }
}
