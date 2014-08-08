package br.unesp.feg.modelo;

import java.awt.Color;
import java.awt.Point;

public class Destinos {
	private int id;
	private String Descricao;
	private Color cor;
	private Point localizacao;
	
	
	public Point getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(Point localizacao) {
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
