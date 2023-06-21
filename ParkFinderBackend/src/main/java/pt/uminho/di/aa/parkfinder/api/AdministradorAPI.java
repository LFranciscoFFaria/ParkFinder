package pt.uminho.di.aa.parkfinder.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.uminho.di.aa.parkfinder.api.DTOs.ReservaDTO;
import pt.uminho.di.aa.parkfinder.api.auxiliar.ResponseEntityBadRequest;
import pt.uminho.di.aa.parkfinder.logicaParques.model.LugarEstacionamento;
import pt.uminho.di.aa.parkfinder.logicaParques.model.TipoLugarEstacionamento;
import pt.uminho.di.aa.parkfinder.logicaReservas.Reserva;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.AdministradorServiceBean;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/apiV1/administradores")
public class AdministradorAPI {

    private final AdministradorServiceBean administradorServiceBean;

    public AdministradorAPI(AdministradorServiceBean administradorServiceBean) {
        this.administradorServiceBean = administradorServiceBean;
    }

    @PutMapping("/adicionar_instantaneo")
    public ResponseEntity<Void> addLugarInstantaneo(@RequestParam("id_parque") int id_parque, @RequestParam("numero") int N) {
        try{
            administradorServiceBean.addLugarInstantaneo(id_parque,N);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/adicionar_especial")
    public ResponseEntity<Void> addLugarEspecial(@RequestParam("id_parque") int id_parque, @RequestParam("numero") int N, @RequestParam("tipo_lugar") String tipo) {
        try{
            administradorServiceBean.addLugarEspecial(id_parque,N,new TipoLugarEstacionamento(tipo));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/remover_instantaneo")
    public ResponseEntity<Void> removerLugarInstantaneo(@RequestParam("id_parque") int id_parque, @RequestParam("numero") int N) {
        try{
            administradorServiceBean.removerLugarInstantaneo(id_parque,N);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/remover_especial")
    public ResponseEntity<Void> removerLugarEspecial(@RequestParam("id_parque") int id_parque, @RequestParam("numero") int N, @RequestParam("tipo_lugar") String tipo) {
        try{
            administradorServiceBean.removerLugarEspecial(id_parque, N, new TipoLugarEstacionamento(tipo));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/encontrar_reserva")
    public ResponseEntity<ReservaDTO> encontrarReservaPorMatricula(@RequestParam("id_parque") int id_parque, @RequestParam("matricula") String matricula) {
        try{
            Reserva r = administradorServiceBean.encontrarReservaPorMatricula(id_parque, matricula);
            return new ResponseEntity<>(reservaToDTO(r),HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<ReservaDTO>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/associar_matricula_reserva")
    public ResponseEntity<Boolean> associarMatriculaAReserva(@RequestParam("id_reserva") int id_reserva, @RequestParam("matricula") String matricula) {
        try{
            return new ResponseEntity<>(administradorServiceBean.associarMatriculaAReserva(id_reserva, matricula),HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Boolean>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/reservas_ativas")
    public ResponseEntity<List<ReservaDTO>> verReservasAtivasDeParque(@RequestParam("id_parque") int id_parque){
        try{
            List<Reserva> reservas = administradorServiceBean.verReservasAtivasDeParque(id_parque);
            List<ReservaDTO> reservaDTOS = reservas.stream().map(this::reservaToDTO).toList();
            return new ResponseEntity<>(reservaDTOS,HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<List<ReservaDTO>>().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout() {
        try{
            administradorServiceBean.logout();
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
