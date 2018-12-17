package SistemaRH.model;

public class Vaga {
	private int num_vaga;
	private String status_vaga;
	private String num_processo_conc;
	private Especialidade vaga_espec;
	private Historico_Vaga hist;
	private String especialidade;
	
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
	public int getNum_vaga() {
		return num_vaga;
	}
	public void setNum_vaga(int num_vaga) {
		this.num_vaga = num_vaga;
	}
	
	
}