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
	private boolean prorrogavel;
	private ArrayList<Especialidade> espec_conc;
	private Date DT_realizacao;
	private Date DT_validade;
	private Set<Muda_Estado> cand_espec_classif;
	private HashMap<Especialidade,ArrayList<Vaga>> vagas_conc;
	
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
		vagas_conc = new HashMap<Especialidade,ArrayList<Vaga>>();
		cand_espec_classif = new HashSet<Muda_Estado> ();
		for(Especialidade e: espec_conc) {
			//Carregando um array de vagas que correspondem a especialidade
			ArrayList<Vaga> vaga_esp = new ArrayList<Vaga>();
			vaga_conc.forEach(vaga ->{
				if(vaga.getVaga_espec().equals(e)) {
					//Colocando a especialidade e o array de vagas correspondentes no HashMap
					vaga_esp.add(vaga);
					//Removendo a vaga já tratada do arraylist
					vaga_conc.remove(vaga);
				}
			}
			);
			//Colocando a especialidade e o array de vagas correspondentes no HashMap
			vagas_conc.put(e, vaga_esp);
			//Carregando o objeto que contem as informacoes da classificacao da esp
			Muda_Estado classif_esp;
			ArrayList<Candidato> cand_esp = new ArrayList<Candidato>();
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
		ArrayList<Vaga> vagas_esp  = vagas_conc.get(esp);
		//Verificando se a especialidade passada como parâmetro é igual ao que consta nas vagas
		for(int i = 0; i< vagas_add.length;i++) {
			if(!esp.equals(vagas_add[i].getVaga_espec())) return false;
		}
		//Adicionando as vagas no concurso na devida especialidade
		for(int i = 0; i < vagas_add.length;i++) {
			vagas_esp.add(vagas_add[i]);
		}
		vagas_conc.put(esp, vagas_esp);
		return true;
	}
	
	public Candidato chamaCandidato(Especialidade esp_cand) {
		//Logica do Caso de Uso RH3
		//Pegando a colecao de vagas da referida especialidade
		ArrayList<Vaga> vagas_esp = vagas_conc.get(esp_cand);
		Candidato cand_select = null;
		/*Verificando se há vagas com o status de desocupada
		 *
		 */
		Iterator<Vaga> iterador = vagas_esp.iterator();
		int i = 0;
		boolean des = false;
		while(iterador.hasNext() && !des) {
			if(vagas_esp.get(i).getStatus().equals("Desocupada")) {
				des = true;
				vagas_esp.get(i).setStatus("Candidato Selecionado");/*Nao lembro o nome o estado da vaga 
				quando ha candidato selecionado para a verificacao de documentos*/
				//Pegando o objeto da classificacao e realizando as devidas alteracoes
				for(Muda_Estado e:cand_espec_classif) {
					if(e.getConcurso_Especialidade().equals(esp_cand)) {
						//Pegando o primeiro candidado da arvore e o retirando da mesma
						// Verificar depois se a sua retirada da colecao é mesmo desejada
						cand_select = e.getCand_classif().pollFirst();
						//Mudando o estado do candidato
						cand_select.MudarStatus("Selecionado");
						//Atualizando historico da vaga
						vagas_esp.get(i).getHist().atualiza_historico(cand_select);
						// Saindo o loop (mesmo que seja deselegante)
						break;
					}
				}
			}
			i++;
		}
		// Retornando o objeto do candidato selecionado
		return cand_select;
	}
	public void manutencaoCandidato(Candidato c_select) {
		//Logica do Caso de Uso RH4
		// Se o candidato for efetivado
		if(c_select.getStatus().equals("Efetivado")) {
			/*OBS: No método chamaCandidato, o candidato selecionado
			 * já foi retirado da relacao de classificacao da especilidade
			 * na qual ele se inscreveu
			*/ 
			//Retirando a vaga ocupada das devidas colecoes
			//Pegando a colecao de vagas que correponde a especialidade do candidato
			ArrayList<Vaga> vagas_esp = vagas_conc.get(c_select.getCad_esp());
			//Verificando qual vaga tem o candidato selecionado no topo da pilha de seu historico
			ListIterator<Vaga> it = vagas_esp.listIterator();
			boolean achou_cand = false;
			while(it.hasNext() && !achou_cand) {
				if(it.next().getHist().getCandidatos_hist().peek().equals(c_select)){
					//Removendo a vaga da colecao e a reescrevendo no HashMap
					//Continua...
				}
			}
		}
	}
}
