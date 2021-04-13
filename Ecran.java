/**
 * Gestion de l'affichage en Console : texte en couleur, clear de l'écran.
 * @author Thomas Raimbault
 * @version 01/2021
 */

public class Ecran {

	/// Permet d'effacer le contenu de la Console, et de repositionner le curseur en haut de la Console.
	static public void effacerConsole() {
		System.out.println(); // pour limiter un décalage possible d'affichage
		System.out.print("\033[H\033[2J"); // effacer la Console
	}

	/// Les couleurs utilisables pour l'affichage de texte en couleur.
	public enum Couleur {ROUGE, VERT, JAUNE, BLEU, VIOLET, CYAN, NOIR, BLANC};

	/**
	 * Changer la couleur d'un texte sur la Console.
	 * @param couleurTexte La couleur de texte à utiliser
	 * @see annulerAffichageCouleur
	 */
	static public void changerCouleurTexte(Couleur couleur) {
		switch(couleur) {
			case NOIR:
				System.out.print(ANSI_BLACK);
				break;
			case ROUGE:
				System.out.print(ANSI_RED);
				break;
			case VERT:
				System.out.print(ANSI_GREEN);
				break;
			case JAUNE:
				System.out.print(ANSI_YELLOW);
				break;
			case BLEU:
				System.out.print(ANSI_BLUE);
				break;
			case VIOLET:
				System.out.print(ANSI_PURPLE);
				break;
			case CYAN:
				System.out.print(ANSI_CYAN);
				break;
			case BLANC:
				System.out.print(ANSI_WHITE);
				break;
		}
	}

	/**
	 * Changer la couleur de fond sur la Console.
	 * @param couleurFond La couleur de fond à utiliser
	 * @see annulerAffichageCouleur
	 */
	static public void changerCouleurFond(Couleur couleur) {
		switch(couleur) {
			case NOIR:
				System.out.print(ANSI_BACKGROUND_BLACK);
				break;
			case ROUGE:
				System.out.print(ANSI_BACKGROUND_RED);
				break;
			case VERT:
				System.out.print(ANSI_BACKGROUND_GREEN);
				break;
			case JAUNE:
				System.out.print(ANSI_BACKGROUND_YELLOW);
				break;
			case BLEU:
				System.out.print(ANSI_BACKGROUND_BLUE);
				break;
			case VIOLET:
				System.out.print(ANSI_BACKGROUND_PURPLE);
				break;
			case CYAN:
				System.out.print(ANSI_BACKGROUND_CYAN);
				break;
			case BLANC:
				System.out.print(ANSI_BACKGROUND_WHITE);
				break;
		}
	}

	/** Revenir à la couleur par défaut d'affichage de texte et de fond en Console.  
	 * @see changerCouleurTexte
	 * @see changerCouleurFond
	 */
	static public void annulerAffichageCouleur() {
		System.out.print(ANSI_RESET);
	}


	static final String ANSI_RESET = "\u001B[0m";

	static final String ANSI_BLACK = "\u001B[30m";
	static final String ANSI_RED = "\u001B[31m";
	static final String ANSI_GREEN = "\u001B[32m";
	static final String ANSI_YELLOW = "\u001B[33m";
	static final String ANSI_BLUE = "\u001B[34m";
	static final String ANSI_PURPLE = "\u001B[35m";
	static final String ANSI_CYAN = "\u001B[36m";
	static final String ANSI_WHITE = "\u001B[37m";

	static final String ANSI_BACKGROUND_BLACK = "\u001B[40m";
	static final String ANSI_BACKGROUND_RED = "\u001B[41m";
	static final String ANSI_BACKGROUND_GREEN = "\u001B[42m";
	static final String ANSI_BACKGROUND_YELLOW = "\u001B[43m";
	static final String ANSI_BACKGROUND_BLUE = "\u001B[44m";
	static final String ANSI_BACKGROUND_PURPLE = "\u001B[45m";
	static final String ANSI_BACKGROUND_CYAN = "\u001B[46m";
	static final String ANSI_BACKGROUND_WHITE = "\u001B[47m";
}