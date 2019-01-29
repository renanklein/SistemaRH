package br.uerj.rh.model;

public class Vaga {
	private int id_vaga;
	private int id_concurso;
	private String status_vaga;
	private String num_processo_conc;
	private Especialidade vaga_espec;
	private Historico_Vaga hist;
	private String especialidade;
	
	
	
	public Vaga(int id_concurso, int id_vaga, String status_vaga, String num_processo_conc, String especialidade) {
		super();
		this.id_vaga = id_vaga;
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