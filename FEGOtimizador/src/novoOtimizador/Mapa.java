package novoOtimizador;

import java.util.ArrayList;
import java.lang.*;
import java.util.Iterator;

import lejos.robotics.navigation.Waypoint;

public class Mapa {
	/*
	 * oi
	 */
	protected ArrayList<Rua> ruas;
	protected ArrayList<Destinos> destinos;
	
	//Construtores
	public Mapa() 
	{
		//
	}
	public Mapa(ArrayList<Rua> ruas, ArrayList<Destinos> destinos) 
	{
		super();
		this.ruas = ruas;
		this.destinos = destinos;
	}

	//Metodos Acessores
	public ArrayList<Rua> getRuas() 
	{
		return ruas;
	}
	public void setRuas(ArrayList<Rua> ruas) 
	{
		this.ruas = ruas;
	}
	public ArrayList<Destinos> getDestinos() 
	{
		return destinos;
	}
	public void setDestinos(ArrayList<Destinos> destinos) 
	{
		this.destinos = destinos;
	}

	//Método para encontrar o menor caminho
	public ArrayList<Rua> menorCaminho(Destinos[] destino )
	{
		// destino[0] = ponto atual
		// destino[1] = ponto final
		//Variaveis auxiliares
		ArrayList<Rua> aL = new ArrayList<Rua>();
		Iterator it = ruas.iterator();
		int a = 0;
		Rua r = new Rua();
		
		
		while(it.hasNext()) //Percorrer o arrayList até o fim
		{
			r = (Rua) it.next();
			if (destino[0].getNomeDaRua().equals(r.getNome()))
			{
				aL.add(r);
			}
		}
		it = aL.iterator(); //aL com as ruas de mesmo nome
		
		
		/*
		 * Com o nome da Rua, encontrar qual Rua, das ruas (trechos) com o mesmo nome, o robô está!
		 * Encontrar através da menor distância do ponto de destino[0] aos pontos de Inicio e Fim das ruas...
		 */
		double d;
		Rua atual;
		r = (Rua) it.next();
		d = Math.sqrt((Math.pow((destino[0].getLocalizacao().getX()-r.getInicio().getX()),2.0))+(Math.pow((destino[0].getLocalizacao().getY()-r.getInicio().getY()),2.0)))+Math.sqrt((Math.pow((destino[0].getLocalizacao().getX()-r.getFim().getX()),2.0))+(Math.pow((destino[0].getLocalizacao().getY()-r.getFim().getY()),2.0)));
		atual = r;
		double x;
		while(it.hasNext())
		{
			r = (Rua) it.next();
			x = Math.sqrt((Math.pow((destino[0].getLocalizacao().getX()-r.getInicio().getX()),2.0))+(Math.pow((destino[0].getLocalizacao().getY()-r.getInicio().getY()),2.0)))+Math.sqrt((Math.pow((destino[0].getLocalizacao().getX()-r.getFim().getX()),2.0))+(Math.pow((destino[0].getLocalizacao().getY()-r.getFim().getY()),2.0)));
			if (d > x)
			{
				d = x;
				atual = r;
			}
		}
		//Logo, o destino[0] (atual localiczação) esta na rua "atual"
		
		
		/*
		 * Agora, encontrar o menor caminho (sequencia de ruas válidas)
		 * uma vez que já sabemos qual a rua inicial da sequência..
		 **********************************************************************************************************
		 * Propõe-se um algoritmo que leve em conta a menor distancia entre
		 * os pontos de inicio e fim da rua em questão até o ponto de destino final
		 * (deve-se considerar o sentido de fluxo pelas ruas, e cascatear as respostas, ou seja, montar aL final)
		 */
		
		return aL;
	}
}
