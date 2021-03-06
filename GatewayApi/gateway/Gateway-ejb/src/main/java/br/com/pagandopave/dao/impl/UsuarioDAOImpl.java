package br.com.pagandopave.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.pagandopave.dao.UsuarioDAO;
import br.com.pagandopave.entidade.Usuario;

@Stateless
public class UsuarioDAOImpl implements UsuarioDAO {

	@PersistenceContext(unitName = "pagandopavePU")
	private EntityManager em;


	@Override
	public void cadastrar(Usuario usuario) {
		
		if (em != null) {
            em.persist(usuario);
        }

	}

	@Override
	public Usuario alterar(Usuario usuario) {
		em.merge(usuario);
		return usuario;
	}

	@Override
	public void deletar(Usuario usuario) {
		this.deletar(usuario);

	}

	@Override
	public Usuario consultarPorDeviceId(String deviceId) {
		
		try {
			String qlString = "SELECT u FROM Usuario u WHERE u.deviceId = :deviceId";
			Query query = em.createQuery(qlString);		
			query.setParameter("deviceId", deviceId);
			return (Usuario) query.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		}
		
	}
	
	@Override
	public Usuario consultarPorIdCartao(String idCartao) {
		
		try {
			String qlString = "SELECT u FROM Usuario u WHERE u.idCartao = :idCartao";
			Query query = em.createQuery(qlString);		
			query.setParameter("idCartao", idCartao);
			return (Usuario) query.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		}
		
	}

	@Override
	public List<Usuario> consultarUsuarios() {
		
		try {
			String qlString = "SELECT u FROM Usuario u";
			Query query = em.createQuery(qlString);	
			return (List<Usuario>) query.getResultList();
			
		} catch (NoResultException e) {
			return null;
		}
	}

}
