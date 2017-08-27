package br.com.pagandopave.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.pagandopave.dao.PaiDAO;
import br.com.pagandopave.entidade.Pai;

@Stateless
public class PaiDAOImpl implements PaiDAO {

	@PersistenceContext(unitName = "pagandopavePU")
	private EntityManager em;
	
	@Override
	public Pai alterar(Pai pai) {
		
		em.merge(pai);
		return pai;
	}

	@Override
	public Pai consultarPorIdPai(String idPai) {
		
		try {
			String qlString = "SELECT p FROM Pai p WHERE p.idPai = :idPai";
			Query query = em.createQuery(qlString);		
			query.setParameter("idPai", Long.parseLong(idPai));
			return (Pai) query.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		}		
	}

}
