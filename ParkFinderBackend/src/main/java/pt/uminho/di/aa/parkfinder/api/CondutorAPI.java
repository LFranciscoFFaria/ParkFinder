package pt.uminho.di.aa.parkfinder.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.uminho.di.aa.parkfinder.api.auxiliar.ResponseEntityBadRequest;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaCondutores.Condutor;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaCondutores.CondutorServiceBean;
import pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica.UtilizadorServiceBean;

@RestController
@CrossOrigin
@RequestMapping("/apiV1/condutores")
public class CondutorAPI {
    private final UtilizadorServiceBean utilizadorServiceBean;
    private final CondutorServiceBean condutorServiceBean;

    @Autowired
    public CondutorAPI(UtilizadorServiceBean utilizadorServiceBean, CondutorServiceBean condutorServiceBean) {
        this.utilizadorServiceBean = utilizadorServiceBean;
        this.condutorServiceBean = condutorServiceBean;
    }

    @PutMapping
    public ResponseEntity<Condutor> criarCondutor(@RequestBody Condutor c){
        try{ return new ResponseEntity<>((Condutor) utilizadorServiceBean.criarUtilizador(c), HttpStatus.OK); }
        catch (Exception e){
            return new ResponseEntityBadRequest<Condutor>().createBadRequest(e.getMessage());
        }
    }

    @PutMapping("/editarPerfil")
    public ResponseEntity<Boolean> editarPerfil(@RequestBody Condutor c){
        try{ return new ResponseEntity<>(condutorServiceBean.editarPerfil(c), HttpStatus.OK); }
        catch (Exception e){
            return new ResponseEntityBadRequest<Boolean>().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(){
        try{
            condutorServiceBean.logout();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }

    }

}
