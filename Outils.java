/**
 * Ensemble d'outils utilisables.
 * @author Thomas Raimbault
 * @version 01/2021
 */

import java.util.Random;

public class Outils {

	/** Marquer une pause d'un certains temps dans l'exécution du programme. 
	 * @param millisecondes temps d'attende en millisecondes. Si < 0 la méthode ne fait rien (aucune pause).
	 */
	static public void attendre(int millisecondes) {
		if(millisecondes > 0) {
			try {
				Thread.sleep(millisecondes);
			}
			catch(InterruptedException e) {}
		}
	}

	/// Générateur aléatoire pour les méthodes générant des valeurs aléatoires
	private static Random rand = new Random();

	/** Génère une valeur entière aléatoire.
	 * @param min valeur minimale incluse
	 * @param max valeur maximale incluse
	 * @return un entier dans l'intervalle [min,max]
	 * @exception si max < min, une exception java.lang.IllegalArgumentException est levée
	 */
	static public int entierAleatoire(int min, int max) {
		return rand.nextInt((max - min) + 1) + min; // dans intervalle [min,max]
	}

	/** Génère un tableau d'entiers dont les valeurs sont aléatoires.
	 * @param taille longueur du tableau à générer
	 * @param min valeur minimale incluse
	 * @param max valeur maximale incluse
	 * @return le tableau généré d'entiers aléatoires dans l'intervalle [min,max]. null si taille <= 0 ou si max < min
	 */
	static public int[] genererTableauEntiersAleatoires(int taille, int min, int max) {
		int[] resultat = null;
		if( (taille > 0) && (min <= max) ) {
			resultat = new int[taille];
			for (int i = 0; i < resultat.length; i++) {
				resultat[i] = entierAleatoire(min,max);
			}
		}
		return resultat;
	}

}