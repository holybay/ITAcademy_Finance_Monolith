package by.it_academy.jd2.finance.service.impl;

import by.it_academy.jd2.finance.config.property.PageProperty;
import by.it_academy.jd2.finance.platform.util.PageUtil;
import by.it_academy.jd2.finance.repository.IAuditUnitRepository;
import by.it_academy.jd2.finance.repository.entity.auditUnit.AuditUnit;
import by.it_academy.jd2.finance.repository.entity.user.User;
import by.it_academy.jd2.finance.service.IAuditService;
import by.it_academy.jd2.finance.service.IUserRecordService;
import by.it_academy.jd2.finance.service.dto.auditUnit.AuditUnitCreateDto;
import by.it_academy.jd2.finance.service.dto.auditUnit.AuditUnitOutDto;
import by.it_academy.jd2.finance.service.dto.page.PageDto;
import by.it_academy.jd2.finance.service.dto.page.PageOf;
import by.it_academy.jd2.finance.service.mapper.AuditUnitMapper;
import by.it_academy.jd2.finance.service.mapper.UserMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class AuditService implements IAuditService {

    private final IAuditUnitRepository auditRepository;
    private final IUserRecordService userRecordService;
    private final AuditUnitMapper auditMapper;
    private final UserMapper userMapper;
    private final PageProperty pageProperty;

    public AuditService(IAuditUnitRepository auditRepository, IUserRecordService userRecordService,
                        AuditUnitMapper auditMapper, UserMapper userMapper, PageProperty pageProperty) {
        this.auditRepository = auditRepository;
        this.userRecordService = userRecordService;
        this.auditMapper = auditMapper;
        this.userMapper = userMapper;
        this.pageProperty = pageProperty;
    }

    @Override
    @Transactional
    public void create(AuditUnitCreateDto dto) {
        User user = userRecordService.getById(dto.getUserId());
        auditRepository.saveAndFlush(auditMapper.toEntity(dto, user));
    }

    @Override
    @Transactional(readOnly = true)
    public AuditUnitOutDto getById(UUID id) {
        return auditRepository.findById(id)
                              .map(e -> auditMapper.toOutDto(
                                      e, userMapper.toUserAuditOutDto(e.getUser())))
                              .orElseThrow(() ->
                                      new NoSuchElementException("There is no audit unit with id:" + id));
    }

    @Override
    @Transactional(readOnly = true)
    public PageOf<AuditUnitOutDto> getAll(PageDto pageDto) {
        Sort.TypedSort<AuditUnit> auditUnit = Sort.sort(AuditUnit.class);
        Sort sort = auditUnit.by(AuditUnit::getCreatedAt).descending();
        PageRequest pageReq = PageUtil.getPageRequest(pageDto.getPage(), pageDto.getSize(), pageProperty,
                auditRepository.count(), sort);
        Page<AuditUnitOutDto> auditPage = auditRepository.findAll(pageReq)
                                                         .map(e -> auditMapper.toOutDto(
                                                                 e, userMapper.toUserAuditOutDto(e.getUser())));
        return PageOf.convert(auditPage);
    }
}
