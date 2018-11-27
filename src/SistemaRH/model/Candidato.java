package SistemaRH.model;

import java.util.Comparator;

public class Candidato extends Pessoa{
	private String num_inscricao;
	private Especialidade cad_esp;
	private int classificacao;
	@Override
	public void setStatus(String status) {
		// TODO Auto-generated method stub
		this.status = status;
		
	}
	
	public int getClassificacao() {
		return this.classificacao;
	}
	public Especialidade getCad_esp() {
		return cad_esp;
	}
	
}
