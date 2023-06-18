package pt.uminho.di.aa.parkfinder.logicaReservas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaDAO extends JpaRepository<Reserva,Integer> {
    List<Reserva> findAllByUtilizadorIdOrderByDataInicioDesc(int id_user);

    List<Reserva> findAllByUtilizadorId(int id_user);
}
