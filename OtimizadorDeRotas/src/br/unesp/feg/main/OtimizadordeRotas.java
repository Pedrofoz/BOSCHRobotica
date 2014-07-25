package br.unesp.feg.main;

import java.util.ArrayList;
import java.util.List;

import br.unesp.feg.modelo.Locais;
import br.unesp.feg.ui.*;

public class OtimizadordeRotas {

	List<Locais> lLocais = new ArrayList<Locais>();
	int linhaLocais;
	int colunaLocais;
	
	public static void main(String[] args) {
		new TelaDeOpcoes().montaTela();
		//new EscolhedorDeXML().escolhe();
		//new TelaDeOpcoes();
	}

}
