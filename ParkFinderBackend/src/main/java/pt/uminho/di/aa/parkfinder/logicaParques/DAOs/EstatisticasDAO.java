package pt.uminho.di.aa.parkfinder.logicaParques.DAOs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Estatisticas;

@Repository
public interface EstatisticasDAO extends JpaRepository<Estatisticas, Integer> {
}
