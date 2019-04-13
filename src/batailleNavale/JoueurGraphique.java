package batailleNavale;

import java.awt.Color;
import javax.swing.JOptionPane;

public class JoueurGraphique extends JoueurAvecGrille {
	private GrilleGraphique grilleTirs;

	public JoueurGraphique(GrilleNavaleGraphique grilleDefense, GrilleGraphique grilleTirs, String nom) {
		//permet d'obtenir un joueur graphique de nom nom qui effectue des tirs en cliquant sur
		//grilleTirs et dont la flotte est placée sur grilleDefense.
		super(grilleDefense, nom);
		this.grilleTirs = grilleTirs;
//		FenetreJoueur fr = new FenetreJoueur(nom, grilleDefense.getTaille()); // we shoul maybe ask?   
	}
	public JoueurGraphique(GrilleNavaleGraphique grilleDefense, GrilleGraphique grilleTirs) {
		super(grilleDefense);
		this.grilleTirs = grilleTirs;
//		FenetreJoueur fr = new FenetreJoueur(); //same
	}
	public Coordonnee choisirAttaque() {
		//Consiste à récupérer la coordonnée choisie depuis grilleTirs.
		return grilleTirs.getCoordonneeSelectionnee();
	}
	protected void retourDefense(Coordonnee c, int etat) {
		//Affichage d'un JOptionPane lorsque le tir a touché ou coulé un navire, ou lorsque la partie
		//est perdue.
		if(etat == 1) {
			JOptionPane.showMessageDialog(grilleTirs, "votre navire a été touché en "+c+".");
		}
		if(etat == 2) {
			JOptionPane.showMessageDialog(grilleTirs, "votre navire a été coulé.");
		}
		if(etat == 3) {
			JOptionPane.showMessageDialog(grilleTirs, "le tir " +c +" est dans l'eau.");
		}
		if(etat == 4) {
			JOptionPane.showMessageDialog(grilleTirs, "vous avez perdu!");
		}

	}
	protected void retourAttaque(Coordonnee c, int etat) {
		Color couleur = etat == A_L_EAU ? Color.BLUE : Color.RED;
		grilleTirs.colorie(c, couleur);
		switch (etat) {
		case TOUCHE:
			JOptionPane.showMessageDialog(grilleTirs, "Vous avez touché un navire en " + c);
			break;
		case COULE:
			JOptionPane.showMessageDialog(grilleTirs, "Vous avez coulé un navire en " + c);
			break;
		case GAMEOVER:
			JOptionPane.showMessageDialog(grilleTirs, "Vous avez gagné!!!");
		}
	}
	public static void main(String[] args) {

	}
}