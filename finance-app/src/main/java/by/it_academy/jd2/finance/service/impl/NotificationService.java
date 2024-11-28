package by.it_academy.jd2.finance.service.impl;

import by.it_academy.jd2.finance.service.INotificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements INotificationService {

    private final JavaMailSender mailSender;
    private final String from;

    public NotificationService(@Value("${spring.mail.username}") String from, JavaMailSender mailSender) {
        this.from = from;
        this.mailSender = mailSender;
    }

    @Override
    public void sendMessage(String mailTo, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(mailTo);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
