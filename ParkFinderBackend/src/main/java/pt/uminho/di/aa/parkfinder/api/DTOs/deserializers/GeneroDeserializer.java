package pt.uminho.di.aa.parkfinder.api.DTOs.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class GeneroDeserializer extends JsonDeserializer<Boolean> {
    @Override
    public Boolean deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        try {
            String genero = jsonParser.getText().toUpperCase();
            switch (genero){
                case "M" -> { return true; }
                case "F" -> { return false; }
                default -> throw new IOException("Género apenas pode ser 'M'(Masculino) ou 'F'(Feminino)");
            }
        }catch (IOException ioe){
            throw new IOException("Genero inválido.");
        }
    }
}