package projet;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class WordPanelTable extends JPanel {

    private JTable table;
    
    
    public WordPanelTable(int line, int column, ArrayList<WordFound> alWf) {

        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        this.setMaximumSize(new Dimension(300, 400));
        this.setMinimumSize(new Dimension(200, 400));
        
        
        DefaultTableModel model = new DefaultTableModel(); 
        this.table = new JTable(model); 

        model.addColumn("Mots"); 
        model.addColumn("Points");
        model.addColumn(" A"); 
         


        
        for (WordFound wf : alWf) {
            
            model.addRow(new Object[]{wf.getMot(), wf.getPoint(),wf.getListCouple()});
        }
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(139);
        table.getColumnModel().getColumn(1).setPreferredWidth(139);
        table.getColumnModel().getColumn(2).setPreferredWidth(640);
        //table.removeColumn(table.getColumnModel().getColumn(2));
        
        
        /* Centrer la colonnes des points */
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );

        

        

        this.add(new JScrollPane(table));
        
        
    }


    public JTable getTable() {
        return table;
    }
    
    


}
