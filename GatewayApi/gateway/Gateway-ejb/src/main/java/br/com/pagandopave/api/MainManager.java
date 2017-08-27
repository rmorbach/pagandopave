package br.com.pagandopave.api;

import javax.ejb.Local;

@Local
public interface MainManager {

	public boolean inserirCredito(String input);
	
	public boolean pedirDinheiro(String input);
	
	public String buscarCampanhas();
	
	public boolean registerToken(String input);
}
