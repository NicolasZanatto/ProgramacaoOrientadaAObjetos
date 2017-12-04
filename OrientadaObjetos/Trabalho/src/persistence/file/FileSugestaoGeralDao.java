package persistence.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import persistence.exceptions.FalhaAcessoDadosSugestaoGeralException;
import persistence.intf.SugestaoGeralDao;
import trabalhoOrientadaAObjetos.Sugestao;


public class FileSugestaoGeralDao implements SugestaoGeralDao
{
protected static String NOME_ARQUIVO = "sugestaoGeral.dat";
	
	@Override
	public List<Sugestao> buscaTodos() throws FalhaAcessoDadosSugestaoGeralException {

		return this.getSugestaoGeral();
	}
	
	@Override
	public int insereSugestaoGeral(Sugestao sugestao) throws FalhaAcessoDadosSugestaoGeralException {
		List<Sugestao> sugestoes = this.getSugestaoGeral();
		sugestoes.add(sugestao);
		this.gravaArquivo(sugestoes);
		return 1;
	}
	
	@SuppressWarnings("unchecked")
	protected List<Sugestao> getSugestaoGeral() throws FalhaAcessoDadosSugestaoGeralException {	//INPUT - lê
		List<Sugestao> sugestoes = new ArrayList<Sugestao>();
		ObjectInputStream ois = null;
		try {
	
			FileInputStream fin = new FileInputStream(NOME_ARQUIVO);
			ois = new ObjectInputStream(fin);
			sugestoes = (List<Sugestao>) ois.readObject();
		} catch (FileNotFoundException e) {
			this.gravaArquivo(sugestoes);
		} catch (IOException e) {
			throw new FalhaAcessoDadosSugestaoGeralException("Problemas lendo o arquivo "
					+ NOME_ARQUIVO, e);
		} catch (ClassNotFoundException e) {
			throw new FalhaAcessoDadosSugestaoGeralException("Problemas lendo o arquivo "
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
		return sugestoes;
	}

	protected void gravaArquivo(List<Sugestao> sugestao)					//OUTPUT - Escreve
				throws FalhaAcessoDadosSugestaoGeralException {
			FileOutputStream fout;
			try {
				fout = new FileOutputStream(NOME_ARQUIVO);
				ObjectOutputStream oos = new ObjectOutputStream(fout);
				oos.writeObject(sugestao);
				oos.close();
			} catch (IOException e) {
				throw new FalhaAcessoDadosSugestaoGeralException(
						"Problemas gravando o arquivo " + NOME_ARQUIVO, e);
			}
		}
}