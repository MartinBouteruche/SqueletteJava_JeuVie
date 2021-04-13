class Main {

  enum Statut {Morte, Vivante};
  static Statut[][] grille;
  static Statut[][] grilleTemp;
  // Paramètres
  static int tailleX;
  static int tailleY;
  static double tauxDeRemplissage;
  static boolean modeCouleur;
  static boolean etapesIntermédiares;
  
	public static void main(String[] args) {
    //Ecran.changerCouleurFond(Ecran.Couleur.BLEU);
    System.out.println("Saisir nombre de lignes : ");
		tailleX = Clavier.lireEntier();

    System.out.println("Saisir nombre de colonnes : ");
		tailleY = Clavier.lireEntier();

    System.out.println("Saisir taux de remplissage(en %) : ");
		tauxDeRemplissage = Clavier.lireReel();

    System.out.println("Voulez-vous le mode couleur : ");
		modeCouleur = Clavier.lireBooleen();

    /*System.out.println("Voulez-vous visualiser les étapes intermédiares : ");
		etapesIntermédiares = Clavier.lireBooleen();*/

    grille = new Statut[tailleX][tailleY];
    grilleTemp = new Statut[tailleX][tailleY];

    for (int i = 0; i < 4; i++) {
      if (i == 0) {
        for (int x = 0; x < tailleX; x++) {
          for (int y = 0; y < tailleY; y++) {
            int nbrAleatoire = Outils.entierAleatoire(0,100);
            if (nbrAleatoire <= tauxDeRemplissage) {
              grille[x][y] = Statut.Vivante;
            } else {
              grille[x][y] = Statut.Morte;
            }
            afficherCellule(grille[x][y]);
          }
          System.out.println("");
        }
        //System.out.println("");
      } else {
        Ecran.annulerAffichageCouleur();
        System.out.println("Géneration n°" + i);
        parcourirLaListe();
        copierGrille();
      }
    }
	}

	static void parcourirLaListe() {
    for (int x = 0; x < tailleX; x++) {
      for (int y = 0; y < tailleY; y++) {
        int nombreDeVoisins = evaluerNombreDeCellules(x,y);
        if ((nombreDeVoisins < 2) || (nombreDeVoisins > 3)) {
          grilleTemp[x][y] = Statut.Morte;
        } else if (nombreDeVoisins == 3) {
          grilleTemp[x][y] = Statut.Vivante;
        } else {
          grilleTemp[x][y] = grille[x][y];
        }
        afficherCellule(grilleTemp[x][y]);
      }
      Ecran.annulerAffichageCouleur();
      System.out.println("");
    }
  }

  static int evaluerNombreDeCellules(int indexX, int indexY) {
    int nombreDeCellules = 0;
    for (int x = indexX - 1; x <= indexX + 1; x++) {
      for (int y = indexY - 1; y <= indexY + 1; y++) {
        if ((x >= 0) && (y >= 0) && (x < tailleX) && (y < tailleY)) {
          if (((x == indexX) && (y == indexY)) != true) {
            if (grille[x][y] == Statut.Vivante) {
              nombreDeCellules += 1;
            }
          }
        }
      }
    }
    return nombreDeCellules;
  }

  static void afficherCellule(Statut statut) {
    if (modeCouleur) {
      switch(statut) {
			case Morte:
        Ecran.changerCouleurFond(Ecran.Couleur.BLANC);
        System.out.print("  ");
				break;
			case Vivante:
        Ecran.changerCouleurFond(Ecran.Couleur.NOIR);    
        System.out.print("  ");
		    break;
		  }
    } else {
      switch(statut) {
			case Morte:
        System.out.print(".");
				break;
			case Vivante:
        System.out.print("#");
				break;
		  }
    }
  }

  static void copierGrille() {
    for (int x = 0; x < tailleX; x++) {
      for (int y = 0; y < tailleY; y++) {
        grille[x][y] = grilleTemp[x][y];
      }
    }
  }

}