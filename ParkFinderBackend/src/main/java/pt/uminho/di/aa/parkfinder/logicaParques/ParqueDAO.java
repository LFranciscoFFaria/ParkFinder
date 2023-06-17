package pt.uminho.di.aa.parkfinder.logicaParques;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.uminho.di.aa.parkfinder.logicaParques.model.Parque;

import java.util.List;

@Repository
public interface ParqueDAO extends JpaRepository<Parque,Integer> {
    List<Parque> findAllByNomeContainingIgnoreCase(String nome);
}
