package batailleNavale;

public class Coordonnee implements Comparable<Coordonnee>   {

	private int ligne;
	private int colonne;

	public Coordonnee(int ligne, int colonne) {
		if (ligne < 0 || ligne > 25  || colonne < 0  || colonne > 25) {
			throw new  IndexOutOfBoundsException("Votre coordonnée doit être entre compris entre 1 et 26.");
		} 
		this.ligne = ligne; 
		this.colonne = colonne;
	}

	public Coordonnee(String s) {
		//Exception string null 
		if (s == null) {
			throw new NullPointerException("il faut initializer");
		}
		colonne = s.charAt(0)-65;
		if(colonne < 0  || colonne > 25) {
			throw new IndexOutOfBoundsException("Votre coordonnée doit être entre compris entre A et Z (mayuscules).");
		}
		if(s.length()==2) {
			String number = ""+s.charAt(1);
			ligne = Integer.parseInt(number)-1;
		}else if (s.length() == 3) {
			String number = ""+s.charAt(1)+s.charAt(2);
			ligne = Integer.parseInt(number)-1;
		}else if (s.length() <=1  || s.length()>3) {
			throw new StringIndexOutOfBoundsException("Coordonnee doit être composé d'une lettre mayuscule et un nombre de 1 ou 2 chiffres ");
		}
	}

	public String toString() {  
		String [] num = { "1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26"};
		String [] lettre = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		return lettre[colonne]+ num[ligne];

	}

	public int getLigne() {
		//Accesseur en lecture pour l'indice de ligne. 
		return ligne;
	}

	public int getColonne() {
		//Accesseur en lecture pour l'indice de colonne. 
		return colonne; 
	}

	public boolean equals(Object obj) {
		//Retourne true si et seulement si this est équivalent à obj.
		if (!(obj instanceof Coordonnee)) {
			return false;
		}
		return (ligne == ((Coordonnee) obj).ligne && colonne == ((Coordonnee) obj).colonne);
	}

	public boolean voisine(Coordonnee c) {
		//Retourne true si et seulement si this est une coordonnée voisine (verticalement ou
		//horizontalement) de c.
		try {
			Coordonnee v1 = new Coordonnee(ligne,colonne+1); //Coordonnee v1 = new Coordonnee(ligne - 1,colonne);
			Coordonnee v2 = new Coordonnee(ligne + 2, colonne+1);
			Coordonnee v3 = new Coordonnee(ligne+1, colonne);
			Coordonnee v4 = new Coordonnee(ligne+1, colonne + 2);
			return (c.equals(v1)||c.equals(v2)||c.equals(v3)||c.equals(v4));
		} catch(Exception e) {

		}
		try { 
			Coordonnee v2 = new Coordonnee(ligne + 2, colonne+1);
			Coordonnee v3 = new Coordonnee(ligne+1, colonne);
			Coordonnee v4 = new Coordonnee(ligne+1, colonne + 2);
			return (c.equals(v2)||c.equals(v3)||c.equals(v4));
		}catch(Exception e) {

		} try {
			Coordonnee v1 = new Coordonnee(ligne,colonne+1);
			Coordonnee v2 = new Coordonnee(ligne + 2, colonne+1);
			Coordonnee v3 = new Coordonnee(ligne+1, colonne);
			return (c.equals(v1)||c.equals(v2)||c.equals(v3));
		}catch(Exception e) {

		} try {
			Coordonnee v1 = new Coordonnee(ligne,colonne+1); //Coordonnee v1 = new Coordonnee(ligne - 1,colonne);
			Coordonnee v2 = new Coordonnee(ligne + 2, colonne+1);
			Coordonnee v4 = new Coordonnee(ligne+1, colonne + 2);
			return (c.equals(v1)||c.equals(v2)||c.equals(v4));
		}catch(Exception e) {

		}try {
			Coordonnee v1 = new Coordonnee(ligne,colonne+1); //Coordonnee v1 = new Coordonnee(ligne - 1,colonne);
			Coordonnee v3 = new Coordonnee(ligne+1, colonne);
			Coordonnee v4 = new Coordonnee(ligne+1, colonne + 2);
			return (c.equals(v1)||c.equals(v3)||c.equals(v4));
		}catch(Exception e) {

		}try {
			Coordonnee v2 = new Coordonnee(ligne + 2, colonne+1);
			Coordonnee v4 = new Coordonnee(ligne+1, colonne + 2);
			return (c.equals(v2)||c.equals(v4));
		}catch(Exception e) {
			Coordonnee v1 = new Coordonnee(ligne,colonne+1); 
			Coordonnee v3 = new Coordonnee(ligne+1, colonne);
			return (c.equals(v1)||c.equals(v3));
		} 
	}

