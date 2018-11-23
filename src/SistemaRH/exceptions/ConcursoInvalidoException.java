package SistemaRH.exceptions;

public class ConcursoInvalidoException extends Exception {

	
	private static final long serialVersionUID = 1L;
	
	public ConcursoInvalidoException() {
		super("Esse concurso j� expirou ou n�o existe");
	}
	

}