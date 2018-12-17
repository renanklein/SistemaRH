package SistemaRH.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;

public class Concurso {
	private String num_processo;
	private String descricao;
	private Calendar DT_realizacao;
	private Calendar DT_validade;
	private Set<Muda_Estado> cand_espec_classif;
	private HashMap<Especialidade,ArrayList<Vaga>> vagas_conc;
}
	
