package SistemaRH.model;

import java.util.Stack;

public class Historico_Vaga {
	private Stack<String> status_vaga;
	private Stack<Candidato> candidatos_hist;
	
	public Historico_Vaga(Vaga v) {
		this.status_vaga = new Stack<String>();
		this.candidatos_hist = new Stack<Candidato>();
		status_vaga.push(v.getStatus());
	}
	public void atualiza_historico(Candidato c) {
		
	}
	public void exibeHistorico() {
		
	}
	
}