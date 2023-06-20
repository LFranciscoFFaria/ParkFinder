package pt.uminho.di.aa.parkfinder.api.DTOs.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class GeneroSerializer extends JsonSerializer<Boolean> {
    @Override
    public void serialize(Boolean genero, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (genero == null)
            jsonGenerator.writeNull();
        else if (genero) jsonGenerator.writeString("M");
        else jsonGenerator.writeString("F");
    }
}
