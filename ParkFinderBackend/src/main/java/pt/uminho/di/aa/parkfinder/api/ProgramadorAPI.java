package pt.uminho.di.aa.parkfinder.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.uminho.di.aa.parkfinder.api.auxiliar.ResponseEntityBadRequest;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Estatisticas;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Parque;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.ProgramadorServiceBean;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model.Gestor;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/apiV1/programador")
public class ProgramadorAPI {
    private final ProgramadorServiceBean programadorServiceBean;

    @Autowired
    public ProgramadorAPI(ProgramadorServiceBean programadorServiceBean) {
        this.programadorServiceBean = programadorServiceBean;
    }

    @PutMapping("/criar_gestor")
    public ResponseEntity<Void> criarGestor(@RequestBody Gestor g, @RequestBody List<Integer> ids_parques) {
        try{
            programadorServiceBean.criarGestor(g, ids_parques);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/remover_gestor")
    public ResponseEntity<Void> removerGestor(@RequestParam("id_gestor") int id_gestor) {
        try{
            programadorServiceBean.removerGestor(id_gestor);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/adicionar_parques")
    public ResponseEntity<Void> adicionarParquesAGestor(@RequestBody List<Integer> ids_parques, @RequestParam("id_gestor") int id_gestor) {
        try{
            programadorServiceBean.adicionarParquesAGestor(ids_parques,id_gestor);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/remover_parques")
    public ResponseEntity<Void> removerParquesAGestor(@RequestBody List<Integer> ids_parques, @RequestParam("id_gestor") int id_gestor) {
        try{
            programadorServiceBean.removerParquesAGestor(ids_parques,id_gestor);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }


    @PutMapping("/registar_parque")
    public ResponseEntity<Void> registarParque(@RequestBody Parque p) {
        try{
            programadorServiceBean.registarParque(p);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/remover_parque")
    public ResponseEntity<Void> removerParque(@RequestBody Parque p) {
        try{
            programadorServiceBean.removerParque(p);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/procurar_gestor")
    public ResponseEntity<List<Gestor>> procurarGestor(@RequestParam("nome") String nome) {
        try{
            return new ResponseEntity<List<Gestor>>(programadorServiceBean.procurarGestor(nome), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<List<Gestor>>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/lista_gestores")
    public ResponseEntity<List<Gestor>> listarGestores() {
        try{
            return new ResponseEntity<List<Gestor>>(programadorServiceBean.listarGestores(), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<List<Gestor>>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/estatisticas_gerais")
    public ResponseEntity<Estatisticas> verEstatisticasGerais() {
        try{
            return new ResponseEntity<Estatisticas>(programadorServiceBean.verEstatisticasGerais(), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntityBadRequest<Estatisticas>().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(){
        programadorServiceBean.logout();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
