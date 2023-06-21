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

    @Query("SELECT l FROM LugarEstacionamento l LEFT JOIN Reserva r ON l.lugarId = r.lugar.lugarId " +
                                               "WHERE l.tipo.nome = :tipoLugar " +
                                               "AND l.parque.id = :id_parque " +
                                               "AND NOT (r.dataInicio BETWEEN :data_inicio AND :data_fim) " +
                                               "AND NOT (r.dataFim BETWEEN :data_inicio AND :data_fim)")
    Set<LugarEstacionamento> procurarLugaresDisponiveis(@Param("id_parque") int id_parque, @Param("tipoLugar") String tipoLugar,
                                                     @Param("data_inicio") LocalDateTime data_inicio, @Param("data_fim") LocalDateTime data_fim);

    List<LugarEstacionamento> findAllByParqueId(int id_parque);
}
