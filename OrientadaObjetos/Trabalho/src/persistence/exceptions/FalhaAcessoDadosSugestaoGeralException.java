package persistence.exceptions;

public class FalhaAcessoDadosSugestaoGeralException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Ocorreu uma falha acessando os dados da sugestão geral";
	
	public FalhaAcessoDadosSugestaoGeralException() {
		super(MESSAGE);
	}
	
	public FalhaAcessoDadosSugestaoGeralException(String message, Throwable cause) {
		super(MESSAGE + " " + message, cause);
	}
	
	public FalhaAcessoDadosSugestaoGeralException(String message) {
		super(MESSAGE + " " + message);
	}
	
	public FalhaAcessoDadosSugestaoGeralException(Throwable cause) {
		super(MESSAGE, cause);
	}
}
