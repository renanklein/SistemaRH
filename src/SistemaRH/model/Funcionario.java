package SistemaRH.model;
import java.sql.Date;

public class Funcionario extends Pessoa{
	private String matricula;
	private Especialidade esp_func;
	private Date data_admissao;
	private Date data_desligamento;
	private String unidade;
	private Vaga vaga_func;
	private Date data_portaria;
	private boolean status;
	
	public Funcionario(String mat, String cpf, String nome, boolean status) {
		super.setNome(nome);
		super.setCPF(cpf);
		this.matricula = mat;
		this.status = status;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	

}

