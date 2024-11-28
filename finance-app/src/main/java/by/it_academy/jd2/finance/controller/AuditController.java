package by.it_academy.jd2.finance.controller;

import by.it_academy.jd2.finance.service.IAuditService;
import by.it_academy.jd2.finance.service.dto.auditUnit.AuditUnitOutDto;
import by.it_academy.jd2.finance.service.dto.page.PageDto;
import by.it_academy.jd2.finance.service.dto.page.PageOf;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/audit")
public class AuditController {

    private static final String PATH_VAR_ACCOUNT_ID = "uuid";
    private final IAuditService auditService;

    public AuditController(IAuditService auditService) {
        this.auditService = auditService;
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<AuditUnitOutDto> getById(@PathVariable(PATH_VAR_ACCOUNT_ID) UUID id) {
        return new ResponseEntity<>(auditService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PageOf<AuditUnitOutDto>> getAll(PageDto pageDto) {
        return new ResponseEntity<>(auditService.getAll(pageDto), HttpStatus.OK);
    }
}
