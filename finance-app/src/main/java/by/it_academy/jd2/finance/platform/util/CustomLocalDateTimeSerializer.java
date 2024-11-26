package by.it_academy.jd2.finance.platform.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class CustomLocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

    public CustomLocalDateTimeSerializer(Class<LocalDateTime> t) {
        super(t);
    }

    public CustomLocalDateTimeSerializer() {
        this(null);
    }

    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli());
    }
}
