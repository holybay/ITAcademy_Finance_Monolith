package by.it_academy.jd2.finance.service;

public interface INotificationService {

    void sendMessage(String mailTo, String subject, String body);
}
