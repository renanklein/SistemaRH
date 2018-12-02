package SistemaRH.model;
import java.util.Calendar;

public class Funcionario extends Pessoa{
	private String matricula;
	private Especialidade esp_func;
	private Calendar data_admissao;
	private Calendar data_desligamento;
	private String unidade;
	private Vaga vaga_func;
	private Calendar data_portaria;
	private boolean status;
	
	public Funcionario(String mat, String cpf, String nome, boolean status) {
		super.setNome(nome);
		super.setCPF(cpf);
		this.matricula = mat;
		this.status = status;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Especialidade getEsp_func() {
		return esp_func;
	}

	public void setEsp_func(Especialidade esp_func) {
		this.esp_func = esp_func;
	}

	public Calendar getData_admissao() {
		return data_admissao;
	}

	public void setData_admissao(Calendar data_admissao) {
		this.data_admissao = data_admissao;
	}

	public Calendar getData_desligamento() {
		return data_desligamento;
	}

	public void setData_desligamento(Calendar data_desligamento) {
		this.data_desligamento = data_desligamento;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public Vaga getVaga_func() {
		return vaga_func;
	}

	public void setVaga_func(Vaga vaga_func) {
		this.vaga_func = vaga_func;
	}

	public Calendar getData_portaria() {
		return data_portaria;
	}

	public void setData_portaria(Calendar data_portaria) {
		this.data_portaria = data_portaria;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	
	
	

}

