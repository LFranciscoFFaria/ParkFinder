package pt.uminho.di.aa.parkfinder.api;

import org.hibernate.LazyInitializationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.uminho.di.aa.parkfinder.api.DTOs.AdminDTO;
import pt.uminho.di.aa.parkfinder.api.DTOs.PrecarioDecLinearCriarDTO;
import pt.uminho.di.aa.parkfinder.api.DTOs.PrecarioLinearCriarDTO;
import pt.uminho.di.aa.parkfinder.api.auxiliar.ResponseEntityBadRequest;
import pt.uminho.di.aa.parkfinder.logicaParques.model.*;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios.Precario;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios.PrecarioDecrementoLinear;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios.PrecarioLinear;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.GestorServiceBean;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model.Administrador;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<ParqueDTO>> listarMeusParques(){
        try{
            List<Parque> parques = gestorServiceBean.listarMeusParques();
            List<ParqueDTO> parqueDTOS = parques.stream().map(this::parqueToDTO).toList();
            return new ResponseEntity<>(parqueDTOS, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<List<ParqueDTO>>().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/remover_precario")
    public ResponseEntity<Void> removerPrecario(@RequestParam("id_parque") int id_parque, @RequestParam("tipo_lugar") String tipo_lugar){
        try{
            gestorServiceBean.removerPrecario(id_parque, new TipoLugarEstacionamento(tipo_lugar));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/meus_administradores")
    public ResponseEntity<List<AdminDTO>> listarMeusAdministradores(){
        try{
            List<Administrador> administradores = gestorServiceBean.listarMeusAdministradores();
            List<AdminDTO> adminDTOS = administradores.stream().map(this::adminToDTO).toList();
            return new ResponseEntity<>(adminDTOS, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<List<AdminDTO>>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/criar_administrador")
    public ResponseEntity<Void> criarAdmin(@RequestBody AdminDTO a){
        try{
            gestorServiceBean.criarAdmin(a.getNome(), a.getEmail(), a.getPassword(), a.getNr_telemovel(), a.getIds_parques());
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
    public ResponseEntity<Boolean> alterarInformacoesParque(@RequestParam int id_parque, @RequestBody ParqueDTO newInfo){
        try{
            return new ResponseEntity<>(gestorServiceBean.alterarInformacoesParque(id_parque,newInfo), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Boolean>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/alterar_disponibilidade_parque")
    public ResponseEntity<Void> alterarEstadoDisponivelDeParque(@RequestParam int id_parque, @RequestParam boolean disponivel){
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
    public ResponseEntity<Void> logout() {
        try{
            gestorServiceBean.logout();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    private ParqueDTO parqueToDTO(Parque parque){
        if(parque == null) return null;
        return new ParqueDTO(Optional.of(parque.getId()), Optional.of(parque.getNome()), Optional.of(parque.getMorada()),
                Optional.of(parque.getDescricao()), Optional.of(parque.getLatitude()), Optional.of(parque.getLongitude()),
                Optional.of(parque.isDisponivel()), Optional.of(parque.getInstantaneos_livres()),
                Optional.of(parque.getInstantaneos_total()), Optional.of(parque.getTotal_lugares()),
                Optional.of(parque.getCaminho_foto()));
    }

    private AdminDTO adminToDTO(Administrador a){
        if(a == null) return null;
        List<Integer> ids_parques = null;

        //Se a load initialize exception for invocada, então ignora e envia uma lista a null.
        try{ ids_parques = a.getParques().stream().map(Parque::getId).toList(); }
        catch (LazyInitializationException ignored){}

        return new AdminDTO(a.getNome(), a.getEmail(), a.getNrTelemovel(), a.getPassword(), a.getGestorID(), ids_parques);
    }
}
