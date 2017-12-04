package persistence.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import persistence.exceptions.FalhaAcessoAosDadosException;
import persistence.intf.AvisosDao;
import trabalhoOrientadaAObjetos.Aviso;


public class FileAvisosDao implements AvisosDao {

	protected static String NOME_ARQUIVO = "avisos.dat";

	@Override
	public List<Aviso> buscaTodos() throws FalhaAcessoAosDadosException {

		return this.getAviso();
	}

	@Override
	public int insereAviso(Aviso aviso) throws FalhaAcessoAosDadosException {
		List<Aviso> avisos = this.getAviso();
		avisos.add(aviso);
		this.gravaArquivo(avisos);
		return 1;
	}
	
	
	@SuppressWarnings("unchecked")
	protected List<Aviso> getAviso() throws FalhaAcessoAosDadosException {	//INPUT - lê
		List<Aviso> avisos = new ArrayList<Aviso>();
		ObjectInputStream ois = null;
		try {
	
			FileInputStream fin = new FileInputStream(NOME_ARQUIVO);
			ois = new ObjectInputStream(fin);
			avisos = (List<Aviso>) ois.readObject();
		} catch (FileNotFoundException e) {
			this.gravaArquivo(avisos);
		} catch (IOException e) {
			throw new FalhaAcessoAosDadosException("Problemas lendo o arquivo "
					+ NOME_ARQUIVO, e);
		} catch (ClassNotFoundException e) {
			throw new FalhaAcessoAosDadosException("Problemas lendo o arquivo "
					+ NOME_ARQUIVO, e);
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return avisos;
	}	
	
	protected void gravaArquivo(List<Aviso> aviso)					//OUTPUT - Escreve
			throws FalhaAcessoAosDadosException {
		FileOutputStream fout;
		try {
			fout = new FileOutputStream(NOME_ARQUIVO);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(aviso);
			oos.close();
		} catch (IOException e) {
			throw new FalhaAcessoAosDadosException(
					"Problemas gravando o arquivo " + NOME_ARQUIVO, e);
		}
	}
}
