package persistence.intf;

import java.util.List;

import persistence.exceptions.FalhaAcessoAosDadosException;
import trabalhoOrientadaAObjetos.Sugestao;

public interface SugestaoGeralDao {
	
	public abstract List<Sugestao> buscaTodos() throws FalhaAcessoAosDadosException;

	public abstract int insereSugestaoGeral(Sugestao sugestao) throws FalhaAcessoAosDadosException;
	
}
