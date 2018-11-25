package SistemaRH.model;

import java.util.Comparator;

public class Candidato extends Pessoa implements Comparator{
	private String num_inscricao;
	private Especialidade cad_esp;
	private int classificacao;
	public Funcionario deferir_matricula(String status) {
		Funcionario novoFunc = new Funcionario();
		
		return novoFunc;
	}
	@Override
	public void MudarStatus(String status) {
		// TODO Auto-generated method stub
		this.status = status;
		
	}
	// Para a implementacao de collections
	@Override
	public int compare(Object arg0, Object arg1) {
		if(arg0 instanceof Candidato && arg1 instanceof Candidato) {
			Candidato c1 = (Candidato) arg0;
			Candidato c2 = (Candidato) arg1;
			return c1.getClassificacao() - c2.getClassificacao();
		}
		
		return -1;
	}
	public int getClassificacao() {
		return this.classificacao;
	}
	public Especialidade getCad_esp() {
		return cad_esp;
	}
}
