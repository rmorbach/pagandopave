package br.com.pagandopave.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.pagandopave.api.UsuarioManager;
import br.com.pagandopave.dao.UsuarioDAO;
import br.com.pagandopave.entidade.Usuario;

@Stateless
public class UsuarioManagerBean implements UsuarioManager{
	
	@EJB
	private UsuarioDAO dao;

	@Override
	public void cadastrar(Usuario usuario) {
		dao.cadastrar(usuario);		
	}

	@Override
	public Usuario alterar(Usuario usuario) {
		dao.alterar(usuario);
		return usuario;
	}

	@Override
	public void deletar(Usuario usuario) {
		dao.deletar(usuario);		
	}

	@Override
	public Usuario consultarPorDeviceId(String deviceId) {
		Usuario usuario = dao.consultarPorDeviceId(deviceId);
		return usuario;
	}
	
	

}
