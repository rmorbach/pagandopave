package br.com.pagandopave.api;

import javax.ejb.Local;

import br.com.pagandopave.entidade.Pai;

@Local
public interface PaiManager {

	public Pai consultarPorIdPai(String idPai);
	public void alterar(Pai pai);
	
	
}
