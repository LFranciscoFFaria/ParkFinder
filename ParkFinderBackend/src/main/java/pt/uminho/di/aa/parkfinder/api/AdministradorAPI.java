package pt.uminho.di.aa.parkfinder.api;

import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.uminho.di.aa.parkfinder.api.auxiliar.ResponseEntityBadRequest;
import pt.uminho.di.aa.parkfinder.logicaParques.model.TipoLugarEstacionamento;
import pt.uminho.di.aa.parkfinder.logicaReservas.Reserva;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.AdministradorServiceBean;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/apiV1/administradores")
public class AdministradorAPI {

    private final ApplicationContext context;
    private final AdministradorServiceBean administradorServiceBean;

    public AdministradorAPI(ApplicationContext context, AdministradorServiceBean administradorServiceBean) {
        this.context = context;
        this.administradorServiceBean = administradorServiceBean;
    }

    @PutMapping("/adicionar_instantaneo")
    public ResponseEntity addLugarInstantaneo(@RequestParam int id_parque,@RequestParam int N) {
        try{
            administradorServiceBean.addLugarInstantaneo(id_parque,N);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/adicionar_especial")
    public ResponseEntity addLugarEspecial(@RequestParam int id_parque,@RequestParam int N,@RequestBody TipoLugarEstacionamento tipo) {
        try{
            administradorServiceBean.addLugarEspecial(id_parque,N,tipo);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/remover_instantaneo")
    public ResponseEntity removerLugarInstantaneo(@RequestParam int id_parque, @RequestParam int N) {
        try{
            administradorServiceBean.removerLugarInstantaneo(id_parque,N);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/remover_especial")
    public ResponseEntity removerLugarEspecial(@RequestParam int id_parque, @RequestParam int N, @RequestBody TipoLugarEstacionamento tipo) {
        try{
            administradorServiceBean.removerLugarEspecial(id_parque,N,tipo);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/encontrar_reserva")
    public ResponseEntity<Reserva> encontrarReservaPorMatricula(@RequestParam String matricula) {
        try{
            return new ResponseEntity<Reserva>(administradorServiceBean.encontrarReservaPorMatricula(matricula),HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Reserva>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/associar_matricula_reserva")
    public ResponseEntity<Boolean> associarMatriculaAReserva(@RequestParam int id_reserva,@RequestParam String matricula) {
        try{
            return new ResponseEntity<Boolean>(administradorServiceBean.associarMatriculaAReserva(id_reserva, matricula),HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Boolean>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/reservas_ativas")
    public ResponseEntity<List<Reserva>> verReservasAtivasDeParque(@RequestParam int id_parque){
        try{
            return new ResponseEntity<List<Reserva>>(administradorServiceBean.verReservasAtivasDeParque(id_parque),HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<List<Reserva>>().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/logout")
    public ResponseEntity logout(){
        administradorServiceBean.logout();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
