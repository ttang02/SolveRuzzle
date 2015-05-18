package projet;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTable;

public class MainPanel extends JPanel {

    private WordPanel wordPanel;
    private MatricePanel matricePanel;

    public MainPanel(Matrice m, ArrayList<WordFound> alWf) {

        this.setPreferredSize(new Dimension(600, 400));
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        /* Création du panel pour les 2 tableaux */
        this.wordPanel = new WordPanel(alWf);
        this.matricePanel = new MatricePanel(m);
        
        /* Ajout du panel au panel principale */
        this.add(wordPanel);
        /* Ajout de la matrice */
        this.add(matricePanel, BoxLayout.X_AXIS);

    }

    public WordPanel getWordPanel() {
        return wordPanel;
    }

    public MatricePanel getMatricePanel() {
        return matricePanel;
    }

    public void lisenKey(MatricePanel m) {
        /* Tableau de gauche */
        this.getWordPanel().getWordPanelTableLeft().getTable().addKeyListener(new KeyAdapter() {

            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                JTable target; 
                int row;
                Object o;
                switch (keyCode) {

                case KeyEvent.VK_UP:
                    target = (JTable) e.getSource();
                    row = target.getSelectedRow();
                    o = target.getValueAt(row, 2); // La colonne 3 contient la liste de couple(parcours) elle est invisible
                    MatricePanel.setPressedButton((ArrayList<Couple>) o);
                    break;
                case KeyEvent.VK_DOWN:
                    target = (JTable) e.getSource();
                    row = target.getSelectedRow();
                    o = target.getValueAt(row, 2); // La colonne 3 contient la liste de couple(parcours) elle est invisible
                    MatricePanel.setPressedButton((ArrayList<Couple>) o);
                    break;
                }
            }
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                JTable target; 
                int row;
                Object o;
                switch (keyCode) {

                case KeyEvent.VK_UP:
                    target = (JTable) e.getSource();
                    row = target.getSelectedRow();
                    o = target.getValueAt(row, 2); // La colonne 3 contient la liste de couple(parcours) elle est invisible
                    MatricePanel.setPressedButton((ArrayList<Couple>) o);
                    break;
                case KeyEvent.VK_DOWN:
                    target = (JTable) e.getSource();
                    row = target.getSelectedRow();
                    o = target.getValueAt(row, 2); // La colonne 3 contient la liste de couple(parcours) elle est invisible
                    MatricePanel.setPressedButton((ArrayList<Couple>) o);
                    break;
                }
            }
        });
        /* Tableau de droite */
        this.getWordPanel().getWordPanelTableRight().getTable().addKeyListener(new KeyAdapter() {

            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                JTable target; 
                int row;
                Object o;
                switch (keyCode) {

                case KeyEvent.VK_UP:
                    target = (JTable) e.getSource();
                    row = target.getSelectedRow();
                    o = target.getValueAt(row, 2); // La colonne 3 contient la liste de couple(parcours) elle est invisible
                    MatricePanel.setPressedButton((ArrayList<Couple>) o);
                    break;
                case KeyEvent.VK_DOWN:
                    target = (JTable) e.getSource();
                    row = target.getSelectedRow();
                    o = target.getValueAt(row, 2); // La colonne 3 contient la liste de couple(parcours) elle est invisible
                    MatricePanel.setPressedButton((ArrayList<Couple>) o);
                    break;
                }
            }
            
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                JTable target; 
                int row;
                Object o;
                switch (keyCode) {

                case KeyEvent.VK_UP:
                    target = (JTable) e.getSource();
                    row = target.getSelectedRow();
                    o = target.getValueAt(row, 2); // La colonne 3 contient la liste de couple(parcours) elle est invisible
                    MatricePanel.setPressedButton((ArrayList<Couple>) o);
                    break;
                case KeyEvent.VK_DOWN:
                    target = (JTable) e.getSource();
                    row = target.getSelectedRow();
                    o = target.getValueAt(row, 2); // La colonne 3 contient la liste de couple(parcours) elle est invisible
                    MatricePanel.setPressedButton((ArrayList<Couple>) o);
                    break;
                }
            }
        });
    }

    public void lisenMouse(MatricePanel m) {
        this.getWordPanel().getWordPanelTableLeft().getTable().addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {

                JTable target = (JTable) e.getSource();
                int row = target.getSelectedRow();
                Object o = target.getValueAt(row, 2); // La colonne 3 contient la liste de couple(parcours) elle est invisible

                /* On met la case en orange */
                MatricePanel.setPressedButton((ArrayList<Couple>) o);

            }
        });
        this.getWordPanel().getWordPanelTableRight().getTable().addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {

                JTable target = (JTable) e.getSource();
                int row = target.getSelectedRow();
                Object o = target.getValueAt(row, 2); // La colonne 3 contient la liste de couple(parcours) elle est invisible

                /* On met la case en orange */
                System.out.println(o.toString());
                MatricePanel.setPressedButton((ArrayList<Couple>) o);

            }
        });

    }

}
