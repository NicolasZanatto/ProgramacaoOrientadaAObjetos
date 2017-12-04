package persistence.exceptions;

public class FalhaAcessoDadosSugestaoPresenteException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Ocorreu uma falha acessando os dados da sugestão de presente";
	
	public FalhaAcessoDadosSugestaoPresenteException() {
		super(MESSAGE);
	}
	
	public FalhaAcessoDadosSugestaoPresenteException(String message, Throwable cause) {
		super(MESSAGE + " " + message, cause);
	}
	
	public FalhaAcessoDadosSugestaoPresenteException(String message) {
		super(MESSAGE + " " + message);
	}
	
	public FalhaAcessoDadosSugestaoPresenteException(Throwable cause) {
		super(MESSAGE, cause);
	}
}
