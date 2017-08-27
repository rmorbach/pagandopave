package br.com.pagandopave.entidade;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parceiro")
public class Parceiro implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2898494393896399579L;

	@Id
	@GeneratedValue
	private Long idParceiro;
	private String cnpj;
	private String razaosocial;
	private String endereco;
	private String login;
	private String senha;
	private String logo;

	public Parceiro() {

	}

	public Long getIdParceiro() {
		return idParceiro;
	}



	public void setIdParceiro(Long idParceiro) {
		this.idParceiro = idParceiro;
	}



	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazaosocial() {
		return razaosocial;
	}

	public void setRazaosocial(String razaosocial) {
		this.razaosocial = razaosocial;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	

}
