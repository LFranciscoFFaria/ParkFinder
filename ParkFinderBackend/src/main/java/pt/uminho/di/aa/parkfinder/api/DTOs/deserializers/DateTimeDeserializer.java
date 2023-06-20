package pt.uminho.di.aa.parkfinder.api.DTOs.deserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        try{
            return LocalDateTime.parse(jsonParser.getText(), dateTimeFormatter);
        }catch (Exception e){
            throw new IOException("Formato de data e hora errado. Formato: 'yyyy-MM-dd HH:mm'. Exemplo: '2023-06-20 21:32'.");
        }
    }
}
