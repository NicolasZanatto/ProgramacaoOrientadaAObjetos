package persistence.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import persistence.exceptions.FalhaAcessoDadosSugestaoPresenteException;
import persistence.intf.SugestaoPresenteDao;
import trabalhoOrientadaAObjetos.SugestaoPresente;

public class FileSugestaoPresente implements SugestaoPresenteDao
{
	
	protected static String NOME_ARQUIVO = "sugestaoPresente2.dat";
	
	@Override
	public List<SugestaoPresente> buscaTodos() throws FalhaAcessoDadosSugestaoPresenteException {

		return this.getSugestoesPresentes();
	}
	
	@Override
	public int insereSugestaoPresente(SugestaoPresente sugestaoPresente) throws FalhaAcessoDadosSugestaoPresenteException {
		List<SugestaoPresente> sugestoesPresentes = this.getSugestoesPresentes();
		sugestoesPresentes.add(sugestaoPresente);
		this.gravaArquivo(sugestoesPresentes);
		return 1;
	}
	
	protected void gravaArquivo(List<SugestaoPresente> sugestaoPresente)					//OUTPUT - Escreve
			throws FalhaAcessoDadosSugestaoPresenteException {
		FileOutputStream fout;
		try {
			fout = new FileOutputStream(NOME_ARQUIVO);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(sugestaoPresente);
			oos.close();
		} catch (IOException e) {
			throw new FalhaAcessoDadosSugestaoPresenteException(
					"Problemas gravando o arquivo " + NOME_ARQUIVO, e);
		}
	}
	
	@SuppressWarnings("unchecked")
	protected List<SugestaoPresente> getSugestoesPresentes() throws FalhaAcessoDadosSugestaoPresenteException {	//INPUT - lê
		List<SugestaoPresente> sugestoesPresentes = new ArrayList<SugestaoPresente>();
		ObjectInputStream ois = null;
		try {

			FileInputStream fin = new FileInputStream(NOME_ARQUIVO);
			ois = new ObjectInputStream(fin);
			sugestoesPresentes = (List<SugestaoPresente>) ois.readObject();
		} catch (FileNotFoundException e) {
			this.gravaArquivo(sugestoesPresentes);
		} catch (IOException e) {
			throw new FalhaAcessoDadosSugestaoPresenteException("Problemas lendo o arquivo "
					+ NOME_ARQUIVO, e);
		} catch (ClassNotFoundException e) {
			throw new FalhaAcessoDadosSugestaoPresenteException("Problemas lendo o arquivo "
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
		return sugestoesPresentes;
	}
	
	
}
	
