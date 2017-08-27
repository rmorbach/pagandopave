package br.com.pagandopave.entidade;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pais")
public class Pai implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3202378697339900277L;

	@Id
	@GeneratedValue
	private Long idPai;
	private String pushToken;
	private String nome;

	public Pai() {

	}

	public Long getIdPai() {
		return idPai;
	}

	public void setIdPai(Long idPai) {
		this.idPai = idPai;
	}

	public String getPushToken() {
		return pushToken;
	}

	public void setPushToken(String pushToken) {
		this.pushToken = pushToken;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((idPai == null) ? 0 : idPai.hashCode());

		result = prime * result + ((nome == null) ? 0 : nome.hashCode());

		result = prime * result + ((pushToken == null) ? 0 : pushToken.hashCode());

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
		Pai other = (Pai) obj;

		if (idPai == null) {
			if (other.idPai != null)
				return false;
		} else if (!idPai.equals(other.idPai))
			return false;

		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;

		if (pushToken == null) {
			if (other.pushToken != null)
				return false;
		} else if (!pushToken.equals(other.pushToken))
			return false;

		return true;
	}

}
