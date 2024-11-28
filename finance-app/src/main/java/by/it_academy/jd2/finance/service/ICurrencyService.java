package by.it_academy.jd2.finance.service;


import by.it_academy.jd2.finance.repository.entity.Currency;
import by.it_academy.jd2.finance.service.dto.classifier.CurrencyDto;
import by.it_academy.jd2.finance.service.dto.page.PageDto;
import by.it_academy.jd2.finance.service.dto.page.PageOf;

import java.util.UUID;

public interface ICurrencyService {

    void create(CurrencyDto dto, String token);

    Currency getById(UUID id);

    PageOf<CurrencyDto> getAll(PageDto pageDto);

}
