package web.LoginSecurity.domain.mail.service;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import web.LoginSecurity.global.utils.RandomNumberGenerator;

import static web.LoginSecurity.domain.mail.MailMessageDto.MAIL_CONTENT_FORMAT;
import static web.LoginSecurity.domain.mail.MailMessageDto.MAIL_TITLE;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {
    @Value("${mail.senderEmail}")
    private String senderEmail;
    private final JavaMailSender javaMailSender;

    public int sendMail(String memberEmail) throws MessagingException {
        int authenticationNumber = new RandomNumberGenerator().createRandomNumber();
        MimeMessage message = createMail(memberEmail, authenticationNumber);

        javaMailSender.send(message);

        return authenticationNumber;
    }

    private MimeMessage createMail(String memberEmail, int authenticationNumber) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            mimeMessage.setFrom(senderEmail);
            mimeMessage.setRecipients(Message.RecipientType.TO, memberEmail);
            mimeMessage.setSubject(MAIL_TITLE);
            String mailBody = MAIL_CONTENT_FORMAT + authenticationNumber;
            mimeMessage.setText(mailBody, "UTF-8", "html");
        }catch (MessagingException e){
            log.info("[mail error] {}", e.getMessage());
        }

        return mimeMessage;
    }
}
