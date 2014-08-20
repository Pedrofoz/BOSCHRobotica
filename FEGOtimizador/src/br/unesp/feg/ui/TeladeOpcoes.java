package br.unesp.feg.ui;


import lejos.robotics.Color;
import lejos.robotics.mapping.LineMap;
import lejos.robotics.mapping.RangeMap;
import lejos.robotics.navigation.DestinationUnreachableException;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.ShortestPathFinder;

import lejos.geom.Line;
import lejos.geom.Rectangle;
import java.util.ArrayList;
import java.util.Collection;

import javax.microedition.lcdui.Graphics;

import lejos.nxt.*;

import br.unesp.feg.modelo.*;

public class TeladeOpcoes {
	private ArrayList<Destinos> destinos = new ArrayList<Destinos>();
	private Integer select=0;
	private Waypoint pontos[];
	private int p=1;
	boolean start=true;
	
	
	//variaveis mapa
	private RangeMap map;
	private Rectangle bound = new Rectangle(0,0,300,300); 
	Collection<Waypoint> route = null;
	
	public void montaTela() throws InterruptedException, DestinationUnreachableException{
		criaMapa();
		preparaTela();
		preparaBotoes();		
	}


	public ArrayList<Waypoint> tracaRota() throws DestinationUnreachableException{
		ShortestPathFinder pf = new ShortestPathFinder((LineMap)map);
		route = pf.findRoute(destinos.get(1).getLocalizacao().getPose(), destinos.get(2).getLocalizacao());
		ArrayList<Waypoint> rt = (ArrayList<Waypoint>) route;
		return rt;
	}

	private void preparaTela() throws InterruptedException {
		Graphics g = new Graphics();
	    g.drawLine(5,5,60,60);
	    g.drawRect(62, 10, 25, 35);
	    
	    LCD.drawString("Otimizador de Rotas", 31, 0);
	    Button.waitForAnyPress();
	    
	    LCD.clear();
	    LCD.drawString("SELEC O DESTINO "+p, 0, 0);
		LCD.drawString(destinos.get(select).getDescricao(), 0, 5);
		
	}



	private void preparaBotoes() {
		Button.ENTER.addButtonListener(new ButtonListener() { // Verificar se enter esta pressionado
			public void buttonReleased(Button b) {}
			public void buttonPressed(Button b) {
				LCD.clear();
				if(p<2){
					pontos[p] = destinos.get(select).getLocalizacao();
					p++;
					destinos.remove(select);
				}else{
					start=false;
				}
				
			}
		});

		Button.LEFT.addButtonListener(new ButtonListener() { // Verificar se enter esta pressionado
			public void buttonReleased(Button c) {}
			public void buttonPressed(Button c) {
				LCD.clear();
				select--;
				LCD.drawString("SELEC O DESTINO "+p, 0, 0);
				LCD.drawString(destinos.get(select).getDescricao(), 0, 5);
				if (select < 0)
					select = destinos.size();
			}
		});
		
		Button.RIGHT.addButtonListener(new ButtonListener() { // Verificar se enter esta pressionado
			public void buttonReleased(Button d) {}
			public void buttonPressed(Button d) {
				LCD.clear();
				select++;
				LCD.drawString("SELEC O DESTINO "+p, 0, 0);
				LCD.drawString(destinos.get(select).getDescricao(), 0, 5);
				if (select > destinos.size())
					select = 0;
			}
		});

		
	}


	private void criaMapa() throws DestinationUnreachableException {
	    Color[] cores = new Color[] { 	    		  
	    	        new Color(0, 0, 0),			//Color(int red, int green, int blue, int color)
	    	        new Color(255,255,255),
	    	        new Color(255,0,0),		//RED
	    	        new Color(0,255,0),		//BLUE
	    	        new Color(0,0,255),		//BLUE
	    	        new Color(0,0,0,12),	//CYAN
	    	        new Color(0,0,0,11),	//DARK GRAY
	    	        new Color(0,0,0,9),		//GRAY
	    	        new Color(0,0,0,4),		//Margenta
	    	        new Color(0,0,0,5),		//Orange
	    	        new Color(0,0,0,8),		//Pink
	    	        new Color(0,0,0,3)		//Yellow
	    };
		
	    ArrayList<Line> lines = new ArrayList<Line>();
		
		int id=1;
		for(int i = 0; i<=3; i++){
			for(int j=0; j<=3;j++){
				Destinos destino = new Destinos();
				destino.setId(id);
				destino.setDescricao("Local "+id);
				destino.setCor(cores[id]);
				destino.setLocalizacao(new Waypoint(i,j));
				
				lines.add(new Line(i,0,i,j));
				lines.add(new Line(0,j,i,j));
				id++;
			}			
		}
		
		/*Line[] lines = {new Line(0,0,0,300),
						new Line(0,0,300,0),
						new Line(0,300,300,300),
						new Line(300,0,300,300),
		};*/
				
		bound = new Rectangle(0,0,300,300); 
		map = new LineMap((Line[]) lines.toArray(), bound);
				
		
	}


}
