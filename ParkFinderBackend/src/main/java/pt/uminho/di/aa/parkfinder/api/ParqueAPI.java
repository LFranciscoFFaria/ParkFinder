package pt.uminho.di.aa.parkfinder.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.uminho.di.aa.parkfinder.api.auxiliar.ResponseEntityBadRequest;
import pt.uminho.di.aa.parkfinder.logicaParques.ParqueServiceBean;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Parque;
import pt.uminho.di.aa.parkfinder.logicaParques.model.ParqueDTO;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apiV1/parques")
public class ParqueAPI {
    private final ParqueServiceBean parqueServiceBean;

    @Autowired
    public ParqueAPI(ParqueServiceBean parqueServiceBean) {
        this.parqueServiceBean = parqueServiceBean;
    }

    // Métodos do programador

    @PutMapping("/criarparque")
    public ResponseEntity<ParqueDTO> criarParque(@RequestBody ParqueDTO pDTO){
        try{
            Parque p = new Parque(pDTO.getNome().orElse(null),
                                  pDTO.getMorada().orElse(null),
                                  pDTO.getDescricao().orElse(null),
                                  pDTO.getLatitude().orElse(null),
                                  pDTO.getLongitude().orElse(null),
                                  true, 0, 0, 0,
                                  pDTO.getCaminho_foto().orElse(null));
            p = parqueServiceBean.criarParque(p);
            return new ResponseEntity<>(parqueToDTO(p), HttpStatus.OK);
        } catch (NullPointerException nue){
            return new ResponseEntityBadRequest<ParqueDTO>().createBadRequest("Parque não pode ser criado. Campos em falta!");
        } catch (Exception e){
            return new ResponseEntityBadRequest<ParqueDTO>().createBadRequest(e.getMessage());
        }
    }

    @DeleteMapping("")
    public ResponseEntity<Void> removerParque(@RequestBody int id_parque){
        try{
            parqueServiceBean.removerParque(id_parque);
            return  new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntityBadRequest<Void>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/allparques")
    public ResponseEntity<List<ParqueDTO>> listarParques(){
        try{
            List<Parque> parques = parqueServiceBean.listarParques();
            List<ParqueDTO> parqueDTOS = parques.stream().map(this::parqueToDTO).toList();
            return new ResponseEntity<>(parqueDTOS,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntityBadRequest<List<ParqueDTO>>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/allparquesids")
    public ResponseEntity<List<ParqueDTO>> listarParquesIds(@RequestBody List<Integer> ids){
        try{
            List<Parque> parques = parqueServiceBean.listarParques(ids);
            List<ParqueDTO> parqueDTOS = parques.stream().map(this::parqueToDTO).toList();
            return new ResponseEntity<>(parqueDTOS,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntityBadRequest<List<ParqueDTO>>().createBadRequest(e.getMessage());
        }
    }

    // Métodos de todos os utilizadores

    @GetMapping("/nome")
    public ResponseEntity<List<ParqueDTO>> procurarParque(@RequestParam String nome){
        try{
            List<Parque> parques = parqueServiceBean.procurarParque(nome);
            List<ParqueDTO> parqueDTOS = parques.stream().map(this::parqueToDTO).toList();
            return new ResponseEntity<>(parqueDTOS,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntityBadRequest<List<ParqueDTO>>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/id")
    public ResponseEntity<ParqueDTO> procurarParque(@RequestParam int id_parque){
        try{
            Parque p = parqueServiceBean.procurarParque(id_parque);
            return new ResponseEntity<>(parqueToDTO(p),HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntityBadRequest<ParqueDTO>().createBadRequest(e.getMessage());
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
}
