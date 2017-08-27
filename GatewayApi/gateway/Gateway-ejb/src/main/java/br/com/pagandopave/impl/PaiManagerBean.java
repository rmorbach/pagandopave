package br.com.pagandopave.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.pagandopave.api.PaiManager;
import br.com.pagandopave.dao.PaiDAO;
import br.com.pagandopave.entidade.Pai;

@Stateless
public class PaiManagerBean implements PaiManager{

	@EJB
	PaiDAO dao;
	
	@Override
	public Pai consultarPorIdPai(String idPai) {

		return dao.consultarPorIdPai(idPai);
	}

	@Override
	public void alterar(Pai pai) {
		dao.alterar(pai);
	}

}
