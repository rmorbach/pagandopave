package br.com.pagandopave.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.pagandopave.dao.BaseDAO;

public class BaseDAOImpl<T> implements BaseDAO<T> {
	
	/** The entity manager. */
	@PersistenceContext
	private EntityManager entityManager;

	/** The classe persistente. */
	private Class<T> classePersistente;

	/**
	 * Instantiates a new base dao impl.
	 */
	@SuppressWarnings("unchecked")
	public BaseDAOImpl() {
		this.classePersistente = (Class<T>) ((ParameterizedType) getClass()
			.getGenericSuperclass()).getActualTypeArguments()[0];
	}


	/* (non-Javadoc)
	 * @see br.com.arremate.facil.dao.BaseDAO#inserir(java.lang.Object)
	 */
	@Override
	public void insert(final T entidade) {
		this.entityManager.persist(entidade);
		//this.entityManagerFactory.flush();
	}

	/* (non-Javadoc)
	 * @see br.com.arremate.facil.dao.BaseDAO#remover(java.lang.Object)
	 */
	@Override
	public void remove(final T entidade) {
		final Object obj = entityManager.merge(entidade);
		this.entityManager.remove(obj);
	}

	/* (non-Javadoc)
	 * @see br.com.arremate.facil.dao.BaseDAO#removerPorId(java.lang.Integer)
	 */
	@Override
	public void removeById(final Integer id) {
		final Object obj = this.findById(id);
		this.entityManager.remove(obj);
		//this.entityManager.flush();
	}

	/* (non-Javadoc)
	 * @see br.com.arremate.facil.dao.BaseDAO#buscarPorId(java.lang.Object)
	 */
	@Override
	public T findById(final Object pk) {
		return this.entityManager.find(getClassePersistente(), pk);
	}

	/* (non-Javadoc)
	 * @see br.com.arremate.facil.dao.BaseDAO#buscarTodos()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		final Query query = entityManager.createQuery("select T from " + getClassePersistente().getName() + " T ");
		return query.getResultList();
	}


	/* (non-Javadoc)
	 * @see br.com.arremate.facil.dao.BaseDAO#atualizar(java.lang.Object)
	 */
	@Override
	public T update(final T entidade) {
		return this.entityManager.merge(entidade);
	}

	/* (non-Javadoc)
	 * @see br.com.arremate.facil.dao.BaseDAO#getClassePersistente()
	 */
	@Override
	public Class<T> getClassePersistente() {
		return classePersistente;
	}

	/* (non-Javadoc)
	 * @see br.com.arremate.facil.dao.BaseDAO#setClassePersistente(java.lang.Class)
	 */
	@Override
	public void setClassePersistente(final Class<T> classePersistente) {
		this.classePersistente = classePersistente;
	}

	/* (non-Javadoc)
	 * @see br.com.arremate.facil.dao.BaseDAO#getEntityManager()
	 */
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/* (non-Javadoc)
	 * @see br.com.arremate.facil.dao.BaseDAO#setEntityManager(javax.persistence.EntityManager)
	 */
	@Override
	public void setEntityManager(final EntityManager entityManagerFactory) {
		this.entityManager = entityManagerFactory;
	}

}
