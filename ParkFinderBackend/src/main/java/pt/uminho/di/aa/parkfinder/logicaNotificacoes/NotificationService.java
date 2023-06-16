package pt.uminho.di.aa.parkfinder.logicaNotificacoes;

import java.util.List;
import java.util.function.Predicate;

public interface NotificationService {

	/**
	 * 
	 * @param n
	 */
	void addNotificacao(Notificacao n);

	/**
	 * 
	 * @param n
	 */
	void removerNotificacao(Notificacao n);

	/**
	 * 
	 * @param id_user
	 */
	List<Notificacao> getNotificacoes(int id_user);

	/**
	 * 
	 * @param id_user
	 */
	List<Notificacao> getNotificacoesNaoLidas(int id_user);

	/**
	 * 
	 * @param id_user
	 * @param predicate
	 */
	List<Notificacao> getNotificacoes(int id_user, Predicate<Notificacao> predicate);

	/**
	 * 
	 * @param predicate
	 */
	void removerNotificacoes(Predicate<Notificacao> predicate);

	/**
	 * 
	 * @param id_notificacao
	 */
	void setNotificacaoLida(int id_notificacao);
}