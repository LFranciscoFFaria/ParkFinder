package pt.uminho.di.aa.parkfinder.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.uminho.di.aa.parkfinder.api.auxiliar.ResponseEntityBadRequest;
import pt.uminho.di.aa.parkfinder.logicaParques.ParqueServiceBean;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Parque;

import java.util.List;

@RestController
@RequestMapping("/apiV1/parques")
public class ParqueAPI {
    private final ApplicationContext context;
    private final ParqueServiceBean parqueServiceBean;

    @Autowired
    public ParqueAPI(ApplicationContext context, ParqueServiceBean parqueServiceBean) {
        this.context = context;
        this.parqueServiceBean = parqueServiceBean;
    }

    // Métodos do programador

    @PutMapping()
    public ResponseEntity<Parque> criarParque(@RequestBody Parque p){
        try{ return  new ResponseEntity<>((Parque) parqueServiceBean.criarParque(p), HttpStatus.OK); }
        catch (Exception e){
            return new ResponseEntityBadRequest<Parque>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/remove")
    public ResponseEntity removerParque(@RequestBody int id_parque){
        try{
            parqueServiceBean.removerParque(id_parque);
            return  new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntityBadRequest<>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/allparques")
    public ResponseEntity<List<Parque>> listarParques(){
        try{ return new ResponseEntity<>(parqueServiceBean.listarParques(),HttpStatus.OK);}
        catch (Exception e){
            return new ResponseEntityBadRequest<List<Parque>>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/allparquesids")
    public ResponseEntity<List<Parque>> listarParquesIds(@RequestBody List<Integer> ids){
        try{ return new ResponseEntity<>(parqueServiceBean.listarParques(ids),HttpStatus.OK);}
        catch (Exception e){
            return new ResponseEntityBadRequest<List<Parque>>().createBadRequest(e.getMessage());
        }
    }

    // Métodos de todos os utilizadores

    // Não sei muito bem com funciona o Post,mas deve ser o mais indicado para a situação
    @PostMapping("/nome")
    public ResponseEntity<List<Parque>> procurarParque(@RequestBody String nome){
        try{ return new ResponseEntity<>(parqueServiceBean.procurarParque(nome),HttpStatus.OK);}
        catch (Exception e){
            return new ResponseEntityBadRequest<List<Parque>>().createBadRequest(e.getMessage());
        }
    }

    //Não sei a que utilizadores se aplica este método nem qual é método HTML apropriado
    @PostMapping("/nome")
    public ResponseEntity<Parque> procurarParque(@RequestBody int id_parque){
        try{ return new ResponseEntity<>((Parque) parqueServiceBean.procurarParque(id_parque),HttpStatus.OK);}
        catch (Exception e){
            return new ResponseEntityBadRequest<Parque>().createBadRequest(e.getMessage());
        }
    }

    //Métodos Gestor

}
