package pt.uminho.di.aa.parkfinder.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.uminho.di.aa.parkfinder.api.DTOs.LoginDTO;
import pt.uminho.di.aa.parkfinder.api.auxiliar.ResponseEntityBadRequest;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaCondutores.CondutorServiceBean;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.AdministradorServiceBean;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.GestorServiceBean;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.ProgramadorServiceBean;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.Utilizador;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.UtilizadorService;

@RestController
@RequestMapping("/apiV1/utilizadores")
public class UtilizadorAPI {
    private final ApplicationContext context;
    private final UtilizadorService utilizadorService;

    @Autowired
    public UtilizadorAPI(ApplicationContext context, UtilizadorService utilizadorService) {
        this.context = context;
        this.utilizadorService = utilizadorService;
    }

    @GetMapping("/login")
    public ResponseEntity<Utilizador> login(@RequestBody LoginDTO loginDTO){
        try{
            Utilizador u = utilizadorService.login(loginDTO.getEmail(), loginDTO.getPassword());
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

    //@GetMapping("/procurar")
    //public ResponseEntity<List<Utilizador>> procurar(@RequestParam String nome){
    //    try{ return new ResponseEntity<>(utilizadorServiceBean.procurarUtilizador(nome, "Condutor"), HttpStatus.OK); }
    //    catch (Exception e){
    //        return new ResponseEntityBadRequest<List<Utilizador>>().createBadRequest(e.getMessage());
    //    }
    //}
}
