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
	
	public Funcionario(String matri,String status,String nome,String CPF){
		this.matricula = matri;
		this.status = status;
		this.setNome(nome);
		this.setCPF(CPF);
	}
	
	@Override
	public void setStatus(String status) {
		if(status.equals("Inativo")) {
			this.status = status;
			//Gerar ação para a transferencia do funcionário para o quadro de inativos
		}
		//Gerar mensagem de vacancia
	}

}

