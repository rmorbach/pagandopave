package br.com.pagandopave.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.Stateless;

import org.joda.time.LocalDate;

import com.google.gson.Gson;

import br.com.pagandopave.api.AgillitasApiManager;
import br.com.pagandopave.constants.Constants;
import br.com.pagandopave.model.AtivarCartaoRequest;
import br.com.pagandopave.model.ConsultarExtratoRequest;
import br.com.pagandopave.model.ConsultarPortadorRequest;
import br.com.pagandopave.model.ConsultarPortadorResponse;
import br.com.pagandopave.model.ConsultarSaldoRequest;
import br.com.pagandopave.model.InserirCreditoRequest;
import io.swagger.client.ApiException;
import io.swagger.client.api.CartoesApi;
import io.swagger.client.model.ExtratoResponse;
import io.swagger.client.model.NovoCartao;
import io.swagger.client.model.NovoCartaoPortador;
import io.swagger.client.model.NovoCartaoPortadorContato;
import io.swagger.client.model.NovoCartaoPortadorEndereco;
import io.swagger.client.model.PortadorResponse;
import io.swagger.client.model.Saldo;
import io.swagger.client.model.SetNovoCartao;
import io.swagger.client.model.SetSaldo;

@Stateless
public class AgillitasApiManagerBean implements AgillitasApiManager {

	private DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

	@Override
	public String consultarSaldo(String input) {

		Gson gson = new Gson();
		
		CartoesApi apiInstance = new CartoesApi();

		ConsultarSaldoRequest req = gson.fromJson(input, ConsultarSaldoRequest.class);
		
		try {

			Saldo saldo = apiInstance.getSaldo(Constants.CLIENT_ID, Constants.ACCESS_TOKEN, req.getIdCartao());   

			System.out.println(saldo);
			return saldo.getSaldo().getValor().toString();

		} catch (ApiException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}	
	}

	@Override
	public String consultarExtrato(String input) {

		Gson gson = new Gson();

		ConsultarExtratoRequest req = gson.fromJson(input, ConsultarExtratoRequest.class);

		CartoesApi apiInstance = new CartoesApi();

		String response = null;

		try {		

			ExtratoResponse extrato = apiInstance.getExtrato(Constants.CLIENT_ID, Constants.ACCESS_TOKEN, req.getIdCartao(), new LocalDate("2017-08-25"), new LocalDate("2017-08-27"));
			
			response = gson.toJson(extrato);

		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}

	@Override
	public boolean ativarCartao(String input) {

		Gson gson = new Gson();

		AtivarCartaoRequest req = gson.fromJson(input, AtivarCartaoRequest.class);

		CartoesApi apiInstance = new CartoesApi();

		try {

			SetNovoCartao cartao = new SetNovoCartao();

			NovoCartao novoCartao = new NovoCartao();

			NovoCartaoPortador portador = new NovoCartaoPortador();
			NovoCartaoPortadorContato contato = new NovoCartaoPortadorContato();
			NovoCartaoPortadorEndereco endereco = new NovoCartaoPortadorEndereco();

			endereco.setCidade(req.getCidade());
			endereco.setCodigoPostal(req.getCep());
			endereco.setComplemento(req.getComplemento());
			endereco.setEstado(req.getEstado());
			endereco.setLogradouro(req.getLogradouro());
			endereco.setPais(req.getPais());

			contato.setEmail(req.getEmail());
			contato.setTelCelular(req.getCelular());
			contato.setTelResidencial(req.getResidencial());

			portador.setCpf(req.getCpf());
			portador.setNome(req.getNome());
			portador.setSobrenome(req.getSobrenome());
			portador.setDataNascimento(new LocalDate(df.parse(req.getDataNascimento()).getTime()));             
			portador.setContato(contato);
			portador.setEndereco(endereco);

			novoCartao.setContrasenha(req.getContrasenha());
			novoCartao.setIdCartao(req.getIdCartao());
			novoCartao.setValor(Double.parseDouble(req.getValor()));
			novoCartao.setPortador(portador);

			cartao.setCartao(novoCartao);       

			apiInstance.criarCartao(Constants.CLIENT_ID, Constants.ACCESS_TOKEN, cartao);
			
			return true;
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean inserirCredito(String input) {

		Gson gson = new Gson();

		InserirCreditoRequest req = gson.fromJson(input, InserirCreditoRequest.class);

		CartoesApi apiInstance = new CartoesApi();		

		try {
			SetSaldo setSaldo = new SetSaldo();
			setSaldo.setSaldo(Double.parseDouble(req.getCredito()));

			apiInstance.atualizarSaldo(Constants.CLIENT_ID, Constants.ACCESS_TOKEN, req.getIdCartao(), setSaldo);

			return true;
			
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public String consultarPortador(String input) {

		Gson gson = new Gson();

		ConsultarPortadorRequest req = gson.fromJson(input, ConsultarPortadorRequest.class);

		CartoesApi apiInstance = new CartoesApi();	

		try {
			PortadorResponse resp = apiInstance.getPortador(Constants.CLIENT_ID, Constants.ACCESS_TOKEN, req.getIdCartao());

			ConsultarPortadorResponse response = new ConsultarPortadorResponse();
			response.setCelular(resp.getPortador().getCelular());
			response.setEmail(resp.getPortador().getEmail());
			response.setNome(resp.getPortador().getNome());

			return gson.toJson(response);

		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
