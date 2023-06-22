package pt.uminho.di.aa.parkfinder.logicaReservas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pt.uminho.di.aa.parkfinder.logicaParquesReservas.EstadoReserva;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaDAO extends JpaRepository<Reserva,Integer> {
    List<Reserva> findAllByUtilizadorIdOrderByDataInicioDesc(int id_user);

    @Query("SELECT r FROM Reserva r WHERE r.parque.id = :parque_id AND r.matricula = :matricula AND r.estado = :estado ORDER BY r.dataInicio DESC LIMIT 1")
    Optional<Reserva> findReservaByParqueIDAndMatriculaAndEstado(int parque_id, String matricula, int estado);

    List<Reserva> findAllByParqueIdOrderByDataInicioDesc(int id_parque);

    List<Reserva> findAllByUtilizadorId(int id_user);


    List<Reserva> findAllByParqueIDAndEstado(@Param("id_parque") int id_parque, @Param("estado") int estado);
}
