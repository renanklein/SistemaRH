package br.uerj.rh.model;

	public class Candidato extends Pessoa{
		private String num_inscricao;
		private Especialidade cad_esp;
		private int classificacao;
		private int id_vaga;
		private String status;
		private String unidade, processo, perfil, especialidade, regiao;
		private int id_espec;
		private int id_situacao;
		private int idconcurso;
		private int empate;
		private String localizacao;
		private String lotacao;
		private String matricula;
		private int idVaga;
		
		public Candidato(String cpf, String nome, String status, String unidade, String processo, String perfil, String especialidade,
				String regiao, String localizacao, String lotacao, String matricula, int idVaga, int idConcurso) {
			super.setCPF(cpf);
			super.setNome(nome);
			this.status = status;
			this.unidade = unidade;
			this.processo = processo;
			this.perfil = perfil;
			this.especialidade = especialidade;
			this.regiao = regiao;
			this.localizacao = localizacao;
			this.lotacao = lotacao;
			this.matricula = matricula;
			this.idVaga = idVaga;
			this.idconcurso = idConcurso;
			
		}
				
		public Candidato(String cpf, String nome, int idconcurso, int classificacao, int empate, String processo, String perfil,
				String especialidade, String regiao) {
			super.setCPF(cpf);
			super.setNome(nome);
			this.classificacao = classificacao;
			this.idconcurso = idconcurso;
			this.empate = empate;
			this.processo = processo;
			this.perfil = perfil;
			this.especialidade = especialidade;
			this.regiao = regiao;
		}

		public Candidato(String cpf,Especialidade esp,String s,String nome) {
			super.setCPF(cpf);
			super.setNome(nome);
			this.cad_esp = esp;
			this.status = s;
		}
		
		
				
		public Candidato(String cpf, String nome,String status, String unidade, String processo,
				String perfil, String especialidade,String regiao) {
			super.setCPF(cpf);
			super.setNome(nome);
			this.status = status;
			this.unidade = unidade;
			this.processo = processo;
			this.perfil = perfil;
			this.especialidade = especialidade;
			this.regiao = regiao;
		}

				
		public int getIdconcurso() {
			return idconcurso;
		}

		public void setIdconcurso(int idconcurso) {
			this.idconcurso = idconcurso;
		}

		public int getEmpate() {
			return empate;
		}

		public void setEmpate(int empate) {
			this.empate = empate;
		}

		public String getLocalizacao() {
			return localizacao;
		}

		public void setLocalizacao(String localizacao) {
			this.localizacao = localizacao;
		}

		public String getLotacao() {
			return lotacao;
		}

		public void setLotacao(String lotacao) {
			this.lotacao = lotacao;
		}

		public String getMatricula() {
			return matricula;
		}

		public void setMatricula(String matricula) {
			this.matricula = matricula;
		}

		public int getIdVaga() {
			return idVaga;
		}

		public void setIdVaga(int idVaga) {
			this.idVaga = idVaga;
		}

		public void setCad_esp(Especialidade cad_esp) {
			this.cad_esp = cad_esp;
		}

		public void setUnidade(String unidade) {
			this.unidade = unidade;
		}

		public void setProcesso(String processo) {
			this.processo = processo;
		}

		public void setPerfil(String perfil) {
			this.perfil = perfil;
		}

		public void setRegiao(String regiao) {
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

		public void setEspecialidade(String especialidade) {
			this.especialidade = especialidade;
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

		public int getId_vaga() {
			return id_vaga;
		}

		public void setId_vaga(int id_vaga) {
			this.id_vaga = id_vaga;
		}

		public int getId_espec() {
			return id_espec;
		}

		public void setId_espec(int id_espec) {
			this.id_espec = id_espec;
		}

		public int getId_situacao() {
			return id_situacao;
		}

		public void setId_situacao(int id_situacao) {
			this.id_situacao = id_situacao;
		}
		
	}
