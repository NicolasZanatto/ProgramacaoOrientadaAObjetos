

	package persistence.exceptions;

	public class FalhaAcessoDadosAvisosException extends Exception {

		private static final long serialVersionUID = 1L;
		private static final String MESSAGE = "Ocorreu uma falha acessando os dados do aviso";
		
		public FalhaAcessoDadosAvisosException() {
			super(MESSAGE);
		}
		
		public FalhaAcessoDadosAvisosException(String message, Throwable cause) {
			super(MESSAGE + " " + message, cause);
		}
		
		public FalhaAcessoDadosAvisosException(String message) {
			super(MESSAGE + " " + message);
		}
		
		public FalhaAcessoDadosAvisosException(Throwable cause) {
			super(MESSAGE, cause);
		}
	}
