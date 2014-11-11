package br.unesp.feg.modelo;




import lejos.robotics.Color;
import lejos.robotics.navigation.Waypoint;

public class Destinos {
	private int id;
	private String Descricao;
	private Color cor;
	private Waypoint localizacao;
	
	
	public Waypoint getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(Waypoint localizacao) {
		this.localizacao = localizacao;
	}
	public Color getCor() {
		return cor;
	}
	public void setCor(Color cor) {
		this.cor = cor;
	}
	public String getDescricao() {
		return Descricao;
	}
	public void setDescricao(String descricao) {
		Descricao = descricao;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
