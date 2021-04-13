/**
 * Gestion des saisies au clavier.
 * @author Thomas Raimbault
 * @version 01/2021
 */

import java.util.Scanner; 

public class Clavier {
    static private Scanner scanner = new Scanner(System.in);
    
    /** 
     * Lecture au clavier d'un entier, suivi d'un retour à la ligne (touche Entrer).
     * @return la valeur entière saisie au clavier
     */
    static public int lireEntier() {
      int resultat = scanner.nextInt();
      scanner.nextLine(); // pour vider la ligne (le caractère retour chariot)
      return resultat;
    }

    /** 
     * Lecture au clavier d'un réel, suivi d'un retour à la ligne (touche Entrer). Attention : le séparateur décimal est le caractère point (.).
     * @return la valeur réelle saisie au clavier
     */
    static public double lireReel() {
      double resultat = scanner.nextDouble();
      scanner.nextLine(); // pour vider la ligne (le caractère retour chariot)
      return resultat;
    }

    /** 
     * Lecture au clavier d'un booléen, suivi d'un retour à la ligne (touche Entrer).
     * @return la valeur booléenne saisie au clavier, c'est à dire "false" ou "true" (sans les guillemets, non sensible aux minuscules/majuscules)
     */
    static public boolean lireBooleen() {
      boolean resultat = scanner.nextBoolean();
      scanner.nextLine(); // pour vider la ligne (le caractère retour chariot)
      return resultat; 
    } 
    
    /** 
     * Lecture au clavier d'une chaîne de caractères, terminée par un retour à la ligne (touche Entrer).
     * @return le contenu "brut" saisi au clavier
     */
    static public String lireChaine() {
      return scanner.nextLine();
    }

	/** Nécessite d'appuyer sur la touche ENTRER pour que l'exécution du programme puisse continuer.
	 */
	static public void appuyerSurEntrer() {
		lireChaine();
	}
}
