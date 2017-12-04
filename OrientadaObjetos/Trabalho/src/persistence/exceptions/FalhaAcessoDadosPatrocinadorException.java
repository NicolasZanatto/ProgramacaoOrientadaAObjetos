package persistence.exceptions;

public class FalhaAcessoDadosPatrocinadorException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Ocorreu uma falha acessando os dados do patrocinador";
	
	public FalhaAcessoDadosPatrocinadorException() {
		super(MESSAGE);
	}
	
	public FalhaAcessoDadosPatrocinadorException(String message, Throwable cause) {
		super(MESSAGE + " " + message, cause);
	}
	
	public FalhaAcessoDadosPatrocinadorException(String message) {
		super(MESSAGE + " " + message);
	}
	
	public FalhaAcessoDadosPatrocinadorException(Throwable cause) {
		super(MESSAGE, cause);
	}
}
