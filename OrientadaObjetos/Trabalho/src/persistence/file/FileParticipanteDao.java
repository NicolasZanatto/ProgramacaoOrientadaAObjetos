package persistence.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import trabalhoOrientadaAObjetos.Participante;
import persistence.exceptions.FalhaAcessoDadosParticipanteException;
import persistence.intf.ParticipanteDAO;


public class FileParticipanteDao implements ParticipanteDAO {
	
	protected static String NOME_ARQUIVO = "participantes.dat";
	
	
	@Override
	public List<Participante> buscaTodos() throws FalhaAcessoDadosParticipanteException{
		return this.getParticipantes();
	}

	@Override
	public int insereParticipante(Participante participante) throws FalhaAcessoDadosParticipanteException {

		List<Participante> participantes = this.getParticipantes();
		participantes.add(participante);
		this.gravaArquivo(participantes);
		return 1;
	}
	
	
	
	public void gravaArquivo(List<Participante> participantes)					//OUTPUT - Escreve
			throws FalhaAcessoDadosParticipanteException {
		FileOutputStream fout;
		try {
			fout = new FileOutputStream(NOME_ARQUIVO);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(participantes);
			oos.close();
		} catch (IOException e) {
			throw new FalhaAcessoDadosParticipanteException(
					"Problemas gravando o arquivo " + NOME_ARQUIVO, e);
		}
	}
	
	@SuppressWarnings("unchecked")
	protected List<Participante> getParticipantes() throws FalhaAcessoDadosParticipanteException {	//INPUT - lê
		List<Participante> participantes = new ArrayList<Participante>();
		ObjectInputStream ois = null;
		try {

			FileInputStream fin = new FileInputStream(NOME_ARQUIVO);
			ois = new ObjectInputStream(fin);
			participantes = (List<Participante>) ois.readObject();
		} catch (FileNotFoundException e) {
			this.gravaArquivo(participantes);
		} catch (IOException e) {
			throw new FalhaAcessoDadosParticipanteException("Problemas lendo o arquivo "
					+ NOME_ARQUIVO, e);
		} catch (ClassNotFoundException e) {
			throw new FalhaAcessoDadosParticipanteException("Problemas lendo o arquivo "
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


	
	@Override
	public int alteraParticipante(Participante participante)
			throws FalhaAcessoDadosParticipanteException {

		List<Participante> participantes = this.getParticipantes();

		int index = participantes.indexOf(participante);
		if (index >= 0) {
			participantes.set(index, participante);
		}

		this.gravaArquivo(participantes);

		return 1;
	}

	
}