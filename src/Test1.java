class Test1 {
	public static void main(String args[]) {
		SpelerHand Speler1 = new SpelerHand();
		Kaart kaart1 = Speler1.pakken("hearts");
		System.out.println(Speler1);
		System.out.println(kaart1.naam);
		System.out.println(kaart1.waarde);
		System.out.println(kaart1.kleur);
		
		SpelerHand Speler2 = new SpelerHand();
		Speler2.pakken("diamonds");
		System.out.println(Speler2);
	}
}

class SpelerHand {
	Kaart pakken(String color) {
		Kaart kaart = new Kaart();
		if (color == "hearts") {
			kaart.naam = "Harten Aas";
			kaart.waarde = 11;
			kaart.kleur = "Harten";
			return kaart;
		} else {
			return null;
		}

	}

}

class Kaart {
	String naam;
	int waarde;
	String kleur;
	public String toString() {
		return "Kaart" + waarde;
	}

}