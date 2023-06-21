package pt.uminho.di.aa.parkfinder.api;

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
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaCondutores.CondutorServiceBean;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.UtilizadorService;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.UtilizadorServiceBean;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/apiV1/condutores")
public class CondutorAPI {

    private final UtilizadorService utilizadorService;
    private final CondutorService condutorService;

    @Autowired
    public CondutorAPI(UtilizadorService utilizadorService, CondutorService condutorService) {
        this.utilizadorService = utilizadorService;
        this.condutorService = condutorService;
    }

    @PutMapping
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
    public ResponseEntity<Boolean> editarPerfil(@RequestBody CondutorEdit c){
        try{ return new ResponseEntity<>(condutorService.editarPerfil(c), HttpStatus.OK); }
        catch (Exception e){
            return new ResponseEntityBadRequest<Boolean>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/minhasReservas")
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
    public ResponseEntity<ReservaDTO> fazerReservaAgendada(@RequestParam("id_parque") int id_parque, @RequestParam("tipo_lugar") String tipo, @RequestParam("data_inicio") LocalDateTime data_inicio, @RequestParam("data_fim") LocalDateTime data_fim){
        try{
            Reserva r = condutorService.fazerReservaAgendada(id_parque, tipo, data_inicio, data_fim);
            return new ResponseEntity<>(reservaToDTO(r), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<ReservaDTO>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/reserva/instantanea/custo")
    public ResponseEntity<Float> calcularCustoReservaInstantanea(@RequestParam("id_reserva") int id_reserva){
        try{ return new ResponseEntity<>(condutorService.calculaCustoReservaInstantanea(id_reserva), HttpStatus.OK); }
        catch (Exception e) { return new ResponseEntityBadRequest<Float>().createBadRequest(e.getMessage()); }
    }

    @PutMapping("/reserva/pagar")
    public ResponseEntity<Boolean> pagarReserva(@RequestParam("id_reserva") int id_reserva){
        try{
            return new ResponseEntity<>(condutorService.pagarReserva(id_reserva), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Boolean>().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/logout")
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
        return new ReservaDTO(r.getId(), r.getUtilizadorID(), r.getParqueID(), tipo_lugar, r.getEstado(),
                r.getCusto(), r.isPago(), r.getMatricula(), r.getDataInicio(), r.getDataFim());
    }
}
