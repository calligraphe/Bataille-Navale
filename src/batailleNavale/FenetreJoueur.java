package batailleNavale;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FenetreJoueur extends JFrame {

	private JPanel contentPane;
	private GrilleGraphique grilleTirs;
	private GrilleNavaleGraphique grilleDefense;
	
	public FenetreJoueur() {
		this("Nom du joueur", 10);
	}
	
	public FenetreJoueur(String nom, int taille) {
		this.setTitle(nom);
		this.setSize(taille, taille);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setLayout(new GridLayout(1, 2));
		setContentPane(contentPane);

		grilleTirs=new GrilleGraphique(taille); //taille
		grilleTirs.setBorder(BorderFactory.createTitledBorder("Grille de tir"));
		contentPane.add(getGrilleTirs());


		grilleDefense=new GrilleNavaleGraphique(taille); //taille
//		grilleDefense.placementAuto(new int[] {1,2});
		grilleDefense.getGrilleGraphique().setBorder(BorderFactory.createTitledBorder("Grille de defense"));
		contentPane.add(grilleDefense.getGrilleGraphique());

	}
	public GrilleGraphique getGrilleTirs() {
		return grilleTirs;

	}
	public GrilleNavaleGraphique getGrilleDefense() {
		return grilleDefense;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreJoueur frame = new FenetreJoueur("Nom du joueur", 12);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}