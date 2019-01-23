package br.uerj.rh.exceptions;

public class ConcursoSemCandidatoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ConcursoSemCandidatoException () {
		super("N�o h� mais candidatos dispon�veis no concurso");
	}


}
