package pt.uminho.di.aa.parkfinder.api;

import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.uminho.di.aa.parkfinder.api.DTOs.PrecarioDecLinearCriarDTO;
import pt.uminho.di.aa.parkfinder.api.DTOs.PrecarioLinearCriarDTO;
import pt.uminho.di.aa.parkfinder.api.auxiliar.ResponseEntityBadRequest;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Estatisticas;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Horario;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Parque;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios.Precario;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios.PrecarioDecrementoLinear;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios.PrecarioLinear;
import pt.uminho.di.aa.parkfinder.logicaParques.model.TipoLugarEstacionamento;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.GestorServiceBean;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model.Administrador;

import java.time.LocalTime;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/apiV1/gestores")
public class GestorAPI {
    private final GestorServiceBean gestorServiceBean;

    public GestorAPI(GestorServiceBean gestorServiceBean) {
        this.gestorServiceBean = gestorServiceBean;
    }

    @GetMapping("/estatisticas_parque")
    public ResponseEntity<Estatisticas> verEstatisticasParque(@RequestParam("id_parque") int id_parque){
        try{
            return new ResponseEntity<>(gestorServiceBean.verEstatisticasParque(id_parque), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Estatisticas>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/adicionar_precario/dec_linear")
    public ResponseEntity<Void> adicionarPrecario(@RequestParam("id_parque") int id_parque, @RequestBody PrecarioDecLinearCriarDTO precarioDTO){
        try{
            TipoLugarEstacionamento tipoLugar = new TipoLugarEstacionamento(precarioDTO.getTipo_lugar());
            Precario p = new PrecarioDecrementoLinear(tipoLugar, precarioDTO.getPreco_fixo(), precarioDTO.getPreco_max_por_intervalo(),
                    precarioDTO.getPreco_min_por_intervalo(), precarioDTO.getIntervalo(), precarioDTO.getIntervalo_ate_min());
            gestorServiceBean.adicionarPrecario(id_parque, tipoLugar, p);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/adicionar_precario/linear")
    public ResponseEntity<Void> adicionarPrecarioLinear(@RequestParam("id_parque") int id_parque, @RequestBody PrecarioLinearCriarDTO precarioDTO){
        try{
            TipoLugarEstacionamento tipoLugar = new TipoLugarEstacionamento(precarioDTO.getTipo_lugar());
            Precario precario = new PrecarioLinear(tipoLugar, precarioDTO.getPreco_fixo(),
                                                    precarioDTO.getPreco_por_intervalo(), precarioDTO.getIntervalo());
            gestorServiceBean.adicionarPrecario(id_parque, tipoLugar, precario);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/meus_parques")
    public ResponseEntity<List<Parque>> listarMeusParques(){
        try{
            return new ResponseEntity<>(gestorServiceBean.listarMeusParques(), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<List<Parque>>().createBadRequest(e.getMessage());
        }
    };

    @DeleteMapping("/remover_precario")
    public ResponseEntity<Void> removerPrecario(@RequestParam int id_parque, @RequestBody TipoLugarEstacionamento tipoLugar){
        try{
            gestorServiceBean.removerPrecario(id_parque, tipoLugar);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/meus_administradores")
    public ResponseEntity<List<Administrador>> listarMeusAdministradores(){
        try{
            return new ResponseEntity<>(gestorServiceBean.listarMeusAdministradores(), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<List<Administrador>>().createBadRequest(e.getMessage());
        }
    };

    @PutMapping("/criar_administrador")
    public ResponseEntity<Void> criarAdmin(@RequestBody Administrador a, @RequestBody List<Integer> ids_parques){
        try{
            gestorServiceBean.criarAdmin(a,ids_parques);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/remover_administrador")
    public ResponseEntity<Void> removerAdmin(@RequestParam int id_admin){
        try{
            gestorServiceBean.removerAdmin(id_admin);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/remover_permissao_administrador")
    public ResponseEntity<Void> removerPermissaoAdminSobreParques(@RequestParam int id_admin, @RequestBody List<Integer> ids_parques){
        try{
            gestorServiceBean.removerPermissaoAdminSobreParques(id_admin, ids_parques);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/alterar_parque")
    public ResponseEntity<Boolean> alterarInformacoesParque(@RequestParam int id_parque, @RequestBody Parque newInfo){
        try{
            return new ResponseEntity<>(gestorServiceBean.alterarInformacoesParque(id_parque,newInfo), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Boolean>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/alterar_disponibilidade_parque")
    public ResponseEntity<Void> alterarEstadoDisponivelDeParque(@RequestParam int id_parque, @RequestBody boolean disponivel){
        try{
            gestorServiceBean.alterarEstadoDisponivelDeParque(id_parque, disponivel);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/adicionar_permissao_administrador")
    public ResponseEntity<Void> adicionarParquesAAdmin(@RequestBody List<Integer> ids_parques, @RequestParam int id_admin){
        try{
            gestorServiceBean.adicionarParquesAAdmin(ids_parques, id_admin);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/adiciona_horario")
    public ResponseEntity<Boolean> criarOuAtualizarHorario(@RequestParam int id_parque, @RequestBody Horario horario){
        try{
            return new ResponseEntity<>(gestorServiceBean.criarOuAtualizarHorario(id_parque, horario), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Boolean>().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(){
        gestorServiceBean.logout();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
