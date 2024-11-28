package by.it_academy.jd2.finance.controller;

import by.it_academy.jd2.finance.service.ICategoryService;
import by.it_academy.jd2.finance.service.ICurrencyService;
import by.it_academy.jd2.finance.service.dto.classifier.CategoryDto;
import by.it_academy.jd2.finance.service.dto.classifier.CurrencyDto;
import by.it_academy.jd2.finance.service.dto.page.PageDto;
import by.it_academy.jd2.finance.service.dto.page.PageOf;
import by.it_academy.jd2.finance.service.util.JwtTokenHandler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/classifier")
public class ClassifierController {

    private static final String AUTH_HEADER = "Authorization";
    private final ICurrencyService currencyService;
    private final ICategoryService categoryService;

    public ClassifierController(ICurrencyService currencyService, ICategoryService categoryService) {
        this.currencyService = currencyService;
        this.categoryService = categoryService;
    }

    @PostMapping("/currency")
    public ResponseEntity<HttpStatus> createCurrency(@RequestBody @Valid CurrencyDto createDto,
                                                     @RequestHeader(AUTH_HEADER) String header) {
        currencyService.create(createDto, JwtTokenHandler.getTokenFromHeader(header));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/currency")
    public ResponseEntity<PageOf<CurrencyDto>> getAllCurrencies(PageDto pageDto) {
        PageOf<CurrencyDto> users = currencyService.getAll(pageDto);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/operation/category")
    public ResponseEntity<HttpStatus> createCategory(@RequestBody @Valid CategoryDto createDto,
                                                     @RequestHeader(AUTH_HEADER) String header) {
        categoryService.create(createDto, JwtTokenHandler.getTokenFromHeader(header));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/operation/category")
    public ResponseEntity<PageOf<CategoryDto>> getAllCategories(PageDto pageDto) {
        PageOf<CategoryDto> users = categoryService.getAll(pageDto);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
