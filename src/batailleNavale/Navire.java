package batailleNavale;

public class Navire {
	private Coordonnee debut;
	private Coordonnee fin;
	private Coordonnee[] partiesTouchees; 
	private int nbTouchees;
	private int longueur;
	private boolean estVertical;

	public Navire(Coordonnee debut, int longueur, boolean estVertical) {
		if (longueur <= 0)
			throw new IllegalArgumentException("longueur <= 0");
		if (debut == null)
			throw new NullPointerException("debut est null!");
		
		this.debut = debut;
		this.longueur = longueur;
		this.estVertical = estVertical;
		this.nbTouchees = 0;
		this.partiesTouchees = new Coordonnee[longueur];

		try {
			if (estVertical)
				this.fin = new Coordonnee(debut.getLigne() + longueur - 1, 
											debut.getColonne());
			else
				this.fin = new Coordonnee(debut.getLigne(),
											debut.getColonne() + longueur - 1);
		} catch(IndexOutOfBoundsException e) {
			e.getMessage();
		}
	}
	
	public String toString() {
		return "Navire(" + debut + ", " + longueur + ", " + (estVertical ? "vertical" : "horizontal") + ")";
	}
	
	public Coordonnee getDebut() { return debut; }
	public Coordonnee getFin() 	 { return fin; }
	
	public boolean contient(Coordonnee c) { 
		if (c == null)
			throw new NullPointerException("Coordonnee donnee est null!");
		return debut.getLigne() <= c.getLigne() && c.getLigne() <= fin.getLigne() &&
				debut.getColonne() <= c.getColonne() && c.getColonne() <= fin.getColonne();
	} 
	
	public boolean touche(Navire n) { 
		//TODO: check for: touche mais ne chevauche pas
		try {
			if (n == null)
				throw new NullPointerException("Navire donnee est null!");
			if (chevauche(n))
				return false;
			if (estVertical) {
				if ((debut.getLigne() - 1) >= 0 &&
						(n.contient(new Coordonnee(debut.getLigne() - 1, debut.getColonne()))))
					return true;

				if ((fin.getLigne() + 1) < 26 &&
						n.contient(new Coordonnee(fin.getLigne() + 1, fin.getColonne())))
					return true;

				boolean max = (debut.getColonne() + 1) < 26;
				boolean min = (debut.getColonne() - 1) >= 0;
				for (int i = 0; i < longueur; i++) {
					if (max && n.contient(new Coordonnee(debut.getLigne() + i, debut.getColonne() + 1)))
						return true;
					if (min && n.contient(new Coordonnee(debut.getLigne() + i, debut.getColonne() - 1)))
						return true;
				}
			}
			else {
				if ((debut.getColonne() - 1) >= 0 && 
						n.contient(new Coordonnee(debut.getLigne(), debut.getColonne() - 1)))
					return true;

				if ((fin.getColonne() + 1) < 26 &&
						n.contient(new Coordonnee(fin.getLigne(), fin.getColonne() + 1)))
					return true;
				boolean max = (debut.getLigne() + 1) < 26;
				boolean min = (debut.getLigne() - 1) >= 0;
				for (int i = 0; i < longueur; i++) {
					if (max && n.contient(new Coordonnee(debut.getLigne() + 1, debut.getColonne() + i)))
						return true;
					if (min && n.contient(new Coordonnee(debut.getLigne() - 1, debut.getColonne() + i)))
						return true;
				}
			}
		} catch (IndexOutOfBoundsException e) {
			e.getMessage();
		} catch (IllegalArgumentException e) {
			e.getMessage();
		}
		return false;
	} 
	
	public boolean chevauche(Navire n) { 
		if (n == null)
			throw new NullPointerException("Navire donnee est null!");
		if (estVertical)
			for (int i = 0; i < longueur; i++) {
				Coordonnee tmp = new Coordonnee(debut.getLigne() + i, debut.getColonne());
				if (n.contient(tmp))
					return true;
			}
		else
			for (int i = 0; i < longueur; i++) {
				Coordonnee tmp = new Coordonnee(debut.getLigne(), debut.getColonne() + i);
				if (n.contient(tmp))
					return true;
			}
		return false;
	} 
	
	public boolean recoitTir(Coordonnee c) { 
		if (c == null)
			throw new NullPointerException("Coordonnee donnee est null!");
		if (contient(c)) {
			etiq : for (int i = 0; i < longueur; i++) {
				if (partiesTouchees[i] == null) {
					partiesTouchees[i] = c;
					nbTouchees++;
					break etiq;
				}
			}
			return true;
		}
		else
			return false;
	} 
	
	public boolean estTouche(Coordonnee c) { 
		if (c == null)
			throw new NullPointerException("Coordonnee donnee est null!");
		return chevauche(new Navire(c, 1, true));
	} 
	
	public boolean estTouche() { return nbTouchees > 0; }
	public boolean estCoule() { return nbTouchees == longueur; }

