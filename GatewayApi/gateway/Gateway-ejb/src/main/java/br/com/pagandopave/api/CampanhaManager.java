package br.com.pagandopave.api;

import javax.ejb.Local;

@Local
public interface CampanhaManager {

	public String buscarCampanhas();
}
