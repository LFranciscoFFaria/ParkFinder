package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.DAOs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model.Gestor;

@Repository
public interface GestorDAO extends JpaRepository<Gestor, Integer> {
}
