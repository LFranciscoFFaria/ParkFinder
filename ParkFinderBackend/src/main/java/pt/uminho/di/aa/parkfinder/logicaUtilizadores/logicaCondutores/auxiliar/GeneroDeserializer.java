package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaCondutores.auxiliar;

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
            return "M".equals(genero);
        }catch (IOException ioe){
            throw new IOException("Genero inválido.");
        }
    }
}