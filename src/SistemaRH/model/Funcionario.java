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
	
	public Vaga gerar_vaga() {
		//Após confirmação para gerar vacância
		Vaga novaVaga = new Vaga();
		novaVaga.setStatus("Desocupada");
		novaVaga.setProcesso("Numero do concurso que do funcionario, se ainda válido");
		
		return novaVaga;
	}
	@Override
	public void MudarStatus(String status) {
		if(status.equals("Inativo")) {
			this.status = status;
			//Gerar ação para a transferencia do funcionário para o quadro de inativos
		}
		//Gerar mensagem de vacancia
	}

}

