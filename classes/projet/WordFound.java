package projet;

import java.util.ArrayList;
import java.util.Iterator;

public class WordFound implements java.lang.Comparable<WordFound>{

    private String mot;
    private int points; // Total des points d'un mot trouvé
    private ArrayList<Couple> listCouple;
    private ArrayList<Case> listCase;

    public WordFound(String mot,ArrayList<Couple> alCouple ,ArrayList<Case> alCase) {

        this.mot = mot;
        this.listCouple = alCouple;
        
        this.listCase = alCase;
        Iterator<Case> iteC = alCase.iterator();
        
       
        int DW = 0,TW = 0;
        while (iteC.hasNext()) {
            Case myCase = iteC.next();
            /* Calcul des points en fonction des bonus */
            switch (myCase.getBonus()) {
            case NO:
                /* Sans bonus*/
                this.points += myCase.getPoint();
                break;
            case DL: 
                this.points += (myCase.getPoint() * 2);
                break;
            case TL:
                this.points += (myCase.getPoint() * 3);
                break;
            case DW:
                this.points += myCase.getPoint();
                DW++;
                break;
            case TW:
                this.points += myCase.getPoint();
                TW++;
                break;
            default:
                System.err.println("Bonus inconnue");
                break;
            }

        }//FIN DU WHILE
        
        /* Bonus du mot compte double */
        for (int i = 0; i < DW; i++) {
            this.points = this.points * 2;
        }
        
        /* Bonus du mot compte triple */
        for (int i = 0; i < TW; i++) {
            this.points = this.points * 3;
        }
        
        /* Bonus de point par longeur se fait après les bonus lettres et mots */
        
        switch (mot.length()) {
        case 1:
        case 2:
        case 3:
        case 4:
            break;
        case 5:
            this.points += 5;
            break;
        case 6:
            this.points += 10;
            break;
        case 7:
            this.points += 15;
            break;
        case 8:
            this.points += 20;
            break;
        case 9:
            this.points += 25;
            break;
        case 10:
            this.points += 30;
            break;
        case 11:
            this.points += 35;
            break;
        case 12:
            this.points += 40;
            break;
        case 13:
            this.points += 45;
            break;
        case 14:
            this.points += 50;
            break;
        case 15:
            this.points += 55;
            break;
        case 16:
            this.points += 60;
            break;
        /* Cas supérieur a 16 */
        default:
            this.points += 60;
            break;
        }
    }

    public int getPoint() {
        return points;
    }

    public String getMot() {
        return mot;
    }

    public void setMot(String mot) {
        this.mot = mot;
    }

    public ArrayList<Case> getListCase() {
        return listCase;
    }
    
    public ArrayList<Couple> getListCouple() {
        return listCouple;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(mot).append(' ');
        /* Affichage du parcours */
            sb.append(listCouple);
        
        /* Affichage des points */
        sb.append(' ').append(points).append(" points");
        return sb.toString();
    }
    
    @Override
    public int compareTo(WordFound wf){
        int nombre1 = ((WordFound) wf).points;
        int nombre2 = this.points;
        if(nombre1 > nombre2) return -1;
        else if(nombre1 == nombre2) return 0;
        else return 1;
    }
}
