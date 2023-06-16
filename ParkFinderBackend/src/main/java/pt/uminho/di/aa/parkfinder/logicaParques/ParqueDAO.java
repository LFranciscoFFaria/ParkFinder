package pt.uminho.di.aa.parkfinder.logicaParques;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParqueDAO extends JpaRepository<Parque,Integer> {
    //List<Parque> findByAllNome(String nome);
}
