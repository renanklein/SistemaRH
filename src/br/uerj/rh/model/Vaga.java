package br.uerj.rh.model;

import java.util.Date;

public class Vaga {
	private int id_vaga;
	private int id_concurso;
	private String status_vaga;
	private String num_processo_conc;
	private Especialidade vaga_espec;
	private Historico_Vaga hist;
	private String especialidade;
	private int num_vaga;
	private String perfil;
	private String regiao;
	private String unidade;
	private String localizacao;
	private String lotacao;
	private Date validade;
	
	
	
	public Vaga(int num_vaga, int id_concurso, String status_vaga, String num_processo_conc, String perfil,
			String especialidade, String regiao, String unidade, String localizacao, String lotacao, Date validade) {
		super();
		this.num_vaga = num_vaga;
		this.id_concurso = id_concurso;
		this.status_vaga = status_vaga;
		this.num_processo_conc = num_processo_conc;
		this.perfil = perfil;
		this.especialidade = especialidade;
		this.regiao = regiao;
		this.unidade = unidade;
		this.localizacao = localizacao;
		this.lotacao = lotacao;
		this.validade = validade;
	}
	public String getStatus_vaga() {
		return status_vaga;
	}
	public void setStatus_vaga(String status_vaga) {
		this.status_vaga = status_vaga;
	}
	public String getNum_processo_conc() {
		return num_processo_conc;
	}
	public void setNum_processo_conc(String num_processo_conc) {
		this.num_processo_conc = num_processo_conc;
	}
	public int getNum_vaga() {
		return num_vaga;
	}
	public void setNum_vaga(int num_vaga) {
		this.num_vaga = num_vaga;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public String getRegiao() {
		return regiao;
	}
	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}
	public String getUnidade() {
		return unidade;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	public String getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	public String getLotacao() {
		return lotacao;
	}
	public void setLotacao(String lotacao) {
		this.lotacao = lotacao;
	}
	public Date getValidade() {
		return validade;
	}
	public void setValidade(Date validade) {
		this.validade = validade;
	}
	public void setId_concurso(int id_concurso) {
		this.id_concurso = id_concurso;
	}
	public void setHist(Historico_Vaga hist) {
		this.hist = hist;
	}
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	public Vaga(int id_concurso, int num_vaga, String status_vaga, String num_processo_conc, String especialidade) {
		super();
		this.num_vaga = num_vaga;
		this.id_concurso = id_concurso;
		this.status_vaga = status_vaga;
		this.num_processo_conc = num_processo_conc;
		this.especialidade = especialidade;
	}
	public Vaga () {
		
	}
	public int getId_concurso() {
		return id_concurso;
	}
	public String getEspecialidade() {
		return especialidade;
	}
	public String getStatus() {
		return this.status_vaga;
	}
	public Historico_Vaga getHist() {
		return this.hist;
	}
	public void setStatus(String status) {
		this.status_vaga = status;
	}
	public String getProcesso(){
		return this.num_processo_conc;
	}
	public void setProcesso(String num_proc) {
		this.num_processo_conc = num_proc;
	}
	public Especialidade getVaga_espec() {
		return vaga_espec;
	}

	public void setVaga_espec(Especialidade vaga_espec) {
		this.vaga_espec = vaga_espec;
	}
	public void setHistorico(Historico_Vaga hist) {
		this.hist = hist;
	}
	public int getId_vaga() {
		return id_vaga;
	}
	public void setId_vaga(int id_vaga) {
		this.id_vaga = id_vaga;
	}
	
	
}