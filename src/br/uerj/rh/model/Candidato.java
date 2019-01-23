package br.uerj.rh.model;

	public class Candidato extends Pessoa{
		private String num_inscricao;
		private Especialidade cad_esp;
		private int classificacao;
		private String status;
		private String unidade, processo, perfil, especialidade, regiao;
		
		public Candidato(String cpf,Especialidade esp,String s,String nome) {
			super.setCPF(cpf);
			super.setNome(nome);
			this.cad_esp = esp;
			this.status = s;
		}
				
		public Candidato(String cpf, String nome,String status, String unidade, String processo, String perfil, String especialidade,String regiao) {
			super.setCPF(cpf);
			super.setNome(nome);
			this.status = status;
			this.unidade = unidade;
			this.processo = processo;
			this.perfil = perfil;
			this.especialidade = especialidade;
			this.regiao = regiao;
		}

		public String getUnidade() {
			return unidade;
		}

		public String getProcesso() {
			return processo;
		}

		public String getPerfil() {
			return perfil;
		}

		public String getEspecialidade() {
			return especialidade;
		}

		public String getRegiao() {
			return regiao;
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
