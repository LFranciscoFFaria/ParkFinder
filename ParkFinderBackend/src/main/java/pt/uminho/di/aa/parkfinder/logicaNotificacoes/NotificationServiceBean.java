package pt.uminho.di.aa.parkfinder.logicaNotificacoes;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NotificationServiceBean implements NotificationService {

	/**
	 * 
	 * @param n
	 */
	public void addNotificacao(Notificacao n) {
		//TODO
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param n
	 */
	public void removerNotificacao(Notificacao n) {
		//TODO
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_user
	 */
	public List<Notificacao> getNotificacoes(int id_user) {
		//TODO
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_user
	 */
	public List<Notificacao> getNotificacoesNaoLidas(int id_user) {
		//TODO
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_user
	 * @param predicate
	 */
	public List<Notificacao> getNotificacoes(int id_user, Predicate<Notificacao> predicate) {
		//TODO
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param predicate
	 */
	public void removerNotificacoes(Predicate<Notificacao> predicate) {
		//TODO
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param id_notificacao
	 */
	public void setNotificacaoLida(int id_notificacao) {
		//TODO
		throw new UnsupportedOperationException();
	}

}