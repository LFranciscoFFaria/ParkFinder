package pt.uminho.di.aa.parkfinder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pt.uminho.di.aa.parkfinder.logicaParques.ParqueService;
import pt.uminho.di.aa.parkfinder.logicaParques.ParqueServiceBean;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Parque;
import pt.uminho.di.aa.parkfinder.logicaParques.model.TipoLugarEstacionamento;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class TestesParqueService {

    private final ParqueServiceBean parqueServiceBean;


    @Autowired
    public TestesParqueService(ParqueServiceBean parqueServiceBean) {
        this.parqueServiceBean = parqueServiceBean;
    }

    @Test
    void TestarParqueService(){
        //listarParques()
        List<Parque> parqueList = parqueServiceBean.listarParques();
        if (parqueList.size()>0){
            for (Parque parque:parqueList){
                System.out.println(parque.toString());
            }
        }

        System.out.println("");

        //procurarLugaresDisponiveis(int id_parque,@NotNull  TipoLugarEstacionamento tipo,LocalDateTime data_inicio,LocalDateTime data_fim)
        TipoLugarEstacionamento tipoAgendado = new TipoLugarEstacionamento("Agendado");
        LocalDateTime data_inicio = LocalDateTime.of(2023,6,24,11, 0,0);
        LocalDateTime data_fim = LocalDateTime.of(2023,6,24,12, 0,0);
        List<Integer> ids = parqueServiceBean.procurarLugaresDisponiveis(1,tipoAgendado,data_inicio,data_fim);
        if (ids.size()>0){
            for (int id:ids){
                System.out.println(ids);
            }
        }
    }
}
