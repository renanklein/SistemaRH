package SistemaRH.model;

	public class Candidato extends Pessoa{
		private String num_inscricao;
		private Especialidade cad_esp;
		private int classificacao;
		private String status;
		
		public Candidato(String cpf,Especialidade esp,String s,String nome) {
			this.setCPF(cpf);
			this.setNome(nome);
			this.cad_esp = esp;
			this.status = s;
		}
		public Especialidade getCad_esp() {
			return cad_esp;
		}

		public String getNum_inscricao() {
			return num_inscricao;
		}

		public void setNum_inscricao(String num_inscricao) {
			this.num_inscricao = num_inscricao;
		}

		public int getClassificacao() {
			return classificacao;
		}

		public void setClassificacao(int classificacao) {
			this.classificacao = classificacao;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
		
	}
