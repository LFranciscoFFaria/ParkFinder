package pt.uminho.di.aa.parkfinder.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.uminho.di.aa.parkfinder.api.auxiliar.ResponseEntityBadRequest;
import pt.uminho.di.aa.parkfinder.logicaParques.ParqueServiceBean;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Parque;
import pt.uminho.di.aa.parkfinder.logicaParques.model.ParqueDTO;
import pt.uminho.di.aa.parkfinder.logicaParques.model.TipoLugarEstacionamento;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apiV1/parques")
public class ParqueAPI {
    private final ParqueServiceBean parqueServiceBean;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Autowired
    public ParqueAPI(ParqueServiceBean parqueServiceBean) {
        this.parqueServiceBean = parqueServiceBean;
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

    /**
     *
     * @param id_parque identificador do parque
     * @param tipo tipo de lugar de estacionamento / tipo de reserva
     * @param data_inicio_s string com data que marca o início do intervalo. Formato: "yyyy-MM-dd HH:mm"
     * @param data_fim_s string com data que marca o fim do intervalo. Formato: "yyyy-MM-dd HH:mm"
     * @return simula o custo de um certo tipo de reserva para um dado intervalo.
     */
    @GetMapping("/simular_custo")
    public ResponseEntity<Float> simularCustoReserva(@RequestParam("id_parque") int id_parque,
                                                     @RequestParam("tipo") String tipo,
                                                     @RequestParam("data_inicio") String data_inicio_s,
                                                     @RequestParam("data_fim") String data_fim_s){
        LocalDateTime data_inicio, data_fim;
        try {
            data_inicio = LocalDateTime.parse(data_inicio_s, dateTimeFormatter);
            data_fim = LocalDateTime.parse(data_fim_s, dateTimeFormatter);
        }catch (Exception e){
            return new ResponseEntityBadRequest<Float>().createBadRequest("Uma das datas não se apresenta na forma correta.");
        }

        try {
            Float custo = parqueServiceBean.simularCusto(id_parque, new TipoLugarEstacionamento(tipo), data_inicio, data_fim);
            return new ResponseEntity<>(custo, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntityBadRequest<Float>().createBadRequest(e.getMessage());
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
