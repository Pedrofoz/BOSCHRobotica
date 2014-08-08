package br.unesp.feg.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.unesp.feg.modelo.Locais;

public class TelaMapa {
	
	private JFrame janela;
	private JPanel painelDeMapa;
	private JPanel painelDeSetas;
	private JPanel painelDeDados;
	private JPanel painelBotões;
	private JLabel legoLabel;
	
	private List<Locais> lLocais = TelaDefineLocais.getListaLocais();

	public void montaTela(int l, int c){
	   preparaJanela();		   
	   preparaTitulo();
	   preparaPainel(l,c);
	   preparaBlocoLego();
	   criaMapa();
	   criaSetas();
	   criaMostraDados();
	   criaBotões();
	   mostraJanela();
		
	}

	private void preparaBlocoLego() {
		final ImageIcon icon = new ImageIcon("img/blocoLego.png");
		legoLabel = new JLabel(icon);
        legoLabel.setBounds(15, 225,icon.getIconWidth(), icon.getIconHeight());
        painelDeMapa.add(legoLabel);
		
	}

	private void preparaJanela() {
		janela = new JFrame("Mapa");
	    janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //configuração para terminar o programa Java quando a fecharem as janelas
		
	}

	private void preparaTitulo() {
		// TODO Auto-generated method stub
		
	}

	private void preparaPainel(int l, int c) {
		painelDeMapa = new JPanel();
		painelDeMapa.setPreferredSize(new Dimension(250, 250));
		painelDeMapa.setBorder(BorderFactory.createTitledBorder("Mapa"));
		painelDeMapa.setLayout(new GridBagLayout());
		janela.add(painelDeMapa, BorderLayout.CENTER);
	}

	private void criaMapa() {       
        GridBagConstraints c = new GridBagConstraints();
        int offset=20; //espaçamento entre os locais
        for (Locais l : lLocais) {
            JLabel label = createColoredLabel(l.getDescLocal(), l.getCorLocal(), new Point(l.getLinhaLocal(), l.getColunaLocal()));
            c.gridx=l.getLinhaLocal();
            c.gridy=l.getColunaLocal();
            c.insets=new Insets(offset,offset,5,5); //espaçamento
            painelDeMapa.add(label,c);
        }		
	}

	private void criaSetas() {
		// TODO Auto-generated method stub
		
	}

	private void criaMostraDados() {
		// TODO Auto-generated method stub
		
	}

	private void criaBotões() {
		// TODO Auto-generated method stub
		
	}

	private void mostraJanela() {
	    janela.setSize(500, 500);		//configura o tamanho
	    janela.setVisible(true);		//mostra-a	(largura x altura)	
	}
	
	

    //Create and set up a colored label.
    private JLabel createColoredLabel(String text,Color color, Point p) {
        JLabel label = new JLabel(text);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(true);
        label.setBackground(color);
        label.setForeground(Color.black);
        label.setBorder(BorderFactory.createLineBorder(Color.black));
        label.setPreferredSize(new Dimension(50, 50));
        label.setBounds(p.x+50,p.y+50,0,0);
        label.setLocation(p.x+50,p.y+50);
        return label;
    }
    
}
