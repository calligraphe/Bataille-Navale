package batailleNavale;
import java.util.Scanner;

public class JoueurTexte extends JoueurAvecGrille{
	private GrilleNavale grille; // added

	private Scanner sc;
	/*
	 * 3.2 Définir les méthodes de JoueurTexte.
Les méthodes retourAttaque(Coordonne c, int etat), et
retourDefense(Coordonne c, int etat) doivent réaliser l'affichage à la console des
étapes de jeu. La méthode choixAttaque() doit recueillir au clavier la saisie de la coordonnée à
attaquer.
*/		
	public JoueurTexte(GrilleNavale g, String nom) {
		super(g, nom);
		grille = g; // added
	}
	public JoueurTexte(GrilleNavale g) {
		super(g);
		grille = g; // added
	}
	
	//doivent réaliser l'affichage à la console de étapes de jeu.
	protected void retourAttaque(Coordonnee c, int etat) {
		/*Cette méthode est invoquée sur le joueur attaquant à la fin d’un tour de jeu. c est la
coordonnée à laquelle le tir a eu lieu et etat le résultat de l'attaque. etat ne peut être que
TOUCHE, COULE, A_L_EAU, ou GAMEOVER. */
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
		/*Cette méthode est invoquée sur le joueur défenseur à la fin d’un tour de jeu. c est la
coordonnée à laquelle le tir a eu lieu et etat le résultat de ce tir. etat ne peut être que
TOUCHE, COULE, A_L_EAU, ou GAMEOVER. */
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
		// doit recueillir au clavier la saisie de la coordonnée  attaquer.
		Scanner sc = new Scanner(System.in);
		System.out.println(this.getNom());
		System.out.println(" entrez Coordonnee a attaquer");
		String s = sc.next();
		Coordonnee c = new Coordonnee(s);
		return c;
	}
	
	@Override
	public Coordonnee choisirAttaque() {
		return choixAttaque();
	}

	public static void main(String[] args) {
		
	}

}
