package br.com.pagandopave.api;

import javax.ejb.Local;

import br.com.pagandopave.entidade.Usuario;

@Local
public interface UsuarioManager {
	
	public void cadastrar(Usuario usuario);
	public Usuario alterar(Usuario usuario);
	public void deletar(Usuario usuario);
	public Usuario consultarPorDeviceId(String deviceId);

}
