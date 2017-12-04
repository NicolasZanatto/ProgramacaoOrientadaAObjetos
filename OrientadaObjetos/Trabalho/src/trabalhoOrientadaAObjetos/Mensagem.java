package trabalhoOrientadaAObjetos;

import java.io.Serializable;

public class Mensagem implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3605495471578028427L;
	private Participante remetente;
	private Participante destinatario;
	private String mensagem;
	
	
	
	public Participante getRemetente() {
		return remetente;
	}
	public void setRemetente(Participante remetente) {
		this.remetente = remetente;
	}
	public Participante getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(Participante destinatario) {
		this.destinatario = destinatario;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
}
