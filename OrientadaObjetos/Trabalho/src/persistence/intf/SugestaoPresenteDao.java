package persistence.intf;

import java.util.List;

import persistence.exceptions.FalhaAcessoDadosSugestaoPresenteException;
import trabalhoOrientadaAObjetos.SugestaoPresente;

public interface SugestaoPresenteDao {
	
	public abstract List<SugestaoPresente> buscaTodos() throws FalhaAcessoDadosSugestaoPresenteException;

	public abstract int insereSugestaoPresente(SugestaoPresente sugestaoPresente) throws FalhaAcessoDadosSugestaoPresenteException;


}
