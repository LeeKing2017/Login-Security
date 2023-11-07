package web.LoginSecurity.domain.mail.controller;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.LoginSecurity.domain.mail.service.MailService;
import web.LoginSecurity.domain.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("web")
@Slf4j
public class MailController {
    private final MemberService memberService;
    private final MailService mailService;

    @PostMapping("/mail")
    public int sendMail(String memberEmail) throws MessagingException {
        memberService.checkEmailAllowed(memberEmail);

        int authenticationNumber = mailService.sendMail(memberEmail);

        log.info("컨트롤러 실행됨 {}", authenticationNumber);
        return authenticationNumber;
    }
}
