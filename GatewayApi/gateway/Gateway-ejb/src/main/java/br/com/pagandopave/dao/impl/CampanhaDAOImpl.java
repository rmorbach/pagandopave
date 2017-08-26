package br.com.pagandopave.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.pagandopave.dao.CampanhaDAO;
import br.com.pagandopave.entidade.Campanha;

@Stateless
public class CampanhaDAOImpl implements CampanhaDAO{
	
	@PersistenceContext(unitName = "pagandopavePU")
	private EntityManager em;

	@Override
	public List<Campanha> listar() {
		try {
			String qlString = "SELECT c FROM Campanha";
			Query query = em.createQuery(qlString);	
			return (List<Campanha>) query.getResultList();
			
		} catch (NoResultException e) {
			return null;
		}
	}

}
