package web.LoginSecurity.mail.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import web.LoginSecurity.mail.service.MailService;

@Controller
@RequiredArgsConstructor
public class MailController {
    private final MailService mailService;


}
