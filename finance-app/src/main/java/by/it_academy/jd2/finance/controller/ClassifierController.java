package by.it_academy.jd2.finance.controller;

import by.it_academy.jd2.finance.service.ICurrencyService;
import by.it_academy.jd2.finance.service.IOperationCategoryService;
import by.it_academy.jd2.finance.service.dto.CurrencyDto;
import by.it_academy.jd2.finance.service.dto.OperationCategoryDto;
import by.it_academy.jd2.finance.service.dto.page.PageDto;
import by.it_academy.jd2.finance.service.dto.page.PageOf;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/classifier")
public class ClassifierController {

    private final ICurrencyService currencyService;
    private final IOperationCategoryService categoryService;

    public ClassifierController(ICurrencyService currencyService, IOperationCategoryService categoryService) {
        this.currencyService = currencyService;
        this.categoryService = categoryService;
    }

    @PostMapping("/currency")
    public ResponseEntity<HttpStatus> createCurrency(@RequestBody @Valid CurrencyDto createDto) {
        currencyService.create(createDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/currency")
    public ResponseEntity<PageOf<CurrencyDto>> getAllCurrencies(PageDto pageDto) {
        PageOf<CurrencyDto> users = currencyService.getAll(pageDto);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/operation/category")
    public ResponseEntity<HttpStatus> createCategory(@RequestBody @Valid OperationCategoryDto createDto) {
        categoryService.create(createDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/operation/category")
    public ResponseEntity<PageOf<OperationCategoryDto>> getAllCategories(PageDto pageDto) {
        PageOf<OperationCategoryDto> users = categoryService.getAll(pageDto);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}