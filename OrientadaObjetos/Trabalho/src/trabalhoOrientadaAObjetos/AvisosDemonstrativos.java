package trabalhoOrientadaAObjetos;

import java.util.*;

public class AvisosDemonstrativos
{
	public final void mostraAmigosSecretos(ArrayList<Participante> participantes)
	{
		
		for (Participante participante : participantes)
		{
			if(participante.getAmigoSecreto()==null){
				System.out.println("Sorteio não foi realizado!");
				break;
			}
			System.out.println(participante.getNome() + " tirou " + participante.getAmigoSecreto().getNome());
		}
		if(participantes.isEmpty()==true) System.out.println("A lista de participantes está vazia.");
	}
	
	public void mostraCodinomes(ArrayList<Participante> participantes)
	{
		for( Participante participante : participantes)
			System.out.println(participante.getCodinome() + " = " + participante.getNome() );
	}
	
	public void mostraQntdMensagens(ArrayList<Mensagem> mensagens)
	{
		System.out.println("A quantidade de mensagens enviadas foi " + mensagens.size());
	}
}