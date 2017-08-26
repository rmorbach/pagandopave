package br.com.pagandopave.impl;

import javax.ejb.Stateless;

import com.google.gson.Gson;

import br.com.pagandopave.api.MainManager;
import br.com.pagandopave.model.InserirCreditoRequest;
import io.swagger.client.api.CartoesApi;

@Stateless
public class MainManagerBean implements MainManager {

	@Override
	public boolean inserirCredito(String input) {
		
		Gson gson = new Gson();

		InserirCreditoRequest req = gson.fromJson(input, InserirCreditoRequest.class);

		req.
		
		return false;
	}

}