	public static void main(String[] args) {

//		Coordonnee c4 = new Coordonnee(0,0);
//		Navire n4 = new Navire(c4, 4, false);
//		System.out.println(n4.getDebut());
//		System.out.println(n4.getFin());
		
//		System.out.println(n4.toString());
//		
//		System.out.println(n4.contient(c4)); // true
//		System.out.println(n4.contient(new Coordonnee("A1"))); // true
//		System.out.println(n4.contient(new Coordonnee("D1"))); // true
//		System.out.println(n4.contient(new Coordonnee("E1"))); // false
//		System.out.println(n4.contient(new Coordonnee("A2"))); // false
		
//		Coordonnee c3 = new Coordonnee(2,2);
//		Navire n2 = new Navire(c3, 4, true);
//		System.out.println(n2.toString());
//		Coordonnee c4 = new Coordonnee(1,1);
//		Navire n3 = new Navire(c4, 2, true);
//		System.out.println(n3.toString());
//		System.out.println(n2.touche(n3)); // true 
//		System.out.println(n3.touche(n2)); // true
		
//		Navire na1 = new Navire((new Coordonnee(3,0)), 3, false);
//		Navire na2 = new Navire((new Coordonnee(1,3)), 2, true);
//		System.out.println(na2.touche(na1)); // false 
//		System.out.println(na1.touche(na2)); // false
		
//		Coordonnee c1 = new Coordonnee(5,4);
//		Navire n1 = new Navire(c1, 4, false);
//		System.out.println(n1.recoitTir(c1)); // bINF
//		System.out.println(n1.recoitTir(new Coordonnee(5,6))); // mid
//		System.out.println(n1.recoitTir(new Coordonnee(5,7))); // bSUP
//		System.out.println(n1.recoitTir(new Coordonnee(5,8))); // bSUR +1
		
//		System.out.println(n1.estTouche());

//		Coordonnee c2 = new Coordonnee(4,4);
//		System.out.println(n1.estTouche(c2));
//		System.out.println(n1.estTouche(c1));

		/* estCoule */
		
//		Coordonnee c1 = new Coordonnee(5,4);
//		Navire n1 = new Navire(c1, 4, true);
//		n1.recoitTir(c1);
//		n1.recoitTir(new Coordonnee(6,4));
//		n1.recoitTir(new Coordonnee(7,4));
//		n1.recoitTir(new Coordonnee(8,4));
//		System.out.println(n1.estCoule());
		
//		Coordonnee c1 = new Coordonnee(5,4);
//		Navire n1 = new Navire(c1, 4, false);
//		n1.recoitTir(c1);
//		n1.recoitTir(new Coordonnee(5,5));
//		n1.recoitTir(new Coordonnee(5,6));
//		n1.recoitTir(new Coordonnee(5,7));
//		System.out.println(n1.estCoule());

//		Coordonnee c1 = new Coordonnee(0,0);
//		Navire n1 = new Navire(c1, 4, false);
//		n1.recoitTir(c1);
//		n1.recoitTir(new Coordonnee(0,1));
//		n1.recoitTir(new Coordonnee(0,2));
//		n1.recoitTir(new Coordonnee(0,3));
//		System.out.println(n1.estCoule());
		
//		Coordonnee c1 = new Coordonnee(0,0);
//		Navire n1 = new Navire(c1, 4, true);
//		n1.recoitTir(c1);
//		n1.recoitTir(new Coordonnee(1,0));
//		n1.recoitTir(new Coordonnee(2,0));
//		n1.recoitTir(new Coordonnee(3,0));
//		System.out.println(n1.estCoule());
		
//		Coordonnee c1 = new Coordonnee(22,0);
//		Navire n1 = new Navire(c1, 4, true);
//		n1.recoitTir(c1);
//		n1.recoitTir(new Coordonnee(23,0));
//		n1.recoitTir(new Coordonnee(24,0));
//		n1.recoitTir(new Coordonnee(25,0));
//		System.out.println(n1.estCoule());
		
//		Coordonnee c1 = new Coordonnee(25,0);
//		Navire n1 = new Navire(c1, 4, false);
//		n1.recoitTir(c1);
//		n1.recoitTir(new Coordonnee(25,1));
//		n1.recoitTir(new Coordonnee(25,2));
//		n1.recoitTir(new Coordonnee(25,3));
//		System.out.println(n1.estCoule());

//		Coordonnee c1 = new Coordonnee(22,25);
//		Navire n1 = new Navire(c1, 4, true);
//		n1.recoitTir(c1);
//		n1.recoitTir(new Coordonnee(23,25));
//		n1.recoitTir(new Coordonnee(24,25));
//		n1.recoitTir(new Coordonnee(25,25));
//		System.out.println(n1.estCoule());
		
//		Coordonnee c1 = new Coordonnee(25,22);
//		Navire n1 = new Navire(c1, 4, false);
//		n1.recoitTir(c1);
//		n1.recoitTir(new Coordonnee(25,23));
//		n1.recoitTir(new Coordonnee(25,24));
//		n1.recoitTir(new Coordonnee(25,25));
//		System.out.println(n1.estCoule());
		
		/* fin estCoule */
		

//		Navire n4 = new Navire(new Coordonnee(3,2), 4, true);
//		System.out.println(n2.chevauche(n4));
//		System.out.println(n2.touche(n4));
		
//		Coordonnee c5 = new Coordonnee(2,2);
//		Navire n5 = new Navire(c5, 4, false);
//		System.out.println(n5.estTouche(new Coordonnee(2,4)));
//		Coordonnee c6 = new Coordonnee(1,1);
//		Navire n6 = new Navire(c4, 2, false);
//		System.out.println(n5.recoitTir(new Coordonnee("D2")));
	}

}