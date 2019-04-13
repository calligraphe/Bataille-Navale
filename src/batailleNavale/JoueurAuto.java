package batailleNavale;

public class JoueurAuto extends JoueurAvecGrille {
	private Coordonnee[] used;
	private int precedent; 		// reaction precedent
	private Coordonnee[] voisins;
	private int nbCoordonnee; 	// possibles
	private GrilleNavale grille;
	
	private int counterVoisins;
	
	public JoueurAuto(GrilleNavale g, String nom) {
		super(g, nom);
		init(g);
	} 
	
	public JoueurAuto(GrilleNavale g) {
		super(g);
		init(g);
	}
	
	public void init(GrilleNavale g) {
		precedent = 0;
		nbCoordonnee = 0;
		used = new Coordonnee[g.getTaille()*g.getTaille()];
		grille = g;
		voisins = new Coordonnee[4];
		counterVoisins = 0;
	}
	
	protected void retourAttaque(Coordonnee c, int etat) {
//		retourAttaque(c, etat);
		System.out.println(c+": Coordonnee à laquelle le tir a eu lieu ");
		if(etat == 1) {
			System.out.println("votre navire a été touché");
//			grille.recoitTir(c); // added
		}
		if(etat == 2) {
			System.out.println("votre navire a été coulé");
//			grille.recoitTir(c); // added
		}
		if(etat == 3) {
			System.out.println("le tir est dans l'eau");
		}
		if(etat == 4) {
			System.out.println("GAMEOOOOVER LOOOOOOSER!");
		}
	} 
	
	protected void retourDefense(Coordonnee c, int etat) {
//		retourDefense(c, etat);
		System.out.println(c+": Coordonnee à laquelle le tir a eu lieu ");
		if(etat == 1) {
			System.out.println("vous avez touché le navire de votre adversaire");
		}
		if(etat == 2) {
			System.out.println("vous avez coulé le navire de votre adversaire");
		}
		if(etat == 3) {
			System.out.println("le tir est dans l'eau");
		}
		if(etat == 4) {
			System.out.println("votre adversaire vient de perdre");
		}
	} 
	
	public Coordonnee choixAttaque() {
//		if (voisins[counterVoisins] != null) {
//			ajoutDansUsed(voisins[counterVoisins]);
//			if (counterVoisins == 3) {
//				counterVoisins = 0;
//				voisins = new Coordonnee[4];
//			}
//			else {
//				counterVoisins++;
//			}
//			return voisins[counterVoisins];
//		}
//		else {
			Coordonnee c = rand(grille);
			System.out.println(c.toString());
			while (estDansUsed(c))
				c = rand(grille);
			ajoutDansUsed(c);
			return c;
//		}
	}

	@Override
	public Coordonnee choisirAttaque() {
		return choixAttaque();
	}
	
	public boolean ajoutDansUsed(Coordonnee c) {
		if(nbCoordonnee == used.length) {
			return false;
		}
		if (!estDansUsed(c)) {
			  used[nbCoordonnee] = c;
			  nbCoordonnee ++;
			  return true;
		  }
		  return false;
	}
	
	public boolean estDansUsed(Coordonnee c) {
		for (int i = 0; i < nbCoordonnee; i++) {
			if(used[i] == c) { //il existe deja
				return true;
			}
	  }
	  return false; 
	}
	
	public Coordonnee rand(GrilleNavale g) {
		int tailleIntervalle = g.getTaille();
		int randomLigne = (int)(Math.random() * tailleIntervalle);
		int randomCol = (int)(Math.random() * tailleIntervalle);
		Coordonnee c = new Coordonnee(randomLigne,randomCol);
		  return c; 
	}
	
//	public void getVoisins(Coordonnee c) {
//		for (int i = 0; i < voisins.length; i++) {
//			if ((c.getLigne() - 1) > 0) 
//				voisins[i] = new Coordonnee(c.getLigne() - 1, c.getColonne());
//			else if ((c.getLigne() + 1) <= grille.getTaille())
//				voisins[i] = new Coordonnee(c.getLigne() + 1, c.getColonne());
//			else if ((c.getColonne() - 1) > 0)
//				voisins[i] = new Coordonnee(c.getLigne(), c.getColonne() - 1);
//			else if ((c.getColonne() + 1) <= grille.getTaille())
//				voisins[i] = new Coordonnee(c.getLigne(), c.getColonne() + 1);
//		}
//	}

	public static void main(String[] args) {
		int max = 10; //J
		
		for (int i = 0; i < max*max; i++) {
			int tailleIntervalle = 10 ;
			int randomLigne = (int)(Math.random() * tailleIntervalle) + 1;
			int randomCol = (int)(Math.random() * tailleIntervalle) + 1;
			System.out.println(randomLigne+" "+randomCol+'\n');
		}
	}

}