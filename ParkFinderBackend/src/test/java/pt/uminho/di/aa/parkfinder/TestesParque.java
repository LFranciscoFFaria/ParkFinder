package pt.uminho.di.aa.parkfinder;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Horario;
import pt.uminho.di.aa.parkfinder.logicaParques.model.HorarioPeriodo;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios.Precario;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios.PrecarioDecrementoLinear;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios.PrecarioLinear;
import pt.uminho.di.aa.parkfinder.logicaParques.model.TipoLugarEstacionamento;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

@SpringBootTest
public class TestesParque {

    @Test
    void testeFormatter(){
        LocalTime t = LocalTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime t2 = LocalTime.parse(dateTimeFormatter.format(t), dateTimeFormatter);
        t = t.withSecond(0).withNano(0);
        assert t.equals(t2);
    }

    @Test
    void testeEstaAbertoHorario(){
        HashSet<HorarioPeriodo> periodos = new HashSet<>();

        // Aberto segunda das 9:00 às 13:00 e 14:00 às 18:30
        periodos.add(new HorarioPeriodo(0, LocalTime.of(9,0), LocalTime.of(13,0), DayOfWeek.MONDAY.getValue()));
        periodos.add(new HorarioPeriodo(0, LocalTime.of(14,0), LocalTime.of(18,30), DayOfWeek.MONDAY.getValue()));

        // Criar horario
        Horario horario = new Horario(0, periodos);

        // Testes
        boolean teste = horario.estaAberto(DayOfWeek.MONDAY, LocalTime.of(9,0), LocalTime.of(13,0));
        assert teste;

        teste = horario.estaAberto(DayOfWeek.MONDAY, LocalTime.of(9,1), LocalTime.of(13,0));
        assert teste;

        teste = horario.estaAberto(DayOfWeek.MONDAY, LocalTime.of(9,0), LocalTime.of(12,59));
        assert teste;

        teste = horario.estaAberto(DayOfWeek.MONDAY, LocalTime.of(8,59), LocalTime.of(12,59));
        assert !teste;

        teste = horario.estaAberto(DayOfWeek.MONDAY, LocalTime.of(9,1), LocalTime.of(13,59));
        assert !teste;

        teste = horario.estaAberto(DayOfWeek.MONDAY, LocalTime.of(13,1), LocalTime.of(13,59));
        assert !teste;

        teste = horario.estaAberto(DayOfWeek.MONDAY, LocalTime.of(9,1), LocalTime.of(14,59));
        assert !teste;

        teste = horario.estaAberto(DayOfWeek.TUESDAY, LocalTime.of(9,1), LocalTime.of(13,59));
        assert !teste;
    }

    @Test
    void TestePrecarioLinear(){
        TipoLugarEstacionamento tipoLugar = new TipoLugarEstacionamento(1, "Reservavel");
        Precario precario = new PrecarioLinear(tipoLugar,1F, 1F, LocalTime.of(0,1));
        LocalDateTime now = LocalDateTime.now();
        assert 61F == precario.calcular_preco(now, now.plusHours(1));
    }

    @Test
    void TesteDecrementoPrecarioLinear(){
        TipoLugarEstacionamento tipoLugar = new TipoLugarEstacionamento(1, "Reservavel");
        Precario precario = new PrecarioDecrementoLinear(tipoLugar,1F, 1F, 1F, LocalTime.of(0,1), LocalTime.of(0,1));
        Precario precarioLinear = new PrecarioLinear(tipoLugar,1F, 1F, LocalTime.of(0,1));
        LocalDateTime now = LocalDateTime.now();
        assert precarioLinear.calcular_preco(now, now.plusHours(1)) == precario.calcular_preco(now, now.plusHours(1));

        precario = new PrecarioDecrementoLinear(tipoLugar,1F, 3F, 1F, LocalTime.of(0,1), LocalTime.of(1,0));
        assert precario.calcular_preco(now, now) == 1F;
        assert precario.calcular_preco(now, now.plusHours(1)) == 61F;
        assert precario.calcular_preco(now, now.plusHours(1).plusMinutes(30)) == 91F;

        precario = new PrecarioDecrementoLinear(tipoLugar,0F, 0.1F, 0.05F, LocalTime.of(0,1), LocalTime.of(1,0));
        assert precario.calcular_preco(now, now) == 0F;
        assert precario.calcular_preco(now, now.plusMinutes(30)) == 2.25F;
        assert precario.calcular_preco(now, now.plusHours(1)) == 3F;
    }
}
