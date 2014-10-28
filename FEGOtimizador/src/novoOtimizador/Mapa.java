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
	protected Rua[][] caminhosPossiveis = {};
	
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
		
		/*for(Rua r1: ruas)
		{
			r = (Rua) it.next();
			if (destino[0].getNomeDaRua().equals(r.getNome()))
			{
				aL.add(r);
			}
		}*/
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
		//Logo, o destino[0] (atual localiczação) esta na rua "atual"
	
		//Ocorre o mesmo para o ponto final
		it = ruas.iterator();
		aL = new ArrayList<Rua>();
		while(it.hasNext()) //Percorrer o arrayList até o fim
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
				
		//Logo, o destino[0] (atual localiczação) esta na rua "atualIn"
		//Logo, o destino[1] (atual localiczação) esta na rua "atualFim"
	
		
		/*
		 * Agora, encontrar o menor caminho (sequencia de ruas válidas)
		 * uma vez que já sabemos qual a rua inicial da sequência..
		 **********************************************************************************************************
		 *
		 * Propõe-se um algoritmo que leve em conta a menor distancia entre
		 * os pontos de inicio e fim da rua em questão até o ponto de destino final
		 * (deve-se considerar o sentido de fluxo pelas ruas, e cascatear as respostas, ou seja, montar aL final)
		 */

		aL = new ArrayList<Rua>();
		int l = 0;
		int c = 0;
		caminhosPossiveis[0][0] = atualIn;
		c++;
		Rua atual = atualIn;
		int linhaOrigem = 0;
		Rua[] ruasPossiveis = {};
		boolean nCaminhosFinalizados = false;
		boolean liberaCol = false;
		while(nCaminhosFinalizados == false)
		{
			liberaCol = false;
			if(atual.getDirecao()[0] == true)
			{				
				//Inicio -> Fim
				ruasPossiveis = verConexoes(atual.getFim(), atual);
					//Cascatear o processo de conexoes
					//Adicionando toda nova conexao a uma nova linha e copiando as colunas anteriores
					/*
					 * [INICIO][a][b][c]
					 * 	                [d][FINAL]
					 * 	                [e][g][h][FINAL]
					 * .
					 * Ao final, preencher as linhas com colunas vazias com as colunas anteriores e considerar
					 * apenas as que tiverem [FINAL] como último elemento, ou seja:
					 *
					 * 1) [INICIO][a][b][c][d][FINAL]
					 * 2) [INICIO][a][b][c][e][g][h][FINAL]
					 */
					for (int z = 0; z < ruasPossiveis.length; z++) 
					{
						if(!compararRua(ruasPossiveis[z],l)) //verifica se a rua a ser adicionada não é preexistente na mesma linha
						{
							l++;
							caminhosPossiveis[l][c] = ruasPossiveis[z];
							for(int v = c; v >= 0; v--)
							{
								//preenche as colunas anteriores da nova linha, com os valores de coluna da linha de Origem
								caminhosPossiveis[l][v] = caminhosPossiveis[linhaOrigem][v];
							}
							liberaCol = true;//libera uma nova coluna, caso uma nova bifurcação tenha sidop encontrada
						}						
					}
			}
			if(atual.getDirecao()[1] == true)
			{
				//Fim -> Inicio
				//Executar similarmente ao anterior (Inicio -> Fim)
				ruasPossiveis = verConexoes(atual.getInicio(), atual);
				//Cascatear o processo de conexoes
				//Adicionando toda nova conexao a uma nova linha e copiando as colunas anteriores
				/*
				 * [INICIO][a][b][c]
				 * 	                [d][FINAL]
				 * 	                [e][g][h][FINAL]
				 * .
				 * Ao final, preencher as linhas com colunas vazias com as colunas anteriores e considerar
				 * apenas as que tiverem [FINAL] como último elemento, ou seja:
				 *
				 * 1) [INICIO][a][b][c][d][FINAL]
				 * 2) [INICIO][a][b][c][e][g][h][FINAL]
				 */
				for (int z = 0; z < ruasPossiveis.length; z++) 
				{
					if(!compararRua(ruasPossiveis[z],l)) //verifica se a rua a ser adicionada não é preexistente na mesma linha
					{
						l++;
						caminhosPossiveis[l][c] = ruasPossiveis[z];
						for(int v = c; v >= 0; v--)
						{
							//preenche as colunas anteriores da nova linha, com os valores de coluna da linha de Origem
							caminhosPossiveis[l][v] = caminhosPossiveis[linhaOrigem][v];
						}
						liberaCol = true;//libera uma nova coluna, caso uma nova bifurcação tenha sidop encontrada
					}						
				}
			}
			if(liberaCol)
				c++;
			//mover apos inseir o outro caso de if, linhaOrigem++ e atual blabla  para depois do 2º if (atual.getDirecao()[1] == true)
			linhaOrigem++; //atualiza linha de Origem para análise dos novos casos
			/*linhaOrigem = 0
			 * [a]
			 * 		[b1]
			 * 		[b2]
			 * 
			 * linhaOrigem = 1
			 * [a]	[b1]	[c1]
			 * 				[c2]
			 */
			atual = caminhosPossiveis[linhaOrigem][caminhosPossiveis[linhaOrigem].length];	//ultimo elemento da linha de Origem
			/*
			 * Adicionar método para comparar os caminhos por tempo e distancia
			 * Encontrando o desejado
			 */
			if (caminhosPossiveis[linhaOrigem] == null)
			{
				nCaminhosFinalizados = true;
			}
			//nCaminhosFinalizados = true;
		}
		return aL;
	}
	
	//Analisa todas as ruas que fazem conexão com um determinado Waypoint
	public Rua[] verConexoes(Waypoint a, Rua b)
	{
		Rua[] r = {b};
		Rua ruaux = new Rua();
		ArrayList<Rua> auxL = new ArrayList<Rua>();
		Iterator it = ruas.iterator();
		int i = 1;
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
	public Boolean compararRua(Rua r, int linha)
	{
		for(int col = 0; col < caminhosPossiveis[0].length; col++)
			{
				if(r.equals(caminhosPossiveis[linha][col]))
				{
					return false;
				}
			}
		return true;	
	}
}
