package br.unesp.feg.ui;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import javax.swing.*;

public class EscolhedorDeXML {
	
	public void escolhe() {
		   try {
		     JFileChooser chooser = new JFileChooser();
		     int retorno = chooser.showOpenDialog(null);
		 
		     if (retorno == JFileChooser.APPROVE_OPTION) {
		       FileReader reader = new FileReader(chooser.getSelectedFile());
		 
		       String mensagem = "Primeiro negócio: ";
		       JOptionPane.showMessageDialog(null, mensagem);
		     }
		   } catch (FileNotFoundException e) {
		     e.printStackTrace();
		   }
		 }

}
