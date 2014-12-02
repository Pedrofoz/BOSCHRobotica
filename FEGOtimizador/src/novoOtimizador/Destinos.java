package novoOtimizador;

import lejos.robotics.Color;
import lejos.robotics.navigation.Waypoint;

public class Destinos {
	
	private int id;
	private String Descricao;
	private String nomeDaRua;
	private Color cor;
	/*private Rua rua;*/
	private Waypoint localizacao;
	
	
	public Destinos(int id, String descricao, String nomeDaRua, Color cor, Waypoint localizacao) 
	{
		this.id = id;
		Descricao = descricao;
		this.nomeDaRua = nomeDaRua;
		this.cor = cor;
		this.localizacao = localizacao;
	}
	public Destinos(){}
	
	public String getNomeDaRua()
	{
		return this.nomeDaRua;
	}
	public void setNomeDaRua(String s)
	{
		this.nomeDaRua = s;
	}
	
	public Waypoint getLocalizacao() 
	{
		return localizacao;
	}
	public void setLocalizacao(Waypoint localizacao) 
	{
		this.localizacao = localizacao;
	}
	
	public Color getCor() 
	{
		return cor;
	}
	public void setCor(Color cor) 
	{
		this.cor = cor;
	}
	
	public String getDescricao() 
	{
		return Descricao;
	}
	public void setDescricao(String descricao) 
	{
		Descricao = descricao;
	}
	
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	
	/*public Rua getRua() {
		return rua;
	}
	public void setRua(Rua rua) {
		this.rua = rua;
	}*/
	
}
