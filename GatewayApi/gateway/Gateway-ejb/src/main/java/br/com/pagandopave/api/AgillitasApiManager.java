package br.com.pagandopave.api;

import javax.ejb.Local;

@Local
public interface AgillitasApiManager {

	public String consultarSaldo(String idCartao);
	
	public String consultarExtrato(String idCartao);
	
	public boolean ativarCartao(String input);
	
	public boolean inserirCredito(String input);
	
	public String consultarPortador(String input);
	
}
