package pt.uminho.di.aa.parkfinder.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.uminho.di.aa.parkfinder.api.DTOs.CondutorDTO;
import pt.uminho.di.aa.parkfinder.api.DTOs.CondutorEditDTO;
import pt.uminho.di.aa.parkfinder.api.auxiliar.ResponseEntityBadRequest;
import pt.uminho.di.aa.parkfinder.logicaReservas.Reserva;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaCondutores.Condutor;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaCondutores.CondutorServiceBean;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.UtilizadorServiceBean;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/apiV1/condutores")
public class CondutorAPI {

    private final UtilizadorServiceBean utilizadorServiceBean;
    private final CondutorServiceBean condutorServiceBean;

    @Autowired
    public CondutorAPI(UtilizadorServiceBean utilizadorServiceBean, CondutorServiceBean condutorServiceBean) {
        this.utilizadorServiceBean = utilizadorServiceBean;
        this.condutorServiceBean = condutorServiceBean;
    }

    @PutMapping
    public ResponseEntity<Void> criarCondutor(@RequestBody CondutorDTO dto){
        try{
            Condutor condutor = new Condutor(dto.getNome(), dto.getEmail(), dto.getPassword(), dto.getNif(), dto.isGenero(), dto.getNrTelemovel());
            utilizadorServiceBean.criarUtilizador(condutor);
            return new ResponseEntity<>(HttpStatus.OK); }
        catch (Exception e){
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/editarPerfil")
    public ResponseEntity<Boolean> editarPerfil(@RequestBody CondutorEditDTO c){
        try{ return new ResponseEntity<>(condutorServiceBean.editarPerfil(c), HttpStatus.OK); }
        catch (Exception e){
            return new ResponseEntityBadRequest<Boolean>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/minhasReservas")
    public ResponseEntity<List<Reserva>> listarMinhasReservas(){
        try{
            return new ResponseEntity<>(condutorServiceBean.listarMinhasReservas(), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<List<Reserva>>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/reserva/instantanea")
    public ResponseEntity<Reserva> fazerReservaInstantanea(@RequestParam("id_parque") int id_parque){
        try{
            return new ResponseEntity<>(condutorServiceBean.fazerReservaInstantanea(id_parque), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Reserva>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/reserva/agendada")
    public ResponseEntity<Reserva> fazerReservaAgendada(@RequestParam("id_parque") int id_parque, @RequestParam("tipo_lugar") String tipo, @RequestParam("data_inicio") LocalDateTime data_inicio, @RequestParam("data_fim") LocalDateTime data_fim){
        try{
            return new ResponseEntity<>(condutorServiceBean.fazerReservaAgendada(id_parque, tipo, data_inicio, data_fim), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Reserva>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/reserva/pagar")
    public ResponseEntity<Boolean> pagarReserva(@RequestParam("id_reserva") int id_reserva){
        try{
            return new ResponseEntity<>(condutorServiceBean.pagarReserva(id_reserva), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Boolean>().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(){
        try{
            condutorServiceBean.logout();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }
}
