package pt.uminho.di.aa.parkfinder.logicaParques.DAOs;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Horario;

public interface HorarioDAO extends JpaRepository<Horario, Integer> {
}
