package pt.uminho.di.aa.parkfinder.logicaParques.DAOs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.uminho.di.aa.parkfinder.logicaParques.model.TipoLugarEstacionamento;

import java.util.Optional;

@Repository
public interface TipoLugarEstacionamentoDAO extends JpaRepository<TipoLugarEstacionamento, Integer> {
    TipoLugarEstacionamento findByNome(String nome);
}