	public int compareTo(Coordonnee c) {
		//Retourne le résultat de la comparaison de this et de c. Une coordonnée est considérée
		//inférieure à une autre, si elle se trouve sur une ligne inférieure ou si elle se trouve sur la
		//même ligne mais sur une colonne inférieure.

		if (c.getLigne()==ligne){
			return (colonne-c.getColonne());
		}
		return (ligne-c.getLigne());
	}

	public static void main(String[] args) {
		Coordonnee c1 = new Coordonnee(0,0);
		Coordonnee c2 = new Coordonnee(25,25);
		Coordonnee cc = new Coordonnee("Z26");
		Coordonnee cc2 = new Coordonnee("A1");
		System.out.println(c1.toString() + c1.getColonne());
		System.out.println(c2.toString() + c2.getLigne());
		System.out.println(cc.toString() +cc.getColonne());
		System.out.println(cc2.toString() +cc2.getColonne());
		System.out.println(c1.equals(c2)); //false
		System.out.println(c1.equals(cc2)); //true
		System.out.println(cc.equals(c2)); //true
		System.out.println(cc.equals(cc2)); //false



		Coordonnee c8 = new Coordonnee(4,4);
		Coordonnee c9 = new Coordonnee(3,5);
		System.out.println(c8.compareTo(c9)); // 1 
		System.out.println(c9.compareTo(c8)); // -1 
		Coordonnee c10 = new Coordonnee(4,5);
		System.out.println(c10.compareTo(c8)); //  1 
		System.out.println(c8.compareTo(c10)); // -1 




		//  Coordonnee c1 = new Coordonnee(-3,4);
		//  Coordonnee c1 = new Coordonnee(27,4);
		//  Coordonnee c1 = new Coordonnee(3,27);
		//  Coordonnee c1 = new Coordonnee(0,4);
		//  System.out.println(c1.toString());
		//  //System.out.println('C' + 0);
		//Coordonnee c2 = new Coordonnee("D13");
		//Coordonnee c2 = new Coordonnee("D5"); /// cambiar cero , cambiar minusculas 
	//System.out.println(c2.toString());

	//getters 
	//  System.out.println(c2.getLigne());
	//  System.out.println(c2.getColonne());

	//equals 
	//Coordonnee c3 = new Coordonnee(3,2);
	//Coordonnee c4 = new Coordonnee(2,2);
	//System.out.println("c3 con c4 "+c3.equals(c4));

	//Coordonnee c5 = new Coordonnee(3,2);
	//System.out.println("c3 con c5 "+c3.equals(c5));


	//  //voisins
	//  Coordonnee c6 = new Coordonnee(2,3);
	//  Coordonnee c7 = new Coordonnee(2,2);
	//  System.out.println("voisin "+c6.voisine(c7));
	//  
	//  //compare to
	//  Coordonnee c8 = new Coordonnee(4,4);
	//  Coordonnee c9 = new Coordonnee(3,5);
	//  System.out.println(c8.compareTo(c9)); // 1 
	//  System.out.println(c9.compareTo(c8)); // -1 
	//  Coordonnee c10 = new Coordonnee(4,5);
	//  System.out.println(c10.compareTo(c8)); //  1 
	//  System.out.println(c8.compareTo(c10)); // -1 
	}




}