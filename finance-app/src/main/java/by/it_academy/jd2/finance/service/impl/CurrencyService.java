package by.it_academy.jd2.finance.service.impl;

import by.it_academy.jd2.finance.config.property.PageProperty;
import by.it_academy.jd2.finance.platform.util.PageUtil;
import by.it_academy.jd2.finance.repository.ICurrencyRepository;
import by.it_academy.jd2.finance.repository.entity.Currency;
import by.it_academy.jd2.finance.service.ICurrencyService;
import by.it_academy.jd2.finance.service.dto.classifier.CurrencyDto;
import by.it_academy.jd2.finance.service.dto.page.PageDto;
import by.it_academy.jd2.finance.service.dto.page.PageOf;
import by.it_academy.jd2.finance.service.mapper.ClassifierMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CurrencyService implements ICurrencyService {

    private final ICurrencyRepository currencyRepository;
    private final ClassifierMapper mapper;
    private final PageProperty pageProperty;

    public CurrencyService(ICurrencyRepository currencyRepository, ClassifierMapper mapper, PageProperty pageProperty) {
        this.currencyRepository = currencyRepository;
        this.mapper = mapper;
        this.pageProperty = pageProperty;
    }

    @Override
    public void create(CurrencyDto dto) {
        currencyRepository.saveAndFlush(mapper.toEntity(dto));
    }

    @Override
    @Transactional(readOnly = true)
    public PageOf<CurrencyDto> getAll(PageDto pageDto) {
        Sort.TypedSort<Currency> currency = Sort.sort(Currency.class);
        Sort sort = currency.by(Currency::getCreatedAt).descending().and(currency.by(Currency::getTitle).ascending());
        PageRequest pageReq = PageUtil.getPageRequest(pageDto.getPage(), pageDto.getSize(), pageProperty,
                currencyRepository.count(), sort);
        Page<CurrencyDto> currencyPage = currencyRepository.findAll(pageReq)
                                                           .map(mapper::toCategoryDto);
        return PageOf.convert(currencyPage);
    }
}
