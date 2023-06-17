package pt.uminho.di.aa.parkfinder.logicaNotificacoes;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificaoDAO extends JpaRepository<Notificacao,Integer> {
        List<Notificacao> getByUtilizadorID(int id_user);
}
