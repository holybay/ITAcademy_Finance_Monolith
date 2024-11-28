package by.it_academy.jd2.finance.service;

import by.it_academy.jd2.finance.repository.entity.user.User;

import java.util.UUID;

public interface IUserRecordService {

    User getById(UUID id);

}
