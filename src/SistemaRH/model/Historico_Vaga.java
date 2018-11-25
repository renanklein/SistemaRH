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
	public void atualiza_historico(Candidato c_sele) {
		//Atualizando o historico de estado da vaga
		if(status_vaga.peek().equals("Desocupado")) {
			this.status_vaga.push("Ocupada");
			//Colocando o candidato selecionado para a vaga
			this.candidatos_hist.push(c_sele);
		}else if(status_vaga.peek().equals("Ocupada")); // Para o caso de Uso RH4
	}
	public void exibeHistorico() {
		
	}
	
}