package br.unesp.feg.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TelaDeOpcoes {
	
	private JFrame janela;
	private JPanel painelPrincipal;
	
	  public static void main(String[] args) {
		     new TelaDeOpcoes().montaTela();
		   }
		 
		   private void montaTela() {
			   preparaJanela();
			   preparaPainelPrincipal();
			   preparaTitulo();
			   preparaBotaoCarregar();
			   preparaBotaoSair();
			   criaToolbar();
			   mostraJanela();
		   }


		private void criaToolbar() {
			JToolBar toolbar = new JToolBar();
			
			toolbar.add(new JButton("Abrir"));
			toolbar.add(new JButton("Novo"));
			toolbar.add(new JButton("Salvar"));
			toolbar.add(new JButton("Fechar"));

			painelPrincipal.add(toolbar);
			
			
		}

		private void preparaJanela() {
			janela = new JFrame("Argentum");
		    janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//configuração para terminar o programa Java quando a fecharem
		}   
		   
		private void preparaPainelPrincipal() {
			painelPrincipal = new JPanel();
			janela.add(painelPrincipal);			
		}
		
		private void preparaTitulo() {
			  JLabel titulo = new JLabel("Lista de Negócios", SwingConstants.CENTER);
			  titulo.setFont(new Font("Verdana", Font.BOLD, 25));
			  painelPrincipal.add(titulo);  
			}

		private void preparaBotaoCarregar() {
			JButton botaoCarregar = new JButton("Carregar XML");
			ActionListener carregarXML = new ActionListener() {			
				@Override
				public void actionPerformed(ActionEvent e) {
					new EscolhedorDeXML().escolhe();
					
				}
			};
			botaoCarregar.addActionListener(carregarXML);
			painelPrincipal.add(botaoCarregar);
			
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
			janela.pack();					//organizando os componentes
		    janela.setSize(540, 540);		//configura o tamanho
		    janela.setVisible(true);		//mostra-a
		}

}
