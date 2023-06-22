package pt.uminho.di.aa.parkfinder.api;

import io.swagger.v3.oas.annotations.Operation;
import org.hibernate.LazyInitializationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.uminho.di.aa.parkfinder.api.DTOs.*;
import pt.uminho.di.aa.parkfinder.api.auxiliar.ResponseEntityBadRequest;
import pt.uminho.di.aa.parkfinder.logicaParques.DTOs.ParqueDTO;
import pt.uminho.di.aa.parkfinder.logicaParques.model.*;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios.Precario;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios.PrecarioDecrementoLinear;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios.PrecarioLinear;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaCondutores.Condutor;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.GestorService;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model.Administrador;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model.Gestor;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/apiV1/gestores")
public class GestorAPI {
    private final GestorService gestorService;

    public GestorAPI(GestorService gestorService) {
        this.gestorService = gestorService;
    }

    @GetMapping("/estatisticas_parque")
    @Operation(summary = "Mostra as estatíticas do parque especificado")
    public ResponseEntity<Estatisticas> verEstatisticasParque(@RequestParam("id_parque") int id_parque){
        try{
            return new ResponseEntity<>(gestorService.verEstatisticasParque(id_parque), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Estatisticas>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/adicionar_precario/dec_linear")
    @Operation(summary = "Adiciona um precario com decremento linear ao parque")
    public ResponseEntity<Void> adicionarPrecarioDecLinear(@RequestParam("id_parque") int id_parque, @RequestBody PrecarioDecLinearCriarDTO precarioDTO){
        try{
            TipoLugarEstacionamento tipoLugar = new TipoLugarEstacionamento(precarioDTO.getTipo_lugar());
            Precario p = new PrecarioDecrementoLinear(tipoLugar, precarioDTO.getPreco_fixo(), precarioDTO.getPreco_max_por_intervalo(),
                    precarioDTO.getPreco_min_por_intervalo(), precarioDTO.getIntervalo(), precarioDTO.getIntervalo_ate_min());
            gestorService.adicionarPrecario(id_parque, tipoLugar, p);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/adicionar_precario/linear")
    @Operation(summary = "Adiciona um precario linear ao parque")
    public ResponseEntity<Void> adicionarPrecarioLinear(@RequestParam("id_parque") int id_parque, @RequestBody PrecarioLinearCriarDTO precarioDTO){
        try{
            TipoLugarEstacionamento tipoLugar = new TipoLugarEstacionamento(precarioDTO.getTipo_lugar());
            Precario precario = new PrecarioLinear(tipoLugar, precarioDTO.getPreco_fixo(),
                                                    precarioDTO.getPreco_por_intervalo(), precarioDTO.getIntervalo());
            gestorService.adicionarPrecario(id_parque, tipoLugar, precario);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/meus_parques")
    @Operation(summary = "Apresenta a lista de parques associados ao gestor")
    public ResponseEntity<List<ParqueDTO>> listarMeusParques(){
        try{
            List<Parque> parques = gestorService.listarMeusParques();
            List<ParqueDTO> parqueDTOS = parques.stream().map(this::parqueToDTO).toList();
            return new ResponseEntity<>(parqueDTOS, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<List<ParqueDTO>>().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/remover_precario")
    @Operation(summary = "Remove um precario do parque")
    public ResponseEntity<Void> removerPrecario(@RequestParam("id_parque") int id_parque, @RequestParam("tipo_lugar") String tipo_lugar){
        try{
            gestorService.removerPrecario(id_parque, new TipoLugarEstacionamento(tipo_lugar));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/meus_administradores")
    @Operation(summary = "Apresenta a lista de administradores associados ao gestor")
    public ResponseEntity<List<AdminDTO>> listarMeusAdministradores(){
        try{
            List<Administrador> administradores = gestorService.listarMeusAdministradores();
            List<AdminDTO> adminDTOS = administradores.stream().map(this::adminToDTO).toList();
            return new ResponseEntity<>(adminDTOS, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<List<AdminDTO>>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/criar_administrador")
    @Operation(summary = "Cria um administrador no sistema")
    public ResponseEntity<Void> criarAdmin(@RequestBody AdminDTO a){
        try{
            gestorService.criarAdmin(a.getNome(), a.getEmail(), a.getPassword(), a.getNr_telemovel(), a.getIds_parques());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/remover_administrador")
    @Operation(summary = "Remove um administrador do sistema")
    public ResponseEntity<Void> removerAdmin(@RequestParam("id_admin") int id_admin){
        try{
            gestorService.removerAdmin(id_admin);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/remover_permissao_administrador")
    @Operation(summary = "Remove parques do administrador")
    public ResponseEntity<Void> removerPermissaoAdminSobreParques(@RequestParam("id_admin") int id_admin, @RequestParam("ids_parques") List<Integer> ids_parques){
        try{
            gestorService.removerPermissaoAdminSobreParques(id_admin, ids_parques);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/alterar_parque")
    @Operation(summary = "Altera a informação do parque")
    public ResponseEntity<Boolean> alterarInformacoesParque(@RequestParam("id_parque") int id_parque, @RequestBody ParqueDTO newInfo){
        try{
            return new ResponseEntity<>(gestorService.alterarInformacoesParque(id_parque,newInfo), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Boolean>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/alterar_disponibilidade_parque")
    @Operation(summary = "Altera a disponibilidade do parque")
    public ResponseEntity<Void> alterarEstadoDisponivelDeParque(@RequestParam("id_parque") int id_parque, @RequestParam("disponivel") boolean disponivel){
        try{
            gestorService.alterarEstadoDisponivelDeParque(id_parque, disponivel);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/adicionar_permissao_administrador")
    @Operation(summary = "Adiciona parques ao administrador")
    public ResponseEntity<Void> adicionarParquesAAdmin(@RequestBody List<Integer> ids_parques, @RequestParam int id_admin){
        try{
            gestorService.adicionarParquesAAdmin(ids_parques, id_admin);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/adiciona_horario")
    @Operation(summary = "Adiciona um horario ao parque especificado")
    public ResponseEntity<Boolean> criarOuAtualizarHorario(@RequestParam int id_parque, @RequestBody Horario horario){
        try{
            return new ResponseEntity<>(gestorService.criarOuAtualizarHorario(id_parque, horario), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Boolean>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/perfil/info")
    public ResponseEntity<GestorDTO> getGestorInfo(){
        try{
            Gestor gestor = gestorService.getGestorInfo();
            GestorDTO gestorDTO = new GestorDTO(gestor.getId(), gestor.getNome(), gestor.getEmail(), gestor.getNrTelemovel(),
                                                gestor.getPassword(), null, null);
            return new ResponseEntity<>(gestorDTO, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<GestorDTO>().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/logout")
    @Operation(summary = "Efetua o logout do gestor")
    public ResponseEntity<Void> logout() {
        try{
            gestorService.logout();
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

        return new AdminDTO(a.getId(), a.getNome(), a.getEmail(), a.getNrTelemovel(), a.getPassword(), a.getGestorID(), ids_parques);
    }
}
