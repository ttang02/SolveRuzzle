package projet;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class WordPanel extends JPanel {

    private WordPanelTable wordPanelTableLeft;
    private WordPanelTable wordPanelTableRight;
    
    public WordPanel(ArrayList<WordFound> alWf) {

        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS)); // choix du layout
        this.setMaximumSize(new Dimension(600, 400));
        this.setMinimumSize(new Dimension(400, 400));
        
        Collections.sort(alWf, Collections.reverseOrder());
        this.wordPanelTableLeft = new WordPanelTable(alWf.size(), 2, alWf);
        this.add(wordPanelTableLeft);

        Collections.sort(alWf);
        this.wordPanelTableRight = new WordPanelTable(alWf.size(), 2, alWf);
        this.add(wordPanelTableRight);

    }

    public WordPanelTable getWordPanelTableLeft() {
        return wordPanelTableLeft;
    }

    public WordPanelTable getWordPanelTableRight() {
        return wordPanelTableRight;
    }
    


}
