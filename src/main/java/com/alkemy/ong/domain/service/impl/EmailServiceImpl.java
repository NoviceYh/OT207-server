package com.alkemy.ong.domain.service.impl;

import com.alkemy.ong.domain.service.IEmailService;
import com.alkemy.ong.enums.EmailConstants;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class EmailServiceImpl implements IEmailService {

    @Value("${app.sendgrid.key}")
    private String apikey;

    @Value("${from.email}")
    private String fromEmail;

    @Value("${template.id}")
    private String templateId;

    @Override
    public void sendEmail(String email, String subject, String contentValue) {

        Email from = new Email(fromEmail);
        Email to = new Email(email);
        Content content = new Content("text/plain", contentValue);
        Mail mail = new Mail(from, subject, to, content);

        sendRequest(mail);
    }

    @Override
    public void sendWelcomeEmail(String email) {
        EmailConstants constant = EmailConstants.NEW_USER_WELCOME;
        Mail mail = prepareMail(email, constant);

        sendRequest(mail);
    }

    @Override
    public void sendThankForContactingEmail(String email) {
        EmailConstants constant = EmailConstants.NEW_CONTACT_WELCOME;
        Mail mail = prepareMail(email, constant);

        sendRequest(mail);
    }

    private void sendRequest(Mail mail) {
        SendGrid sg = new SendGrid(apikey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            sg.api(request);
        } catch (IOException ex) {
            log.error("Error sending email", ex);
            throw new RuntimeException("Error sending email");
        }
    }

    private Mail prepareMail(String email, EmailConstants constant) {

        Mail mail = new Mail();

        Email from = new Email();
        from.setEmail(fromEmail);

        mail.setFrom(from);

        Email to = new Email();
        to.setEmail(email);

        Personalization personalization = new Personalization();
        personalization.addTo(to);
        switch (constant) {
            case NEW_USER_WELCOME:
                personalization.addDynamicTemplateData("welcome", EmailConstants.NEW_USER_WELCOME.getMsg());
                personalization.addDynamicTemplateData("body", EmailConstants.NEW_USER_BODY.getMsg());
                break;
            case NEW_CONTACT_WELCOME:
                personalization.addDynamicTemplateData("welcome", EmailConstants.NEW_CONTACT_WELCOME.getMsg());
                personalization.addDynamicTemplateData("body", EmailConstants.NEW_CONTACT_BODY.getMsg());
                break;
        }
        List<String> contacts = Arrays.asList("Mail: somosfundacionmas@gmail.com", "Instagram: SomosMás", "Facebook: Somos_Más", "Teléfono de contacto: 1160112988");
        personalization.addDynamicTemplateData("contacts", contacts);
        mail.addPersonalization(personalization);
        
        mail.setTemplateId(templateId);

        return mail;
    }
}
