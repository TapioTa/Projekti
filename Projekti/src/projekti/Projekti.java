package projekti;

import java.util.Scanner;
import java.util.Random;

public class Projekti {
	public static void main(String[] args) {
		Scanner lukija = new Scanner(System.in);
	    Random random = new Random();
		String nimi = "";

		while (true) {
			System.out.println("Haluatko pelata? (kyll‰/ei)");
			String vastaus = lukija.nextLine();

			if (vastaus.equals("ei")) {
				System.out.println("Hyv‰sti!");
				break;

			}

			if (vastaus.equals("kyll‰")) {
				while (true) {

					System.out.println("Mik‰ on nimesi?");
					nimi = lukija.nextLine();

					if (nimi.isBlank()) {
						System.out.println("Nimesi on tyhj‰..." + "\n");
						continue;
					}
					System.out.println("Hei " + nimi
							+ "! Seuraavaksi esitet‰‰n 20 kysymyst‰. Vastaa kirjaimilla a, b tai c. Onnea peliin" + "\n");
					break;
				}
				break;
			}

			{
				System.out.println("En ymm‰rt‰nyt vastausta. Kokeile uudelleen" + "\n");
				continue;
			}

		}
		peli();

			
			while (true) {
				System.out.println("\n" + "Haluatko pelata uudelleen? (kyll‰/ei)");
				String vastaus = lukija.nextLine();

				if (vastaus.equals("ei")) {
					System.out.println("Hyv‰sti!");
					break;
				} else if (vastaus.equals("kyll‰")) {
					System.out.println("Peli alkaa taas " + nimi
							+ ". Seuraavaksi esitet‰‰n 20 kysymyst‰. Vastaa kirjaimilla a, b tai c. Onnea peliin" + "\n");
					peli();
					continue;
				} else {
					System.out.println("En ymm‰rt‰nyt vastausta. Kokeile uudelleen");
					continue;
				}
				
			}
		
		


	}

	public static int peli() {
		int kysytty = 0;
		int oikein = 0;

		while (true) {
			if (kysytty >= 20) {
				break;
			} else {
				kysymys();
			}
		}
		lopputekstit(oikein);
		;
		return oikein;
	}

	public static void kysymys() {

		laskuri();
	}



	public static int laskuri(int kysytty, int oikein, String tulos) {
		kysytty++;
		if (tulos.equals("Oikein")) {
			oikein++;
		}
		
		return kysytty + oikein;
	}

	public static void lopputekstit(int oikein) {
		if (oikein == 0) {
			System.out.println("Et saanut yht‰‰n oikeaa vastausta. Parempi onni ensikerralla");
		} else if (oikein == 20) {
			System.out.println("Mahtavaa! Sait kaikki oikein");
		} else {
			System.out.println("Oikein vastasit " + oikein + "/20");
		}
	}

}
