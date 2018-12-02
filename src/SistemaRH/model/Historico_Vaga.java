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
	public void atualiza_historico(Object o) {
		//Atualizando o historico de estado da vaga
		if(o instanceof Candidato) {
			this.status_vaga.push("Ocupada");
		}else if(o instanceof Funcionario) {
			this.status_vaga.push("Exonerado");
		}
		//Verificar se os nomes dados aos status são mesmo os desejados
	}
	public void exibeHistorico() {
		
	}
	public Stack<String> getStatus_vaga() {
		return status_vaga;
	}
	public Stack<Candidato> getCandidatos_hist() {
		return candidatos_hist;
	}
	
}