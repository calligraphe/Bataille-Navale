package batailleNavale;

public class TestNavire {

	public static void TestConstructeur() {
		Coordonnee co1 = new Coordonnee(1,1);
		Navire n1 = new Navire(co1, 4, false);
		System.out.println("OK !");

		try {
			Coordonnee col2 = new Coordonnee(-1, -1);
			Navire n2 = new Navire(col2, 6, false);
			System.out.println("PAS OK!");
		}catch (IndexOutOfBoundsException e) {
			System.out.println("OK!");
		}
		try {
			Coordonnee co13 = new Coordonnee(1,1);
			Navire n3 = new Navire(co13, -8, false);
			System.out.println("PAS OK!");
		}catch (IllegalArgumentException e) {
			System.out.println("OK!");
		}
	}

	public static void testToString() {
		Coordonnee c1 = new Coordonnee("B2");
		Navire n3 = new Navire(c1, 8,false);
		System.out.println(n3.toString());
		if (n3.toString().equals("Navire(" + n3.getDebut() + ", 8, horizontal)"))
			System.out.println("OK");
		else
			System.out.println("PAS OK"); 
	}
	
	public static void testGetters() {
		Coordonnee c4 = new Coordonnee(0,0);
		Navire n4 = new Navire(c4, 4, false);
		if (n4.getDebut().toString().equals("A1")) {
			System.out.println("OK!");
		}
		else {
			System.out.println("PAS OK!");
		}
		if (n4.getFin().toString().equals("D1")) {
			System.out.println("OK!");
		}
		else {
			System.out.println("PAS OK!");
		}
	}
	
	public static void testContient() {
		Coordonnee c4 = new Coordonnee(0,0);
		Navire n4 = new Navire(c4, 4, false);
		if (n4.contient(c4) && n4.contient(new Coordonnee("A1")) && 
			n4.contient(new Coordonnee("D1")) && !n4.contient(new Coordonnee("E1")) && 
			!n4.contient(new Coordonnee("A2"))) {
			System.out.println("OK!");
		}
		else {
			System.out.println("PAS OK!");
		}
	}
	
	public static void testTouche() {
		Coordonnee c3 = new Coordonnee(2,2);
		Navire n2 = new Navire(c3, 4, true);
		Coordonnee c4 = new Coordonnee(1,1);
		Navire n3 = new Navire(c4, 2, true);
		Navire na1 = new Navire((new Coordonnee(3,0)), 3, false);
		Navire na2 = new Navire((new Coordonnee(1,3)), 2, true);
		if (n2.touche(n3) && n3.touche(n2) && !na2.touche(na1) && !na1.touche(na2))
			System.out.println("OK!");
		else

			System.out.println("PAS OK!");
	}
	
	public static void testEstToucheSansParam() {
		Coordonnee c1 = new Coordonnee(5,4);
		Navire n1 = new Navire(c1, 4, false);
		if (n1.recoitTir(c1) && n1.recoitTir(new Coordonnee(5,6)) &&
			n1.recoitTir(new Coordonnee(5,7)) && !n1.recoitTir(new Coordonnee(5,8))
			&& n1.estTouche()){

			System.out.println("OK!");
		}
		else {
			System.out.println("PAS OK!");
		}
	}

	public static void recoitTirEtCoule() {
		Coordonnee c4 = new Coordonnee(0,0);
		Navire n4 = new Navire(c4, 4, false);
		n4.recoitTir(new Coordonnee("A1"));
		if (n4.recoitTir(new Coordonnee("A1"))) {
			System.out.println("OK"); //true 
		}else {
			System.out.println("Pas Ok");
		}
		n4.estCoule();
		if(n4.estCoule()) {
			System.out.println("Pas Ok");
		}else {
			System.out.println("OK");
		}
		n4.recoitTir(new Coordonnee("B1"));
		if(n4.recoitTir(new Coordonnee("B1"))) {
			System.out.println("OK");
		}else {
			System.out.println("Pas Ok");
		}
		n4.estCoule();
		if (n4.estCoule()) {
			System.out.println("Ok");
		} else {
			System.out.println("Pas Ok");
		}
		n4.recoitTir(new Coordonnee("C1"));
		if(n4.recoitTir(new Coordonnee("C1"))) {
			System.out.println("Ok");
		} else {
			System.out.println("Pas Ok");
		}
		n4.estCoule();
		if (n4.estCoule()) {
			System.out.println("Ok");
		} else {
			System.out.println(" Pas Ok");
		}
		n4.recoitTir(new Coordonnee("D1"));
		if (n4.recoitTir(new Coordonnee("D1"))) {
			System.out.println("Ok");
		}else {
			System.out.println("Pas Ok");
		}
		n4.estCoule();
		if (n4.estCoule()) {
			System.out.println("Ok");
		}else {
			System.out.println("Pas Ok");
		}
	}
	
	public static void main(String[] args) {
		//TestConstructeur();
//		testToString();
//		testGetters();
//		testContient();
//		testTouche();
//		testEstToucheSansParam();
//		recoitTirEtCoule();
		

	}

}