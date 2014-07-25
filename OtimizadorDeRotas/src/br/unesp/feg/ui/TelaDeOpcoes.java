package br.unesp.feg.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class TelaDeOpcoes {
	
	private JFrame janela;
	private JPanel painelPrincipal;
	private JPanel painelBotoes;
	
	private JFormattedTextField textLinha;
	private JFormattedTextField textCol;
	
		 
	public void montaTela() {
	   preparaJanela();			   
	   preparaTitulo();
	   preparaPainel();
	   criaToolbar();
	   adicionaTexto();
	   mostraJanela();
	}


		private void adicionaTexto() {
			MaskFormatter fmt = null;  
			try{  
				fmt = new MaskFormatter("##########");  
			}catch(ParseException e){} 
			
			
			JLabel labelLinha = new JLabel("Quantidade de casa por linha:");
			textLinha = new JFormattedTextField();
			fmt.install(textLinha);
			JLabel labelCol = new JLabel("Quantidade de casa por coluna:");
			textCol = new JFormattedTextField();
			fmt.install(textCol);
						
			painelPrincipal.add(labelLinha);
			painelPrincipal.add(textLinha);			
			painelPrincipal.add(labelCol);
			painelPrincipal.add(textCol);	
			
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			flowLayout.preferredLayoutSize(textLinha);
			painelPrincipal.setLayout(flowLayout);
		}

		private void criaToolbar() {
			JToolBar toolbar = new JToolBar();
			
			JButton botaosair = new JButton("Sair");
			botaosair.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
					
				}
			});
			
			JButton botaonovo = new JButton("Limpar");
			botaonovo.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					textLinha.setText("");
					textCol.setText("");
					System.out.println("Apaga");					
				}
			});
			
			JButton botaoSalvar = new JButton("Salvar");
			botaoSalvar.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					textLinha.getText();
					textCol.getText();
					System.out.println("Salvando e Gerando Mapa");					
				}
			});			
			
			painelBotoes.add(botaonovo);
			painelBotoes.add(botaoSalvar);
			painelBotoes.add(botaosair);
			//painelBotoes.add(toolbar);
		}

		private void preparaJanela() {
			janela = new JFrame("Tela de Opções");
		    janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //configuração para terminar o programa Java quando a fecharem as janelas
		}   
		   
		private void preparaPainel() {
			painelPrincipal = new JPanel();
			janela.add(painelPrincipal, BorderLayout.CENTER);
			
			painelBotoes = new JPanel();
			janela.add(painelBotoes,BorderLayout.SOUTH);
		}
		
		private void preparaTitulo() {
			  JLabel titulo = new JLabel("Opções de Mapa", SwingConstants.CENTER);
			  titulo.setFont(new Font("Verdana", Font.BOLD, 25));
			  janela.add(titulo, BorderLayout.NORTH);  
			}
		
		
		private void preparaBotaoSair() {
			JButton botaosair = new JButton("Sair");
			botaosair.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
					
				}
			});
			painelPrincipal.add(botaosair);
			
		}		
		
		private void mostraJanela() {
			//janela.pack();					//faz com que a janela se ajuste ao tamanho de seus componentes
		    janela.setSize(400, 300);		//configura o tamanho
		    janela.setVisible(true);		//mostra-a
		}

}
