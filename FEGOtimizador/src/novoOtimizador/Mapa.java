package novoOtimizador;

import java.util.ArrayList;
import java.lang.*;
import java.util.Iterator;

import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.Path;

public class Mapa {
	

	protected ArrayList<Rua> ruas;
	protected ArrayList<Destinos> destinos;
	protected Rua[][] caminhosPossiveis = {};
	
	
	
	//Construtores
	public Mapa() {}
	public Mapa(ArrayList<Rua> ruas, ArrayList<Destinos> destinos) {
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
	
	
	
	public void trilhaCaminho (ArrayList<Rua> aL, Destinos[] destino) {
	 
	 	// destino[0] = ponto atual
		// destino[1] = ponto final
		 
	 	Path p = new Path();
	 	p.add(destino[0].getLocalizacao());
	 	for(int i = 0; i < aL.size() - 1; i++){
			if (aL.get(i).getInicio().equals(aL.get(i+1).getInicio()) || aL.get(i).getInicio().equals(aL.get(i+1).getFim())){
				p.add(aL.get(i).getInicio());
			}
			else{
				p.add(aL.get(i).getFim());
			}
		}
		p.add(destino[1].getLocalizacao());
		
		//PATH FORMADO! AGORA DEVE-SE NAVEGAR O MESMO
		
		DifferentialPilot pilot = new DifferentialPilot(42,81,Motor.C,Motor.B,true);
		Navigator n = new  Navigator(pilot);
		n.followPath(p);
		
	 }

	//M�todo para encontrar o menor caminho
	public ArrayList<Rua> menorCaminho(Destinos[] destino, int op )
	{
		
		/*
		 * Rua rotas[][] = {};
		rotas[0][0] = ruaAtual;
		
		Integer linhaI = 0;
		Integer linhaF = 0;
		
		Rua[] ruasPossiveis;
		
		do{
			
			for(int i=linhaI; i<= linhaF; i ++){	//para percorrer apenas as ruas de bifurca��o encontradas na ultima analise
				if(rotas[i][rotas[i].length] == ruaDestino){ //caso a ultima ruas encontrada seja a rua de destino ele nao tentara encontrar conex�o
					ruasPossiveis = verConexoes(rotas[i][rotas[i].length].getFim(), rotas[i][rotas[i].length]);
					for(Rua r : ruasPossiveis){
						rotas[rotas.length+1] = ruasPossiveis; //adiciona na nova linha a linha de origem
						rotas[rotas.length+1] [rotas[i].length + 1] = r; //adiciona na ultima coluna a possivel rua
					}
				}
			}
			linhaI = linhaF + 1;
			linhaF = rotas.length;
			
		}while(linhaI<linhaF); //Caso n�o exista mais conex�o nao sera adicionado nenhuma linha nova... desta forma linhaI vai ser igual ao valor da quantidade atual de linhas +1 e a linhaF ser� igual... ou seja ele para de ver as rotas
		
		Rua rotasComDestinoFinal[][] = {};
		for(int i=0;i<rotas.length;i++){
			for(int j=0;j<rotas[i].length;j++){
				if(rotas[i][j] == ruaDestino){
					rotasComDestinoFinal[i] = rotas[i];
				}
			}
		}
		 		
		 */
//		
//		/*****************************************************************************************************************************
//		 * ACIMA ALTERA��ES COM PAT
//		 * ABAIXO  ALTERA��ES MINIMAS 
//		 * 		----FALTA COLOCAR RUA DO OBJETO DESTINO COMO ATUALIN E ATUALFIM, CANCELANDO O ARTIFICIO MATEMATICO
//		 * 
//		 * 
//		 * ****************************************************************************************************************************
//		 */
//		
//		  op = 0 ----> menor distancia
//		  op = 1 ----> menor tempo
//		  op = 2 ----> menor numero de ruas
//		 
		// destino[0] = ponto atual
		// destino[1] = ponto final
		//Variaveis auxiliares
		ArrayList<Rua> aL = new ArrayList<Rua>();
		Iterator<Rua> it = ruas.iterator();
		int a = 0;
		Rua r = new Rua();
		
		//for(Rua r1: ruas)
		//{
			//r = (Rua) it.next();
			//if (destino[0].getNomeDaRua().equals(r.getNome()))
			//{
			//	aL.add(r);
			//}
		//}
		while(it.hasNext()) //Percorrer o arrayList at� o fim
		{
			r = (Rua) it.next();
			if (destino[0].getNomeDaRua().equals(r.getNome()))
			{
				aL.add(r);
			}
		}
		it = aL.iterator(); //aL com as ruas de mesmo nome
		
		  //Com o nome da Rua, encontrar qual Rua, das ruas (trechos) com o mesmo nome, o rob� est�!
		  //Encontrar atrav�s da menor dist�ncia do ponto de destino[0] aos pontos de Inicio e Fim das ruas...
		 
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
	

/**
 * PROBLEMA NO CAO*/
		
		Rua rotas[][] = {};
		rotas[0][0] = atualIn;
		
		Integer linhaI = 0;
		Integer linhaF = 0;
		
		Rua[] ruasPossiveis;
		
		do{
			/**Length ou Length - 1 ????? **/
			 
			for(int i=linhaI; i<= linhaF; i ++){	//para percorrer apenas as ruas de bifurca��o encontradas na ultima analise
				if(rotas[i][rotas[i].length+1] == atualFim){ //caso a ultima ruas encontrada seja a rua de destino ele nao tentara encontrar conex�o
					ruasPossiveis = verConexoes(rotas[i][rotas[i].length]);
					for(Rua q : ruasPossiveis){
						rotas[rotas.length+1] = ruasPossiveis; //adiciona na nova linha a linha de origem
						rotas[rotas.length+1] [rotas[i].length+1] = q; //adiciona na ultima coluna a possivel rua
					}
				}
			}
			linhaI = linhaF + 1;
			linhaF = rotas.length;
			
		}while(linhaI<linhaF); //Caso n�o exista mais conex�o nao sera adicionado nenhuma linha nova... desta forma linhaI vai ser igual ao valor da quantidade atual de linhas +1 e a linhaF ser� igual... ou seja ele para de ver as rotas
		
		Rua rotasComDestinoFinal[][] = {};
		for(int i=0;i<rotas.length;i++){
			for(int j=0;j<rotas[i].length;j++){
				if(rotas[i][j] == atualFim){
					rotasComDestinoFinal[i] = rotas[i];
				}
			}
		}
		//*****************************************************************************************************************************************************************************************************
//		aL = new ArrayList<Rua>();
//		int l = 0;
//		int c = 0;
//		caminhosPossiveis[0][0] = atualIn;
//		c++;
//		Rua atual = atualIn;
//		int linhaOrigem = 0;
//		Rua[] ruasPossiveis = {};
//		boolean nCaminhosFinalizados = false;
//		boolean liberaCol = false;
//		while(nCaminhosFinalizados == false)
//		{
//			liberaCol = false;
//			if(atual.getDirecao()[0] == true)
//			{				
//				//Inicio -> Fim
//				ruasPossiveis = verConexoes(atual);
//						//					//Cascatear o processo de conexoes
//						//					//Adicionando toda nova conexao a uma nova linha e copiando as colunas anteriores
//						//					/
//						//					  [INICIO][a][b][c]
//						//					  	                [d][FINAL]
//						//					  	                [e][g][h][FINAL]
//						//					  .
//						//					  Ao final, preencher as linhas com colunas vazias com as colunas anteriores e considerar
//						//					  apenas as que tiverem [FINAL] como �ltimo elemento, ou seja:
//						//					 
//						//					  1) [INICIO][a][b][c][d][FINAL]
//						//					  2) [INICIO][a][b][c][e][g][h][FINAL]
//						//					 /
//					for (int z = 0; z < ruasPossiveis.length; z++) 
//					{
//						if(!compararRua(ruasPossiveis[z],l)) //verifica se a rua a ser adicionada n�o � preexistente na mesma linha
//						{
//							l++;
//							caminhosPossiveis[l][c] = ruasPossiveis[z];
//							for(int v = c; v >= 0; v--)
//							{
//								//preenche as colunas anteriores da nova linha, com os valores de coluna da linha de Origem
//								caminhosPossiveis[l][v] = caminhosPossiveis[linhaOrigem][v];
//							}
//							liberaCol = true;//libera uma nova coluna, caso uma nova bifurca��o tenha sidop encontrada
//						}						
//					}
//			}
//			
//			if(liberaCol)
//				c++;
//			
//			//mover apos inseir o outro caso de if, linhaOrigem++ e atual blabla  para depois do 2� if (atual.getDirecao()[1] == true)
//			
//			//linhaOrigem = 0			 * [a]			 * 		[b1]			 * 		[b2]			 *			 * linhaOrigem = 1			 * [a]	[b1]	[c1]			 * 				[c2]			 
//			
//			do{
//				linhaOrigem++; //atualiza linha de Origem para an�lise dos novos casos
//				atual = caminhosPossiveis[linhaOrigem][caminhosPossiveis[linhaOrigem].length-1];	//ultimo elemento da linha de Origem
//				/**
//				 * 
//				 */
//			}while(atual == atualFim); //Nesta condi��o, n�o h� necessidade de verificar as conex�es... j� chegamos � rua final
//
//			if (caminhosPossiveis[linhaOrigem][0] == null)
//			{
//				nCaminhosFinalizados = true;
//			}
//			//nCaminhosFinalizados = true;
//		}
//		Rua[][] caminhosObtidos = {};
//		l = 0;
		
		
//		for(int i = 0; i < caminhosPossiveis.length; i++)
//		{
//			for (int j = 0; j < caminhosPossiveis[i].length; j++)
//			{
//				if(caminhosPossiveis[i][j] == atualFim)
//				{
//					caminhosObtidos[l] = caminhosPossiveis[i];
//				}
//			}
//			l++;
//		}
		//*****************************************************************************************************************************************************************************************************
		/***switch(op)
		{
			case 0: aL = menorDist(rotasComDestinoFinal, destino[0], destino[1]);break;
			case 1: break;
			case 2: break;
			default: break;
		}***/
		return aL;
	}
	public ArrayList<Rua> menorDist(Rua[][] caminhos, Destinos partida, Destinos chegada)
	{
		ArrayList<Rua> al = new ArrayList<Rua>();
		int menorDist = 0;
//		/*
//		 * r = caminhos[0][0];
//		 * Distancia da partida para a proxima rua da conexao
//		 * if (r.getInicio.equals(caminhos[0][1].getInicio()) || (r.getFim.equals(caminhos[0][1].getInicio()))
//		 * {
//		 * 		dist =  Math.sqrt((Math.pow((partida.getLocalizacao().getX()-r.getInicio().getX()),2.0))+(Math.pow((partida.getLocalizacao().getY()-r.getInicio().getY()),2.0)))		
//		 * }
//		 * else
//		 * {
//		 * 		dist = Math.sqrt((Math.pow((partida.getLocalizacao().getX()-r.getFim().getX()),2.0))+(Math.pow((partida.getLocalizacao().getY()-r.getFim().getY()),2.0)));  
//		 * }
//		 * 
//		 * Distancia da chegada para a penultima rua da conexao
//		 * r = caminhos[0][caminhos[0].length];
//		 * if (r.getInicio.equals(caminhos[0][caminhos[0].length-1].getInicio()) || (r.getFim.equals(caminhos[0][1].getInicio()))
//		 * {
//		 * 		dist =  Math.sqrt((Math.pow((chegada.getLocalizacao().getX()-r.getInicio().getX()),2.0))+(Math.pow((chegada.getLocalizacao().getY()-r.getInicio().getY()),2.0)))		
//		 * }
//		 * else
//		 * {
//		 * 		dist = Math.sqrt((Math.pow((chegada.getLocalizacao().getX()-r.getFim().getX()),2.0))+(Math.pow((chegada.getLocalizacao().getY()-r.getFim().getY()),2.0)));  
//		 * }
//		 * 
//		 * ACRESCENTADO A DIST, AS DISTANCIAS ENTRE PARTIDA E CHEGADA �S RUAS DE CONEXAO
//		 */
		
		double dist = 0;
			for (int j = 1; j < caminhos[0].length - 1; j++)
			{
				dist = caminhos[0][j].getDistancia() + dist;
			}
			int i = 0;
		 Rua r = caminhos[i][0];
		  //Distancia da partida para a proxima rua da conexao
		  if (r.getInicio().equals(caminhos[i][1].getInicio()) || (r.getFim().equals(caminhos[i][1].getInicio())))
		  {
		  		dist =  Math.sqrt((Math.pow((partida.getLocalizacao().getX()-r.getInicio().getX()),2.0))+(Math.pow((partida.getLocalizacao().getY()-r.getInicio().getY()),2.0)));		
		  }
		  else
		  {
		  		dist = Math.sqrt((Math.pow((partida.getLocalizacao().getX()-r.getFim().getX()),2.0))+(Math.pow((partida.getLocalizacao().getY()-r.getFim().getY()),2.0)));  
		  }
		  
		  //Distancia da chegada para a penultima rua da conexao
		  r = caminhos[i][caminhos[i].length];
		  if (r.getInicio().equals(caminhos[i][caminhos[i].length-1].getInicio()) || (r.getFim().equals(caminhos[i][caminhos[i].length-1].getInicio())))
		  {
		  		dist =  Math.sqrt((Math.pow((chegada.getLocalizacao().getX()-r.getInicio().getX()),2.0))+(Math.pow((chegada.getLocalizacao().getY()-r.getInicio().getY()),2.0)));		
		  }
		  else
		  {
		  		dist = Math.sqrt((Math.pow((chegada.getLocalizacao().getX()-r.getFim().getX()),2.0))+(Math.pow((chegada.getLocalizacao().getY()-r.getFim().getY()),2.0)));  
		  }
		  for (int j = 1; j < caminhos[0].length - 1; j++)
			{
				dist = caminhos[0][j].getDistancia() + dist;
			}
			
			//dist = distancia da primeira linha!
			 
			 
		  i++;
		  double distTemp = 0;
		  do{
		  
		  r = caminhos[i][0];
		  //Distancia da partida para a proxima rua da conexao
		  if (r.getInicio().equals(caminhos[i][1].getInicio()) || (r.getFim().equals(caminhos[i][1].getInicio())))
		  {
		  		dist =  Math.sqrt((Math.pow((partida.getLocalizacao().getX()-r.getInicio().getX()),2.0))+(Math.pow((partida.getLocalizacao().getY()-r.getInicio().getY()),2.0)));		
		  }
		  else
		  {
		  		dist = Math.sqrt((Math.pow((partida.getLocalizacao().getX()-r.getFim().getX()),2.0))+(Math.pow((partida.getLocalizacao().getY()-r.getFim().getY()),2.0)));  
		  }
		  
		  //Distancia da chegada para a penultima rua da conexao
		  r = caminhos[i][caminhos[i].length];
		  if (r.getInicio().equals(caminhos[i][caminhos[i].length-1].getInicio()) || (r.getFim().equals(caminhos[i][caminhos[i].length-1].getInicio())))
		  {
		  		dist =  Math.sqrt((Math.pow((chegada.getLocalizacao().getX()-r.getInicio().getX()),2.0))+(Math.pow((chegada.getLocalizacao().getY()-r.getInicio().getY()),2.0)));		
		  }
		  else
		  {
		  		dist = Math.sqrt((Math.pow((chegada.getLocalizacao().getX()-r.getFim().getX()),2.0))+(Math.pow((chegada.getLocalizacao().getY()-r.getFim().getY()),2.0)));  
		  }
		  for (int j = 1; j < caminhos[i].length - 1; j++)
			{
				distTemp = caminhos[i][j].getDistancia() + distTemp;
			}
			if (distTemp < dist)
			{
				dist = distTemp;
				menorDist = i;
			}
			i++;
		  }while(i < caminhos.length);
		  
		  //UMA VEZ ENCONTRADA A LINHA COM MENOR DISTANCIA, ENTAO ADICIONA-SE ELA AO ARRAYLIST aL
		   
		 for (int s = 0; s < caminhos[menorDist].length; s++)
		 {
		 		al.add(caminhos[menorDist][s]);
		 }
		

		
		return al;
		
	}
	
	//Analisa todas as ruas que fazem conex�o com um determinado Waypoint
	public Rua[] verConexoes(Rua a)
	{
		Rua[] r = {a};
		Rua ruaux = new Rua();
		ArrayList<Rua> auxL = new ArrayList<Rua>();
		Iterator<Rua> it = ruas.iterator();
		int i = 1;
		while(it.hasNext())
		{
			ruaux = (Rua) it.next();
			//Viabiliza a conexao por sentidos permitidos
			if ((ruaux.getInicio().equals(a.getInicio()) && ruaux.getDirecao()[0] == true) || 
					(ruaux.getFim().equals(a.getInicio()) && ruaux.getDirecao()[1] == true) ||
					(ruaux.getInicio().equals(a.getFim()) && ruaux.getDirecao()[0] == true) || 
					(ruaux.getFim().equals(a.getFim()) && ruaux.getDirecao()[1] == true))			
			{
				r[i] = ruaux;
				i++;
			}
		}
		return r;
	}
	public Boolean compararRua(Rua r, int linha){
		for(int col = 0; col < caminhosPossiveis[0].length; col++){
				if(r.equals(caminhosPossiveis[linha][col])){
					return false;
				}
			}
		return true;	
	}

}