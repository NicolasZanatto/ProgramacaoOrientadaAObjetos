package persistence.intf;

import java.util.List;

import persistence.exceptions.FalhaAcessoDadosParticipanteException;
import trabalhoOrientadaAObjetos.Participante;


public interface ParticipanteDAO {
//	public abstract Participante getProdutoPorCodigo(); -- CRIAR getParticipantePorCodinome();

	public abstract List<Participante> buscaTodos() throws FalhaAcessoDadosParticipanteException;

	public abstract int insereParticipante(Participante participante) throws FalhaAcessoDadosParticipanteException;

	public abstract int alteraParticipante(Participante participante) throws FalhaAcessoDadosParticipanteException;

	
}
