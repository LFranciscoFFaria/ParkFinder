package pt.uminho.di.aa.parkfinder.api.DTOs.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import pt.uminho.di.aa.parkfinder.logicaParquesReservas.EstadoReserva;

import java.io.IOException;

public class ReservaEstadoSerializer extends JsonSerializer<Integer> {
    @Override
    public void serialize(Integer value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (value == null)
            jsonGenerator.writeNull();
        else {
            if (value == EstadoReserva.PENDENTE_PAGAMENTO) {
                jsonGenerator.writeString("Pagamento pendente");
            } else if (value == EstadoReserva.AGENDADA) {
                jsonGenerator.writeString("Agendada");
            } else if (value == EstadoReserva.OCUPADA) {
                jsonGenerator.writeString("Ocupada");
            } else if (value == EstadoReserva.CONCLUIDA) {
                jsonGenerator.writeString("Concluida");
            }
        }
    }
}
