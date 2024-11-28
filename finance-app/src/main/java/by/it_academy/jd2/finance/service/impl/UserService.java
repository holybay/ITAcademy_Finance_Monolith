package by.it_academy.jd2.finance.service.impl;

import by.it_academy.jd2.finance.config.property.PageProperty;
import by.it_academy.jd2.finance.platform.util.PageUtil;
import by.it_academy.jd2.finance.repository.IUserRepository;
import by.it_academy.jd2.finance.repository.entity.auditUnit.EEssenceType;
import by.it_academy.jd2.finance.repository.entity.user.EUserRole;
import by.it_academy.jd2.finance.repository.entity.user.EUserStatus;
import by.it_academy.jd2.finance.repository.entity.user.User;
import by.it_academy.jd2.finance.service.IAuditService;
import by.it_academy.jd2.finance.service.IUserService;
import by.it_academy.jd2.finance.service.dto.UpdateCoordinate;
import by.it_academy.jd2.finance.service.dto.auditUnit.AuditUnitCreateDto;
import by.it_academy.jd2.finance.service.dto.page.PageDto;
import by.it_academy.jd2.finance.service.dto.user.UserCreateDto;
import by.it_academy.jd2.finance.service.dto.user.UserSelfCreateDto;
import by.it_academy.jd2.finance.service.dto.user.UserUpdateDto;
import by.it_academy.jd2.finance.service.mapper.UserMapper;
import by.it_academy.jd2.finance.service.util.JwtTokenHandler;
import by.it_academy.jd2.finance.service.validation.IUserValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final IUserValidator userValidator;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;
    private final PageProperty pageProperty;
    private final IAuditService auditService;
    private final JwtTokenHandler tokenHandler;

    public UserService(IUserRepository userRepository, IUserValidator userValidator, UserMapper userMapper,
                       PasswordEncoder encoder, PageProperty pageProperty, IAuditService auditService, JwtTokenHandler tokenHandler) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
        this.userMapper = userMapper;
        this.encoder = encoder;
        this.pageProperty = pageProperty;
        this.auditService = auditService;
        this.tokenHandler = tokenHandler;
    }

    @Override
    public IUserValidator getUserValidator() {
        return userValidator;
    }

    @Override
    @Transactional
    public void create(UserCreateDto createDto, String token) {
        createDto.setId(UUID.randomUUID());
        createDto.setPassword(encoder.encode(createDto.getPassword()));
        userRepository.saveAndFlush(userMapper.toEntity(createDto));
        auditService.create(AuditUnitCreateDto.builder()
                                              .setUserId(tokenHandler.getTokenDto(token).getUserId())
                                              .setText("Created a new user!")
                                              .setType(EEssenceType.USER)
                                              .setEssenceTypeId(createDto.getId())
                                              .build());
    }

    @Override
    @Transactional
    public void create(UserSelfCreateDto createDto) {
        createDto.setId(UUID.randomUUID());
        createDto.setPassword(encoder.encode(createDto.getPassword()));
        createDto.setRole(EUserRole.USER.name());
        createDto.setStatus(EUserStatus.WAITING_ACTIVATION.name());
        userRepository.saveAndFlush(userMapper.toEntity(createDto));
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Haven't found a user with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public User getByMail(String mail) {
        return userRepository.findByMail(mail).orElseThrow(() -> new NoSuchElementException("Haven't found a user with mail: " + mail));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> getAll(PageDto pageDto) {
        PageUtil.validatePage(pageDto);
        Sort.TypedSort<User> user = Sort.sort(User.class);
        Sort sort = user.by(User::getCreatedAt).descending().and(user.by(User::getFio).ascending());
        PageRequest pageReq = PageUtil.getPageRequest(pageDto.getPage(), pageDto.getSize(), pageProperty, userRepository.count(), sort);
        return userRepository.findAll(pageReq);
    }

    @Override
    @Transactional
    public void update(UserUpdateDto updateDto, UpdateCoordinate coordinate) {
        updateDto.setPassword(encoder.encode(updateDto.getPassword()));
        User entity = userMapper.toEntity(updateDto, coordinate);
        userRepository.saveAndFlush(entity);
    }
}
