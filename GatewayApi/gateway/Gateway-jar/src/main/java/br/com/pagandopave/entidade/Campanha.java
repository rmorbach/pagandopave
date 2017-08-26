package br.com.pagandopave.entidade;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "campanha")
public class Campanha implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7025512690209810625L;

	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	@JoinColumn(name = "id")
	private Parceiro parceiro;

	private String titulo;
	private String descricao;
	private int pessoas;
	private String fotopath;
	private String data_fim;
	private String data_inicio;
	private String hora_inicio;
	private String hora_fim;
	private String valor_original;
	private String valor_final;

	public Campanha() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public int getPessoas() {
		return pessoas;
	}

	public void setPessoas(int pessoas) {
		this.pessoas = pessoas;
	}

	public String getFotopath() {
		return fotopath;
	}

	public void setFotopath(String fotopath) {
		this.fotopath = fotopath;
	}

	public String getData_fim() {
		return data_fim;
	}

	public void setData_fim(String data_fim) {
		this.data_fim = data_fim;
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

	public String getHora_fim() {
		return hora_fim;
	}

	public void setHora_fim(String hora_fim) {
		this.hora_fim = hora_fim;
	}

	public String getValor_original() {
		return valor_original;
	}

	public void setValor_original(String valor_original) {
		this.valor_original = valor_original;
	}

	public String getValor_final() {
		return valor_final;
	}

	public void setValor_final(String valor_final) {
		this.valor_final = valor_final;
	}

	public Parceiro getParceiro() {
		return parceiro;
	}

	public void setParceiro(Parceiro parceiro) {
		this.parceiro = parceiro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data_fim == null) ? 0 : data_fim.hashCode());
		result = prime * result + ((data_inicio == null) ? 0 : data_inicio.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((fotopath == null) ? 0 : fotopath.hashCode());
		result = prime * result + ((hora_fim == null) ? 0 : hora_fim.hashCode());
		result = prime * result + ((hora_inicio == null) ? 0 : hora_inicio.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((parceiro == null) ? 0 : parceiro.hashCode());
		result = prime * result + pessoas;
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		result = prime * result + ((valor_final == null) ? 0 : valor_final.hashCode());
		result = prime * result + ((valor_original == null) ? 0 : valor_original.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Campanha other = (Campanha) obj;
		if (data_fim == null) {
			if (other.data_fim != null)
				return false;
		} else if (!data_fim.equals(other.data_fim))
			return false;
		if (data_inicio == null) {
			if (other.data_inicio != null)
				return false;
		} else if (!data_inicio.equals(other.data_inicio))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (fotopath == null) {
			if (other.fotopath != null)
				return false;
		} else if (!fotopath.equals(other.fotopath))
			return false;
		if (hora_fim == null) {
			if (other.hora_fim != null)
				return false;
		} else if (!hora_fim.equals(other.hora_fim))
			return false;
		if (hora_inicio == null) {
			if (other.hora_inicio != null)
				return false;
		} else if (!hora_inicio.equals(other.hora_inicio))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (parceiro == null) {
			if (other.parceiro != null)
				return false;
		} else if (!parceiro.equals(other.parceiro))
			return false;
		if (pessoas != other.pessoas)
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		if (valor_final == null) {
			if (other.valor_final != null)
				return false;
		} else if (!valor_final.equals(other.valor_final))
			return false;
		if (valor_original == null) {
			if (other.valor_original != null)
				return false;
		} else if (!valor_original.equals(other.valor_original))
			return false;
		return true;
	}

}
