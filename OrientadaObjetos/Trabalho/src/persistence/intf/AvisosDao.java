package persistence.intf;

import java.util.List;

import persistence.exceptions.FalhaAcessoDadosAvisosException;
import trabalhoOrientadaAObjetos.Aviso;

public interface AvisosDao {
	
	public abstract List<Aviso> buscaTodos() throws FalhaAcessoDadosAvisosException;

	public abstract int insereAviso(Aviso aviso) throws FalhaAcessoDadosAvisosException;


}
