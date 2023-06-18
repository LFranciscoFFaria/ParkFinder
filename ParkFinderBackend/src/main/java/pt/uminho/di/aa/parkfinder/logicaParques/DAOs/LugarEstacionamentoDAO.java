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
    //@Modifying
    //@Query("DELETE FROM LugarEstacionamento " +
    //            "WHERE lugarId IN (SELECT l.lugarId FROM LugarEstacionamento l " +
    //                                          "LEFT JOIN Reserva r ON l.lugarId = r.lugar.lugarId " +
    //                                                "WHERE l.tipo.nome = :tipoLugar " +
    //                                                  "AND r.data_fim >= current_timestamp " +
    //                                                "HAVING COUNT(r.id) = 0 " +
    //                                                "LIMIT 1)")
    //void eliminar1LugarSemReservasFuturas(@Param("tipoLugar") String tipoLugar);

    @Query("SELECT l.lugarId FROM LugarEstacionamento l " +
                       "LEFT JOIN Reserva r ON l.lugarId = r.lugar.lugarId " +
                       "WHERE l.tipo.nome = :tipoLugar " +
                       "AND l.parque.id = :id_parque " +
                       "AND r.dataFim >= current_timestamp() " +
                       "GROUP BY l " +
                       "HAVING COUNT(r.id) = 0 ")
    List<LugarEstacionamento> findLugaresSemReservasFuturas(@Param("id_parque") int id_parque, @Param("tipoLugar") String tipoLugar);

    @Modifying
    @Query(nativeQuery = true, value="UPDATE reserva r SET r.lugarid = null WHERE r.lugarId = :id_lugar; DELETE FROM lugar_estacionamento WHERE id = :id_lugar")
    void eliminarLugar(@Param("id_lugar") int id_lugar);

    @Query("SELECT l FROM LugarEstacionamento l JOIN Reserva r ON l.lugarId = r.lugar.lugarId " +
                                "WHERE l.tipo.nome = :tipoLugar " +
                                "AND l.parque.id = :id_parque " +
                                "AND NOT (r.dataInicio BETWEEN :data_inicio AND :data_fim) " +
                                "AND NOT (r.dataFim BETWEEN :data_inicio AND :data_fim)")
    Set<LugarEstacionamento> procurarLugaresDisponiveis(@Param("id_parque") int id_parque, @Param("tipoLugar") String tipoLugar,
                                                     @Param("data_inicio") LocalDateTime data_inicio, @Param("data_fim") LocalDateTime data_fim);
}
