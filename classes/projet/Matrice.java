package projet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Matrice {

    private Case[][] matrice;

    public Case[][] getMatrice() {
        return matrice;
    }

    public Case getCase(int i, int j) {

        return matrice[i][j];
    }

    private Matrice(Case[][] matrice) {
        this.setMatrice(matrice);
    }

    public void setMatrice(Case[][] matrice) {
        this.matrice = matrice;
    }

    public static Matrice initMatrice(String fileMatrice, String fileBonus) {
        Case[][] matrice = new Case[4][4];
        File fileNameMatrice = new File(fileMatrice);

        File fileNameBonus = new File(fileBonus);

        try {
            Scanner scannerMatrice = new Scanner(fileNameMatrice);
            Scanner scannerBonus = new Scanner(fileNameBonus);
            while (scannerMatrice.hasNext() && scannerBonus.hasNext()) {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        matrice[i][j] = new Case(scannerMatrice.next().charAt(0), Bonus.valueOf(scannerBonus.next()));
                    }
                }
            }
            scannerMatrice.close();
            scannerBonus.close();
        } catch (FileNotFoundException e) {
            /* Cas d'erreur */
            System.out.println("Le fichier " + fileNameMatrice.getName() + " n'a pas été trouvé");
        }

        return new Matrice(matrice);
    }

    /* Vérifé OK */
    /* Cette fonction renvoie la liste de couple des voisins de la case[i][j] */
    public ArrayList<Couple> voisin(int i, int j) {
        ArrayList<Couple> alVoisin = new ArrayList<Couple>();
        for (int k1 = -1; k1 < 2; k1++) {
            for (int k2 = -1; k2 < 2; k2++) {
                /* On ne retoune pas la même case */
                if (!((k1 == 0) && (k2 == 0))) {
                    /* si c'est pas la même case, on vérie que c'est bien dans les bornes */
                    if ((0 <= (i - k1)) && ((i - k1) < 4) && (0 <= (j - k2)) && ((j - k2) < 4)) {
                        alVoisin.add(new Couple(i - k1, j - k2));
                    }
                }
            }
        }
        return alVoisin;
    }

    public static void parcoursFromLetter(Matrice m, ArrayList<Couple> alCouple, ArbreH aH, ArrayList<WordFound> alWf) {

        StringBuilder sb = new StringBuilder();
        /* Un combinaison de mot */
        for (Couple c1 : alCouple) {

            sb.append(m.getCase(c1.getI(), c1.getJ()).getLetter());

        }
        if (!(ArbreH.estPrefixe(sb.toString(),aH))){
            return;
        }
        /* Si on trouve le mot */
        if (ArbreH.findWord(sb.toString(), aH)) {
           
            ArrayList<Case> alCase = new ArrayList<Case>();
            for (Couple c : alCouple) {
                alCase.add(m.getCase(c.getI(), c.getJ()));
            }
            alWf.add(new WordFound(sb.toString(), new ArrayList<Couple>(alCouple), alCase));
        }
        Couple lastCouple;
        lastCouple = alCouple.get(alCouple.size() - 1);

        ArrayList<Couple> alC = new ArrayList<Couple>();
        alC = m.voisin(lastCouple.getI(), lastCouple.getJ());

        for (Couple c2 : alC) {
            if (!(alCouple.contains(c2))) {
                alCouple.add(c2);

                parcoursFromLetter(m, alCouple, aH, alWf);
                alCouple.remove(c2);

            }

        }

    }

    /* Parcours a partir de toutes les cases du tableau */
    public static ArrayList<WordFound> parcours(Matrice matrice, ArbreH aH) {
        ArrayList<WordFound> alWf = new ArrayList<WordFound>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                ArrayList<Couple> alCouple = new ArrayList<Couple>();
                alCouple.add(new Couple(i, j));
                /* On appel afficheMotRuzzle sur toutes les cases du tableau */

                parcoursFromLetter(matrice, alCouple, aH, alWf);
            }
        }
        /* Affichage du parcours pour chaque mot trouvé */

        return alWf;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        /* Affichage de la matrice */
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                sb.append(matrice[i][j].getLetter());
                if (j < 3) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        sb.append("\n");
        /* Affichage du bonus */
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                sb.append(matrice[i][j].getBonus());
                if (j < 3) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
