package trabalhoOrientadaAObjetos;

import java.util.*;

import persistence.exceptions.FalhaAcessoAosDadosException;
import persistence.file.FileMensagemDao;
import persistence.file.FileParticipanteDao;
import persistence.file.FilePatrocinadorDao;
import persistence.file.FileSugestaoGeralDao;
import persistence.file.FileSugestaoPresente;
import persistence.intf.ParticipanteDAO;


public class Gerenciador
{

	
	private ArrayList<Mensagem> ListaDeMensagens;

	private ArrayList<Participante> listaParticipantes;
	
	private ArrayList<Sugestao> listaSugestoes;
	
	private ArrayList<Patrocinador> listaPatrocinadores;
	
	private ArrayList<SugestaoPresente> listaSugestoesPresentes;
	public Gerenciador()
	{
		this.inicializa();
	}
	
	public void atualizaListaParticipantes() throws FalhaAcessoAosDadosException
	{
		FileParticipanteDao participanteDao = new FileParticipanteDao();
		listaParticipantes = (ArrayList<Participante>) participanteDao.buscaTodos();

	}
	
	public void atualizaListaSugestaoPresente() throws FalhaAcessoAosDadosException
	{
		FileSugestaoPresente sugestaoPresenteDao = new FileSugestaoPresente();
		listaSugestoesPresentes = (ArrayList<SugestaoPresente>) sugestaoPresenteDao.buscaTodos();

	}
	
	public void atualizaListaPatrocinadores() throws FalhaAcessoAosDadosException
	{
		FilePatrocinadorDao patrocinadorDao = new FilePatrocinadorDao();
		listaPatrocinadores = (ArrayList<Patrocinador>) patrocinadorDao.buscaTodos();
	}
	
	public void atualizaListaMensagens() throws FalhaAcessoAosDadosException
	{
		FileMensagemDao mensagemDao = new FileMensagemDao();
		ListaDeMensagens = (ArrayList<Mensagem>) mensagemDao.buscaTodos();
	}
	
	public void gravaListaParticipantes()
	{
		
			FileParticipanteDao participanteDao = new FileParticipanteDao();
			participanteDao.gravaArquivo(listaParticipantes);
	}
	public void inicializa()
	{
		ListaDeMensagens = new ArrayList<Mensagem>();
		listaParticipantes = new ArrayList<Participante>();
		listaSugestoes = new ArrayList<Sugestao>();
		listaPatrocinadores = new ArrayList<Patrocinador>();
		listaSugestoesPresentes = new ArrayList<SugestaoPresente>();
	}
	
	public final Participante cadastroInfo()
	{
		Scanner sc = new Scanner(System.in);
		Participante participante = new Participante();

		System.out.println("Digite o nome do participante: ");
		participante.setNome(sc.nextLine()); 
		
		System.out.println("Digite o ramal: ");
		participante.setRamal(sc.nextInt()); 

		System.out.println("Digite o codinome: ");
		participante.setCodinome(new Scanner(System.in).nextLine());

		listaParticipantes.add(participante);
		return participante;
	}
	
