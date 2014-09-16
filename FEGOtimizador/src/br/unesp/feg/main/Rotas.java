package br.unesp.feg.main;
import java.io.IOException;
import java.util.ArrayList;

import lejos.nxt.Button;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DestinationUnreachableException;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.util.PilotProps;
import lejos.robotics.navigation.Waypoint;

import br.unesp.feg.ui.TeladeOpcoes;



public class Rotas {
	
	public static void main(String[] args) throws IOException, InterruptedException, DestinationUnreachableException {
		/*
		 * Configurações do NXT
		 */
		
		PilotProps pp = new PilotProps();
		pp.loadPersistentValues();
		float wheelDiameter = Float.parseFloat(pp.getProperty(PilotProps.KEY_WHEELDIAMETER, "4.32"));
		float trackWidth = Float.parseFloat(pp.getProperty(PilotProps.KEY_TRACKWIDTH, "16.35"));
		RegulatedMotor leftMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_LEFTMOTOR, "B"));
		RegulatedMotor rightMotor = PilotProps.getMotor(pp.getProperty(PilotProps.KEY_RIGHTMOTOR, "C"));
		boolean reverse = Boolean.parseBoolean(pp.getProperty(PilotProps.KEY_REVERSE,"true"));

		System.out.println("Any button to start");
		Button.waitForAnyPress();
		
		DifferentialPilot p = new DifferentialPilot(wheelDiameter, trackWidth, leftMotor, rightMotor, reverse);
		Navigator nav = new Navigator(p);
		
		
		TeladeOpcoes tela1 = new TeladeOpcoes();
		tela1.montaTela();
		ArrayList<Waypoint> pontosSelect = tela1.tracaRota();
		nav.addWaypoint((Waypoint) pontosSelect.subList(0,pontosSelect.size()));
		nav.followPath();
		//oiiiiiiieeee
		//fuuuuunffaaa *-*
		
		//a vida não é justa
		//Serah q agora vai ??
	}

}
