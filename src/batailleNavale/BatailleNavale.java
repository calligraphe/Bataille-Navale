package batailleNavale;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class BatailleNavale extends JFrame {

	private JPanel contentPane;
	private Joueur joueur1, joueur2;
	private int taille;
	private JTextField textField;
	private JTextField txtPlayer;
	private JTextField txtPlayer_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BatailleNavale frame = new BatailleNavale();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public BatailleNavale() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(550, 200, 350, 399);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		setTitle("Bataille Navale");

		/* Taille Grille */
		JPanel tailleGrille = new JPanel();

		JLabel lblTailleDeGrille = new JLabel("Taille de Grille: ");
		tailleGrille.add(lblTailleDeGrille);

		textField = new JTextField();
		textField.setText("10");
		tailleGrille.add(textField);
		textField.setColumns(10);
		/* FIN Taille Grille */

		/* Joueur 1*/
		JPanel joueur1 = new JPanel();
		joueur1.setBorder(BorderFactory.createTitledBorder("Joueur 1"));

		JLabel lblLabel = new JLabel("Nom:");

		txtPlayer = new JTextField();
		txtPlayer.setText("Player 1 ");
		txtPlayer.setColumns(10);

		JRadioButton rdbtnJoueurGraphique = new JRadioButton("Joueur graphique");
		buttonGroup.add(rdbtnJoueurGraphique);
		rdbtnJoueurGraphique.setSelected(true);

		JRadioButton rdbtnJoueurTexte = new JRadioButton("Joueur Texte");
		buttonGroup.add(rdbtnJoueurTexte);

		JRadioButton rdbtnJoueurAuto = new JRadioButton("Joueur Auto");
		buttonGroup.add(rdbtnJoueurAuto);
		/* FIN Joueur 1*/


		/*Joueur 2*/
		JPanel joueur2 = new JPanel();
		joueur2.setBorder(BorderFactory.createTitledBorder("Joueur 2"));

		JLabel label = new JLabel("Nom:");

		txtPlayer_1 = new JTextField();
		txtPlayer_1.setText("Player 2");
		txtPlayer_1.setColumns(10);

		JRadioButton rdbtnJoueurGraphique_1 = new JRadioButton("Joueur graphique");
		buttonGroup_1.add(rdbtnJoueurGraphique_1);
		rdbtnJoueurGraphique_1.setSelected(true);

		JRadioButton rdbtnJoueurTexte_1 = new JRadioButton("Joueur Texte");
		buttonGroup_1.add(rdbtnJoueurTexte_1);

		JRadioButton rdbtnJoueurAuto_1 = new JRadioButton("Joueur Auto");
		buttonGroup_1.add(rdbtnJoueurAuto_1);
		/* FIN Joueur 2*/


		/*Button */
		JPanel button = new JPanel();

		JButton btnLancerLaPartie = new JButton("Lancer la partie");
		/* ------------------------------------------------ */
		btnLancerLaPartie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BatailleNavale.this.dispose(); // ADDED!
				String joueur1Nom = txtPlayer.getText();
				String number = textField.getText();
				BatailleNavale.this.taille = Integer.parseInt(number);

				int[] taillesNavires = {2,3,1};

				String joueur2Nom = txtPlayer_1.getText();

				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							FenetreJoueur frame = new FenetreJoueur(joueur1Nom, taille);
							frame.getGrilleDefense().placementAuto(taillesNavires);
							FenetreJoueur frame1 = new FenetreJoueur(joueur2Nom, taille);
							frame1.getGrilleDefense().placementAuto(taillesNavires);

							if (rdbtnJoueurGraphique.isSelected()) {
								//frame.getGrilleDefense().placementAuto(taillesNavires);
								BatailleNavale.this.joueur1 = new JoueurGraphique(frame.getGrilleDefense(), frame.getGrilleTirs(), joueur2Nom);
								frame.setVisible(true);
							}else if(rdbtnJoueurTexte.isSelected()) {
								BatailleNavale.this.joueur1 = new JoueurTexte(frame.getGrilleDefense(), joueur1Nom);
							}else {
								BatailleNavale.this.joueur1 = new JoueurAuto(frame.getGrilleDefense(), joueur1Nom);
							}

							if (rdbtnJoueurGraphique_1.isSelected()) {
								//frame1.getGrilleDefense().placementAuto(taillesNavires);
								BatailleNavale.this.joueur2 = new JoueurGraphique(frame1.getGrilleDefense(), frame1.getGrilleTirs(), joueur1Nom);
								frame1.setVisible(true);
							}else if(rdbtnJoueurTexte_1.isSelected()) {
								BatailleNavale.this.joueur2 = new JoueurTexte(frame1.getGrilleDefense(), joueur2Nom);
							}else {
								BatailleNavale.this.joueur2 = new JoueurAuto(frame1.getGrilleDefense(), joueur2Nom);
							}
							frame1.getGrilleTirs().setClicActive(false);
							frame.setLocation(265, 250); // ADDED!
							frame1.setLocation(715, 250);// ADDED!

							demarrerPartie();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		/* ------------------------------------------------ */
		button.add(btnLancerLaPartie);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(button, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(joueur2, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
								.addGroup(Alignment.LEADING, gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(joueur1, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
										.addComponent(tailleGrille, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)))
						.addContainerGap())
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(tailleGrille, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(joueur1, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(joueur2, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
				);
		GroupLayout gl_joueur2 = new GroupLayout(joueur2);
		gl_joueur2.setHorizontalGroup(
				gl_joueur2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_joueur2.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_joueur2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_joueur2.createSequentialGroup()
										.addComponent(label)
										.addGap(5)
										.addComponent(txtPlayer_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(5)
										.addComponent(rdbtnJoueurGraphique_1))
								.addGroup(gl_joueur2.createSequentialGroup()
										.addGap(175)
										.addGroup(gl_joueur2.createParallelGroup(Alignment.LEADING)
												.addComponent(rdbtnJoueurTexte_1)
												.addComponent(rdbtnJoueurAuto_1))))
						.addContainerGap(66, Short.MAX_VALUE))
				);
		gl_joueur2.setVerticalGroup(
				gl_joueur2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_joueur2.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_joueur2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_joueur2.createSequentialGroup()
										.addGap(5)
										.addComponent(label))
								.addComponent(txtPlayer_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_joueur2.createSequentialGroup()
										.addGap(1)
										.addComponent(rdbtnJoueurGraphique_1)))
						.addGap(5)
						.addComponent(rdbtnJoueurAuto_1)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(rdbtnJoueurTexte_1)
						.addContainerGap(27, Short.MAX_VALUE))
				);
		joueur2.setLayout(gl_joueur2);
		GroupLayout gl_joueur1 = new GroupLayout(joueur1);
		gl_joueur1.setHorizontalGroup(
				gl_joueur1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_joueur1.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblLabel)
						.addGap(5)
						.addComponent(txtPlayer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(5)
						.addGroup(gl_joueur1.createParallelGroup(Alignment.LEADING)
								.addComponent(rdbtnJoueurTexte)
								.addComponent(rdbtnJoueurAuto)
								.addComponent(rdbtnJoueurGraphique))
						.addContainerGap(66, Short.MAX_VALUE))
				);
		gl_joueur1.setVerticalGroup(
				gl_joueur1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_joueur1.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_joueur1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_joueur1.createSequentialGroup()
										.addGap(5)
										.addComponent(lblLabel))
								.addComponent(txtPlayer, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_joueur1.createSequentialGroup()
										.addGap(1)
										.addComponent(rdbtnJoueurGraphique)))
						.addGap(5)
						.addComponent(rdbtnJoueurAuto)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(rdbtnJoueurTexte)
						.addContainerGap(26, Short.MAX_VALUE))
				);
		joueur1.setLayout(gl_joueur1);
		contentPane.setLayout(gl_contentPane);
		/*FIN button*/
	}

	private void demarrerPartie() {
		new Thread() {
			public void run() {

				//				System.out.println("Coucou2"+joueur1.getNom());
				//				System.out.println(joueur2.getNom());
				joueur1.jouerAvec(joueur2);
			}
		}.start();
	}

}