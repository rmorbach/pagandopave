package br.com.pagandopave.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.pagandopave.entidade.Campanha;

@Local
public interface CampanhaDAO {
	
	List<Campanha> listar();

}
