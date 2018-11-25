package SistemaRH.model;

import java.util.ArrayList;
import java.util.TreeSet;

public class Muda_Estado {
	//Classe que administra a classificacao dos candidatos de cada especialidade do concurso
	private Especialidade Concurso_Especialidade;
	private TreeSet<Candidato> cand_classif;
	private String processo_concurso;
	public Muda_Estado(String num_pro,Especialidade esp,ArrayList<Candidato> cands)  {
		this.processo_concurso = num_pro;
		this.Concurso_Especialidade = esp;
		//Verificando se a especialidade dos candidatos condiz com o parametro passado
		for(Candidato c: cands) {
			if(c.getCad_esp().equals(esp)) {
				cand_classif.add(c);
			}
			//Pensar no que fazer quando não houver consistência na especialidade da colecao e candidatos passados como parametro
			else break;
		}
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Concurso_Especialidade == null) ? 0 : Concurso_Especialidade.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Muda_Estado))
			return false;
		Muda_Estado other = (Muda_Estado) obj;
		if (Concurso_Especialidade == null) {
			if (other.Concurso_Especialidade != null)
				return false;
		} else if (!Concurso_Especialidade.equals(other.Concurso_Especialidade))
			return false;
		return true;
	}
	public Especialidade getConcurso_Especialidade() {
		return Concurso_Especialidade;
	}
	public void setConcurso_Especialidade(Especialidade concurso_Especialidade) {
		Concurso_Especialidade = concurso_Especialidade;
	}
	public TreeSet<Candidato> getCand_classif() {
		return cand_classif;
	}
	public void setCand_classif(TreeSet<Candidato> cand_classif) {
		this.cand_classif = cand_classif;
	}
	public String getProcesso_concurso() {
		return processo_concurso;
	}
	public void setProcesso_concurso(String processo_concurso) {
		this.processo_concurso = processo_concurso;
	}
	
}
