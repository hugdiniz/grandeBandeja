package controladores.ccu.exceptions;

public class SiglaAlreadyExistsException extends Exception {
	
	private String sigla;

	public SiglaAlreadyExistsException(String sigla) {
		this.sigla = sigla;
	}

}
