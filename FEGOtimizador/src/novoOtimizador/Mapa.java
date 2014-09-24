package novoOtimizador;

import java.util.ArrayList;
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
		return aL;
	}
}
