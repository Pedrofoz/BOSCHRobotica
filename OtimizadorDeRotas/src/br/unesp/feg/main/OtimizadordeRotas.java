package br.unesp.feg.main;

import java.util.ArrayList;
import java.util.List;

import br.unesp.feg.modelo.Locais;
import br.unesp.feg.ui.*;

public class OtimizadordeRotas {

	List<Locais> lLocais = new ArrayList<Locais>();
	//static int linhaLocais;
	//static int colunaLocais;
	
	public static void main(String[] args) {
		TelaDeOpcoes tela1 = new TelaDeOpcoes();
		tela1.montaTela();
		//linhaLocais = tela1.getLinha();
		//colunaLocais = tela1.getColuna();
		
		//new TelaMapaMoveObjeto().montaTela();
	}

}
