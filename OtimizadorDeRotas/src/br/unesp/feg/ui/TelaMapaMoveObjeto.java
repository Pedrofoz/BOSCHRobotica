package br.unesp.feg.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.navigation.WaypointListener;

import br.unesp.feg.modelo.Locais;

public class TelaMapaMoveObjeto {
	private JFrame janela;
	
	private JPanel btnPanel;
	
	private JButton btnLeft;
	private JButton btnRight;
	private JButton btnUp;
	private JButton btnDown;
	
   // Name-constants for the various dimensions
   public static final int CANVAS_WIDTH = 800;
   public static final int CANVAS_HEIGHT = 800;
   public static final Color LINE_COLOR = Color.BLACK;
   public static final Color CANVAS_BACKGROUND = Color.GRAY;
 
   // The line from (x1, y1) to (x2, y2), initially position at the center
   private int x1 = 0;
   private int y1 = 0;
 
   private DrawCanvas canvas; // the custom drawing canvas (extends JPanel)
   
   private List<Locais> lLocais = TelaDefineLocais.getListaLocais();
   
   //Parametros Lego
   private WaypointListener listaLegoWayPoint;
	
	public void montaTela(){
		preparaJanela();
		preparaPaineis();
		preparaBotoes();
		mostraJanela();
	}

	private void preparaBotoes() {
		  btnLeft = new JButton("Move Left");
	      btnPanel.add(btnLeft,BorderLayout.EAST);
	      btnLeft.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            x1 -= 10;
	            canvas.repaint();
	            janela.requestFocus(); // change the focus to JFrame to receive KeyEvent
	         }
	      });
	      btnRight = new JButton("Move Right");
	      btnPanel.add(btnRight, BorderLayout.WEST);
	      btnRight.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            x1 += 10;
	            canvas.repaint();
	            janela.requestFocus(); // change the focus to JFrame to receive KeyEvent
	         }
	      });
	      btnUp = new JButton("Move UP");
	      btnPanel.add(btnUp, BorderLayout.NORTH);
	      btnUp.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            y1 -= 10;
	            canvas.repaint();
	            janela.requestFocus(); // change the focus to JFrame to receive KeyEvent
	         }
	      });
	      btnDown = new JButton("MoveDown");
	      btnPanel.add(btnDown, BorderLayout.SOUTH);
	      btnDown.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            y1 += 10;
	            canvas.repaint();
	            janela.requestFocus(); // change the focus to JFrame to receive KeyEvent
	         }
	      });
	}

	private void preparaPaineis() {
		 btnPanel = new JPanel(new FlowLayout());
		 
		 canvas = new DrawCanvas();
	     canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));	 
	     canvas.repaint(1,1,2,2);
	}

	private void preparaJanela() {
		 janela = new JFrame("Mapa");
		 janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Handle the CLOSE button		
		// "this" JFrame fires KeyEvent
		/* janela.addKeyListener(new KeyAdapter() {
	          @Override
	          public void keyPressed(KeyEvent evt) {
	             switch(evt.getKeyCode()) {
	                case KeyEvent.VK_LEFT:
	                   x1 -= 10;
	                   janela.repaint();
	                   break;
	                case KeyEvent.VK_RIGHT:
	                   x1 += 10;
	                   janela.repaint();
	                   break;
	             }
	          }
	       });*/
	}
	
	private void mostraJanela() {
		// Add both panels to this JFrame
	      Container cp = new Container();
	      cp.setLayout(new BorderLayout());
	      cp.add(canvas, BorderLayout.CENTER);
	      cp.add(btnPanel, BorderLayout.SOUTH);
	      
	      janela.add(cp);
	      
	      //janela.add(painelDeMapa, BorderLayout.CENTER);
	      
	      janela.setTitle("Move a Line");
	      janela.pack();           // pack all the components in the JFrame
	      janela.setVisible(true); // show it
	      janela.requestFocus();   // set the focus to JFrame to receive KeyEvent		
	}
	
   /**
    * DrawCanvas (inner class) is a JPanel used for custom drawing
    */
   class DrawCanvas extends JPanel {
	  ImageIcon icon = new ImageIcon("img/blocoLego.png");
	  final Image img = icon.getImage();
	   
      @Override
      public void paintComponent(Graphics g) {
         super.paintComponent(g);
         setBackground(CANVAS_BACKGROUND);
         g.setColor(LINE_COLOR);
  
         //Criando o mapa
         int offset = 100; //separação entre os Locais
         for (Locais l : lLocais) {
	         g.setColor(l.getCorLocal());       // change the drawing color
	         g.fillRect(l.getColunaLocal()*offset, l.getLinhaLocal()*offset, 50, 50);
	         // Printing texts
	         g.setColor(Color.WHITE);
	         g.setFont(new Font("Monospaced", Font.PLAIN, 12));
	         g.drawString(l.getDescLocal(), l.getColunaLocal()*offset, l.getLinhaLocal()*offset+12);
	         
	         //Para o Lego
	         listaLegoWayPoint.addWaypoint(new Waypoint(l.getColunaLocal()*offset, l.getLinhaLocal()*offset));
         }
         
         //Colocando o objeto que vai se mover
         g.drawImage(img, x1, y1, icon.getIconWidth()/2, icon.getIconHeight()/2, null);         
      }
   }
}
