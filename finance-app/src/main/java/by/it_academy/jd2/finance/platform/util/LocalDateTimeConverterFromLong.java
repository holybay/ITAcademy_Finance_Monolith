package by.it_academy.jd2.finance.platform.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class LocalDateTimeConverterFromLong implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String source) {
        long epochTime = Long.parseLong(source);
        Instant instant = Instant.ofEpochMilli(epochTime);
        return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
    }
}
