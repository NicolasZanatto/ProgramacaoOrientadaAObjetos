package persistence.exceptions;

public class FalhaAcessoDadosParticipanteException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Ocorreu uma falha acessando os dados do participante";
	
	public FalhaAcessoDadosParticipanteException() {
		super(MESSAGE);
	}
	
	public FalhaAcessoDadosParticipanteException(String message, Throwable cause) {
		super(MESSAGE + " " + message, cause);
	}
	
	public FalhaAcessoDadosParticipanteException(String message) {
		super(MESSAGE + " " + message);
	}
	
	public FalhaAcessoDadosParticipanteException(Throwable cause) {
		super(MESSAGE, cause);
	}
}
