package pt.uminho.di.aa.parkfinder.logicaNotificacoes;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NotificationServiceBean implements NotificationService {

	private final NotificaoDAO notificaoDAO;

	public NotificationServiceBean(NotificaoDAO notificaoDAO) {this.notificaoDAO = notificaoDAO;}

	/**
	 * Persiste a notificação na base de dados.
	 * @param n notificação a ser persistida na base de dados
	 */
	public void addNotificacao(Notificacao n) throws Exception{
		if(n == null)
			throw new Exception("A notificação não pode ser nula.");
		if(n.getUtilizador() == null)
			throw new Exception("Notificação precisa de estar associada a um utilizador.");
		notificaoDAO.save(n);
	}

	/**
	 * Remove o notificação com o id especificado da base de dados.
	 * @param id_notificacao identificador da notificação
	 */
	public void removerNotificacao(int id_notificacao) throws Exception{
		if(!notificaoDAO.existsById(id_notificacao))
			throw new Exception("A notificação não existe!");
		notificaoDAO.deleteById(id_notificacao);
	}

	/**
	 * Devolve as notificações associadas ao identificador de utilizador especificado.
	 * @param id_user identificador do utilizador
	 * @return Retorna a lista de notificações do utilizador.
	 */
	public List<Notificacao> getNotificacoes(int id_user) {
		return notificaoDAO.findAllByUtilizadorID(id_user);
	}

	/**
	 * Devolve as notificações não lidas associadas ao identificador de utilizador especificado.
	 * @param id_user identificador do utilizador
	 * @return Retorna a lista de notificações não lidas do utilizador.
	 */
	public List<Notificacao> getNotificacoesNaoLidas(int id_user) {
		return notificaoDAO.findAllByUtilizadorIDAndLida(id_user, false);
	}

	/**
	 * Devolve as notificações que respeitam determinado predicado
	 * associadas ao identificador de utilizador especificado.
	 * @param id_user identificador do utilizador
	 * @param predicate predicado a partir do qual as notificações vão ser filtradas
	 * @return Retorna a lista de notificações do utilizador que cumprem o predicado.
	 */
	public List<Notificacao> getNotificacoes(int id_user, Predicate<Notificacao> predicate) {
		List<Notificacao> notificacoes = notificaoDAO.findAllByUtilizadorID(id_user);
		if(notificacoes.size() > 0) {
			notificacoes.removeIf(notificacao -> !predicate.test(notificacao));
		}
		return notificacoes;
	}

	/**
	 * Remove as notificações que respeitam determinado predicado da base de dados.
	 * @param predicate predicado a partir do qual as notificações vão ser removidas da base de dados
	 */
	public void removerNotificacoes(Predicate<Notificacao> predicate) {
		List<Notificacao> notificacoes = notificaoDAO.findAll();
		if(notificacoes.size()>0) {
			notificacoes.removeIf(predicate);
		}
		notificaoDAO.deleteAll(notificacoes);
	}

	/**
	 * Marca a notificação com identificador passado por argumento como lida
	 * @param id_notificacao identificador da notificação
	 */
	public void setNotificacaoLida(int id_notificacao) throws Exception{
		Notificacao notificacao = notificaoDAO.findById(id_notificacao).orElse(null);
		if(notificacao == null)
			throw new Exception("A notificação não existe!");
		notificacao.setLida(true);
		notificaoDAO.save(notificacao);
	}

}