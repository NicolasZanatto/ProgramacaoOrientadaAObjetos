package persistence.intf;

import java.util.List;

import persistence.exceptions.FalhaAcessoAosDadosException;
import trabalhoOrientadaAObjetos.Mensagem;

public interface MensagemDao {
	
	public abstract List<Mensagem> buscaTodos() throws FalhaAcessoAosDadosException;

	public abstract int insereMensagem(Mensagem mensagem) throws FalhaAcessoAosDadosException;


}
