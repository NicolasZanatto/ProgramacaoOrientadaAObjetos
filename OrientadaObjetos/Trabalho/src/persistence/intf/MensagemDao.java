package persistence.intf;

import java.util.List;

import persistence.exceptions.FalhaAcessoDadosMensagemException;
import trabalhoOrientadaAObjetos.Mensagem;

public interface MensagemDao {
	
	public abstract List<Mensagem> buscaTodos() throws FalhaAcessoDadosMensagemException;

	public abstract int insereMensagem(Mensagem mensagem) throws FalhaAcessoDadosMensagemException;


}
