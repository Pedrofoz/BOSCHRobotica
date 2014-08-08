package br.unesp.feg.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.unesp.feg.modelo.Locais;

public class TelaDefineLocais {
	
	private JFrame janela;
	private JPanel painelPrincipal;
	private JPanel painelBotoes;
	
	private JTextField textDesc;
	private CorComboBox coresBox = new CorComboBox();
	
	private int i=1;
	private int j=0;
	
	private static List<Locais> lLocais = new ArrayList<Locais>();
	
	public void montaTela(int l, int c){
		preparaJanela();
		preparaTitulo();
		preparaPainel();
		criaBotoes(l,c);
		criaFormulario();
		criaComboBox();
		mostraJanela();
	}

	private void criaComboBox() {		
		painelPrincipal.add(painelPrincipal.add(coresBox));
		
	}

	private void criaFormulario() {		
		JLabel labelLinha = new JLabel("Descrição:");
		textDesc = new  JTextField(20); 
					
		painelPrincipal.add(labelLinha);
		painelPrincipal.add(textDesc);			
	
		
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		flowLayout.preferredLayoutSize(textDesc);
		painelPrincipal.setLayout(flowLayout);
		
	}

	private void preparaJanela() {
		janela = new JFrame("Opções de Locais");
	    janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //configuração para terminar o programa Java quando a fecharem as janelas
	}
	
	private void preparaTitulo() {
		  JLabel titulo = new JLabel("Opções de Locais", SwingConstants.CENTER);
		  titulo.setFont(new Font("Verdana", Font.BOLD, 25));
		  janela.add(titulo, BorderLayout.NORTH);  
	}
	
	private void preparaPainel() {
		painelPrincipal = new JPanel();
		janela.add(painelPrincipal, BorderLayout.CENTER);
		
		painelBotoes = new JPanel();
		janela.add(painelBotoes,BorderLayout.SOUTH);
	}
	
	private void criaBotoes(final int l, final int c) {		
		JButton botaonovo = new JButton("Limpar");
		botaonovo.addActionListener(new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent e) {
				textDesc.setText("");
				coresBox.setCorSelecionada(Color.white);
				System.out.println("Apagando Local");					
			}
		});
		
		JButton botaoProx = new JButton("Proximo");
		botaoProx.addActionListener(new ActionListener() {				
			@Override
			public void actionPerformed(ActionEvent e) {
				if(j<c){			
					j=j+1;
				}else{
					if(i<=l){
						j=1;
						i=i+1;		
					}	
				}
				System.out.println("i="+i+" j="+j);
				
				
				//Pega os parametros
				Locais local = new Locais();
				local.setLinhaLocal(i);
				local.setColunaLocal(j);
				local.setDescLocal(textDesc.getText());
				local.setCorLocal(coresBox.getCorSelecionada());
				lLocais.add(local);	
				
				//Limpa os paremetros
				textDesc.setText("");
				coresBox.setCorSelecionada(Color.white);
				System.out.println("Salvando Local");
				
				
				//Se deu a quantidade de casas ele sai
				if(i>=l && j>=c){						
					System.out.println("Acabou os Locais");
					for(Locais l : lLocais)
						System.out.println(l.getDescLocal()+" "+l.getCorLocal()+" "+l.getLinhaLocal()+" "+l.getColunaLocal());
					janela.setVisible(false);
					new TelaMapaMoveObjeto().montaTela();
				}
			}
		});			
		
		painelBotoes.add(botaonovo);
		painelBotoes.add(botaoProx);
		//painelBotoes.add(toolbar);
	}
	
	private void mostraJanela() {
		//janela.pack();					//faz com que a janela se ajuste ao tamanho de seus componentes
	    janela.setSize(400, 150);		//configura o tamanho(largura x altura)
	    janela.setVisible(true);		//mostra-a
	}
	
	public static List<Locais> getListaLocais(){
		return lLocais;
	}

}
