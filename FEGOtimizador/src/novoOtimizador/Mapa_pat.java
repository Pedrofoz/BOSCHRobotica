package novoOtimizador;

import java.util.ArrayList;

import lejos.geom.Line;
import lejos.robotics.navigation.Waypoint;

public class Mapa_pat {
	
	protected ArrayList<Rua> ruas;
	protected ArrayList<Destinos> destinos;
	protected Rua[][] caminhosPossiveis = {};
	
	//Construtores
	public Mapa_pat() 
	{
		//
	}
	public Mapa_pat(ArrayList<Rua> ruas, ArrayList<Destinos> destinos) 
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
	
	//separado todas as rotas da menor rota pq assim o usuario pode escolher
	public Rua[][] tracarRota(Waypoint pontoAtualRobo, Destinos destino){
		//1� Descobrir a rua atual do rob�
		//2� Descobrir a rua destino do robo
		//3� Criar matriz preenchida dependendo das ruasPossiveis
		//4� Filtras a matriz com apenas as rotas que chegaram no destino final
		//5� Retornar todas as rotas possiveis para tratamento(para retornar a menor, excluir rotas que fazem o msmo percurso)
		
		Rua ruaAtual = null;
		Rua ruaDestino = null;
		
		for(Rua rua : ruas){
			Line linha = new Line(rua.getInicio().x,rua.getInicio().y,rua.getFim().x,rua.getFim().y);
			if(linha.contains(pontoAtualRobo)){
				ruaAtual = rua;
			}
			if(linha.contains(destino.getLocalizacao())){
				ruaDestino = rua;
			}
		}
		
		
		
		Rua rotas[][] = {};
		rotas[0][0] = ruaAtual;
		
		Integer linhaI = 0;
		Integer linhaF = 0;
		
		Rua[] ruasPossiveis;
		
		do{
			
			for(int i=linhaI; i<= linhaF; i ++){	//para percorrer apenas as ruas de bicurca��o encontradas na ultima analise
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
			
		}while(linhaI<linhaF); //Caso n�o existe mais conex�o nao sera adicionado nenhuma linha nova... desta forma linhaI vai ser igual ao valor da quantidade atual de linhas +1 e a linhaF ser� igual... ou seja ele para de ver as rotas
		
		Rua rotasComDestinoFinal[][] = {};
		for(int i=0;i<rotas.length;i++){
			for(int j=0;j<rotas[i].length;j++){
				if(rotas[i][j] == ruaDestino){
					rotasComDestinoFinal[i] = rotas[i];
				}
			}
		}
		
		
		return rotasComDestinoFinal; //Caso n�o exista rota retornar� {}
				
		
	}
	
	
	
	public Rua[] verConexoes(Waypoint a, Rua b)
	{
		Rua[] r = {b};
		int i = 1;
		for(Rua ruaux : ruas)
		{
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
