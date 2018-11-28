package SistemaRH.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Set;

public class Concurso {
	private String num_processo;
	private String descricao;
	private Date DT_realizacao;
	private Date DT_validade;
	private Set<Muda_Estado> cand_espec_classif;
	private HashMap<Especialidade,ArrayList<Vaga>> vagas_conc;
	