	public Participante procuraParticipante(String nome)
	{
		try {
			atualizaListaParticipantes();
			for(Participante part : listaParticipantes)
			{
				System.out.println("Participante="+ part.getNome());
				if(part.getNome().equals(nome)) return part;
			}
				return null;
		} catch (FalhaAcessoAosDadosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public final ArrayList<Mensagem> retornaMensagens()
	{
		return ListaDeMensagens;
	}

	public final ArrayList<Participante> retornaParticipantes() throws FalhaAcessoAosDadosException
	{
		atualizaListaParticipantes();
		return listaParticipantes;
	}

	@SuppressWarnings("resource")
	public final ArrayList<SugestaoPresente> adicionaSugestoesPresentes(Participante participante)
	{
		FileSugestaoPresente sugestaoDao = new FileSugestaoPresente();
		System.out.printf("Digite a sugestao de presente de %1$s: " + "\r\n", participante.getNome());
		ArrayList<SugestaoPresente> SugestoesPresentes = participante.getretornaListaPresentes();
		SugestaoPresente sug = new SugestaoPresente();
		sug.setSugestao(new Scanner(System.in).nextLine());
		try {
			listaSugestoesPresentes.add(sug);
			sugestaoDao.insereSugestaoPresente(sug);
			participante.addSugestoesPresentes(sug);
		} catch (FalhaAcessoAosDadosException e) {
			
			System.out.println("Problema Aqui");
		}
		SugestoesPresentes.add(sug);
		
		return SugestoesPresentes;
	}
	
	public void mostraSugestoesPresentes(Participante participante)
	{
		try {
			this.atualizaListaSugestaoPresente();
			for(SugestaoPresente p: listaSugestoesPresentes)
				System.out.println(p.getSugestao());

		} catch (FalhaAcessoAosDadosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public final ArrayList<Integer> sorteio() throws FalhaAcessoAosDadosException
	{
		atualizaListaParticipantes();
		ArrayList<Integer> numerosSorteados = new ArrayList<Integer>();
		for(Participante p: listaParticipantes)
			System.out.println(p.getNome());
		Random rdn = new Random();

		while (numerosSorteados.size() < listaParticipantes.size())
		{
			int numeroSorteado = rdn.nextInt(listaParticipantes.size());

			while (numerosSorteados.contains(numeroSorteado))
			{
				numeroSorteado = rdn.nextInt(listaParticipantes.size());
			}

			numerosSorteados.add(numeroSorteado);

		}
	/*	System.out.println("Números Sorteados");
		for (int i : numerosSorteados)
		{
			System.out.println(i);
		}
	*/
		return numerosSorteados;
	}

	public final void matchSorteio(ArrayList<Integer> listaSorteada, ArrayList<Participante> listaParticipantes)
	{
		int i = 0;
		while (i < listaSorteada.size())
		{

			Participante participante1 = listaParticipantes.get(listaSorteada.get(i));

			i++;

			if (i != listaSorteada.size())
			{

				Participante participante2 = listaParticipantes.get(listaSorteada.get(i));



				participante1.setAmigoSecreto(participante2);
			}
			else
			{

				participante1.setAmigoSecreto(listaParticipantes.get(listaSorteada.get(0)));

			}

		}
		
		for(Participante participante: listaParticipantes)
		{
			System.out.println(participante.getNome() + " tirou " + participante.getAmigoSecreto().getNome());

		}

	}
	
	public final void mostraAmigosSecretos()
	{
		for (Participante participante : listaParticipantes)
		{
			if(participante.getAmigoSecreto()==null){
				System.out.println("Sorteio não foi realizado!");
				break;
			}
			System.out.println(participante.getNome() + " tirou " + participante.getAmigoSecreto().getNome());
		}
		if(listaParticipantes.isEmpty()==true) System.out.println("A lista de participantes está vazia.");
	}
	
	
	public void mostraCodinomes()
	{
		try {
			atualizaListaParticipantes();
		} catch (FalhaAcessoAosDadosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for( Participante participante : listaParticipantes)
			System.out.println(participante.getCodinome() + " = " + participante.getNome() );
	}
	
/*	public void alteraParticipante(Participante participante)
	{
		FileParticipanteDao participanteDao = new FileParticipanteDao();
		
		try {
			participanteDao.alteraParticipante(participante);
		} catch (FalhaAcessoAosDadosException e) {
			e.printStackTrace();
		}
	}*/
	public final void enviaMensagem() throws FalhaAcessoAosDadosException
	{
		
	    mostraCodinomes();
		
		Mensagem mensagem = new Mensagem();
		Participante remetente = new Participante();
		Participante destinatario = new Participante();
		
		
		System.out.println("Digite o codinome do remetente: ");
		String codinome1 = new Scanner(System.in).nextLine();
		
		while (verificaContemNaLista(listaParticipantes, codinome1)==null)
		{
			System.out.println("Esse codinome não existe, por favor digite outro válido!");
			System.out.println("Para sair digite 'sair'!");
			codinome1 = new Scanner(System.in).nextLine();
			
			if(codinome1.equals("sair")) return;
			
		}
		
			remetente = verificaContemNaLista(listaParticipantes,codinome1);
			remetente.setQntdEnviadas();
		//	alteraParticipante(remetente);
			
		//	System.out.println("Remetente =" + remetente.getNome());
		
		
		System.out.println("Digite o codinome do destinatário: ");
		String codinome2 = new Scanner(System.in).nextLine();

		while (verificaContemNaLista(listaParticipantes, codinome2)==null)
		{
			System.out.println("Esse codinome não existe, por favor digite outro válido!");
			System.out.println("Para sair digite 'sair'!");
			codinome2 = new Scanner(System.in).nextLine();
			
			if(codinome2.equals("sair")) return;
		}
		
		destinatario = verificaContemNaLista(listaParticipantes,codinome2);
		destinatario.setQntdRecebidas();
	//	alteraParticipante(destinatario);
		
		gravaListaParticipantes();
		
		System.out.println("Digite a sua mensagem: ");
		String mens = new Scanner(System.in).nextLine();
		
		
		mensagem.setRemetente(remetente);
		mensagem.setDestinatario(destinatario);
		mensagem.setMensagem(mens);
		
		adicionaListaMensagens(getListaDeMensagens(),mensagem);
		
//		this.setMensagens(remetente.getMensagensEnviadas(),mensagem);
	//	this.setMensagens(destinatario.getMensagensEnviadas(),mensagem);
		
		FileMensagemDao mensagemDao = new FileMensagemDao();
		try {
			mensagemDao.insereMensagem(mensagem);
		} catch (FalhaAcessoAosDadosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public void setMensagens(ArrayList<Mensagem> mensagem, Mensagem mens)
	{
		mensagem.add(mens);
	}
	
	public static Participante verificaContemNaLista(ArrayList<Participante> listaParticipantes, String codinome)
	{
		for (int i = 0; i < listaParticipantes.size(); i++)
		{
			if (listaParticipantes.get(i).getCodinome().equals(codinome))
			{
				return listaParticipantes.get(i);
			}
		}

		return null;
	}
	
	public ArrayList<Mensagem> adicionaListaMensagens(ArrayList<Mensagem> listaMensagens, Mensagem mensagem)
	{
		listaMensagens.add(mensagem);
		
		return listaMensagens;
	}
	
	public ArrayList<Mensagem> getListaDeMensagens() throws FalhaAcessoAosDadosException {
		this.atualizaListaMensagens();
		return ListaDeMensagens;
	}
	
	public void  mostraMensagensEnviadas() throws FalhaAcessoAosDadosException
	{
		try {
			atualizaListaMensagens();
		} catch (FalhaAcessoAosDadosException e) {
			e.printStackTrace();
		}
		
		System.out.println("Mensagens Enviadas!");
		for(Mensagem mensagem : getListaDeMensagens())
		{
			System.out.println("De: " + mensagem.getRemetente().getCodinome() + "\nPara: " + mensagem.getDestinatario().getCodinome() + "\n" + mensagem.getMensagem() );
		}
	}
	public void listaMensagensEnviadas()
	{
		try {
			atualizaListaParticipantes();
		} catch (FalhaAcessoAosDadosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Ranking de mensagens enviadas.");
		Collections.sort(listaParticipantes, new OrdenaEnviadas());
		for(Participante participante : listaParticipantes)
			System.out.println(participante.getQntdEnviadas() + " - " + participante.getCodinome() );
	}
	public void listaMensagensRecebidas()
	{		
		try {
			atualizaListaParticipantes();
		} catch (FalhaAcessoAosDadosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Ranking de mensagens recebidas.");
		Collections.sort(listaParticipantes, new OrdenaRecebidas());
		for(Participante participante : listaParticipantes)
			System.out.println(participante.getQntdRecebidas() + " - " + participante.getNome());
	}
	public Participante estaNaLista(String nome)
	{
		try {
			atualizaListaParticipantes();
		} catch (FalhaAcessoAosDadosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Participante participante : listaParticipantes)
			if(participante.getNome().equals(nome)) return participante;
		
		return null;
	}
	public void mostraParticipantes()
	{
		for(Participante part : listaParticipantes)
			System.out.println(part.getNome());

	}
	
	public void criaSugestaoGeral(Participante participante)
	{
		FileSugestaoGeralDao sugestaoGeral = new FileSugestaoGeralDao();
		
		Sugestao sug = new Sugestao();
		System.out.println("Digite sua sugestão");
		sug.setSugestao(new Scanner(System.in).nextLine());
		System.out.println("Participante=" + participante.getNome());
		sug.setParticipante(participante);
		listaSugestoes.add(sug);
		
		try {
			sugestaoGeral.insereSugestaoGeral(sug);
		} catch (FalhaAcessoAosDadosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Sugestão compilada com sucesso!");
	}
	
	public void atualizaListaSugestoesGerais()
	{
		FileSugestaoGeralDao sugGeral = new FileSugestaoGeralDao();
		try {
			listaSugestoes = (ArrayList<Sugestao>) sugGeral.buscaTodos();
		} catch (FalhaAcessoAosDadosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void mostraSugestoesGerais()
	{
		atualizaListaSugestoesGerais();
		for(Sugestao sug : listaSugestoes)
			System.out.println(sug.getSugestao() + " de " + sug.getParticipante().getNome());
	}
	
	public Patrocinador cadastroPatrocinador()
	{
		Scanner sc = new Scanner(System.in);
		Patrocinador patrocinador = new Patrocinador();

		System.out.println("Digite o nome do patrocinador: ");
		patrocinador.setNome(sc.nextLine()); 
		
		System.out.println("Digite o ramal: ");
		patrocinador.setRamal(sc.nextInt()); 

		System.out.println("Digite o codinome: ");
		patrocinador.setCodinome(new Scanner(System.in).nextLine());

		listaPatrocinadores.add(patrocinador);
		listaParticipantes.add(patrocinador);
			return patrocinador;
	}
	
	public ArrayList<Patrocinador> retornaPatrocinadores() throws FalhaAcessoAosDadosException
	{
		atualizaListaPatrocinadores();
		return listaPatrocinadores;
	}
	
	public void menu()
	{
		System.out.println("\n1  - Cadastrar as informações dos Participantes, incluindo seus codinomes.");
		System.out.println("2  - Cadastrar as sugestões de presentes de cada participante.");
		System.out.println("3  - Sortear o amigo secreto.");
		System.out.println("4  - Enviar mensagens entre os participantes.");
		System.out.println("5  - Mostrar todas as mensagens enviadas entre os Participantes.");
		System.out.println("6  - Mostrar as sugestões de presentes de um determinado nome ou codinome.");
		System.out.println("7  - Cadastrar sugestões gerais para um próximo amigo secreto.");
		System.out.println("8  - Mostrar todas as sugestões gerais.");
		System.out.println("9  - Mostrar listas ordenadas por quantidade de quem recebeu e de quem enviou mais mensagens.");
		System.out.println("10 - Revelar amigo secreto.");
		System.out.println("11 - Cadastrar patrocinador.");
		System.out.println("12 - Criar aviso geral.");
		System.out.println("13 - Mostra avisos gerais");
		System.out.println("14 - Demonstrativos Finais.\n");
		System.out.println("0  - Sair.\n");
		System.out.println("Digite a opção desejada.");
	}
	
}
