package pt.uminho.di.aa.parkfinder.api;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.uminho.di.aa.parkfinder.api.DTOs.AdminDTO;
import pt.uminho.di.aa.parkfinder.api.DTOs.ProgramadorDTO;
import pt.uminho.di.aa.parkfinder.api.DTOs.ReservaDTO;
import pt.uminho.di.aa.parkfinder.api.auxiliar.ResponseEntityBadRequest;
import pt.uminho.di.aa.parkfinder.logicaParques.model.LugarEstacionamento;
import pt.uminho.di.aa.parkfinder.logicaParques.model.TipoLugarEstacionamento;
import pt.uminho.di.aa.parkfinder.logicaReservas.Reserva;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.AdministradorService;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model.Administrador;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model.Programador;

import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
@RequestMapping("/apiV1/administradores")
public class AdministradorAPI {

    private final AdministradorService administradorService;

    public AdministradorAPI(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    @PutMapping("/lugares/instantaneo/adicionar")
    @Operation(summary = "Adiciona lugares instantâneos ao parque")
    public ResponseEntity<Void> addLugarInstantaneo(@RequestParam("id_parque") int id_parque, @RequestParam("numero") int N) {
        try{
            administradorService.addLugarInstantaneo(id_parque,N);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/lugares/especial/adicionar")
    @Operation(summary = "Adiciona lugares especiais ao parque")
    public ResponseEntity<Void> addLugarEspecial(@RequestParam("id_parque") int id_parque, @RequestParam("numero") int N, @RequestParam("tipo_lugar") String tipo) {
        try{
            administradorService.addLugarEspecial(id_parque,N,new TipoLugarEstacionamento(tipo));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/lugares/instantaneo/remover")
    @Operation(summary = "Remove lugares instantâneos do parque")
    public ResponseEntity<Void> removerLugarInstantaneo(@RequestParam("id_parque") int id_parque, @RequestParam("numero") int N) {
        try{
            administradorService.removerLugarInstantaneo(id_parque,N);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/lugares/especial/remover")
    @Operation(summary = "Remover lugares especiais do parque")
    public ResponseEntity<Void> removerLugarEspecial(@RequestParam("id_parque") int id_parque, @RequestParam("numero") int N, @RequestParam("tipo_lugar") String tipo) {
        try{
            administradorService.removerLugarEspecial(id_parque, N, new TipoLugarEstacionamento(tipo));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/reservas/encontrar")
    @Operation(summary = "Encontra reserva ativa no parque pela matrícula do carro")
    public ResponseEntity<ReservaDTO> encontrarReservaPorMatricula(@RequestParam("id_parque") int id_parque, @RequestParam("matricula") String matricula) {
        try{
            Reserva r = administradorService.encontrarReservaPorMatricula(id_parque, matricula);
            return new ResponseEntity<>(reservaToDTO(r),HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<ReservaDTO>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/reservas/ativas")
    @Operation(summary = "Apresenta a lista de reservas ativas do parque")
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

    @PutMapping("/reservas/marcar/entrada")
    @Operation(summary = "Efetua a entrada num parque")
    public ResponseEntity<Void> marcarEntradaParque(@RequestParam("id_reserva") int id_reserva, @RequestParam("matricula") String matricula){
        try{
            administradorService.marcarEntradaParque(id_reserva, matricula);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/reservas/marcar/entrada_com_user")
    @Operation(summary = "Efetua a entrada no parque com o identificador do utilizador")
    public ResponseEntity<Void> criarReservaInstantaneaEMarcaEntrada(@RequestParam("id_utilizador") int idUtilizador,
                                                                     @RequestParam("id_parque") int idParque,
                                                                     @RequestParam("matricula") String matricula){
        try{
            administradorService.criarReservaInstantaneaEMarcaEntrada(idUtilizador, idParque, matricula);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/reservas/marcar/saida")
    @Operation(summary = "Efetua a saida de um parque")
    public ResponseEntity<Void> marcarSaidaParque(@RequestParam("id_reserva") int id_reserva){
        try{
            administradorService.marcarSaidaParque(id_reserva);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/perfil/info")
    public ResponseEntity<AdminDTO> getGestorInfo(){
        try{
            Administrador admin = administradorService.getAdministradorInfo();
            AdminDTO adminDTO = new AdminDTO(admin.getId(), admin.getNome(), admin.getEmail(), admin.getNrTelemovel(), admin.getPassword(), admin.getGestor().getId(), null);
            return new ResponseEntity<>(adminDTO, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<AdminDTO>().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/logout")
    @Operation(summary = "Efetua o logout do administrador")
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
        return new ReservaDTO(r.getId(), r.getUtilizador().getId(), r.getParque().getId(), tipo_lugar, r.getEstado(),
                r.getCusto(), r.isPago(), r.getMatricula(), r.getDataInicio(), r.getDataFim());
    }
}
