package pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.DAOs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model.Administrador;
import pt.uminho.di.aa.parkfinder.logicaUtilizadores.logicaEspeciais.model.Gestor;

import java.util.List;

@Repository
public interface GestorDAO extends JpaRepository<Gestor, Integer> {
    @Query("SELECT g.admins FROM Gestor g WHERE g.id = :id_gestor")
    List<Administrador> getAdministradoresDoGestor(@Param("id_gestor") int id_gestor);
}
