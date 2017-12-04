package persistence.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import persistence.exceptions.FalhaAcessoDadosPatrocinadorException;
import persistence.intf.PatrocinadorDAO;
import trabalhoOrientadaAObjetos.Patrocinador;


public class FilePatrocinadorDao implements PatrocinadorDAO{

	protected static String NOME_ARQUIVO = "patrocinadores.dat";
	
	@Override
	public List<Patrocinador> buscaTodos() throws FalhaAcessoDadosPatrocinadorException {
		return this.getPatrocinador();
	}

	@Override
	public int inserePatrocinador(Patrocinador patrocinador) throws FalhaAcessoDadosPatrocinadorException {
		List<Patrocinador> patrocinadores = this.getPatrocinador();
		patrocinadores.add(patrocinador);
		this.gravaArquivo(patrocinadores);
		return 1;
	}
	
	@SuppressWarnings("unchecked")
	protected List<Patrocinador> getPatrocinador() throws FalhaAcessoDadosPatrocinadorException {	//INPUT - lê
		List<Patrocinador> participantes = new ArrayList<Patrocinador>();
		ObjectInputStream ois = null;
		try {
	
			FileInputStream fin = new FileInputStream(NOME_ARQUIVO);
			ois = new ObjectInputStream(fin);
			participantes = (List<Patrocinador>) ois.readObject();
		} catch (FileNotFoundException e) {
			this.gravaArquivo(participantes);
		} catch (IOException e) {
			throw new FalhaAcessoDadosPatrocinadorException("Problemas lendo o arquivo "
					+ NOME_ARQUIVO, e);
		} catch (ClassNotFoundException e) {
			throw new FalhaAcessoDadosPatrocinadorException("Problemas lendo o arquivo "
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
		return participantes;
	}
	
	protected void gravaArquivo(List<Patrocinador> patrocinador)					//OUTPUT - Escreve
			throws FalhaAcessoDadosPatrocinadorException {
		FileOutputStream fout;
		try {
			fout = new FileOutputStream(NOME_ARQUIVO);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(patrocinador);
			oos.close();
		} catch (IOException e) {
			throw new FalhaAcessoDadosPatrocinadorException(
					"Problemas gravando o arquivo " + NOME_ARQUIVO, e);
		}
	}
}
