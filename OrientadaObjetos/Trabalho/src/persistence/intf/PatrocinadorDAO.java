package persistence.intf;

import java.util.List;

import persistence.exceptions.FalhaAcessoDadosPatrocinadorException;
import trabalhoOrientadaAObjetos.Patrocinador;

public interface PatrocinadorDAO {
	
	
	public abstract List<Patrocinador> buscaTodos() throws FalhaAcessoDadosPatrocinadorException;

	public abstract int inserePatrocinador(Patrocinador patrocinador) throws FalhaAcessoDadosPatrocinadorException;
	
}
