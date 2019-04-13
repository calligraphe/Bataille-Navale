package batailleNavale;

public abstract class Joueur {

	public final static int TOUCHE = 1;
	public final static int COULE = 2;
	public final static int A_L_EAU = 3;
	public final static int GAMEOVER = 4;
	private Joueur adversaire;
	private int tailleGrille;
	private String nom;

	public Joueur(int tailleGrille, String nom) {
		//permet d'obtenir un joueur de nom nom et jouant
		//sur une grille de taille taillegrille.
		
		//this.adversaire;
		this.tailleGrille=tailleGrille;
		if (nom == null) {
			throw new IllegalArgumentException ("you must initialise nom!") ;
		}
		this.nom=nom;
	}
	public Joueur(int tailleGrille) {
		//permet d'obtenir un joueur jouant sur 
		//une grille de taille taillegrille.
		//this.adversaire;
			this.tailleGrille=tailleGrille;
			this.nom="Player";
	}
	public int getTailleGrille() {
		//Accesseur en lecture pour tailleGrille.
		return tailleGrille;
	}
	public String getNom() {
		//Accesseur en lecture pour nom
		return nom;
	}
	public void jouerAvec(Joueur j) {
		if (j.adversaire != null) {
			throw new IllegalArgumentException("adversaire a deja un adversaire");
		}
//		if (adversaire != null) {
//			throw new IllegalArgumentException(" this a Deja un adversaire ");
//		}
		else {
			
			if(this.tailleGrille == j.tailleGrille) { 
			adversaire = j; 
			j.adversaire = this;
			}else {
				throw new IllegalArgumentException(" TailleGrille n'est pas égaux");
			}
		} 
		//plus, nombres bateaux egal 
		
		
		//joueur defenseur
		//Démarre une partie contre j. Avant de lancer le déroulement du jeu, 
		//il faut veiller à établir
		//le lien entre les 2 joueurs et bien entendu vérifier qu’il puisse être établi.
	deroulementJeu(this, j);
	}
	private static void deroulementJeu(Joueur attaquant, Joueur defenseur) {
		int res = 0;
		int countMove = 0;
		while (res != GAMEOVER) {
//			System.out.println(attaquant.nom);
			Coordonnee c = attaquant.choisirAttaque();
			res = defenseur.defendre(c);
			attaquant.retourAttaque(c, res);
			defenseur.retourDefense(c, res);
			Joueur x = attaquant;
			attaquant = defenseur;
			defenseur = x;
			countMove++;
			System.out.println(countMove);
		}
	}
	
	protected abstract void retourAttaque(Coordonnee c, int etat);
	//informer ce quei ce passe  afficher message 
	protected abstract void retourDefense(Coordonnee c, int etat);
	//
	public abstract Coordonnee choisirAttaque(); 
	//choix attaque lecture scanne texte 
	//joueur automatique decice 
	public abstract int defendre(Coordonnee c);
	//resultat  joueur l'attaque
	

	public static void main(String[] args) {
		int[] taillesNavires = {2,3,1};
		
//		GrilleNavale g = new GrilleNavale(4, taillesNavires);
//		GrilleNavale g2 = new GrilleNavale(4, taillesNavires);
//		JoueurTexte joueur1 = new JoueurTexte(g, "player1");
//		System.out.println(g.toString());
//		JoueurTexte joueur2 = new JoueurTexte(g2, "player2");
//		System.out.println(g2.toString());
//		deroulementJeu(joueur1, joueur2);
		
//		GrilleNavale g = new GrilleNavale(4, taillesNavires);
//		GrilleNavale g2 = new GrilleNavale(4, taillesNavires);
//		JoueurAuto joueur1 = new JoueurAuto(g, "player1");
//		System.out.println(g.toString());
//		JoueurAuto joueur2 = new JoueurAuto(g2, "player2");
//		System.out.println(g2.toString());
//		deroulementJeu(joueur1, joueur2);

	}

}
