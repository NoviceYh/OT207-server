package com.alkemy.ong.domain.service;

public interface IEmailService {

    public void sendEmail(String email, String subject, String contentValue);

    public void sendWelcomeEmail(String email);

    public void sendThankForContactingEmail(String email);

}
