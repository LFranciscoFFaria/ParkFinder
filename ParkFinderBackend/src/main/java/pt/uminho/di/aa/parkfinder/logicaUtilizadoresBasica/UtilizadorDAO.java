package pt.uminho.di.aa.parkfinder.logicaUtilizadoresBasica;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilizadorDAO extends JpaRepository<Utilizador,Integer> {
    Optional<Utilizador> findByEmail(String email);
    boolean existsByEmail(String email);
    List<Utilizador> findUtilizadorByNomeContainingIgnoreCaseAndDiscriminator(String nome, String descriminador);
}
