package projet;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        int hauteurArbre = 13;

        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("benchmark.txt", true)))) {
            out.print(hauteurArbre);
        } catch (IOException e) {
            // exception handling left as an exercise for the reader
        }
        
        Matrice m = Matrice.initMatrice("matrice.txt", "bonus.txt");
        long debut = System.nanoTime();
        ArbreH arbre = ArbreH.lectureFichier("mots.txt", hauteurArbre);
        long fin = System.nanoTime();
        
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("benchmark.txt", true)))) {
            out.print("\t" + (fin - debut));
        } catch (IOException e) {
         //    exception handling left as an exercise for the reader
        }
        //System.out.print(fin - debut);
        
        // System.out.println(ArbreH.findWord("DOITSS", arbre));
        // System.out.println(ArbreH.listMoyenne(arbre));
        // System.out.println(ArbreH.estPrefixe("BONJ", arbre));
        // System.out.println(ArbreH.listLongs(arbre));
        /* Affiche l'arbre dans le terminal */
        //System.out.println(arbre.toString());

         //System.out.println(m.toString());

        ArrayList<WordFound> alWf = new ArrayList<WordFound>(); // Contient tous les mots trouvé, les points et le parcours

        long debut2 = System.nanoTime();
        alWf = Matrice.parcours(m, arbre);
        long fin2 = System.nanoTime();
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("benchmark.txt", true)))) {
            out.print("\t" + (fin2 - debut2));
        } catch (IOException e) {
            // exception handling left as an exercise for the reader
        }
        // System.out.print(fin2 - debut2);

        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("benchmark.txt", true)))) {
            out.println("\t" + ((fin - debut) + (fin2 - debut2)));
        } catch (IOException e) {
            // exception handling left as an exercise for the reader
        }
        //System.out.print((fin - debut) + (fin2 - debut2));

        /* Affichage de tout les mots trouvé par ordre arbitraire */
        // for (WordFound w : alWf) {
        // System.out.println(w.toString());
        // }

        /* Affichage de tout les mots trouvé par ordre croissant */
        // Collections.sort(alWf);
        // for (WordFound w : alWf) {
        // System.out.println(w.toString());
        // }

        /* Affichage de tout les mots trouvé par ordre décroissant */
        // Collections.sort(alWf, Collections.reverseOrder());
        // for (WordFound w : alWf) {
        // System.out.println(w.toString());
        // }

         Window fen = new Window(m, alWf);
        
         fen.getMainPanel().lisenKey(fen.getMainPanel().getMatricePanel());
         fen.getMainPanel().lisenMouse(fen.getMainPanel().getMatricePanel());
    }

}
