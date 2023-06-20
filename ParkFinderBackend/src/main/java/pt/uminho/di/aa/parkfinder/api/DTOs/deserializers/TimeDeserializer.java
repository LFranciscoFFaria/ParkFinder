package pt.uminho.di.aa.parkfinder.api.DTOs.deserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeDeserializer extends JsonDeserializer<LocalTime> {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    @Override
    public LocalTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        try{
            return LocalTime.parse(jsonParser.getText(), dateTimeFormatter);
        }catch (Exception e){
            throw new IOException("Formato de hora errado. Formato: 'HH:mm'. Exemplo: '21:32'.");
        }
    }
}
