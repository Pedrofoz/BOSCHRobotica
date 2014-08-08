package br.unesp.feg.ui;


import java.awt.Color;
import java.awt.Point;
import java.lang.reflect.Array;
import java.util.ArrayList;

import br.unesp.feg.modelo.*;

public class TeladeOpcoes {
	ArrayList<Destinos> destinos = new ArrayList<Destinos>();
	
	public void montaTela(){
		criaMapa();
		escolheDestino();
		calculaRota();
		
	}


	private void calculaRota() {
		// TODO Auto-generated method stub
		
	}


	private void escolheDestino() {
		// TODO Auto-generated method stub
		
	}


	private void criaMapa() {
	    Color[] cores = new Color[] {  
	    	        Color.white,  
	    	        Color.lightGray,  
	    	        Color.gray,  
	    	        Color.darkGray,  
	    	        Color.black,  
	    	        Color.red,  
	    	        Color.pink,  
	    	        Color.orange,  
	    	        Color.yellow,  
	    	        Color.green,  
	    	        Color.magenta,  
	    	        Color.cyan,  
	    	        Color.blue,  
	    	        new Color(0, 0, 0)  
	    };
		
		
		int id=1;
		for(int i = 0; i<=3; i++){
			for(int j=0; j<=3;j++){
				Destinos destino = new Destinos();
				destino.setId(id);
				destino.setDescricao("Local "+id);
				destino.setCor(cores[id]);
				destino.setLocalizacao(new Point(i,j));
				
				destinos.add(destino);
				id++;
			}
			
		}
		
	}


	

}
