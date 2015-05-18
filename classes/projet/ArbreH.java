package projet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ArbreH {

    private ArrayList<ArbreH> arrayFils;
    private ArrayList<String> arrayChaine;
    private boolean estFeuille;
    private static int compteurEspace = 0;
    private static int nbElementList = 0;
    private static int nbListe = 0;
    private static int tailleMaxListe = 0;

    public boolean isEstFeuille() {
        return estFeuille;
    }

    /* Constructeur pour une lettre */
    private ArbreH(char lettre, boolean estFeuille) {
        arrayChaine = new ArrayList<String>();
        arrayChaine.add(Character.toString(lettre));
        this.estFeuille = estFeuille;
        this.arrayFils = new ArrayList<ArbreH>();
    }

    /* Constructeur pour la liste */
    private ArbreH(String chaine, boolean estFeuille) {
        this.arrayChaine = new ArrayList<String>();
        this.arrayChaine.add(chaine);
        this.estFeuille = estFeuille;
        this.arrayFils = new ArrayList<ArbreH>();
    }

    private ArbreH ajouteFils(Object element, boolean estFeuille) {
        ArbreH arbreFils = null;

        /* Dans le cas d'un caractère */

        if (element instanceof Character) {

            /* On test si l'arbre actuel ne contient pas de fils , si c'est le cas on initialise un fils */
            if (arrayFils.isEmpty()) {
                arbreFils = new ArbreH((char) element, estFeuille);
                arrayFils.add(arbreFils);
                return arbreFils; // on descend
            }
            /* L'arbre contient des fils donc on recherche la lettre parmis les fils de l'arbre actuel */
            else {
                for (ArbreH h : arrayFils) {
                    if (h.arrayChaine.get(0).length() == 1) {
                        if (h.arrayChaine.get(0).charAt(0) == (char) element) {

                            return h;
                        }
                    }
                }
                /* L'arbre contient des fils mais ne le trouve pas, donc on le crée */
                arbreFils = new ArbreH((char) element, estFeuille);
                arrayFils.add(arbreFils);
                return arbreFils;

            }

        }

        /* Dans le cas d'une chaîne */
        else if (element instanceof String) {
            /* Lorsque la liste contient au moins 1 élément */
            if (arrayChaine.size() > 1) {
                /* On ajoute l'élément dans la liste */
                this.arrayChaine.add((String) element);
                return this;
            }
            /* Lorsque l'arbre actuel n'as pas de fils et que l'arbre actuel est une lettre alors on crée un nouveau fils contenant la chaine */
            if (arrayFils.isEmpty() && arrayChaine.get(0).length() == 1) {
                arbreFils = new ArbreH((String) element, estFeuille);
                this.arrayFils.add(arbreFils);
                return arbreFils;
            }
            /* Si la liste est déjà créee, on ajoute la chaine dans la liste du fils */
            else {
                arbreFils = this.arrayFils.get(0);
                arbreFils.arrayChaine.add((String) element);
                return arbreFils;
            }

        }
        return arbreFils;
    }

    public static ArbreH lectureFichier(String Name, int hauteurArbre) {

        /* Nom du fichier */
        File fileName = new File(Name);
        ArbreH monArbre = new ArbreH('.', false);

        /* Tentative de lecture */
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = reader.readLine()) != null) {
                int longueur = line.length();

                ArbreH arbreH2 = monArbre;

                /* Parcours des lettres d'un mot */
                for (int i = 0; i < longueur; i++) {
                    /* On met les lettres dans une structure d'arbre, lorsque les lettres sont plus petit que: hauteurArbre */
                    if (i < hauteurArbre) {

                        if (i < longueur - 1) {
                            // Si on est pas une feuille
                            arbreH2 = arbreH2.ajouteFils(line.charAt(i), false);
                        }
                        else {
                            // SI on est une feuille
                            arbreH2 = arbreH2.ajouteFils(line.charAt(i), true);
                        }

                    }
                    /* Sinon on met les lettres dans une structure de liste */
                    else {
                        arbreH2 = arbreH2.ajouteFils(line.substring(i), true);
                        break;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            /* Cas d'erreur */
            System.out.println("Le fichier " + fileName.getName() + " n'a pas été trouvé");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return monArbre;
    }

    public String toString() {
        compteurEspace++;
        StringBuilder sb = new StringBuilder();

        if (this.estFeuille) {
            sb.append("{" + arrayChaine + "}").append('\n');
        }
        else {
            sb.append("<" + arrayChaine + ">").append('\n');
        }

        for (ArbreH fils : this.arrayFils) {
            for (int i = 0; i < compteurEspace; i++) {
                sb.append("  |  ");
            }
            sb.append(fils.toString()); /* Récurence */
        }
        compteurEspace--;
        return sb.toString();
    }

    public static boolean estPrefixe(String prefixe, ArbreH arbre) {

        if (arbre.arrayFils.isEmpty()) {
            /* On recherche dans la liste de chaine qd on a pas de fils, en comparant le prefixe à la liste de chaine */
            for (String string : arbre.arrayChaine) {
                if (string.startsWith(prefixe)) {
                    return true;
                }
            }
        }
        /* Lorsque l'arbre contient des fils */
        else {
            /* On parcours des fils */
            for (ArbreH aH : arbre.arrayFils) {
                /* Si le tableau de liste de chaine est composé que d'une seul case */
                if (aH.arrayChaine.size() == 1) {
                    /* Si l'unique case du tableau de chaine est une lettre */
                    if (aH.arrayChaine.get(0).length() == 1) {
                        /* On compare la première de notre lettre du mot et la première lettre de l'unique case */

                        if (aH.arrayChaine.get(0).charAt(0) == prefixe.charAt(0)) {
                            /* Si notre mot contient qu'une seul lettre */
                            if (prefixe.length() == 1) {
                                return true;
                            }
                            else {
                                return estPrefixe(prefixe.substring(1), aH);
                            }
                        }
                    }
                }
                /* Sinon le fils est la liste de chaine */
                else {
                    return estPrefixe(prefixe.substring(0), aH);
                }
            }
        }
        return false;
    }

    /* Recherche d'un mot dans l'arbre hybrid */
    public static boolean findWord(String mot, ArbreH arbre) {
        /* Si le mot contient encore des lettres */
        if (mot.length() > 0) {
            /* Dans le cas où on a pas de fils, cad qu'on doit rechercher dans la liste */
            if (arbre.arrayFils.isEmpty()) {
                for (String str : arbre.arrayChaine) {
                    if (str.equals(mot)) {
                        return true;
                    }
                }
            }
            /* Lorsque l'arbre contient des fils */
            else {
                /* On parcours des fils */
                for (ArbreH aH : arbre.arrayFils) {
                    /* Si le tableau de liste de chaine est composé que d'une seul case */
                    if (aH.arrayChaine.size() == 1) {
                        /* Si l'unique case du tableau de chaine est une lettre */
                        if (aH.arrayChaine.get(0).length() == 1) {
                            /* On compare la première de notre lettre du mot et la première lettre de l'unique case */
                            if (aH.arrayChaine.get(0).charAt(0) == mot.charAt(0)) {
                                /* Si notre mot contient qu'une seul lettre alors on test si c'est une feuille */
                                if (mot.length() == 1) {
                                    if (aH.isEstFeuille()) {
                                        return true;
                                    }
                                    else {
                                        return false;
                                    }
                                }
                                else {
                                    if (aH.arrayFils.isEmpty()) {
                                        return false;
                                    }
                                    return findWord(mot.substring(1), aH);
                                }
                            }
                        }
                        else {

                            return findWord(mot.substring(0), aH);

                        }
                    }
                    /* Sinon le fils est la liste de chaine */
                    else {
                        return findWord(mot.substring(0), aH);
                    }

                }
            }
        }
        return false;
    }

    public static int listMoyenne(ArbreH aH) {
        if (aH.arrayFils.isEmpty()) {
            // System.out.println(aH);
            nbElementList += aH.arrayChaine.size();
            nbListe++;
        }
        else {
            for (ArbreH aHFils : aH.arrayFils) {
                listMoyenne(aHFils);
            }
        }
        return nbElementList / nbListe;

    }

    public static int listLongs(ArbreH aH) {
        if (aH.arrayFils.isEmpty()) {
            if (aH.arrayChaine.size() == 21) {
                // System.out.println(aH.parent);
                // System.out.println(aH);
            }
            if (tailleMaxListe < aH.arrayChaine.size()) {
                tailleMaxListe = aH.arrayChaine.size();
            }
        }
        else {
            for (ArbreH aH1 : aH.arrayFils) {
                listLongs(aH1);
            }
        }
        return tailleMaxListe;
    }

}
