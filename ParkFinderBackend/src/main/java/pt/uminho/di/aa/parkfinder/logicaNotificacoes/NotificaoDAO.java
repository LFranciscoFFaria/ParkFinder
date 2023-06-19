package pt.uminho.di.aa.parkfinder.logicaNotificacoes;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificaoDAO extends JpaRepository<Notificacao,Integer> {
        List<Notificacao> findAllByUtilizadorID(int id_user);
        List<Notificacao> findAllByUtilizadorIDAndLida(int id_user, boolean lida);
}
