package pt.uminho.di.aa.parkfinder.logicaParques.DAOs;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Estatisticas;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Horario;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Parque;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios.Precario;

import java.util.List;

@Repository
public interface ParqueDAO extends JpaRepository<Parque,Integer> {
    List<Parque> findAllByNomeContainingIgnoreCase(String nome);

    @Query(value = "SELECT Precario FROM Parque p JOIN Precario prec WHERE p.id = :id_parque AND prec.tipo.nome = :tipoLugar")
    Precario findPrecarioDoParque(@Param("id_parque") int id_parque, @Param("tipoLugar") String tipoLugar);

    @Query(value = "SELECT p from Parque p LEFT JOIN FETCH p.precarios WHERE p.id = :id_parque")
    Parque findByIdWithPrecarios(@Param("id_parque") int id_parque);

    @Query(value = "SELECT p from Parque p LEFT JOIN FETCH p.horario WHERE p.id = :id_parque")
    Parque findByIdWithHorario(@Param("id_parque") int id_parque);

    @Query(value = "SELECT p from Parque p LEFT JOIN FETCH p.estatisticas WHERE p.id = :id_parque")
    Parque findByIdWithEstatisticas(@Param("id_parque") int id_parque);

    @Query("SELECT p.horario FROM Parque p WHERE p.id = :id_parque")
    Horario getHorarioDoParque(int id_parque);

    @Query("SELECT p.estatisticas FROM Parque p WHERE p.id = :id_parque")
    Estatisticas getEstatisticasDoParque(int id_parque);
}
