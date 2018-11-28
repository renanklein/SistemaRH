package SistemaRH.model;

import java.util.Comparator;

public class Candidato extends Pessoa implements Comparator<Object>{
	private String num_inscricao;
	private Especialidade cad_esp;
	private int classificacao;
	private String status;
	
	public Funcionario deferir_matricula(String status) {
		Funcionario novoFunc = new Funcionario();
		
		return novoFunc;
	}
	public Especialidade getCad_esp() {
		return cad_esp;
	}
	
}
