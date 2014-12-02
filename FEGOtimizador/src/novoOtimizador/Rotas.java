package novoOtimizador;

import java.util.ArrayList;

import lejos.robotics.Color;
import lejos.robotics.navigation.Waypoint;

public class Rotas{
	
	public static void main(String[] args) throws Exception {
	
	/*
	 * boolean[0] -> sentido ir (inicio - fim)
	 * boolean[1] -> sentido volta (fim - inicio)
	 */
	//
	boolean[] iv = {true, true};
	boolean[] v = {false, true};
	boolean[] i = {true, false};
	boolean[] tr = {true, true};
	boolean[] tcd = {false, true};
	boolean[] tce = {false, false};
	
	//Definição das ruas
	
	Rua r1 = new Rua("Parah",new Waypoint(0,1) , new Waypoint(1,1) , 80 , iv, tr ); 
	Rua r2 = new Rua("Rossi",new Waypoint(1,1) , new Waypoint(2,1) , 80 , iv, tr  );
	Rua r3 = new Rua("Tio Leo",new Waypoint(1,2) , new Waypoint(2,2) , 80 , v, tr );
	Rua r4 = new Rua("Lotus",new Waypoint(0,2) , new Waypoint(1,2) , 80 , v, tr  );
	Rua r5 = new Rua("Oscar",new Waypoint(0,1) , new Waypoint(0,2) , 80 , iv, tr  );
	Rua r6 = new Rua("Paloma",new Waypoint(0,0) , new Waypoint(0,1) , 80 , iv, tr );
	Rua r7 = new Rua("Teofilo",new Waypoint(0,0) , new Waypoint(1,0) , 80 , i, tr );
	Rua r8 = new Rua("Durval",new Waypoint(1,0) , new Waypoint(2,0) , 80 , i, tr );
	Rua r9 = new Rua("Samuel",new Waypoint(1,0) , new Waypoint(1,1) , 80 , v, tr );
	Rua r10 = new Rua("Pooh", new Waypoint(2,0) , new Waypoint(2,1) , 80 , iv, tr);
	Rua r11 = new Rua("Rubao", new Waypoint(2,1) , new Waypoint(2,2) , 80 , iv, tr);
	Rua r12 = new Rua("Agnelo", new Waypoint(1,1) , new Waypoint(1,2) , 80 , i, tr);
	//public Rua(String nome, Waypoint inicio, Waypoint fim, float velocidade, boolean[] direcao, boolean[] tipo)
	
	Rua[] r = {r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12};
	ArrayList<Rua> ruas = new ArrayList<Rua>(r); //PEDRO DIZ 25/11: CONTRUTOR RISCADO... ERRO: ArrayList<Rua>(r) is deprecated (=OBSTOLETO)
	
	
	/******************************PEDRO****************************************/
//	ArrayList<Rua> al2 = new ArrayList<Rua>();
//	al2.add(r1);
//	al2.add(r2);
//	al2.add(r3);
//	al2.add(r4);
//	al2.add(r5);
//	al2.add(r6);
//	al2.add(r7);
//	al2.add(r8);
//	al2.add(r9);
//	al2.add(r10);
//	al2.add(r11);
//	al2.add(r12);
	/********************************~~~***************************************/
	
	//Definição dos destinos
	Destinos d1 = new Destinos(1,"Canavial de escravos","Teofilo", new Color(0,0,0),new Waypoint(0.3,0));
	Destinos d2 = new Destinos(2,"Churrascao da FEG","Durval", new Color(0,0,0),new Waypoint(1.6,0));
	Destinos d3 = new Destinos(3,"Pescaria","Samuel", new Color(0,0,0),new Waypoint(1,0.2));
	Destinos d4 = new Destinos(4,"El Salvador","Oscar", new Color(0,0,0),new Waypoint(0,1.5));
	Destinos d5 = new Destinos(5,"Petrobras","Rubao", new Color(0,0,0),new Waypoint(2,1.7));
	Destinos d6 = new Destinos(6,"Chamada 7 e 28","Agnelo", new Color(0,0,0),new Waypoint(1,1.2));
	Destinos d7 = new Destinos(7,"Inferno", "Lotus",new Color(0,0,0), new Waypoint(0.2,2));
	//public Destinos(id, descricao, nomeDaRua, cor, localizacao)
	
	Destinos d[] = {d1,d2,d3,d4,d5,d6,d7};
	ArrayList<Destinos> destinos = new ArrayList<Destinos>(d); //PEDRO DIZ 25/11: CONTRUTOR RISCADO... ERRO: ArrayList<Rua>(r) is deprecated (=OBSTOLETO)
	
	
	/******************************PEDRO****************************************/
//	ArrayList<Destinos> al3 = new ArrayList<Destinos>();
//	al3.add(d1);
//	al3.add(d2);
//	al3.add(d3);
//	al3.add(d4);
//	al3.add(d5);
//	al3.add(d6);
//	al3.add(d7);
	/********************************~~~***************************************/
	
	//Definicao do Mapa
	Mapa m1 = new Mapa(ruas, destinos);
	//public Mapa(ArrayList<Rua> ruas, ArrayList<Destinos> destinos) 
	
	/******************************PEDRO****************************************/
//	Mapa m1 = new Mapa(al2,al3);
	/********************************~~~***************************************/
	
	/**
	  //Adicionar destino e ponto de saida por interface com usuario 
	   
	**/
	Destinos teste[] = {d6,d7}; //Destinos escolhidos
	m1.menorCaminho(teste , 0);
	//m1.trilhaCaminho(m1.menorCaminho(teste, 0),teste); //Execucao
			}
}
