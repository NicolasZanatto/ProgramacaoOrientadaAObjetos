package trabalhoOrientadaAObjetos;


import java.util.ArrayList;
import java.util.Scanner;

import persistence.exceptions.FalhaAcessoAosDadosException;
import persistence.file.FileAvisosDao;
import persistence.file.FileParticipanteDao;
import persistence.file.FilePatrocinadorDao;

public class Program
{
	public static void main(String[] args) throws FalhaAcessoAosDadosException
	{
	
		Scanner sc = new Scanner(System.in);
		Gerenciador Gerenciador = new Gerenciador();
		FilePatrocinadorDao patrocinadorDao = new FilePatrocinadorDao();
		FileParticipanteDao participanteDao = new FileParticipanteDao();
		FileAvisosDao avisosDao = new FileAvisosDao();
		System.out.println("Visto que o patrocinador é um participante também,\nantes de realizar o sorteio deve-se cadastra-lo. ");
		Gerenciador.menu();
		ArrayList<Participante> particip = (ArrayList<Participante>) participanteDao.buscaTodos();
		ArrayList<Patrocinador> patr = (ArrayList<Patrocinador>) patrocinadorDao.buscaTodos();

		
		System.out.println("Participantes:");
		for(Participante par : particip)
			System.out.println(par.getNome());
		System.out.println("Patrocinadores:");
		for(Patrocinador par : patr)
			System.out.println(par.getNome());
		
		int opcao=sc.nextInt();
		while(opcao!=0)
		{	
			switch(opcao)
			{
				case 1: 			//Cadastro Participante
							//Retornar o objeto cadastrado e passar pra linha de baixo					
					 participanteDao.insereParticipante(Gerenciador.cadastroInfo()); 
					break;
			
				case 2:				//Add sugestoes de presentes
					
					System.out.println("Digite para qual participante deseja adicionar sugestões de presentes.");
					Participante part = Gerenciador.procuraParticipante(new Scanner(System.in).nextLine());
					if(part!=null)
					{
					Gerenciador.adicionaSugestoesPresentes(part);
					}
					else
						System.out.println("Participante não encontrado");
					break;	
			
				case 3:				//Sorteia
					if(Gerenciador.retornaParticipantes().size()>2)
					{
						Gerenciador.matchSorteio(Gerenciador.sorteio(), Gerenciador.retornaParticipantes());
						System.out.println("Sorteio realizado com sucesso!");
					}
					
					else
						System.out.println("Não há participantes suficientes para o sorteio");
					break;
					
				case 4: 			//Envia msg
					Gerenciador.enviaMensagem();
					break;
			
				case 5:				//Mostra msg enviadas
					Gerenciador.mostraMensagensEnviadas();
					break;
			
				case 6:				//Sugestoes de presentes de algum participante
					System.out.println("Digite a forma de busca: 1 para Nome e 2 para Codinome.");
					int busca = sc.nextInt();
					System.out.println("Digite o que deve ser buscado.");
					String buscaS = new Scanner(System.in).nextLine();
					boolean encontrou=false;
					if(busca==1)
					{
						for(Participante partic : Gerenciador.retornaParticipantes()){
							
							if(partic.getNome().equals(buscaS))
							{
								encontrou=true;
								Gerenciador.mostraSugestoesPresentes(partic);
							}
						}
						if(encontrou==false) System.out.println("Participante não encontrado.");
					}
					else if(busca==2)
					{
						for(Participante partic : Gerenciador.retornaParticipantes())
							if(partic.getCodinome().equals(buscaS))
							{
								partic.mostraListaPresentes();
							}
						if(encontrou==false) System.out.println("Participante não encontrado.");
					}
					else System.out.println("Número para busca incorreto!");
					break;
			
				case 7: 		//Cadastra sugestoes
					System.out.println("Digite o participante que dará a sugestão");
					String Nome = new Scanner(System.in).nextLine();
					Participante p = Gerenciador.estaNaLista(Nome);
					if(p!=null)
					{
						System.out.println("teste entrou");
						Gerenciador.criaSugestaoGeral(p);
					}
					else System.out.println("Participante não encontrado!");
					break;
					
				case 8:			//Mostra sugestoes gerais
					System.out.println("Lista de Sugestões.");
					Gerenciador.mostraSugestoesGerais();
					break;
			
				case 9:			//Lista por quem enviou e recebeu mais msgs
					Gerenciador.listaMensagensEnviadas();
					Gerenciador.listaMensagensRecebidas();
					break;
			
				case 10: 		//Mostra amigo secreto
					Gerenciador.mostraAmigosSecretos();
					break;
			
				case 11: 		//Cadastra Patrocinador
					
					Patrocinador patroc = new Patrocinador();
					patroc = Gerenciador.cadastroPatrocinador();
					participanteDao.insereParticipante(patroc);
					patrocinadorDao.inserePatrocinador(patroc);
					break;
				
				case 12:		//Cadastra aviso
					System.out.println("Digite o nome do patrocinador que deseja deixar um aviso");
					String pat = new Scanner(System.in).nextLine();
					
					for(Patrocinador patrocinador : Gerenciador.retornaPatrocinadores())
						if(patrocinador.getNome().equals(pat))
						{
							System.out.println("Digite seu aviso: ");
						
							avisosDao.insereAviso(patrocinador.cadastrarAvisosGerais(new Scanner(System.in).nextLine()));
						}
				
					break;
				case 13:		//Mostra avisos
					System.out.println("Lista de Avisos Gerais.");
					for(Patrocinador patrocinador : Gerenciador.retornaPatrocinadores())
					{
						System.out.println("Entrou!");
						patrocinador.showAvisosGerais();
					}
						break;
				
				case 14: // Fim Amigo Secreto/ Demonstrativos
					System.out.println("Demonstrativos Finais");
					System.out.println("Amigo Secreto:\n");
					Gerenciador.mostraAmigosSecretos();
					System.out.println("\nQuantidade de mensagens enviadas =" + Gerenciador.getListaDeMensagens().size());
					Gerenciador.listaMensagensRecebidas();
					Gerenciador.listaMensagensEnviadas();
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