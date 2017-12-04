package persistence.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import persistence.exceptions.FalhaAcessoDadosMensagemException;
import persistence.intf.MensagemDao;
import trabalhoOrientadaAObjetos.Mensagem;

public class FileMensagemDao implements MensagemDao {

	protected static String NOME_ARQUIVO = "mensagens.dat";

	@Override
	public List<Mensagem> buscaTodos() throws FalhaAcessoDadosMensagemException {
		return getMensagens();
	}

	@Override
	public int insereMensagem(Mensagem mensagem) throws FalhaAcessoDadosMensagemException {
		List<Mensagem> mensagens = this.getMensagens();
		mensagens.add(mensagem);
		this.gravaArquivo(mensagens);
		return 1;
	}
	
	@SuppressWarnings("unchecked")
	protected List<Mensagem> getMensagens() throws FalhaAcessoDadosMensagemException {	//INPUT - lê
		List<Mensagem> mensagens = new ArrayList<Mensagem>();
		ObjectInputStream ois = null;
		try {
	
			FileInputStream fin = new FileInputStream(NOME_ARQUIVO);
			ois = new ObjectInputStream(fin);
			mensagens = (List<Mensagem>) ois.readObject();
		} catch (FileNotFoundException e) {
			this.gravaArquivo(mensagens);
		} catch (IOException e) {
			throw new FalhaAcessoDadosMensagemException("Problemas lendo o arquivo "
					+ NOME_ARQUIVO, e);
		} catch (ClassNotFoundException e) {
			throw new FalhaAcessoDadosMensagemException("Problemas lendo o arquivo "
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
		return mensagens;
	}
	
	protected void gravaArquivo(List<Mensagem> mensagem)					//OUTPUT - Escreve
			throws FalhaAcessoDadosMensagemException {
		FileOutputStream fout;
		try {
			fout = new FileOutputStream(NOME_ARQUIVO);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(mensagem);
			oos.close();
		} catch (IOException e) {
			throw new FalhaAcessoDadosMensagemException(
					"Problemas gravando o arquivo " + NOME_ARQUIVO, e);
		}
	}

}
