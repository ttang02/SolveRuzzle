package projet;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

public class MatricePanel extends JPanel {

    private static JToggleButton[][] tabJbutton = new JToggleButton[4][4];

    public MatricePanel(Matrice m) {

        this.setLayout(new GridLayout(4, 4, 2, 2)); // choix du layout
        // matricePanel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setPreferredSize(new Dimension(400, 400));
        this.setMaximumSize(new Dimension(400, 400));
        this.setMinimumSize(new Dimension(400, 400));
        this.setBackground(new Color(51, 153, 255));

        
        
        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 4; j++) {

                tabJbutton[i][j] = new JToggleButton(Character.toString(m.getMatrice()[i][j].getLetter()));
                tabJbutton[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                tabJbutton[i][j].setFont(tabJbutton[i][j].getFont().deriveFont(Font.BOLD));

                tabJbutton[i][j].setForeground(Color.BLACK);
                tabJbutton[i][j].setBackground(Color.WHITE);
                tabJbutton[i][j].setHorizontalAlignment(JLabel.CENTER);
                tabJbutton[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));

                this.add(tabJbutton[i][j]);
            }
        }

        // JLabel label = new JLabel("A");
        // label.setHorizontalAlignment(JLabel.CENTER);
        // label.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // matricePanel.add(label);

    }

    public static void setPressedButton(ArrayList<Couple> alCouple) {

        
        
        /* On désactive la pression pour tout le tableau */
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                
                MatricePanel.tabJbutton[i][j].setSelected(false);
                MatricePanel.tabJbutton[i][j].setBackground(Color.GRAY);
                
            }

        }

        /* Première lettre en VERT */
        MatricePanel.tabJbutton[alCouple.get(0).getI()][alCouple.get(0).getJ()].setSelected(false);
        MatricePanel.tabJbutton[alCouple.get(0).getI()][alCouple.get(0).getJ()].setBackground(Color.GREEN);
        
        /* On active la pression pour le mot */
        int green = 255;
        int red = 0;
        int blue = 30;
        for (Couple c : alCouple) {
            green -= 15;
            red += 15;
            MatricePanel.tabJbutton[c.getI()][c.getJ()].setBackground(new Color(red,green, blue));
            MatricePanel.tabJbutton[c.getI()][c.getJ()].setSelected(false);
        }
        green -= 15;
        red += 15;
        /* Derière lettre en ROUGE */
        Couple lastCouple = alCouple.get(alCouple.size() - 1);
        MatricePanel.tabJbutton[lastCouple.getI()][lastCouple.getJ()].setSelected(false);
        MatricePanel.tabJbutton[lastCouple.getI()][lastCouple.getJ()].setBackground((new Color(red,green, blue)));
    }

    public JToggleButton[][] getTabJbutton() {
        return tabJbutton;
    }

}