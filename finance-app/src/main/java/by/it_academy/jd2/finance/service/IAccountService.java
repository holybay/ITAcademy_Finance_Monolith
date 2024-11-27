package by.it_academy.jd2.finance.service;


import by.it_academy.jd2.finance.service.dto.UpdateCoordinate;
import by.it_academy.jd2.finance.service.dto.account.AccountCreateDto;
import by.it_academy.jd2.finance.service.dto.account.AccountOutDto;
import by.it_academy.jd2.finance.service.dto.account.AccountUpdateDto;
import by.it_academy.jd2.finance.service.dto.page.PageDto;
import by.it_academy.jd2.finance.service.dto.page.PageOf;

import java.util.UUID;

public interface IAccountService {

    void create(AccountCreateDto createDto, String token);

    AccountOutDto getById(UUID id, String token);

    PageOf<AccountOutDto> getAll(PageDto pageDto, String token);

    void update(AccountUpdateDto updateDto, UpdateCoordinate coordinate, String token);
}
