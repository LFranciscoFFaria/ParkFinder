package pt.uminho.di.aa.parkfinder.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.uminho.di.aa.parkfinder.api.auxiliar.LoginRequest;
import pt.uminho.di.aa.parkfinder.api.auxiliar.ResponseEntityBadRequest;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaCondutores.CondutorServiceBean;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.AdministradorServiceBean;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.GestorServiceBean;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.ProgramadorServiceBean;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.Utilizador;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.UtilizadorServiceBean;

import java.util.List;

@RestController
@RequestMapping("/apiV1/utilizadores")
public class UtilizadorAPI {
    private final ApplicationContext context;
    private final UtilizadorServiceBean utilizadorServiceBean;

    @Autowired
    public UtilizadorAPI(ApplicationContext context, UtilizadorServiceBean utilizadorServiceBean) {
        this.context = context;
        this.utilizadorServiceBean = utilizadorServiceBean;
    }

    @GetMapping("/login")
    public ResponseEntity<Utilizador> login(@RequestBody LoginRequest loginRequest){
        try{
            Utilizador u = utilizadorServiceBean.login(loginRequest.getEmail(), loginRequest.getPassword());
            if (u == null)
                return new ResponseEntityBadRequest<Utilizador>().createBadRequest("Credenciais inválidas.");

            switch (u.getDiscriminator()) {
                case "Condutor" -> {
                    CondutorServiceBean condutorServiceBean = context.getBean(CondutorServiceBean.class);
                    condutorServiceBean.setCondutor(u);
                }
                case "Admin" -> {
                    AdministradorServiceBean administradorServiceBean = context.getBean(AdministradorServiceBean.class);
                    administradorServiceBean.setAdministrador(u);
                }
                case "Gestor" -> {
                    GestorServiceBean gestorServiceBean = context.getBean(GestorServiceBean.class);
                    gestorServiceBean.setGestor(u);
                }
                case "Programador" -> {
                    ProgramadorServiceBean programadorServiceBean = context.getBean(ProgramadorServiceBean.class);
                    programadorServiceBean.setProgramador(u);
                }
            }

            return new ResponseEntity<>(u, HttpStatus.OK); }
        catch (Exception e){
            return new ResponseEntityBadRequest<Utilizador>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/procurar")
    public ResponseEntity<List<Utilizador>> procurar(@RequestParam String nome){
        try{ return new ResponseEntity<>(utilizadorServiceBean.procurarUtilizador(nome, "Condutor"), HttpStatus.OK); }
        catch (Exception e){
            return new ResponseEntityBadRequest<List<Utilizador>>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/criar")
    //TODO: Verificar se este método vai ser utilizado
    public ResponseEntity<Utilizador> criarUtilizador(@RequestBody Utilizador utilizador){
        try{ return new ResponseEntity<>(utilizadorServiceBean.criarUtilizador(utilizador), HttpStatus.OK); }
        catch (Exception e){
            return new ResponseEntityBadRequest<Utilizador>().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/remover")
    //TODO: Verificar se este método vai ser utilizado
    public ResponseEntity<Boolean> removerUtilizador(@RequestParam("id_user") int id_user){
        try{ return new ResponseEntity<>(utilizadorServiceBean.removerUtilizador(id_user), HttpStatus.OK); }
        catch (Exception e){
            return new ResponseEntityBadRequest<Boolean>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Utilizador> atualizarUtilizador(@RequestBody Utilizador utilizador){
        try{ return new ResponseEntity<>(utilizadorServiceBean.atualizarUtilizador(utilizador), HttpStatus.OK); }
        catch (Exception e){
            return new ResponseEntityBadRequest<Utilizador>().createBadRequest(e.getMessage());
        }
    }


}
