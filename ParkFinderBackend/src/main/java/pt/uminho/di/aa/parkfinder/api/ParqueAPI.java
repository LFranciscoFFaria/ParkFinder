package pt.uminho.di.aa.parkfinder.api;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.uminho.di.aa.parkfinder.api.DTOs.PrecarioBaseDTO;
import pt.uminho.di.aa.parkfinder.api.DTOs.PrecarioDecLinearCriarDTO;
import pt.uminho.di.aa.parkfinder.api.DTOs.PrecarioLinearCriarDTO;
import pt.uminho.di.aa.parkfinder.api.auxiliar.ResponseEntityBadRequest;
import pt.uminho.di.aa.parkfinder.logicaParques.ParqueService;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Horario;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Parque;
import pt.uminho.di.aa.parkfinder.logicaParques.DTOs.ParqueDTO;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios.Precario;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios.PrecarioDecrementoLinear;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios.PrecarioLinear;
import pt.uminho.di.aa.parkfinder.logicaParques.model.TipoLugarEstacionamento;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apiV1/parques")
public class ParqueAPI {
    private final ParqueService parqueService;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Autowired
    public ParqueAPI(ParqueService parqueService) {
        this.parqueService = parqueService;
    }

    @GetMapping("")
    @Operation(summary = "Apresenta a lista de parques da aplicação")
    public ResponseEntity<List<ParqueDTO>> listarParques(){
        try{
            List<Parque> parques = parqueService.listarParques();
            List<ParqueDTO> parqueDTOS = parques.stream().map(this::parqueToDTO).toList();
            return new ResponseEntity<>(parqueDTOS,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntityBadRequest<List<ParqueDTO>>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/ids")
    @Operation(summary = "Apresenta os parques com os identicadores recebidos")
    public ResponseEntity<List<ParqueDTO>> listarParquesIds(@RequestParam("lista_ids") List<Integer> ids){
        try{
            List<Parque> parques = parqueService.listarParques(ids);
            List<ParqueDTO> parqueDTOS = parques.stream().map(this::parqueToDTO).toList();
            return new ResponseEntity<>(parqueDTOS,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntityBadRequest<List<ParqueDTO>>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/nome")
    @Operation(summary = "Procura um parque pelo nome")
    public ResponseEntity<List<ParqueDTO>> procurarParque(@RequestParam String nome){
        try{
            List<Parque> parques = parqueService.procurarParque(nome);
            List<ParqueDTO> parqueDTOS = parques.stream().map(this::parqueToDTO).toList();
            return new ResponseEntity<>(parqueDTOS,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntityBadRequest<List<ParqueDTO>>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/id")
    @Operation(summary = "Apresenta o parque com o identificador recebido")
    public ResponseEntity<ParqueDTO> procurarParque(@RequestParam int id_parque){
        try{
            Parque p = parqueService.procurarParque(id_parque);
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
    @Operation(summary = "Apresenta o custo de uma reserva simulada")
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
            Float custo = parqueService.simularCusto(id_parque, new TipoLugarEstacionamento(tipo), data_inicio, data_fim);
            return new ResponseEntity<>(custo, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntityBadRequest<Float>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/horario")
    @Operation(summary = "Apresenta o horário do parque")
    public ResponseEntity<Horario> getHorarioParque(@RequestParam("id_parque") int id_parque){
        try {
            return ResponseEntity.ok().body(parqueService.getHorario(id_parque));
        }catch (Exception e){
            return new ResponseEntityBadRequest<Horario>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/precarios/all")
    @Operation(summary = "Apresenta todos os precarios do parque")
    public ResponseEntity<List<PrecarioBaseDTO>> getPrecariosParque(@RequestParam("id_parque") int id_parque){
        try {
            List<Precario> precarios = parqueService.getPrecarios(id_parque);
            List<PrecarioBaseDTO> precarioBaseDTOS = precarios.stream().map(p -> precarioToDTO(id_parque, p)).toList();
            return ResponseEntity.ok().body(precarioBaseDTOS);
        }catch (Exception e){
            return new ResponseEntityBadRequest<List<PrecarioBaseDTO>>().createBadRequest(e.getMessage());
        }
    }

    @GetMapping("/precarios")
    @Operation(summary = "Apresenta o precario para o tipo de lugar pretendido")
    public ResponseEntity<PrecarioBaseDTO> getPrecarioParaTipoLugarDoParque(@RequestParam("id_parque") int id_parque, @RequestParam("tipo_lugar") String tipoLugar){
        try {
            Precario precario = parqueService.getPrecarioByParqueIdAndTipoLugar(id_parque, tipoLugar);
            return ResponseEntity.ok().body(precarioToDTO(id_parque, precario));
        }catch (Exception e){
            return new ResponseEntityBadRequest<PrecarioBaseDTO>().createBadRequest(e.getMessage());
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

    private PrecarioBaseDTO precarioToDTO(Integer id_parque, Precario precario){
        if(precario instanceof PrecarioLinear pl){
            return new PrecarioLinearCriarDTO(id_parque, pl.getTipo().getNome(), pl.getPrecoFixo(),
                                              pl.getPrecoPorMinuto(), LocalTime.of(0,1));
        }else /*if(precario instanceof PrecarioDecrementoLinear pdl)*/{
            PrecarioDecrementoLinear pdl = (PrecarioDecrementoLinear) precario;
            return new PrecarioDecLinearCriarDTO(id_parque, pdl.getTipo().getNome(), pdl.getPrecoFixo(),
                                                 pdl.getPrecoPorMinutoMax(), pdl.getPrecoPorMinutoMin(),
                                                 pdl.getIntervaloParaAtingirMin(), LocalTime.of(0,1));
        }
    }
}
