package by.it_academy.jd2.finance.platform.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class CustomLocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

    public CustomLocalDateTimeDeserializer(Class<LocalDateTime> t) {
        super(t);
    }

    public CustomLocalDateTimeDeserializer() {
        this(null);
    }

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JacksonException {
        long epochTime = Long.parseLong(jsonParser.getText());
        Instant instant = Instant.ofEpochMilli(epochTime);
        return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
    }
}
