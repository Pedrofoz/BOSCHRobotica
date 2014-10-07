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

	//M�todo para encontrar o menor caminho
	public ArrayList<Rua> menorCaminho(Destinos[] destino )
	{
		// destino[0] = ponto atual
		// destino[1] = ponto final
		//Variaveis auxiliares
		ArrayList<Rua> aL = new ArrayList<Rua>();
		Iterator it = ruas.iterator();
		int a = 0;
		Rua r = new Rua();
		
		
		while(it.hasNext()) //Percorrer o arrayList at� o fim
		{
			r = (Rua) it.next();
			if (destino[0].getNomeDaRua().equals(r.getNome()))
			{
				aL.add(r);
			}
		}
		it = aL.iterator(); //aL com as ruas de mesmo nome
		
		
		/*
		 * Com o nome da Rua, encontrar qual Rua, das ruas (trechos) com o mesmo nome, o rob� est�!
		 * Encontrar atrav�s da menor dist�ncia do ponto de destino[0] aos pontos de Inicio e Fim das ruas...
		 */
		double d;
		Rua atualIn;
		r = (Rua) it.next();
		d = Math.sqrt((Math.pow((destino[0].getLocalizacao().getX()-r.getInicio().getX()),2.0))+(Math.pow((destino[0].getLocalizacao().getY()-r.getInicio().getY()),2.0)))+Math.sqrt((Math.pow((destino[0].getLocalizacao().getX()-r.getFim().getX()),2.0))+(Math.pow((destino[0].getLocalizacao().getY()-r.getFim().getY()),2.0)));
		atualIn = r;
		double x;
		while(it.hasNext())
		{
			r = (Rua) it.next();
			x = Math.sqrt((Math.pow((destino[0].getLocalizacao().getX()-r.getInicio().getX()),2.0))+(Math.pow((destino[0].getLocalizacao().getY()-r.getInicio().getY()),2.0)))+Math.sqrt((Math.pow((destino[0].getLocalizacao().getX()-r.getFim().getX()),2.0))+(Math.pow((destino[0].getLocalizacao().getY()-r.getFim().getY()),2.0)));
			if (d > x)
			{
				d = x;
				atualIn = r;
			}
		}
		//Logo, o destino[0] (atual localicza��o) esta na rua "atual"
	
		//Ocorre o mesmo para o ponto final
		it = ruas.iterator();
		aL = new ArrayList<Rua>();
		while(it.hasNext()) //Percorrer o arrayList at� o fim
		{
			r = (Rua) it.next();
			if (destino[1].getNomeDaRua().equals(r.getNome()))
			{
				aL.add(r);
			}
		}
		it = aL.iterator(); //aL com as ruas de mesmo nome
		Rua atualFim = new Rua();
		r = (Rua) it.next();
		d = Math.sqrt((Math.pow((destino[1].getLocalizacao().getX()-r.getInicio().getX()),2.0))+(Math.pow((destino[1].getLocalizacao().getY()-r.getInicio().getY()),2.0)))+Math.sqrt((Math.pow((destino[1].getLocalizacao().getX()-r.getFim().getX()),2.0))+(Math.pow((destino[1].getLocalizacao().getY()-r.getFim().getY()),2.0)));
		atualFim = r;
		while(it.hasNext())
		{
			r = (Rua) it.next();
			x = Math.sqrt((Math.pow((destino[1].getLocalizacao().getX()-r.getInicio().getX()),2.0))+(Math.pow((destino[1].getLocalizacao().getY()-r.getInicio().getY()),2.0)))+Math.sqrt((Math.pow((destino[1].getLocalizacao().getX()-r.getFim().getX()),2.0))+(Math.pow((destino[1].getLocalizacao().getY()-r.getFim().getY()),2.0)));
			if (d > x)
			{
				d = x;
				atualFim = r;
			}
		}
				
		//Logo, o destino[0] (atual localicza��o) esta na rua "atualIn"
		//Logo, o destino[1] (atual localicza��o) esta na rua "atualFim"
	
		
		/*
		 * Agora, encontrar o menor caminho (sequencia de ruas v�lidas)
		 * uma vez que j� sabemos qual a rua inicial da sequ�ncia..
		 **********************************************************************************************************
		 *
		 * Prop�e-se um algoritmo que leve em conta a menor distancia entre
		 * os pontos de inicio e fim da rua em quest�o at� o ponto de destino final
		 * (deve-se considerar o sentido de fluxo pelas ruas, e cascatear as respostas, ou seja, montar aL final)
		 */
			
		Rua[][] caminhosPossiveis = {};
		aL = new ArrayList<Rua>();
		int l = 0;
		int c = 0;
		Rua atual = atualIn;
		Rua[] ruasPossiveis = {};
		boolean nCaminhosFinalizados = false;
		while(nCaminhosFinalizados == false)
		{
			if(atual.getDirecao()[0] == true)
			{				
				//Inicio -> Fim
				ruasPossiveis = verConexoes(atual.getFim());
				for (int i = 0; i < ruasPossiveis.length; i++);
				{
					//Cascatear o processo de conexoes
					//Adicionando toda nova conexao a uma nova linha e copiando as colunas anteriores
					/*
					 * [INICIO][a][b][c]
					 * 	                [d][FINAL]
					 * 	                [e][g][h][FINAL]
					 * ..
					 * Ao final, preencher as linhas com colunas vazias com as colunas anteriores e considerar
					 * apenas as que tiverem [FINAL] como �ltimo elemento, ou seja:
					 *
					 * 1) [INICIO][a][b][c][d][FINAL]
					 * 2) [INICIO][a][b][c][e][g][h][FINAL]
					 */
				}
				
			}
			if(atual.getDirecao()[1] == true)
			{
				//Fim -> Inicio
				//Executar similarmente ao anterior (Inicio -> Fim)
			}
			
			/*
			 * Adicionar m�todo para comparar os caminhos por tempo e distancia
			 * Encontrando o desejado
			 */
			//nCaminhosFinalizados = true;
		}
		return aL;
	}
	
	//Analisa todas as ruas que fazem conex�o com um determinado Waypoint
	public Rua[] verConexoes(Waypoint a)
	{
		Rua[] r = {};
		Rua ruaux = new Rua();
		ArrayList<Rua> auxL = new ArrayList<Rua>();
		Iterator it = ruas.iterator();
		int i = 0;
		while(it.hasNext())
		{
			ruaux = (Rua) it.next();
			//Viabiliza a conexao por sentidos permitidos
			if ((ruaux.getInicio().equals(a) && ruaux.getDirecao()[0] == true) || (ruaux.getFim().equals(a) && ruaux.getDirecao()[1] == true))
			{
				r[i] = ruaux;
				i++;
			}
		}
		return r;
	}
}
