package SistemaRH.model;

import java.util.Comparator;

public class Candidato extends Pessoa {
	private String num_inscricao;
	private Especialidade cad_esp;
	private int classificacao;
	private String status;
	
	public Especialidade getCad_esp() {
		return cad_esp;
	}
	
}
