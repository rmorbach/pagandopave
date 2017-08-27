package br.com.pagandopave.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.google.gson.Gson;

import br.com.pagandopave.api.CampanhaManager;
import br.com.pagandopave.dao.CampanhaDAO;

@Stateless
public class CampanhaManagerBean implements CampanhaManager {

	@EJB
	CampanhaDAO campanhaDao;
	
	@Override
	public String buscarCampanhas() {
		
		Gson gson = new Gson();
		
		String response = gson.toJson(campanhaDao.listar());
		
		return response;
	}

}
