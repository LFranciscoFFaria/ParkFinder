package pt.uminho.di.aa.parkfinder.api.DTOs.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeSerializer extends JsonSerializer<LocalTime> {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public void serialize(LocalTime localTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (localTime == null)
            jsonGenerator.writeNull();
        else
            jsonGenerator.writeString(dateTimeFormatter.format(localTime));
    }
}
