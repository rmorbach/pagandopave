package br.com.pagandopave.model;

public class NotificationRequest {

	private String titulo;
	private String descricao;
	private String data_inicio;
	private String hora_inicio;
	private String data_fim;
	private String hora_fim;
	private String pessoas;
	private String fotoPath;
	private String id_parceiro;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getData_inicio() {
		return data_inicio;
	}
	public void setData_inicio(String data_inicio) {
		this.data_inicio = data_inicio;
	}
	public String getHora_inicio() {
		return hora_inicio;
	}
	public void setHora_inicio(String hora_inicio) {
		this.hora_inicio = hora_inicio;
	}
	public String getData_fim() {
		return data_fim;
	}
	public void setData_fim(String data_fim) {
		this.data_fim = data_fim;
	}
	public String getHora_fim() {
		return hora_fim;
	}
	public void setHora_fim(String hora_fim) {
		this.hora_fim = hora_fim;
	}
	public String getPessoas() {
		return pessoas;
	}
	public void setPessoas(String pessoas) {
		this.pessoas = pessoas;
	}
	public String getFotoPath() {
		return fotoPath;
	}
	public void setFotoPath(String fotoPath) {
		this.fotoPath = fotoPath;
	}
	public String getId_parceiro() {
		return id_parceiro;
	}
	public void setId_parceiro(String id_parceiro) {
		this.id_parceiro = id_parceiro;
	}




}
