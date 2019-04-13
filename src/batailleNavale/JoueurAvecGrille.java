package batailleNavale;

public abstract class JoueurAvecGrille extends Joueur {
	private GrilleNavale grille;
	
	public JoueurAvecGrille(GrilleNavale g, String nom) {
		super(g.getTaille(), nom);
		grille = g;
	}
	
	public JoueurAvecGrille(GrilleNavale g) {
		super(g.getTaille());
	} 
	
	public int defendre(Coordonnee c) {
		if (grille.estALEau(c)) {
			grille.ajouteTirALEau(c);
//			System.out.println(grille.toString());
			return A_L_EAU;
		}
		grille.recoitTir(c);
		if (grille.perdu()) {
//			System.out.println(grille.toString());
			return GAMEOVER;
		}
		if (grille.estCoule(c)) {
//			System.out.println(grille.toString());
			return COULE;
		}
//		System.out.println(grille.toString());
		return TOUCHE;
	}
	
}