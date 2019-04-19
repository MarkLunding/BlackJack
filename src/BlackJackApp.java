import java.util.ArrayList;
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

		ArrayList<Card> hand = new ArrayList<>();
		ArrayList<Card> bank = new ArrayList<>();
		// geef eerste twee kaarten (om en om);
		hand.add(pakje.geefKaart());
		bank.add(pakje.geefKaart());
		hand.add(pakje.geefKaart());
		bank.add(pakje.geefKaart());
		int bankScore = spel.toonEersteTwee(bank, "bank");
		spel.toonScore(bankScore, "de bank");
		int spelerScore = spel.toonEersteTwee(hand, "speler");
		spel.toonScore(spelerScore, "de speler");

		int i = 2;
		// loop totdat gebruiker stopt of stuk is'
		outer: while (!stoppen && spelerScore < 21) { // als 21 bij eerste 2 kaarten, dan niet de loop in.
			keuze = spel.gebruikersKeuze();
			if (keuze == 'q') {
				stoppen = true;
				spelerScore = -1; // om te achterhalen dat speler is gestopt
			} else if (keuze == 'p') {
				stoppen = true;
			} else if (keuze == 'k') {
				hand.add(pakje.geefKaart());
				spelerScore = spel.bepaalScore(spelerScore, hand.get(i).waarde);
				System.out.println("==========");
				for (int x = 0; x < hand.size(); x++) {
					System.out.print(hand.get(x).symbool + " " + hand.get(x).naam + " | ");
				}
				i++;
				if (spelerScore > 21) {
					for (Card kaart : hand) {
						if (kaart.aas) {
							spelerScore -= 10;
							kaart.aas = false;
							spel.toonScore(spelerScore, "de speler");
							continue outer;
						}
					}
					stoppen = true;
				}
				System.out.println();
				spel.toonScore(spelerScore, "de speler");
			} else {
				System.out.println("De input is ongeldig, voer aub een geldige waarde in.");
			}
		}
		// en nu de bank, die stopt als hij 17 of hoger heeft
		int j = 2;
		while (bankScore < 17) {
			bank.add(pakje.geefKaart());
			bankScore = spel.bepaalScore(bankScore, bank.get(j).waarde);
			System.out.println("==========");
			for (int x = 0; x < bank.size(); x++) {
				System.out.print(bank.get(x).symbool + " " + bank.get(x).naam + " | ");
			}
			System.out.println();
			spel.toonScore(bankScore, "de bank");
			j++;
		}

		spel.endGame(spelerScore, bankScore);
	}

}

class Spel {
	void startSpel() {
		System.out.println("Met de optie stopt q het spel");
		System.out.println("Met de optie stopt p past u en eindigt uw beurt.");
		System.out.println("Met de optie stopt k krijgt u (nog) een kaart");
	}

	int toonEersteTwee(ArrayList<Card> cards, String speler) {
		System.out.println("==========");
		System.out.println("Dit zijn de kaarten van: " + speler);
		System.out.print(cards.get(0).symbool + " " + cards.get(0).naam + " | ");
		System.out.println(cards.get(1).symbool + " " + cards.get(1).naam);

		return cards.get(0).waarde + cards.get(1).waarde;
	}

	char gebruikersKeuze() {
		System.out.println("maak uw keuze:");
		Scanner scanner = new Scanner(System.in);
		String invoer = scanner.nextLine();
		invoer = invoer.toLowerCase();
		char eersteLetter = invoer.charAt(0);
		return eersteLetter;
	}

	int bepaalScore(int score, int waarde) {
		score = score + waarde;
		return score;
	}

	void toonScore(int score, String speler) {
		System.out.println("De huidige score van " + speler + " is: " + score);
		System.out.println("==========");
		System.out.println();
	}

	void endGame(int spelerScore, int bankScore) {
		if (spelerScore == -1) {
			System.out.println("U heeft het spel gestopt. De bank wint.");
		} else if (spelerScore > 21) {
			System.out.println("Helaas, u heeft " + spelerScore + " punten. Dit is te veel.");
		} else if (bankScore > 21) {
			System.out.println("De bank is stuk en u niet. U heeft gewonnen");
		} else if (bankScore == 21) {
			System.out.println("De bank heeft 21 en wint.");
		} else if (spelerScore == 21) {
			System.out.println("Gewonnen, u heeft " + spelerScore + " punten.");
		} else if (spelerScore > bankScore) {
			System.out.println("Gewonnen, uw score: " + spelerScore + " is beter dan die van de bank: " + bankScore);
		} else {
			System.out.println("Verloren, uw score: " + spelerScore + " is minder dan die van de bank: " + bankScore);
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
		// idee is simpel, wissel gewoon over de hele array met wikkekeurige
		// andere positie uit de array en je pakje is geschud.
		Random kaartNummer = new Random();
		for (int i = 0; i < pakjeOngeschud.length; i++) {
			int nr = kaartNummer.nextInt(52);
			Card tempCard = pakjeOngeschud[i];
			pakjeOngeschud[i] = pakjeOngeschud[nr];
			pakjeOngeschud[nr] = tempCard;
		}
		this.pakjeGeschud = pakjeOngeschud;
	}

	void toonPakje() {
		System.out.println("Het pakje ziet er zo uit:");
		for (int i = 0; i < this.pakjeGeschud.length; i++) {
			System.out.print(this.pakjeGeschud[i].symbool + " ");
			System.out.print(this.pakjeGeschud[i].naam + " | ");

		}
		System.out.println("==========");
		System.out.println();
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
	char symbool;
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
		switch (kleur) {
		case "Harten":
			this.symbool = (char) '\u2661';
			break;
		case "Ruiten":
			this.symbool = (char) '\u2662';
			break;
		case "Schoppen":
			this.symbool = (char) '\u2660';
			break;
		case "Klaver":
			this.symbool = (char) '\u2663';
			break;
		}
	}

	Card geefKaart() {
		return this;
	}
}