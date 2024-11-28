package by.it_academy.jd2.finance.config;

import by.it_academy.jd2.finance.config.property.MailProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Properties;

@Configuration
public class ServiceConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JavaMailSender javaMailSender(MailProperty property) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(property.getHost());
        mailSender.setPort(property.getPort());
        mailSender.setUsername(property.getUsername());
        mailSender.setPassword(property.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", property.getProtocol());
        props.put("mail.smtp.auth", property.getSmtpAuth());
        props.put("mail.smtp.ssl.enable", property.getSslEnable());
        props.put("mail.debug", "true");
        return mailSender;
    }

}
