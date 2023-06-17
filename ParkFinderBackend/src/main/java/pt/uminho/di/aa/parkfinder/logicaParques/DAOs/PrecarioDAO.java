package pt.uminho.di.aa.parkfinder.logicaParques.DAOs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Precarios.Precario;

@Repository
public interface PrecarioDAO extends JpaRepository<Precario, Integer> {
}
