package SistemaRH.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class Concurso {
	private String num_processo;
	private String descricao;
	private boolean prorrogavel;
	private ArrayList<Especialidade> espec_conc;
	private Date DT_realizacao;
	private Date DT_validade;
	private Set<Muda_Estado> cand_espec_classif;
	private HashMap<Especialidade,Vaga[]> vagas_conc;
	
	//Informacoes necessarias para instanciar um objeto concurso
	public Concurso(String numP,String des, boolean p,ArrayList<Especialidade> esp_conc,
			ArrayList<Vaga> vaga_conc, ArrayList<Candidato> cand_conc,Date dt_rea,Date dt_vali) {
		this.num_processo = numP;
		this.descricao = des;
		this.prorrogavel = p;
		this.DT_realizacao = dt_rea;
		this.DT_validade = dt_vali;
		this.espec_conc = esp_conc;
		
		//Loading dos atributos vagas_conc e cand_espec_classif
		vagas_conc = new HashMap<Especialidade,Vaga[]>();
		cand_espec_classif = new HashSet<Muda_Estado> ();
		for(Especialidade e: espec_conc) {
			Vaga[] vagas_espec = new Vaga[vaga_conc.size()];
			//Carregando um array de vagas que correspondem a especialidade
			vaga_conc.forEach(vaga ->{
				int i = 0;
				if(vaga.getVaga_espec().equals(e)) {
					vagas_espec[i] = vaga;
					vaga_conc.remove(vaga);
					i++;
				}
			}
			);
			//Colocando a especialidade e o array de vagas correspondentes no HashMap
			vagas_conc.put(e,vagas_espec);
			//Carregando o objeto que contem as informacoes da classificacao da esp
			Muda_Estado classif_esp;
			ArrayList<Candidato> cand_esp = new ArrayList();
			//Verificando a especialidade dos candidatos
			cand_conc.forEach(candidato ->{
				if(candidato.getCad_esp().equals(e)) {
					/*Adicionando na colecao que sera passado como parametro no
					 * objeto Muda_Estado
					 */
					cand_esp.add(candidato);
					cand_conc.remove(candidato);
				}
			});
			//Carregando o objeto Muda_Estado
			classif_esp = new Muda_Estado(this.num_processo,e,cand_esp);
			//Adicionando o objeto a colecao
			cand_espec_classif.add(classif_esp);
			espec_conc.remove(e);
			//Seja Feliz
		}
	}
	
	public boolean addVaga(Especialidade esp,Vaga[] vagas_add) {
		//Lógica do caso de Uso RH2
		
		//Verificando se a especialidade passada como parâmetro é igual ao que consta nas vagas
		for(int i = 0; i< vagas_add.length;i++) {
			if(!esp.equals(vagas_add[i].getVaga_espec())) return false;
		}
		//Adicionando as vagas no concurso na devida especialidade
		for(Especialidade e: espec_conc) {
			if(e.equals(esp)) {
				vagas_conc.put(esp, vagas_add);
				break;
			}
		}
		return true;
	}
	
	/*public Candidato chamaCandidato(int num_aprovados) {
		//Logica do Caso de Uso RH3
		
	}*/
}
