package pt.uminho.di.aa.parkfinder.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.uminho.di.aa.parkfinder.api.DTOs.ReservaDTO;
import pt.uminho.di.aa.parkfinder.api.auxiliar.ResponseEntityBadRequest;
import pt.uminho.di.aa.parkfinder.logicaParques.model.LugarEstacionamento;
import pt.uminho.di.aa.parkfinder.logicaParques.model.TipoLugarEstacionamento;
import pt.uminho.di.aa.parkfinder.logicaReservas.Reserva;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.AdministradorService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/apiV1/administradores")
public class AdministradorAPI {

    private final AdministradorService administradorService;

    public AdministradorAPI(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    @PutMapping("/adicionar_instantaneo")
    public ResponseEntity<Void> addLugarInstantaneo(@RequestParam("id_parque") int id_parque, @RequestParam("numero") int N) {
        try{
            administradorService.addLugarInstantaneo(id_parque,N);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/adicionar_especial")
    public ResponseEntity<Void> addLugarEspecial(@RequestParam("id_parque") int id_parque, @RequestParam("numero") int N, @RequestParam("tipo_lugar") String tipo) {
        try{
            administradorService.addLugarEspecial(id_parque,N,new TipoLugarEstacionamento(tipo));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/remover_instantaneo")
    public ResponseEntity<Void> removerLugarInstantaneo(@RequestParam("id_parque") int id_parque, @RequestParam("numero") int N) {
        try{
            administradorService.removerLugarInstantaneo(id_parque,N);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/remover_especial")
    public ResponseEntity<Void> removerLugarEspecial(@RequestParam("id_parque") int id_parque, @RequestParam("numero") int N, @RequestParam("tipo_lugar") String tipo) {
        try{
            administradorService.removerLugarEspecial(id_parque, N, new TipoLugarEstacionamento(tipo));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/encontrar_reserva")
    public ResponseEntity<ReservaDTO> encontrarReservaPorMatricula(@RequestParam("id_parque") int id_parque, @RequestParam("matricula") String matricula) {
        try{
            Reserva r = administradorService.encontrarReservaPorMatricula(id_parque, matricula);
            return new ResponseEntity<>(reservaToDTO(r),HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<ReservaDTO>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/associar_matricula_reserva")
    public ResponseEntity<Boolean> associarMatriculaAReserva(@RequestParam("id_reserva") int id_reserva, @RequestParam("matricula") String matricula) {
        try{
            return new ResponseEntity<>(administradorService.associarMatriculaAReserva(id_reserva, matricula),HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Boolean>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/reservas_ativas")
    public ResponseEntity<List<ReservaDTO>> verReservasAtivasDeParque(@RequestParam("id_parque") int id_parque){
        try{
            List<Reserva> reservas = administradorService.verReservasAtivasDeParque(id_parque);
            List<ReservaDTO> reservaDTOS = reservas.stream().map(this::reservaToDTO).toList();
            return new ResponseEntity<>(reservaDTOS,HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<List<ReservaDTO>>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/entrada")
    public ResponseEntity<Void> marcarEntradaParque(@RequestParam("id_reserva") int id_reserva, @RequestParam("matricula") String matricula){
        try{
            administradorService.marcarEntradaParque(id_reserva, matricula);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/saida")
    public ResponseEntity<Void> marcarSaidaParque(@RequestParam("id_reserva") int id_reserva){
        try{
            administradorService.marcarSaidaParque(id_reserva);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout() {
        try{
            administradorService.logout();
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
