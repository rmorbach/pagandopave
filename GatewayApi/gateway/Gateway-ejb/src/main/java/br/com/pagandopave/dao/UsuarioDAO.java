package br.com.pagandopave.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.pagandopave.entidade.Usuario;

@Local
public interface UsuarioDAO{
	
	/**
	 * Cadastrar.
	 *
	 * @param usuario the usuario
	 */
	void cadastrar(Usuario usuario);
	
	/**
	 * Alterar usuário por telefone.
	 *
	 * @param usuario the usuario
	 * @return the Tipoticket
	 */
	Usuario alterar(Usuario usuario);
	
	/**
	 * deletar.
	 *
	 * @param usuario the usuario
	 * 
	 */
	void deletar(Usuario usuario);
	
	/**
	 * Consultar por nickName.
	 *
	 * @param telefone the nickName
	 * @return the usuario
	 */
	Usuario consultarPorDeviceId(String nickName);
	
	/**
	 * Consultar por idCartao.
	 *
	 * @param idCartao
	 * @return the usuario
	 */
	Usuario consultarPorIdCartao(String idCartao);
	
	List<Usuario> consultarUsuarios();

}
