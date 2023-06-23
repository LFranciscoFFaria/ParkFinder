package pt.uminho.di.aa.parkfinder.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.uminho.di.aa.parkfinder.api.DTOs.AdminDTO;
import pt.uminho.di.aa.parkfinder.api.DTOs.CondutorDTO;
import pt.uminho.di.aa.parkfinder.api.DTOs.GestorDTO;
import pt.uminho.di.aa.parkfinder.api.DTOs.LoginDTO;
import pt.uminho.di.aa.parkfinder.api.auxiliar.ResponseEntityBadRequest;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaCondutores.Condutor;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaCondutores.CondutorService;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.AdministradorService;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.GestorService;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.ProgramadorService;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model.Administrador;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model.Gestor;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model.Programador;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.Utilizador;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.UtilizadorService;
@CrossOrigin(originPatterns = "*", allowCredentials = "true")
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


    @PutMapping("/login")
    @Operation(summary = "Iniciar sessão",
               requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true,
                                                                                   description = "Credenciais para iniciar sessão",
                                                                                   content = @Content(mediaType = "application/json",
                                                                                                      schema = @Schema(implementation = LoginDTO.class)))
    )
    public ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO){
        try{
            Utilizador u = utilizadorService.login(loginDTO.getEmail(), loginDTO.getPassword());
            if (u == null)
                return new ResponseEntityBadRequest<>().createBadRequest("Credenciais inválidas.");

            Object ret;
            switch (u.getDiscriminator()) {
                case "Condutor" -> {
                    CondutorService condutorServiceBean = context.getBean(CondutorService.class);
                    Condutor c = (Condutor) u;
                    condutorServiceBean.setCondutor(c);
                    ret = new CondutorDTO(c.getId(), c.getNome(), c.getEmail(), c.getNrTelemovel(), c.getPassword(), c.getNif(), c.getGenero());
                }
                case "Admin" -> {
                    AdministradorService administradorServiceBean = context.getBean(AdministradorService.class);
                    Administrador a = (Administrador) u;
                    administradorServiceBean.setAdministrador(a);
                    ret = new AdminDTO(a.getId(), a.getNome(), a.getEmail(), a.getNrTelemovel(), a.getPassword(), a.getGestorID(), null);
                }
                case "Gestor" -> {
                    GestorService gestorServiceBean = context.getBean(GestorService.class);
                    Gestor g = (Gestor) u;
                    gestorServiceBean.setGestor(g);
                    ret = new GestorDTO(g.getId(), g.getNome(), g.getEmail(), g.getNrTelemovel(), g.getPassword(), null, null);
                }
                case "Programador" -> {
                    ProgramadorService programadorServiceBean = context.getBean(ProgramadorService.class);
                    Programador p = (Programador) u;
                    programadorServiceBean.setProgramador(u);
                    ret = new Programador(p.getNome(), p.getEmail(), p.getPassword(), p.getNrTelemovel());
                }
                default -> ret = null;
            }

            return new ResponseEntity<>(ret, HttpStatus.OK); }
        catch (Exception e){
            return new ResponseEntityBadRequest<>().createBadRequest(e.getMessage());
        }
    }
}
