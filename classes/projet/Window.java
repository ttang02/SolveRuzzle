package projet;

import java.util.ArrayList;

import javax.swing.JFrame;

public class Window extends JFrame {

    private MainPanel mainPanel;
    
    


    public Window(Matrice m, ArrayList<WordFound> alWf) {
        this.setTitle("Ruzzle");
        this.setSize(1000, 400);
        this.setLocationRelativeTo(null); // centre sur l'écran
        this.setResizable(false); // dimension non modifiable
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // fermer lors du clic sur la croix
        this.mainPanel = new MainPanel(m,alWf);
        this.setContentPane(mainPanel);
        this.setVisible(true); // rendre la fenètre visible
         

    }
    
    public MainPanel getMainPanel() {
        return mainPanel;
    }




}