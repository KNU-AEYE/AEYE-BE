package com.server.aeye.util;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SendGridUtil {

    private final SendGrid sendGrid;

    // Sendgrid 공식 가이드 참고
    // https://github.com/sendgrid/sendgrid-java
    public void sendEmail() throws IOException {
        Email from = new Email("kmicety1@gmail.com");
        String subject = "Sending with Twilio SendGrid is Fun";
        Email to = new Email("kmicety1@gmail.com");
        Content content = new Content("text/plain", "and easy to learn");

        Mail mail = new Mail(from, subject, to, content);
        Email email = new Email("yelnets99@naver.com");
        mail.personalization.get(0).addTo(email);

        send(mail);
    }

    private void send(Mail mail) throws IOException {
        sendGrid.addRequestHeader("X-Mock", "true");

        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());

        Response response = sendGrid.api(request);
        log.info("SendGrid Response: {}", response.getStatusCode());
        log.info("SendGrid Response: {}", response.getBody());
        log.info("SendGrid Response: {}", response.getHeaders());
    }
}
