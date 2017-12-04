package persistence.intf;

import java.util.List;

import persistence.exceptions.FalhaAcessoAosDadosException;
import trabalhoOrientadaAObjetos.Aviso;

public interface AvisosDao {
	
	public abstract List<Aviso> buscaTodos() throws FalhaAcessoAosDadosException;

	public abstract int insereAviso(Aviso aviso) throws FalhaAcessoAosDadosException;


}
