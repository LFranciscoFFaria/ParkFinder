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

    @PutMapping()
    public ResponseEntity<Parque> criarParque(@RequestBody Parque p){
        try{ return  new ResponseEntity<>((Parque) parqueServiceBean.criarParque(p), HttpStatus.OK); }
        catch (Exception e){
            return new ResponseEntityBadRequest<Parque>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/allparques")
    public ResponseEntity<List<Parque>> listarParques(){
        try{ return new ResponseEntity<>(parqueServiceBean.listarParques(),HttpStatus.OK);}
        catch (Exception e){
            return new ResponseEntityBadRequest<List<Parque>>().createBadRequest(e.getMessage());
        }
    }

}
