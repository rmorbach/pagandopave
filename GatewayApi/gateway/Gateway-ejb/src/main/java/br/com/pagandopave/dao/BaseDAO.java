package br.com.pagandopave.dao;

import java.util.List;

import javax.persistence.EntityManager;

public interface BaseDAO<T> {
	
	/**
	 * Salva a entidade.
	 * @param entidade T
	 */
	void insert(final T entidade);

	/**
	 * Exclui uma entidade.
	 * @param entidade T
	 */
	void remove(final T entidade);

	/**
	 * Remover por id.
	 *
	 * @param id the id
	 */
	void removeById(final Integer id);

	/**
	 * Busca uma determinada entidade.
	 * Recupera os campos que nao estao preechidos.
	 *
	 * @param pk the pk
	 * @return entidade T
	 */
	T findById(final Object pk);


	/**
	 * Procura todas as entidades persistidas no banco de dados.
	 *
	 * @return List<T> listaDeEntidades.
	 */
	List<T> findAll();


	/**
	 * Atualiza uma entidade que ja foi persistida.
	 *
	 * @param entidade T
	 */
	T update(final T entidade);

	/**
	 * Retorna a classe de persitencia.
	 *
	 * @return classePersistente
	 */
	Class<T> getClassePersistente();

	/**
	 * Define a classe de persistencia.
	 *
	 * @param classePersistente Class<T>
	 */
	void setClassePersistente(final Class<T> classePersistente);

	/**
	 * Retorna uma instancia de Entity Manager.
	 *
	 * @return entityManagerFactory
	 */
	EntityManager getEntityManager();

	/**
	 * Define uma instancia de Entity Manager.
	 *
	 * @param entityManager the new entity manager
	 */
	void setEntityManager(final EntityManager entityManager);

}
