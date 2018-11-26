package SistemaRH.model;

import java.sql.Date;

public abstract class Pessoa {
	private String nome;
	private Date data_nascimento;
	private String CPF;
	private String etnia;
	private boolean PCD;
	private String cod_concurso;
	protected String status;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getEtnia() {
		return etnia;
	}

	public void setEtnia(String etnia) {
		this.etnia = etnia;
	}

	public boolean isPCD() {
		return PCD;
	}

	public void setPCD(boolean pCD) {
		PCD = pCD;
	}

	public String getCod_concurso() {
		return cod_concurso;
	}

	public void setCod_concurso(String cod_concurso) {
		this.cod_concurso = cod_concurso;
	}

	public String getStatus() {
		return status;
	}
	public abstract void MudarStatus(String status);

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CPF == null) ? 0 : CPF.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Pessoa))
			return false;
		Pessoa other = (Pessoa) obj;
		if (CPF == null) {
			if (other.CPF != null)
				return false;
		} else if (!CPF.equals(other.CPF))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
}
