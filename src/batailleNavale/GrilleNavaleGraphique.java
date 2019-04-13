package batailleNavale;

import java.awt.Color;

public class GrilleNavaleGraphique extends GrilleNavale {
	private GrilleGraphique grille;

	public GrilleNavaleGraphique(int taille) {
		super(taille, taille);
		grille = new GrilleGraphique(taille);
		grille.setClicActive(false);
	}

	public GrilleGraphique getGrilleGraphique() { return grille; } 

	public boolean ajouteNavire(Navire n) {
		if (super.ajouteNavire(n)) {
			grille.colorie(n.getDebut(), n.getFin(), Color.GREEN);
			return true;
		}
		return false;
	} 

	public boolean recoitTir(Coordonnee c) {
		if (super.recoitTir(c)) {
			grille.colorie(c, Color.RED);
			return true;
		}
		grille.colorie(c, Color.BLUE);
		return false;
	}
}