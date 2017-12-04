package trabalhoOrientadaAObjetos;


import java.util.ArrayList;
import java.util.Scanner;

import persistence.exceptions.FalhaAcessoAosDadosException;
import persistence.exceptions.FalhaAcessoDadosAvisosException;
import persistence.exceptions.FalhaAcessoDadosMensagemException;
import persistence.exceptions.FalhaAcessoDadosParticipanteException;
import persistence.exceptions.FalhaAcessoDadosPatrocinadorException;
import persistence.exceptions.FalhaAcessoDadosSugestaoGeralException;
import persistence.exceptions.FalhaAcessoDadosSugestaoPresenteException;
import persistence.file.FileParticipanteDao;
import persistence.file.FilePatrocinadorDao;

public class Program
{
	public static void main(String[] args)
	{
	
		Scanner sc = new Scanner(System.in);
		Gerenciador Gerenciador = new Gerenciador();
		FilePatrocinadorDao patrocinadorDao = new FilePatrocinadorDao();
		FileParticipanteDao participanteDao = new FileParticipanteDao();
		System.out.println("Visto que o patrocinador é um participante também,\nantes de realizar o sorteio deve-se cadastra-lo. ");
		Gerenciador.menu();
		
		ArrayList<Participante> particip;
		try {
			particip = (ArrayList<Participante>) participanteDao.buscaTodos();
			System.out.println("Participantes:");
			for(Participante par : particip)
				System.out.println(par.getNome());
		} catch (FalhaAcessoDadosParticipanteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ArrayList<Patrocinador> patr;
		try {
			patr = (ArrayList<Patrocinador>) patrocinadorDao.buscaTodos();
			System.out.println("Patrocinadores:");
			for(Patrocinador par : patr)
				System.out.println(par.getNome());
		} catch (FalhaAcessoDadosPatrocinadorException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int opcao=sc.nextInt();
		while(opcao!=0)
		{	
			switch(opcao)
			{
				case 1: 			//Cadastro Participante
					try{			
						Participante pt = new Participante();
						pt=Gerenciador.cadastroInfo();
						if(pt!=null)
							participanteDao.insereParticipante(pt); 
					}catch(FalhaAcessoDadosParticipanteException e)
					{
						System.out.println(e.getMessage() + ", case 1");
					}
					break;
			
				case 2:				//Add sugestoes de presentes
						
						System.out.println("Digite para qual participante deseja adicionar sugestões de presentes.");
				Participante part;
					try {
						part = Gerenciador.procuraParticipante(new Scanner(System.in).nextLine());
						if(part!=null)
						{
							Gerenciador.adicionaSugestoesPresentes(part);
						}
						else
							System.out.println("Participante não encontrado");
					
					} catch (FalhaAcessoDadosParticipanteException e1) {
						System.out.println(e1.getMessage() + ", case 2");
					} catch (FalhaAcessoDadosSugestaoPresenteException e) {
						System.out.println(e.getMessage() + ", case 2");
					}
							
					break;	
			
				case 3:				//Sorteia
					try{
						if(Gerenciador.retornaParticipantes().size()>2)
						{
							Gerenciador.matchSorteio(Gerenciador.sorteio(), Gerenciador.retornaParticipantes());
							System.out.println("Sorteio realizado com sucesso!");
						}
						else
							System.out.println("Não há participantes suficientes para o sorteio");
					
					}catch(FalhaAcessoDadosParticipanteException e){
							System.out.println(e.getMessage() + ", case 3");
					}
					
					break;
					
				case 4: 			//Envia msg
				try {
					Gerenciador.enviaMensagem();
				} catch (FalhaAcessoDadosMensagemException e) {
					System.out.println(e.getMessage() + ", case 4");
				} catch (FalhaAcessoDadosParticipanteException e) {
					System.out.println(e.getMessage() + ", case 4");
				}
					break;
			
				case 5:				//Mostra msg enviadas
				try {
					Gerenciador.mostraMensagensEnviadas();
				} catch (FalhaAcessoDadosParticipanteException e) {
					System.out.println(e.getMessage() + ", case 5");
				}
					break;
			
				case 6:				//Sugestoes de presentes de algum participante
					System.out.println("Digite a forma de busca: 1 para Nome e 2 para Codinome.");
					int busca = sc.nextInt();
					System.out.println("Digite o que deve ser buscado.");
					String buscaS = new Scanner(System.in).nextLine();
					boolean encontrou=false;
					if(busca==1)
					{
						try {
							for(Participante partic : Gerenciador.retornaParticipantes()){
								
								if(partic.getNome().equals(buscaS))
								{
									encontrou=true;
									Gerenciador.mostraSugestoesPresentes(partic);
								}
							}
						} catch (FalhaAcessoDadosParticipanteException e) {
							System.out.println(e.getMessage() + ", case 6, busca=1");
						}
						if(encontrou==false) System.out.println("Participante não encontrado.");
					}
					else if(busca==2)
					{
						try {
							for(Participante partic : Gerenciador.retornaParticipantes())
								if(partic.getCodinome().equals(buscaS))
								{
									encontrou=true;
									
									Gerenciador.mostraSugestoesPresentes(partic);
								}
						} catch (FalhaAcessoDadosParticipanteException e) {
							System.out.println(e.getMessage() + ", case 6, busca=2");

						}
						
						if(encontrou==false) System.out.println("Participante não encontrado.");
					}
					else System.out.println("Número para busca incorreto!");
					break;
			
				case 7: 		//Cadastra sugestoes
					System.out.println("Digite o participante que dará a sugestão");
					String Nome = new Scanner(System.in).nextLine();
				Participante p;
				try {
					p = Gerenciador.estaNaLista(Nome);

					if(p!=null)
					{
						System.out.println("teste entrou");
						Gerenciador.criaSugestaoGeral(p);
					}
					else System.out.println("Participante não encontrado!");
				} catch (FalhaAcessoDadosParticipanteException e) {
					System.out.println(e.getMessage() + ", case 7");

				} catch (FalhaAcessoDadosSugestaoGeralException e) {
					System.out.println(e.getMessage() + ", case 7");
				}
					break;
					
				case 8:			//Mostra sugestoes gerais
					System.out.println("Lista de Sugestões.");
				try {
					Gerenciador.mostraSugestoesGerais();
				} catch (FalhaAcessoDadosSugestaoGeralException e) {
					System.out.println(e.getMessage() + ", case 8");

				}
					break;
			
				case 9:			//Lista por quem enviou e recebeu mais msgs
				try {
					Gerenciador.listaMensagensEnviadas();
					Gerenciador.listaMensagensRecebidas();
				} catch (FalhaAcessoDadosParticipanteException e) {
					System.out.println(e.getMessage() + ", case 9");
				}
					
					break;
			
				case 10: 		//Mostra amigo secreto
				
				try {
					Gerenciador.mostraAmigosSecretos();
				} catch (FalhaAcessoDadosParticipanteException e) {
					System.out.println(e.getMessage() + ", case 10");
				}
				
					break;
			
				case 11: 		//Cadastra Patrocinador
					
					Patrocinador patroc = new Patrocinador();
					patroc = Gerenciador.cadastroPatrocinador();
					if(patroc!=null)
					{	
						try {
							patrocinadorDao.inserePatrocinador(patroc);
							participanteDao.insereParticipante(patroc);
						} catch (FalhaAcessoDadosParticipanteException e) {
							System.out.println(e.getMessage() + ", case 11");
							e.printStackTrace();
						} catch (FalhaAcessoDadosPatrocinadorException e) {
							System.out.println(e.getMessage() + ", case 11");
						}
					}
					
					break;
				
				case 12:		//Cadastra aviso
				try {
					Gerenciador.cadastroAvisos();
				} catch (FalhaAcessoDadosPatrocinadorException e) {
					System.out.println(e.getMessage() + ", case 12");

				}
					break;
				
				case 13:		//Mostra avisos		
				try {
					System.out.println("Lista de Avisos Gerais.");
					Gerenciador.mostraAvisos();
				} catch (FalhaAcessoDadosPatrocinadorException e) {
					System.out.println(e.getMessage() + ", case 13");

				}
						break;
				
				case 14: // Fim Amigo Secreto/ Demonstrativos
					
				try {
					System.out.println("Demonstrativos Finais");
					System.out.println("Amigo Secreto:\n");
					Gerenciador.mostraAmigosSecretos();
					System.out.println("\nQuantidade de mensagens enviadas =" + Gerenciador.getListaDeMensagens());
					Gerenciador.listaMensagensRecebidas();
					Gerenciador.listaMensagensEnviadas();
				} catch (FalhaAcessoDadosParticipanteException e) {
					System.out.println(e.getMessage() + ", case 14");
				}
				
					opcao=0;
					break;
					
				default:
					System.out.println("Número Inválido, digite outro.");
			}
			Gerenciador.menu();
			opcao = sc.nextInt();
		}
		System.out.println("Fim do Amigo Secreto.");
		sc.close();
	}
}