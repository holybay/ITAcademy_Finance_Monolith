package by.it_academy.jd2.finance.service.impl;

import by.it_academy.jd2.finance.repository.IUserRepository;
import by.it_academy.jd2.finance.repository.entity.user.User;
import by.it_academy.jd2.finance.service.IUserRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class UserRecordService implements IUserRecordService {

    private final IUserRepository userRepository;

    public UserRecordService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Haven't found a user with id: " + id));
    }
}
