package br.unesp.feg.modelo;

import java.awt.Color;

public class Locais {
	private String descLocal;
	private Color corLocal;
	private int linhaLocal;
	private int colunaLocal;
	
	
	public String getDescLocal() {
		return descLocal;
	}
	public void setDescLocal(String descLocal) {
		this.descLocal = descLocal;
	}
	public Color getCorLocal() {
		return corLocal;
	}
	public void setCorLocal(Color color) {
		this.corLocal = color;
	}
	public int getLinhaLocal() {
		return linhaLocal;
	}
	public void setLinhaLocal(int linhaLocal) {
		this.linhaLocal = linhaLocal;
	}
	public int getColunaLocal() {
		return colunaLocal;
	}
	public void setColunaLocal(int colunaLocal) {
		this.colunaLocal = colunaLocal;
	}

}
