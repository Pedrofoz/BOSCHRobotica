package br.unesp.feg.ui;

import java.awt.*;  
import javax.swing.*;  
      
    /** 
     * Essa classe implementa um combo box de cores. 
     */  
    public class CorComboBox extends JComboBox implements ListCellRenderer {  
      
      public static final String CANCELADO = "cancelado";  
      public static final String OK = "ok";  
      
      /* Array de cor */  
      private Color[] cores = new Color[] {  
        Color.white,  
        Color.lightGray,  
        Color.gray,  
        Color.darkGray,  
        Color.black,  
        Color.red,  
        Color.pink,  
        Color.orange,  
        Color.yellow,  
        Color.green,  
        Color.magenta,  
        Color.cyan,  
        Color.blue,  
        new Color(0, 0, 0)  
      };  
      
      /* Array de nome da cor */  
      private String[] nomes = new String[] {  
        "Branca",  
        "Cinza claro",  
        "Cinza",  
        "Cinza escuro",  
        "Preta",  
        "Vermelha",  
        "Rosa",  
        "Laranja",  
        "Amarela",  
        "Verde",  
        "Magenta",  
        "Ciano",  
        "Azul",  
        "Personalizada"  
      };  
      
      /** 
       * Construtora da classe. 
       */  
      public CorComboBox() {  
        for (int i = 0; i < cores.length; i++)  
          addItem(new Integer(i));  
        setRenderer(this);  
        setActionCommand(OK);  
      }  
      
      /** 
       * Retorna o componente que será rederizado. 
       * @param list JList 
       * @param value Object 
       * @param index int 
       * @param isSelected boolean 
       * @param cellHasFocus boolean 
       * @return Component 
       */  
      public Component getListCellRendererComponent(JList list, Object value,  
                                                    int index, boolean isSelected,  
                                                    boolean cellHasFocus) {  
        ComponenteCor tmp = new ComponenteCor(list.getBackground(), "");  
        if (value != null) {  
          int indice = ( (Integer) value).intValue();  
          tmp = new ComponenteCor(cores[indice], nomes[indice]);  
          tmp.setPreferredSize(new Dimension(10, 15));  
          if (isSelected) {  
            tmp.setBackground(list.getSelectionBackground());  
            tmp.setForeground(list.getSelectionForeground());  
          }  
          else {  
            tmp.setBackground(list.getBackground());  
            tmp.setForeground(list.getForeground());  
          }  
        }  
        return tmp;  
      }  
      
      /** 
       * Especifica a cor personalizada. 
       * @param cor Color 
       */  
      public void setCorPersonalizada(Color cor) {  
        cores[cores.length-1] = cor;  
      }  
      
      /** 
       * Pega a cor selecionada pelo usuário. 
       * @return Color 
       */  
      public Color getCorSelecionada() {  
        int i = getSelectedIndex();  
        return i > -1 ? cores[i] : null;  
      }  
      
      /** 
       * Seleciona uma cor da combo box. 
       * @param cor Color 
       */  
      public void setCorSelecionada(Color cor) {  
        boolean ok = false;  
        setActionCommand(CANCELADO);  
        for (int i = 0; i < cores.length-1 ; i++)  
          if (cores[i].equals(cor)) {  
            setSelectedIndex(i);  
            ok = true;  
          }  
        if (!ok) {  
          cores[cores.length-1] = cor;  
          setSelectedIndex(cores.length-1);  
        }  
        setActionCommand(OK);  
      }  
      
      /** 
       * Pega o nome da cor selecionada pelo usuário. 
       * @return String 
       */  
      public String getNomeCorSelecionada() {  
        int i = getSelectedIndex();  
        return i > -1 ? nomes[i] : "";  
      }  
      
      /** 
       * Pega o índice da cor personalizada. 
       * @return int 
       */  
      public int getIndiceCorPersonalizada() {  
        return cores.length - 1;  
      }  
    }  
      
    /** 
     * Implementa o componente que é usado pelo renderizador. 
     */  
    class ComponenteCor extends JPanel {  
      private Color cor = Color.white;  
      private String nome = "";  
      
      /** 
       * Construtora da classe. Deve ser informada uma cor e seu nome. 
       * @param cor Color 
       * @param nome String 
       */  
      public ComponenteCor(Color cor, String nome) {  
        this.cor = cor;  
        this.nome = nome;  
        this.setOpaque(true);  
      }  
      
      /** 
       * Desenha um retangulo com a cor do componente e escreve o 
       * seu nome. 
       * @param g Graphics 
       */  
      protected void paintComponent(Graphics g) {  
        super.paintComponent(g);  
        Dimension tam = getSize();  
        g.setColor(cor);  
        g.fillRect(2, 2, 15, tam.height - 4);  
        g.setColor(Color.black);  
        g.drawString(nome, 20, tam.height - 4);  
      }  
    }  