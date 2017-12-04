package trabalhoOrientadaAObjetos;

import java.io.Serializable;

public class Sugestao implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sugestao;
	private Participante participante;
	
	public Sugestao()
	{
		participante = new Participante();
	}
	public String getSugestao() {
		return sugestao;
	}
	public void setSugestao(String sugestao) {
		this.sugestao = sugestao;
	}
	public Participante getParticipante() {
		return participante;
	}
	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

}
