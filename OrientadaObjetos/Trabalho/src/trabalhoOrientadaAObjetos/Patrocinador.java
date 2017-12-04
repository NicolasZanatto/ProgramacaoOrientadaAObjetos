package trabalhoOrientadaAObjetos;

import java.io.Serializable;
import java.util.*;

import persistence.exceptions.FalhaAcessoAosDadosException;
import persistence.exceptions.FalhaAcessoDadosAvisosException;
import persistence.file.FileAvisosDao;

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
	
	public Aviso cadastrarAvisosGerais(String aviso)
	{
		Aviso novoAviso = new Aviso();
		novoAviso.setMsg(aviso);
		avisos.add(novoAviso);
	
		return novoAviso;
	}

	public final void showAvisosGerais()
	{
		atualizaAvisosGerais();
	//	System.out.println("-----Avisos Gerais-----");
		for (Aviso mens : avisos)
		{
			System.out.println(mens.getMsg());
		}
	}
	
	public void atualizaAvisosGerais()
	{
		FileAvisosDao avisoDao = new FileAvisosDao();
		
			try {
				avisos = (ArrayList<Aviso>) avisoDao.buscaTodos();
			} catch (FalhaAcessoDadosAvisosException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}