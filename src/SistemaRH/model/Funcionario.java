package SistemaRH.model;
import java.sql.Date;

public class Funcionario extends Pessoa{
	private String matricula;
	private Especialidade esp_func;
	private Date data_admissao;
	private Date data_desligamento;
	private String unidade;
	private int idVaga;
	private Date data_portaria;
	private boolean status;
	
	public Funcionario(String mat, String cpf, String nome, boolean status, int vaga) {
		super.setNome(nome);
		super.setCPF(cpf);
		this.matricula = mat;
		this.status = status;
		this.idVaga = vaga;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMatricula() {
		return matricula;
	}

	public Especialidade getEsp_func() {
		return esp_func;
	}

	public Date getData_admissao() {
		return data_admissao;
	}

	public Date getData_desligamento() {
		return data_desligamento;
	}

	public String getUnidade() {
		return unidade;
	}

	public int getIdVaga() {
		return idVaga;
	}

	public Date getData_portaria() {
		return data_portaria;
	}
	
	

}

