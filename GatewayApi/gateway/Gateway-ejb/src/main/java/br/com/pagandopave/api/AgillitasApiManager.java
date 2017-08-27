package br.com.pagandopave.api;

import javax.ejb.Local;

@Local
public interface AgillitasApiManager {
	
	public String consultarSaldo(String input);
	
	public String consultarExtrato(String input);
	
	public boolean ativarCartao(String input);
	
	public boolean inserirCredito(String input);
	
	public String consultarPortador(String input);
	
}
