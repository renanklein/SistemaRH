package br.uerj.rh.model;
import java.util.Calendar;

public class Funcionario extends Pessoa{
	private String matricula;
	private Especialidade esp_func;
	private Calendar data_admissao;
	private Calendar data_desligamento;
	private String unidade;
	private int idVaga;
	private Vaga vaga_func;
	private Calendar data_portaria;
	private boolean status;
	private String processo;
	private String lotacao;
	private String regiao;
	private String localizacao;
	private int id_especialidade;
	private String nm_especialidade;
	private String perfil;
	
	public String getLotacao() {
		return lotacao;
	}

	public void setLotacao(String lotacao) {
		this.lotacao = lotacao;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public void setIdVaga(int idVaga) {
		this.idVaga = idVaga;
	}

	public Funcionario(String mat, String chave, String nome, String processo, boolean status, int vaga,int id) {
		super.setChave(chave);
		super.setNome(nome);
		this.matricula = mat;
		this.status = status;
		this.idVaga = vaga;
		this.processo = processo;
		this.id_especialidade = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Especialidade getEsp_func() {
		return esp_func;
	}

	public void setEsp_func(Especialidade esp_func) {
		this.esp_func = esp_func;
	}

	public Calendar getData_admissao() {
		return data_admissao;
	}

	public void setData_admissao(Calendar data_admissao) {
		this.data_admissao = data_admissao;
	}

	public Calendar getData_desligamento() {
		return data_desligamento;
	}

	public void setData_desligamento(Calendar data_desligamento) {
		this.data_desligamento = data_desligamento;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public Vaga getVaga_func() {
		return vaga_func;
	}

	public void setVaga_func(Vaga vaga_func) {
		this.vaga_func = vaga_func;
	}

	public Calendar getData_portaria() {
		return data_portaria;
	}

	public void setData_portaria(Calendar data_portaria) {
		this.data_portaria = data_portaria;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getIdVaga() {
		return idVaga;
	}

	public String getProcesso() {
		return processo;
	}

	public void setProcesso(String processo) {
		this.processo = processo;
	}

	public int getId_especialidade() {
		return id_especialidade;
	}

	public void setId_especialidade(int id_especialidade) {
		this.id_especialidade = id_especialidade;
	}

	public String getNm_especialidade() {
		return nm_especialidade;
	}

	public void setNm_especialidade(String nm_especialidade) {
		this.nm_especialidade = nm_especialidade;
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


}

