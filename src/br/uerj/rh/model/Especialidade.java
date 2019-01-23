package br.uerj.rh.model;

public class Especialidade {
	private String perfil;
	private String nome_espec;
	private String regiao;
	private int id_concurso;
	private int id_especialidade;
	private int vagas_iniciais;
	private int vagas_amplicadas;
	private int qtd_aprovados;
	private int vacancias;
	private int qtd_nomeados;
	private int qtd_exonerados;
	private int cand_restantes;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome_espec == null) ? 0 : nome_espec.hashCode());
		result = prime * result + ((perfil == null) ? 0 : perfil.hashCode());
		result = prime * result + ((regiao == null) ? 0 : regiao.hashCode());
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
		Especialidade other = (Especialidade) obj;
		if((this.nome_espec == null) ? (other.nome_espec != null) : !this.nome_espec.equals(other.nome_espec)){
			return false;
		}else if((this.perfil == null) ? (other.perfil != null) : !this.perfil.equals(other.perfil)) {
			return false;
		}else if((this.regiao == null) ? (other.regiao != null) : !this.regiao.equals(other.regiao)) {
			return false;
		}
		return true;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public String getNome_espec() {
		return nome_espec;
	}
	public void setNome_espec(String nome_espec) {
		this.nome_espec = nome_espec;
	}
	public String getRegiao() {
		return regiao;
	}
	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}
	public int getVagas_iniciais() {
		return vagas_iniciais;
	}
	public void setVagas_iniciais(int vagas_iniciais) {
		this.vagas_iniciais = vagas_iniciais;
	}
	public int getVagas_amplicadas() {
		return vagas_amplicadas;
	}
	public void setVagas_amplicadas(int vagas_amplicadas) {
		this.vagas_amplicadas = vagas_amplicadas;
	}
	public int getQtd_aprovados() {
		return qtd_aprovados;
	}
	public void setQtd_aprovados(int qtd_aprovados) {
		this.qtd_aprovados = qtd_aprovados;
	}
	public int getVacancias() {
		return vacancias;
	}
	public void setVacancias(int vacancias) {
		this.vacancias = vacancias;
	}
	public int getQtd_nomeados() {
		return qtd_nomeados;
	}
	public void setQtd_nomeados(int qtd_nomeados) {
		this.qtd_nomeados = qtd_nomeados;
	}
	public int getQtd_exonerados() {
		return qtd_exonerados;
	}
	public void setQtd_exonerados(int qtd_exonerados) {
		this.qtd_exonerados = qtd_exonerados;
	}
	public int getCand_restantes() {
		return cand_restantes;
	}
	public void setCand_restantes(int cand_restantes) {
		this.cand_restantes = cand_restantes;
	}
	public int getId_especialidade() {
		return id_especialidade;
	}
	public void setId_especialidade(int id_especialidade) {
		this.id_especialidade = id_especialidade;
	}
	public int getId_concurso() {
		return id_concurso;
	}
	public void setId_concurso(int id_concurso) {
		this.id_concurso = id_concurso;
	}
	
	
}