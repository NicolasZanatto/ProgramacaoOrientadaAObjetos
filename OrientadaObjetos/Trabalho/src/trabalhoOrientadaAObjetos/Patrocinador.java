package trabalhoOrientadaAObjetos;

import java.io.Serializable;
import java.util.*;


public class Patrocinador extends Participante implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8306813311734979171L;
	/**
	 * 
	 */
	private ArrayList<Aviso> avisos;
	
	public Patrocinador()
	{
		avisos= new ArrayList<Aviso>();
	}
	
	public Aviso cadastrarAvisosGerais(Aviso aviso)
	{

		avisos.add(aviso);
	
		return aviso;
	}


	
	public ArrayList<Aviso> getAvisos() {
		return avisos;
	}

	public void setAvisos(ArrayList<Aviso> avisos) {
		this.avisos = avisos;
	}


}