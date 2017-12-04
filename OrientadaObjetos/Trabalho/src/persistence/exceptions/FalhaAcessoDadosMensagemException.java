package persistence.exceptions;

public class FalhaAcessoDadosMensagemException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Ocorreu uma falha acessando os dados da mensagem";
	
	public FalhaAcessoDadosMensagemException() {
		super(MESSAGE);
	}
	
	public FalhaAcessoDadosMensagemException(String message, Throwable cause) {
		super(MESSAGE + " " + message, cause);
	}
	
	public FalhaAcessoDadosMensagemException(String message) {
		super(MESSAGE + " " + message);
	}
	
	public FalhaAcessoDadosMensagemException(Throwable cause) {
		super(MESSAGE, cause);
	}
}
