package pt.uminho.di.aa.parkfinder.logicaParques.DAOs;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pt.uminho.di.aa.parkfinder.logicaParques.model.LugarEstacionamento;
import pt.uminho.di.aa.parkfinder.logicaParques.model.TipoLugarEstacionamento;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface LugarEstacionamentoDAO extends JpaRepository<LugarEstacionamento,Integer> {

    @Query(nativeQuery = true, value = "SELECT id from (SELECT l.id,MAX(r.data_fim) as max from lugar_estacionamento l " +
                                        "LEFT JOIN reserva r on l.id = r.lugarid " +
                                        "JOIN tipo_lugar_estacionamento tle on l.tipo_lugarid = tle.id " +
                                        "WHERE tle.nome = :tipo_Lugar AND l.parqueid = :id_parque GROUP BY l.id) " +
                                        "as r where r.max < current_timestamp() or r.max IS NULL")
    List<Integer> findLugaresSemReservasFuturas(@Param("id_parque") int id_parque, @Param("tipo_Lugar") String tipo_Lugar);

    @Modifying
    @Query(value="UPDATE Reserva r SET r.lugar.lugarId = null WHERE r.lugar.lugarId = :id_lugar")
    void requisitoEliminarLugar(@Param("id_lugar") int id_lugar);

    @Query(nativeQuery = true, value = "SELECT l.id FROM lugar_estacionamento l JOIN tipo_lugar_estacionamento tle on l.tipo_lugarid = tle.id\n" +
            "WHERE tle.nome = :tipoLugar AND l.parqueid = :id_parque AND l.id NOT IN\n" +
            "(SELECT l.id FROM lugar_estacionamento l JOIN tipo_lugar_estacionamento tle on l.tipo_lugarid = tle.id LEFT JOIN reserva r on l.id = r.lugarid\n" +
            "    WHERE tle.nome = :tipoLugar AND l.parqueid = :id_parque AND (r.data_inicio BETWEEN :data_inicio AND :data_fim) OR (r.data_fim BETWEEN :data_inicio AND :data_fim));")
    Set<Integer> procurarLugaresDisponiveis(@Param("id_parque") int id_parque, @Param("tipoLugar") String tipoLugar,
                                                     @Param("data_inicio") LocalDateTime data_inicio, @Param("data_fim") LocalDateTime data_fim);

    List<LugarEstacionamento> findAllByParqueId(int id_parque);
}
