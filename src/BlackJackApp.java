import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class BlackJackApp {
	public static void main(String[] args) {
		PakjeKaarten pakje = new PakjeKaarten();
		pakje.schudden();
		pakje.toonPakje();
		Spel spel = new Spel();
		spel.startSpel();
		boolean stoppen = false;
		char keuze;
		int score = 0;
		Card[] hand = new Card[10]; // is 10genoeg? meestal al af bij meer dan 6 kaarten
		//geef eerste twee kaarten;
		hand[0] = pakje.geefKaart();
		hand[1] = pakje.geefKaart();
		System.out.println();
		System.out.println("Dit zijn uw eerste twee kaarten:");
		System.out.print(hand[0].kleur + " " + hand[0].naam + " | ");
		System.out.println(hand[1].kleur + " " + hand[1].naam);
		score = hand[0].waarde + hand[1].waarde;
		spel.toonScore(score);
		int i = 2;
		// loop totdat gebruiker stopt of stuk is'
		
		while (!stoppen) {
			keuze = spel.gebruikersKeuze();
			if (keuze == 'q') {
				stoppen = true;
				score = -1; // om te achterhalen dat speler is gestopt
			} else if (keuze == 'p') {
				stoppen = true;
			} else if (keuze == 'k') {
				hand[i] = pakje.geefKaart();
				score = spel.bepaalScore(keuze, score, hand[i].waarde);
				for (int x = 0; x < hand.length; x++) {
					if (hand[x] == null) {
						break;
					}
					System.out.print(hand[x].kleur + " " + hand[x].naam + " ");
				}
				spel.toonScore(score);
				i++;
				if (score > 20) {
					stoppen = true;
				}
			} else {
				System.out.println("De input is ongeldig, voer aub een geldige waarde in.");
			}
		}
		spel.endGame(score);
	}

}

class Spel {
	void startSpel() {
		System.out.println("Met de optie stopt q het spel");
		System.out.println("Met de optie stopt p past u en eindigt uw beurt.");
		System.out.println("Met de optie stopt k krijgt u (nog) een kaart");
	}

	char gebruikersKeuze() {
		System.out.println("maak uw keuze:");
		Scanner scanner = new Scanner(System.in);
		String invoer = scanner.nextLine();
		invoer = invoer.toLowerCase();
		char eersteLetter = invoer.charAt(0);
		return eersteLetter;
	}

	int bepaalScore(char keuze, int score, int waarde) {
		score = score + waarde;
		return score;
	}
	void toonScore(int score) {
		System.out.println();
		System.out.println("Uw huidige score is: " + score);
	}

	void endGame(int score) {
		if (score == -1) {
			System.out.println("U heeft het spel gestopt.");
		} else if (score == 21) {
			System.out.println("Gewonnen, u heeft " + score + " punten.");
		} else if (score > 21) {
			System.out.println("Helaas, u heeft " + score + " punten. Dit is te veel.");
		} else {
			System.out.println("Helaas, u heeft " + score + " punten. Dit is niet genoeg.");
		}
	}

}

class PakjeKaarten {
	Card[] pakjeOngeschud = new Card[52];
	Card[] pakjeGeschud = new Card[52];
	int laatstGedeeldeKaart = 0;

	PakjeKaarten() {
		for (int i = 0; i < 13; i++) {
			this.pakjeOngeschud[i] = new Card(i + 1, "Harten");
		}
		for (int i = 0; i < 13; i++) {
			this.pakjeOngeschud[i + 13] = new Card(i + 1, "Ruiten");
		}
		for (int i = 0; i < 13; i++) {
			this.pakjeOngeschud[i + 26] = new Card(i + 1, "Schoppen");
		}
		for (int i = 0; i < 13; i++) {
			this.pakjeOngeschud[i + 39] = new Card(i + 1, "Klaver");
		}
	}

	void schudden() {
		// extra array om bij te houden of waarde al is gebruikt
		int[] gebruikt = new int[52];
		for (int i = 0; i < gebruikt.length; i++) {
			gebruikt[i] = 0;
		}
		for (int i = 0; i < 52; i++) {
			Random kaartNummer = new Random();
			boolean nietGebruikt = true;
			while (nietGebruikt) {
				int nr = kaartNummer.nextInt(52);
				if (gebruikt[nr] == 0) {

					this.pakjeGeschud[i] = pakjeOngeschud[nr];
					gebruikt[nr] = 1;
					nietGebruikt = false;
				}
			}
		}
	}

	void toonPakje() {
		for (int i = 0; i < this.pakjeGeschud.length; i++) {
			System.out.print(this.pakjeGeschud[i].kleur + " ");
			System.out.println(this.pakjeGeschud[i].naam);
		}
	}

	Card geefKaart() {
		Card kaart = this.pakjeGeschud[laatstGedeeldeKaart];
		laatstGedeeldeKaart++;
		return kaart;

	}

}

class Card {
	String naam;
	int waarde;
	String kleur;
	boolean aas;

	Card(int nr, String kleur) {
		this.kleur = kleur;
		if (nr == 1) {
			this.naam = "Aas";
			this.waarde = 11;
			this.aas = true;
		} else if (nr == 11) {
			this.naam = "Boer";
			this.waarde = 10;
			this.aas = false;
		} else if (nr == 12) {
			this.naam = "Vrouw";
			this.waarde = 10;
			this.aas = false;
		} else if (nr == 13) {
			this.naam = "Heer";
			this.waarde = 10;
			this.aas = false;
		} else {
			this.naam = Integer.toString(nr);
			this.waarde = nr;
			this.aas = false;
		}
	}

	Card geefKaart() {
		return this;
	}
}
