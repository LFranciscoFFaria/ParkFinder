package pt.uminho.di.aa.parkfinder.api;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.uminho.di.aa.parkfinder.api.DTOs.CondutorDTO;
import pt.uminho.di.aa.parkfinder.logicaParques.model.LugarEstacionamento;
import pt.uminho.di.aa.parkfinder.logicaReservas.Reserva;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaCondutores.CondutorEdit;
import pt.uminho.di.aa.parkfinder.api.DTOs.ReservaDTO;
import pt.uminho.di.aa.parkfinder.api.auxiliar.ResponseEntityBadRequest;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaCondutores.Condutor;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaCondutores.CondutorService;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.UtilizadorService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/apiV1/condutores")
public class CondutorAPI {

    private final UtilizadorService utilizadorService;
    private final CondutorService condutorService;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Autowired
    public CondutorAPI(UtilizadorService utilizadorService, CondutorService condutorService) {
        this.utilizadorService = utilizadorService;
        this.condutorService = condutorService;
    }

    @PutMapping
    @Operation(summary = "Registo do condutor")
    public ResponseEntity<Void> criarCondutor(@RequestBody CondutorDTO dto){
        try{
            Condutor condutor = new Condutor(dto.getNome(), dto.getEmail(), dto.getPassword(), dto.getNif(), dto.isGenero(), dto.getNr_telemovel());
            utilizadorService.criarUtilizador(condutor);
            return new ResponseEntity<>(HttpStatus.OK); }
        catch (Exception e){
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/editarPerfil")
    @Operation(summary = "Edição das informações do perfil")
    public ResponseEntity<Boolean> editarPerfil(@RequestBody CondutorEdit c){
        try{ return new ResponseEntity<>(condutorService.editarPerfil(c), HttpStatus.OK); }
        catch (Exception e){
            return new ResponseEntityBadRequest<Boolean>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/minhasReservas")
    @Operation(summary = "Mostra as reservas do condutor")
    public ResponseEntity<List<ReservaDTO>> listarMinhasReservas(){
        try{
            List<Reserva> reservas = condutorService.listarMinhasReservas();
            List<ReservaDTO> reservaDTOS = reservas.stream().map(this::reservaToDTO).toList();
            return new ResponseEntity<>(reservaDTOS, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<List<ReservaDTO>>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/reserva/instantanea")
    @Operation(summary = "Faz uma reserva instantanea")
    public ResponseEntity<ReservaDTO> fazerReservaInstantanea(@RequestParam("id_parque") int id_parque){
        try{
            Reserva r = condutorService.fazerReservaInstantanea(id_parque);
            return new ResponseEntity<>(reservaToDTO(r), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<ReservaDTO>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/reserva/agendada")
    @Operation(summary = "Faz uma reserva agendada")
    public ResponseEntity<ReservaDTO> fazerReservaAgendada(@RequestParam("id_parque") int id_parque, @RequestParam("tipo_lugar") String tipo, @RequestParam("data_inicio") String data_inicio_s, @RequestParam("data_fim") String data_fim_s){
        try{
            LocalDateTime data_inicio = LocalDateTime.parse(data_inicio_s, dateTimeFormatter),
                          data_fim = LocalDateTime.parse(data_fim_s, dateTimeFormatter);
            Reserva r = condutorService.fazerReservaAgendada(id_parque, tipo, data_inicio, data_fim);
            return new ResponseEntity<>(reservaToDTO(r), HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntityBadRequest<ReservaDTO>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/reserva/instantanea/custo")
    @Operation(summary = "Calcula o custo de uma reserva instantanea")
    public ResponseEntity<Float> calcularCustoReservaInstantanea(@RequestParam("id_reserva") int id_reserva){
        try{ return new ResponseEntity<>(condutorService.calculaCustoReservaInstantanea(id_reserva), HttpStatus.OK); }
        catch (Exception e) { return new ResponseEntityBadRequest<Float>().createBadRequest(e.getMessage()); }
    }

    @PutMapping("/reserva/pagar")
    @Operation(summary = "Pagamento de uma reserva")
    public ResponseEntity<Boolean> pagarReserva(@RequestParam("id_reserva") int id_reserva){
        try{
            return new ResponseEntity<>(condutorService.pagarReserva(id_reserva), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Boolean>().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/logout")
    @Operation(summary = "Efetua o logout do condutor")
    public ResponseEntity<Void> logout(){
        try{
            condutorService.logout();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    private ReservaDTO reservaToDTO(Reserva r){
        if(r == null) return null;
        String tipo_lugar;
        LugarEstacionamento lugarEstacionamento = r.getLugar();
        if(lugarEstacionamento == null) tipo_lugar = "Instantaneo";
        else tipo_lugar = lugarEstacionamento.getTipo().getNome();
        System.out.println("Reserva: " + r);
        return new ReservaDTO(r.getId(), r.getUtilizador().getId(), r.getParque().getId(), tipo_lugar, r.getEstado(),
                r.getCusto(), r.isPago(), r.getMatricula(), r.getDataInicio(), r.getDataFim());
    }
}
