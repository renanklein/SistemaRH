package br.uerj.rh.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

import br.uerj.rh.BDconfig.DAO_Concurso;

public class Concurso {
	private String num_processo;
	private String descricao;
	//private Calendar DT_realizacao;
	private Date DT_realizacao;
	//private Calendar DT_validade;
	private Date DT_validade;
	private Date DT_resultfinal;
	private Set<Muda_Estado> cand_espec_classif;
	private HashMap<Especialidade,ArrayList<Vaga>> vagas_conc;
	private String status;
	
	public Concurso(String num_processo, Date DT_validade, Date DT_resultfinal) {
		this.num_processo = num_processo;
		this.DT_validade = DT_validade;
		this.DT_resultfinal = DT_resultfinal;
	}
	
	
	public String getNum_processo() {
		return num_processo;
	}
	public void setNum_processo(String num_processo) {
		this.num_processo = num_processo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDT_realizacao() {
		return DT_realizacao;
	}
	public void setDT_realizacao(Date dT_realizacao) {
		DT_realizacao = dT_realizacao;
	}
	public Date getDT_validade() {
		return DT_validade;
	}
	public void setDT_validade(Date dT_validade) {
		DT_validade = dT_validade;
	}
	
	public Date getDT_resultfinal() {
		return DT_resultfinal;
	}
	
	public void setDT_resultfinal(Date dT_resultfinal) {
		DT_validade = dT_resultfinal;
	}
	
	public Set<Muda_Estado> getCand_espec_classif() {
		return cand_espec_classif;
	}
	public void setCand_espec_classif(Set<Muda_Estado> cand_espec_classif) {
		this.cand_espec_classif = cand_espec_classif;
	}
	public HashMap<Especialidade,ArrayList<Vaga>> getVagas_conc() {
		return vagas_conc;
	}
	public void setVagas_conc(HashMap<Especialidade,ArrayList<Vaga>> vagas_conc) {
		this.vagas_conc = vagas_conc;
	}


	public String getStatus() {
		Date data = new Date(System.currentTimeMillis());
		LinkedList<Concurso> concur = DAO_Concurso.ListarConcursos();
		
		if (concur != null){
	    	for(Concurso aux: concur){
	    		if (data.before(aux.getDT_resultfinal())){
				  	status = "ABERTO";
			    	}
	    			else{
			    	status = "ENCERRADO";
			    	}
	    		}
	    	
			}
				return status;
			}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}