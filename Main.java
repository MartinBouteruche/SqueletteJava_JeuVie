class Main {

  enum Statut {Naissante1, Vivante1, Mourrante1, Naissante2, Vivante2, Mourrante2, Morte};
  //enum Statut {Naissante, Vivante, Mourrante, Morte};

  static Statut[][] grille;
  static Statut[][] grilleTemp;
  // Paramètres
  static int tailleX;
  static int tailleY;
  static int nombreDeGenerations;
  static double tauxDeRemplissage;
  static boolean modeCouleur;
  static boolean etapesIntermediaires;

  static int taillePop1;
  static int taillePop2;
  
	public static void main(String[] args) {
    System.out.println("Saisir nombre de lignes :");
		tailleX = Clavier.lireEntier();

    System.out.println("Saisir nombre de colonnes :");
		tailleY = Clavier.lireEntier();

    System.out.println("Saisir nombre de Générations :");
    nombreDeGenerations = Clavier.lireEntier();

    System.out.println("Saisir taux de remplissage(en %) :");
		tauxDeRemplissage = Clavier.lireReel();

    System.out.println("Voulez-vous le mode couleur :");
		modeCouleur = Clavier.lireBooleen();

    System.out.println("Voulez-vous visualiser les étapes intermédiares :");
		etapesIntermediaires = Clavier.lireBooleen();

    grille = new Statut[tailleX][tailleY];
    grilleTemp = new Statut[tailleX][tailleY];

    for (int g = 0; g <= nombreDeGenerations; g++) {
      if (g == 0) {
        System.out.println("Génération initiale");
        for (int x = 0; x < tailleX; x++) {
          for (int y = 0; y < tailleY; y++) {
            int nbrAleatoire = Outils.entierAleatoire(0,100);
            if (nbrAleatoire <= tauxDeRemplissage) {
              grille[x][y] = Statut.Vivante;

              int choixDePop = Outils.entierAleatoire(0,2);
              if (choixDePop < 1) {
                grille[x][y] = Statut.Vivante1;
                taillePop1 += 1;
              } else {
                grille[x][y] = Statut.Vivante2;
                taillePop2 += 1;               
              }
            } else {
              grille[x][y] = Statut.Morte;
            }
            afficherCellule(grille[x][y]);
          }
          System.out.println("");
        }
      } else {
        Ecran.annulerAffichageCouleur();
        System.out.println("Génération n°" + g + " intermédiaire");
        evaluerNouvelleGen();
        afficherGrille(grilleTemp);
        copierGrille();
        if (etapesIntermediaires) {
          Ecran.annulerAffichageCouleur();
          System.out.println("Génération n°" + g);
          afficherGrille(grille);
        }
      }
    }
	}

	static void evaluerNouvelleGen() {
    for (int x = 0; x < tailleX; x++) {
      for (int y = 0; y < tailleY; y++) {
        /*int nombreDeVoisins = evaluerNombreDeCellules(x,y,1,1);
        if ((nombreDeVoisins < 2) || (nombreDeVoisins > 3)) {
          if (etapesIntermediaires) {
            if (grille[x][y] == Statut.Vivante) {                       
              grilleTemp[x][y] = Statut.Mourrante;
              continue;
            }
          }
          grilleTemp[x][y] = Statut.Morte;
        } else if (nombreDeVoisins == 3) {
          if (etapesIntermediaires) {
            if (grille[x][y] == Statut.Morte) {                       
              grilleTemp[x][y] = Statut.Naissante;
              continue;
            }
          }
          grilleTemp[x][y] = Statut.Vivante;
        } else {
          grilleTemp[x][y] = grille[x][y];
        }*/

        Statut statutPrecedent = grille[x][y];

        if (statutPrecedent == Statut.Vivante1) {
          int nombreDeVoisins = evaluerNombreDeCellules(x, y, 1, Statut.Vivante1);
          if ((nombreDeVoisins < 2) || (nombreDeVoisins > 3)) {
            if (etapesIntermediaires) {
              grilleTemp[x][y] = Statut.Mourrante1;
              continue;
            }
          }
          grilleTemp[x][y] = Statut.Morte;
          taillePop1 -= 1;
        } else if (statutPrecedent == Statut.Vivante2) {
          int nombreDeVoisins = evaluerNombreDeCellules(x, y, 1, Statut.Vivante2);
          if ((nombreDeVoisins < 2) || (nombreDeVoisins > 3)) {
            if (etapesIntermediaires) {
              grilleTemp[x][y] = Statut.Mourrante2;
              continue;
            }
          }
          grilleTemp[x][y] = Statut.Morte;
          taillePop2 -= 1;
        }

        if (statutPrecedent == Statut.Morte) {
          int voisinsPop1 = evaluerNombreDeCellules(x,y,1,Statut.Vivante1);
          int voisinsPop2 = evaluerNombreDeCellules(x,y,1,Statut.Vivante2);
          if (voisinsPop1 == 3 && voisinsPop2 == 3) {
            // TODO : evaluer en cas de conflits (systeme de rescencement de pop)
            int voisins2Pop1 = evaluerNombreDeCellules(x,y,2,Statut.Vivante1);
            int voisins2Pop2 = evaluerNombreDeCellules(x,y,2,Statut.Vivante2);
            if (voisins2Pop1 > voisins2Pop2) {
              grilleTemp[x][y] = Statut.Naissante1;
            } else if (voisins2Pop2 > voisins2Pop1) {
              grilleTemp[x][y] = Statut.Naissante2;
            } else {
              if (taillePop1 > taillePop2) {
                grilleTemp[x][y] = Statut.Naissante1;
              } else if (taillePop2 > taillePop2) {
                grilleTemp[x][y] = Statut.Naissante2;
              } else {
                grilleTemp[x][y] = Statut.Morte;
              }
            }
          } else if (voisinsPop1 == 3) {
            if (etapesIntermediaires) {
              grilleTemp[x][y] = Statut.Naissante1;
              continue;
            }
            grilleTemp[x][y] = Statut.Vivante1;
          } else if (voisinsPop2 == 3) {
            if (etapesIntermediaires) {
              grilleTemp[x][y] = Statut.Naissante2;
              continue;
            }
            grilleTemp[x][y] = Statut.Vivante2;
          }
        }
      }
    }
  }

  static int evaluerNombreDeCellules(int indexX, int indexY, int rang, Statut population) {
    int nombreDeCellules = 0;
    for (int x = indexX - rang; x <= indexX + rang; x++) {
      for (int y = indexY - rang; y <= indexY + rang; y++) {
        if ((x >= 0) && (y >= 0) && (x < tailleX) && (y < tailleY)) {
          if (((x == indexX) && (y == indexY)) != true) {
            if (grille[x][y] == population) {
              nombreDeCellules += 1;
            }
          }
        }
      }
    }
    return nombreDeCellules;
  }

  static void afficherGrille(Statut[][] grilleAfficher) {
    for (int x = 0; x < tailleX; x++) {
      for (int y = 0; y < tailleY; y++) {
        afficherCellule(grilleAfficher[x][y]);
      }
      Ecran.annulerAffichageCouleur();
      System.out.println("");
    }
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
      case Naissante1:
      case Naissante2:
        Ecran.changerCouleurFond(Ecran.Couleur.VERT);
        System.out.print("  ");
				break;
      case Mourrante1:
      case Mourrante2:
        Ecran.changerCouleurFond(Ecran.Couleur.ROUGE);
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
      case Naissante1:
      case Naissante2:
        System.out.print("+");
				break;
      case Mourrante1:
      case Mourrante2:
        System.out.print("-");
				break;
		  }
    }
  }

  static void copierGrille() {
    for (int x = 0; x < tailleX; x++) {
      for (int y = 0; y < tailleY; y++) {
        grille[x][y] = grilleTemp[x][y];
        if (grilleTemp[x][y] == Statut.Morte || grilleTemp[x][y] == Statut.Mourrante1|| grilleTemp[x][y] == Statut.Mourrante2) {
          grille[x][y] = Statut.Morte;
        } else if (grilleTemp[x][y] == Statut.Vivante1 || grilleTemp[x][y] == Statut.Naissante1) {
          grille[x][y] = Statut.Vivante1;
        } else if (grilleTemp[x][y] == Statut.Vivante2 || grilleTemp[x][y] == Statut.Naissante2) {
          grille[x][y] = Statut.Vivante2;
        }  
      }
    }
  }
}