package br.com.pagandopave.dao;

import javax.ejb.Local;

import br.com.pagandopave.entidade.Pai;

@Local
public interface PaiDAO {

	/**
	 * Alterar usuário por telefone.
	 *
	 * @param usuario the usuario
	 * @return the Tipoticket
	 */
	Pai alterar(Pai usuario);
	
	Pai consultarPorIdPai(String idPai);
}
