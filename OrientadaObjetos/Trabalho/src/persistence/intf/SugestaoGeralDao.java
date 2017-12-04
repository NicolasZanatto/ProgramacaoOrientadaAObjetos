package persistence.intf;

import java.util.List;

import persistence.exceptions.FalhaAcessoDadosSugestaoGeralException;
import trabalhoOrientadaAObjetos.Sugestao;

public interface SugestaoGeralDao {
	
	public abstract List<Sugestao> buscaTodos() throws FalhaAcessoDadosSugestaoGeralException;

	public abstract int insereSugestaoGeral(Sugestao sugestao) throws FalhaAcessoDadosSugestaoGeralException;
	
}
