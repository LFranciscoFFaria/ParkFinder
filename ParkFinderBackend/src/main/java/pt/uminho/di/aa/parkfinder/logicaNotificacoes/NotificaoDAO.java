package pt.uminho.di.aa.parkfinder.logicaNotificacoes;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificaoDAO extends JpaRepository<Notificacao,Integer> {
        //@EntityGraph(attributePaths = {"utilizador"})
        //Optional<Notificacao> findById(int id);
        List<Notificacao> findAllByUtilizadorID(int id_user);
        List<Notificacao> findAllByUtilizadorIDAndLida(int id_user, boolean lida);
}
