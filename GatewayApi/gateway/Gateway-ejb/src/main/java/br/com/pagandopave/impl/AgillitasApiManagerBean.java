package br.com.pagandopave.impl;

import br.com.pagandopave.api.AgillitasApiManager;
import br.com.pagandopave.constants.Constants;
import io.swagger.client.ApiException;
import io.swagger.client.api.CartoesApi;
import io.swagger.client.model.Saldo;

public class AgillitasApiManagerBean implements AgillitasApiManager {

	@Override
	public String consultarSaldo(String idCartao) {
		
		CartoesApi apiInstance = new CartoesApi();
        String clientId = Constants.CLIENT_ID; // String | Identificador do cliente utilizado na autenticação.
        String accessToken = Constants.ACCESS_TOKEN; // String | Token de acesso utilizado na autenticação.
        
        try {
        	
        	Saldo saldo = apiInstance.getSaldo(clientId, accessToken, idCartao);   
        	
        	System.out.println(saldo);
        	return saldo.getSaldo().getValor().toString();
        	
            
        } catch (ApiException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
		
		
	}

}
