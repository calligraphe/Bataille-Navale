package batailleNavale;

public class GrilleNavale {
	private Navire[] navires; //représentent les navires placés sur la grille ainsi que leur nombre
	private int nbNavires;
	private int taille;
	private Coordonnee[] tirsRecus;
	private int nbTirsRecus;

	public GrilleNavale(int taille, int[] taillesNavires) {
		//permet d'obtenir une grille navale de taille taille dans laquelle ont été placés
		//automatiquement taillesNavires.length navires dont les tailles sont données dans
		//taillesNavires
		this.taille = taille;
		nbNavires = 0; //placement auto 
		navires = new Navire[taillesNavires.length];
		tirsRecus = new Coordonnee[taille*taille];
		nbTirsRecus = 0;
		placementAuto(taillesNavires); 
	}

	public GrilleNavale(int taille, int nbNavires) {
		//permet d'obtenir une grille navale vide de taille taille pouvant accueillir jusqu'à
		//nbNavires.
		this.taille = taille;
		this.nbNavires = 0; 
		navires = new Navire[nbNavires];
		tirsRecus = new Coordonnee[taille*taille];
		nbTirsRecus = 0; 
	}

	public int getTaille() { return taille; }

	public String toString(){
		//Un '.' dénote une case de la grille libre n'ayant reçu aucun tir, un '#' une case occupée
		//par un navire, un 'O' une case libre ayant reçu un tir et un 'X' une partie touchée d'un
		//navire.

		StringBuffer sb = new StringBuffer();
		for(int k =0;k<this.taille;k++) { //primera línea letras
			sb.append("\t"+ (char)(65 + k) +"\t");
		}
		sb.append("\n");
		for (int i = 0; i < this.taille; i++) { //lineas
			sb.append(i+1); // numeros
			for(int j = 0; j< this.taille; j++ ) { //columnas						
				Coordonnee c = new Coordonnee(i,j);
				//System.out.println(c.toString());
				//System.out.println(c.toString()+"  touche est dans tirs "+(estTouche(c) && estDansTirsRecus(c)) );
				if(estTouche(c)&& estDansTirsRecus(c)) {
					//System.out.println("touche: "+c);
					sb.append("\tX\t");
				}
				else
					if(estALEau(c) && estDansTirsRecus(c)) {
						//System.out.println("leau: "+c);
						sb.append("\tO\t");
					}else {
						boolean taken = false;
						for(int n= 0; n < nbNavires; n++) {
							if(navires[n].contient(c)) {
								taken = true;
								break;
							}
						}
						if (taken) {
							sb.append("\t#\t");
						}

						else {
							sb.append("\t.\t");
						}
					}
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public boolean ajouteNavire(Navire n) {
		//Retourne true après avoir ajouté n à this si cet ajout est possible. 
		//L'ajout est impossible si n touche ou chevauche un navire déjà présent dans this,
		//ou encore si n dépasse les limites de this.
		if (nbNavires == navires.length) {
			return false;
		}
		for (int i = 0; i < nbNavires; i++) {
			if(navires[i] == n) {
				return false;
			}
			if (n.touche(navires[i]) || n.chevauche(navires[i])) {
				return false;
			}
		}
		navires[nbNavires] = n;
		nbNavires ++;
		return true;
	}
	public void placementAuto(int[] taillesNavires) {
		//Place automatiquement et aléatoirement taillesNavires.length navires dont les
		//tailles sont données dans taillesNavire.
		int i = 0;
		while(i < taillesNavires.length) {
			int tailleIntervalle = taille ; //TODO:
			int randomLigne = (int)(Math.random() * tailleIntervalle);
			int randomCol = (int)(Math.random() * tailleIntervalle);
			Coordonnee c = new Coordonnee(randomLigne,randomCol);
			boolean b = Math.random() < 0.5;
			Navire n = new Navire(c,taillesNavires[i],b);
			Coordonnee fin = n.getFin();
			if (estDansGrille(fin)) { // está dentro de la grid 
				if(ajouteNavire(n)) {
					i++; 
				}
			} 
		}
	}

	private boolean estDansGrille(Coordonnee c) {
		//Retourne true si et seulement si c est dans this.
		return c.getColonne()< taille && c.getLigne() <  taille; 
	}

	private boolean estDansTirsRecus(Coordonnee c) {
		//Retourne true si et seulement si c correspond à un tir reçu par this.
		for (int i = 0; i < nbTirsRecus; i++) {
			if(tirsRecus[i].equals(c)) { //ya existe dentro
				return true;
			}
		}
		return false;
	}

	private boolean ajouteDansTirsRecus(Coordonnee c) {
		//Ajoute c aux tirs reçus de this si nécessaire. Retourne true si et seulement si this est
		//modifié.
		if (!estDansTirsRecus(c)) {
			tirsRecus[nbTirsRecus] = c;
			nbTirsRecus ++;
			for(int i = 0; i < nbNavires; i++) {
				if (navires[i].estTouche(c)) {
					navires[i].recoitTir(c);
				}
			}
			return true;
		}
		return false;
	}

	public boolean recoitTir(Coordonnee c) {
		//Ajoute c aux tirs reçus de this si nécessaire. Retourne true si et seulement si c ne
		//correspondait pas déjà à un tir reçu et a permis de toucher un navire de this.
		if (nbTirsRecus >= tirsRecus.length) { //ya no hay mas tiros posibles
			return false;
		}
		if (estDansTirsRecus(c) || !estTouche(c)) {
			return false;
		}

		ajouteDansTirsRecus(c);
		return true;
	}

	public void ajouteTirALEau(Coordonnee c) { ajouteDansTirsRecus(c); }

	public boolean estTouche(Coordonnee c) {
		//Retourne true si et seulement si un des navires présents dans this a été touché en c.
		for(int i = 0; i < nbNavires; i++) {
			//				  System.out.println(navires[i].toString() + " "+navires[i].estTouche(c));
			if (navires[i].estTouche(c)) {
				// System.out.println(navires[i].toString() + " "+navires[i].estTouche(c));
				return true;
			}
		}
		return false;
	}

	public boolean estALEau(Coordonnee c) {
		//Retourne true si et seulement si c correspond à un tir reçu dans l'eau par this.
		//			  if (!estTouche(c)) {
		//				  ajouteDansTirsRecus(c);
		//			  }
		return  !estTouche(c); 


	}
	public boolean estCoule(Coordonnee c) {
		//Retourne true si et seulement si un des navires présents dans this a été touché en c et
		//est coulé.
		if (estTouche(c)) {
			for(int i = 0; i < navires.length; i++) {
				//					  System.out.println(navires[i].toString() + " est touche  "+navires[i].estTouche(c)+ "est coule"+navires[i].estCoule());
				if(navires[i] == null){
					return false;
				}
				if (navires[i].estTouche(c) && navires[i].estCoule()) {
					return true;
				}
			}
		}
		//nbNavires --; //esto es valido no? ?? 
		return false;
	}
	public boolean perdu() {
		//Retourne true si et seulement si tous les navires de this ont été coulés. 
		for(int i = 0; i < nbNavires; i++) {
			if (!navires[i].estCoule()) {
				return false;
			}
		}
		return true;
	}



	public static void main(String[] args) {
		GrilleNavale gn1 = new GrilleNavale(6,3);
		System.out.println(gn1.toString());
		Navire n1 = new Navire (new Coordonnee("A2"), 2,false);
		Navire n2 = new Navire (new Coordonnee("D2"), 4,true);
		System.out.println(n1.toString());

		gn1.ajouteNavire(n1);
		System.out.println(gn1.toString());
		gn1.ajouteNavire(n2);
		System.out.println(n2.toString());
		System.out.println(gn1.toString());
		//		int [] tailleNavires = {1,2,1};
		//		GrilleNavale gn1 = new GrilleNavale(5, tailleNavires); 
		//		System.out.println(gn1.toString());
		//		System.out.println("Recoit Tir A1: "+gn1.recoitTir(new Coordonnee("A1")));
		//		System.out.println("Est touche A1: "+gn1.estTouche(new Coordonnee("A1")));
		//		System.out.println("Est a leau A1: "+gn1.estALEau(new Coordonnee("A1")));
		//		gn1.ajouteDansTirsRecus(new Coordonnee("A1"));
		//		System.out.println("Est Dans Tirs Recus A1: " + gn1.estDansTirsRecus(new Coordonnee("A1")));
		//		System.out.println("Recoit Tir A2: "+gn1.recoitTir(new Coordonnee("A2")));
		//		System.out.println("Est touche A2: "+gn1.estTouche(new Coordonnee("A2")));
		//		System.out.println("Est a leau A2: "+gn1.estALEau(new Coordonnee("A2")));
		//		gn1.ajouteDansTirsRecus(new Coordonnee("A2"));
		//		System.out.println("Est Dans Tirs Recus A2: " + gn1.estDansTirsRecus(new Coordonnee("A2")));
		//		System.out.println("Recoit Tir A3: "+gn1.recoitTir(new Coordonnee("A3")));
		//		System.out.println("Est touche A3: "+gn1.estTouche(new Coordonnee("A3")));
		//		System.out.println("Est a leau A3: "+gn1.estALEau(new Coordonnee("A3")));
		//		gn1.ajouteDansTirsRecus(new Coordonnee("A3"));
		//		System.out.println("Est Dans Tirs Recus A3: " + gn1.estDansTirsRecus(new Coordonnee("A3")));
		//		System.out.println("Recoit Tir A4: "+gn1.recoitTir(new Coordonnee("A4")));
		//		System.out.println("Est touche A4: "+gn1.estTouche(new Coordonnee("A4")));
		//		System.out.println("Est a leau A4: "+gn1.estALEau(new Coordonnee("A4")));
		//		gn1.ajouteDansTirsRecus(new Coordonnee("A4"));
		//		System.out.println("Est Dans Tirs Recus A4: " + gn1.estDansTirsRecus(new Coordonnee("A4")));
		//		System.out.println("Recoit Tir A5: "+gn1.recoitTir(new Coordonnee("A5")));
		//		System.out.println("Est touche A5: "+gn1.estTouche(new Coordonnee("A5")));
		//		System.out.println("Est a leau A5: "+gn1.estALEau(new Coordonnee("A5")));
		//		gn1.ajouteDansTirsRecus(new Coordonnee("A5"));
		//		System.out.println("Est Dans Tirs Recus A5: " + gn1.estDansTirsRecus(new Coordonnee("A5")));
		//		System.out.println(gn1.toString());


	}

}
