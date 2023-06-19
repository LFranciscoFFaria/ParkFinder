package pt.uminho.di.aa.parkfinder.api;

import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.uminho.di.aa.parkfinder.api.auxiliar.ResponseEntityBadRequest;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Estatisticas;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Horario;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Parque;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios.Precario;
import pt.uminho.di.aa.parkfinder.logicaParques.model.TipoLugarEstacionamento;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.GestorServiceBean;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model.Administrador;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/apiV1/gestores")
public class GestorAPI {

    private final ApplicationContext context;
    private final GestorServiceBean gestorServiceBean;

    public GestorAPI(ApplicationContext context, GestorServiceBean gestorServiceBean) {
        this.context = context;
        this.gestorServiceBean = gestorServiceBean;
    }

    @GetMapping("/estatisticas_parque")
    public ResponseEntity<Estatisticas> verEstatisticasParque(@RequestParam int id_parque){
        try{
            return new ResponseEntity<Estatisticas>(gestorServiceBean.verEstatisticasParque(id_parque), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Estatisticas>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/adicionar_precario")
    public ResponseEntity adicionarPrecario(@RequestParam int id_parque, @RequestBody TipoLugarEstacionamento tipoLugar, @RequestBody Precario precario){
        try{
            gestorServiceBean.adicionarPrecario(id_parque, tipoLugar, precario);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/meus_parques")
    public ResponseEntity<List<Parque>> listarMeusParques(){
        try{
            return new ResponseEntity<List<Parque>>(gestorServiceBean.listarMeusParques(), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<List<Parque>>().createBadRequest(e.getMessage());
        }
    };

    @DeleteMapping("/remover_precario")
    public ResponseEntity removerPrecario(@RequestParam int id_parque, @RequestBody TipoLugarEstacionamento tipoLugar){
        try{
            gestorServiceBean.removerPrecario(id_parque, tipoLugar);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/meus_administradores")
    public ResponseEntity<List<Administrador>> listarMeusAdministradores(){
        try{
            return new ResponseEntity<List<Administrador>>(gestorServiceBean.listarMeusAdministradores(), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<List<Administrador>>().createBadRequest(e.getMessage());
        }
    };

    @PutMapping("/criar_administrador")
    public ResponseEntity criarAdmin(@RequestBody Administrador a, @RequestBody List<Integer> ids_parques){
        try{
            gestorServiceBean.criarAdmin(a,ids_parques);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/remover_administrador")
    public ResponseEntity removerAdmin(@RequestParam int id_admin){
        try{
            gestorServiceBean.removerAdmin(id_admin);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/remover_permissao_administrador")
    public ResponseEntity removerPermissaoAdminSobreParques(@RequestParam int id_admin, @RequestBody List<Integer> ids_parques){
        try{
            gestorServiceBean.removerPermissaoAdminSobreParques(id_admin, ids_parques);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/alterar_parque")
    public ResponseEntity<Boolean> alterarInformacoesParque(@RequestParam int id_parque, @RequestBody Parque newInfo){
        try{
            return new ResponseEntity<Boolean>(gestorServiceBean.alterarInformacoesParque(id_parque,newInfo), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Boolean>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/alterar_disponibilidade_parque")
    public ResponseEntity alterarEstadoDisponivelDeParque(@RequestParam int id_parque, @RequestBody boolean disponivel){
        try{
            gestorServiceBean.alterarEstadoDisponivelDeParque(id_parque, disponivel);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/adicionar_permissao_administrador")
    public ResponseEntity adicionarParquesAAdmin(@RequestBody List<Integer> ids_parques, @RequestParam int id_admin){
        try{
            gestorServiceBean.adicionarParquesAAdmin(ids_parques, id_admin);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/adiciona_horario")
    public ResponseEntity<Boolean> criarOuAtualizarHorario(@RequestParam int id_parque, @RequestBody Horario horario){
        try{
            return new ResponseEntity<Boolean>(gestorServiceBean.criarOuAtualizarHorario(id_parque, horario), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Boolean>().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/logout")
    public ResponseEntity logout(){
        gestorServiceBean.logout();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
