package by.it_academy.jd2.finance.service.impl;

import by.it_academy.jd2.finance.platform.util.ApplicationUtil;
import by.it_academy.jd2.finance.repository.IVerificationRepository;
import by.it_academy.jd2.finance.repository.entity.VerificationUnit;
import by.it_academy.jd2.finance.repository.entity.user.User;
import by.it_academy.jd2.finance.service.IVerificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.NoSuchElementException;

@Service
public class VerificationService implements IVerificationService {

    private static final int MIN_CODE_SIZE = 1;
    private static final int MAX_CODE_SIZE = 9999;
    private final IVerificationRepository verificationRepository;
    private final String url;

    public VerificationService(IVerificationRepository verificationRepository,
                               @Value("${application.verification.url}") String url) {
        this.verificationRepository = verificationRepository;
        this.url = url;
    }

    @Override
    @Transactional
    public String generateVerificationLink(User user) {
        int code = ApplicationUtil.generateCode(MIN_CODE_SIZE, MAX_CODE_SIZE);
        String link = UriComponentsBuilder.fromHttpUrl(url)
                                          .queryParam("mail", user.getMail())
                                          .queryParam("code", code)
                                          .build().toUriString();
        verificationRepository.saveAndFlush(VerificationUnit.builder()
                                                            .setUser(user)
                                                            .setCode(code)
                                                            .build());
        return String.format("Please activate your account following the attached link: %n %s", link);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean verify(String mail, int code) {
        VerificationUnit verificationUnit =
                verificationRepository.findByEmail(mail)
                                      .orElseThrow(() -> new NoSuchElementException("Haven't found a user with mail: "
                                              + mail));
        return verificationUnit.getUser().getMail().equals(mail) && verificationUnit.getCode().compareTo(code) == 0;
    }
}
