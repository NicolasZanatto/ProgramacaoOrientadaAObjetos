package trabalhoOrientadaAObjetos;

import java.util.*;
import java.io.Serializable;


public class Participante implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	
	private int Ramal;
	private String Nome;
	private String Codinome;
	private Participante AmigoSecreto;
	private ArrayList<SugestaoPresente> SugestoesPresentes;
//	private ArrayList<Mensagem> recebidas;
//	private ArrayList<Mensagem> enviadas;
		
	private int qntdRecebidas;
	private int qntdEnviadas;
	
	
	public int getQntdRecebidas() {
		return qntdRecebidas;
	}
	public void setQntdRecebidas() {
		this.qntdRecebidas++;
	}
	public int getQntdEnviadas() {
		return qntdEnviadas;
	}
	public void setQntdEnviadas() {
		this.qntdEnviadas++;
	}
	public final String getNome()
	{
		return Nome;
	}
	public final void setNome(String value)
	{
		Nome = value;
	}
	
	public final int getRamal()
	{
		return Ramal;
	}
	public final void setRamal(int value)
	{
		Ramal = value;
	}
	
	public final String getCodinome()
	{
		return Codinome;
	}
	public final void setCodinome(String value)
	{
		Codinome = value;
	}
	public final Participante getAmigoSecreto()
	{
		return AmigoSecreto;
	}
	public final void setAmigoSecreto(Participante value)
	{
		AmigoSecreto = value;
	}

	 

	public Participante(String Nome, int Ramal, String Codinome)
	{
		this.setNome(Nome);
		this.setRamal(Ramal);
		this.setCodinome(Codinome);
	}
	public Participante()
	{ 
		inicializa();
	}
	public final ArrayList<SugestaoPresente> getretornaListaPresentes()
	{
		return SugestoesPresentes;
	}
	public void setSugestoesPresentes(ArrayList<SugestaoPresente> sugestoesPresentes) {
		SugestoesPresentes = sugestoesPresentes;
	}
	
	public void addSugestoesPresentes(SugestaoPresente sugestaoPresente) {
		SugestoesPresentes.add(sugestaoPresente);
	}
	public void mostraListaPresentes()
	{
		System.out.println("Lista de sugestões de presentes.");
		for(SugestaoPresente sug : SugestoesPresentes)
			System.out.println(sug.getSugestao());
	}
	
/*	public ArrayList<Mensagem> getMensagensEnviadas()
	{
		return enviadas;
	}
	
	public ArrayList<Mensagem> getMensagensRecebidas()
	{
		return recebidas;
	}
	*/
	public void inicializa()
	{
		AmigoSecreto=null;
		Codinome="";
		SugestoesPresentes = new ArrayList<SugestaoPresente>();
		Nome=null;
		Ramal=0;
		qntdEnviadas=0;
		qntdRecebidas=0;
	//	enviadas =  new ArrayList<Mensagem>();
	//	recebidas = new ArrayList<Mensagem>();

	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("Nome = ");
		sb.append(this.getNome());
		sb.append(" ");
		
		sb.append(",Codinome = ");
		sb.append(this.getCodinome());
		sb.append(" ");

		sb.append(",Ramal = ");
		sb.append(this.getRamal());
		sb.append(" ");
		
		sb.append(",SugestoesPresentes = ");
		sb.append(this.getretornaListaPresentes());
		sb.append(" ");
		
/*		sb.append(",Mensagens Enviadas = ");
		sb.append(this.getMensagensEnviadas());
		sb.append(" ");

		sb.append(",Mensagens Recebidas = ");
		sb.append(this.getMensagensRecebidas());
		sb.append(" ");
*/				
		return sb.toString();
	}
	
	
}