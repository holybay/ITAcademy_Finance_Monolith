package by.it_academy.jd2.finance.service;

import by.it_academy.jd2.finance.repository.entity.user.User;

public interface IVerificationService {

    String generateVerificationLink(User user);

    boolean verify(String mail, int code);

}
