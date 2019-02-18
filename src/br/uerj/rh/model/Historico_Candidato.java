package br.uerj.rh.model;

import java.util.Calendar;
import java.util.Stack;

public class Historico_Candidato {
	private Especialidade esp;
	private String chave;
	private Stack<String> hist_status;
	private Calendar mudanca_situacao;
	private boolean ap_documentacao;
	
	public Historico_Candidato(Candidato c) {
		this.chave = c.getChave();
		this.esp = c.getCad_esp();
		this.hist_status.push(c.getStatus());
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public Especialidade getEsp() {
		return esp;
	}

	public void setEsp(Especialidade esp) {
		this.esp = esp;
	}


	public Stack<String> getHist_status() {
		return hist_status;
	}

	public void setHist_status(Stack<String> hist_status) {
		this.hist_status = hist_status;
	}

	public Calendar getMudanca_situacao() {
		return mudanca_situacao;
	}

	public void setMudanca_situacao(Calendar mudanca_situacao) {
		this.mudanca_situacao = mudanca_situacao;
	}

	public boolean isAp_documentacao() {
		return ap_documentacao;
	}

	public void setAp_documentacao(boolean ap_documentacao) {
		this.ap_documentacao = ap_documentacao;
	}
	
	
}
