package br.uerj.rh.model;

import java.sql.Date;
import java.util.Calendar;

public abstract class Pessoa {
	private String nome;
	private Calendar data_nascimento;
	private String chave;
	private String CPF;
	private String etnia;
	private boolean PCD;
	private String cod_concurso;
		
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calendar getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(Calendar data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
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
	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}
	
}
