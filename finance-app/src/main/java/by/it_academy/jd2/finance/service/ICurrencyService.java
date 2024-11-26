package by.it_academy.jd2.finance.service;


import by.it_academy.jd2.finance.service.dto.CurrencyDto;
import by.it_academy.jd2.finance.service.dto.page.PageDto;
import by.it_academy.jd2.finance.service.dto.page.PageOf;

public interface ICurrencyService {

    void create(CurrencyDto dto);

    PageOf<CurrencyDto> getAll(PageDto pageDto);

}
