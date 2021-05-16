package projekti;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;

public class Peli {
	public static void main(String[] args) {
		Scanner lukija = new Scanner(System.in);
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
							+ "! Seuraavaksi esitet‰‰n 20 kysymyst‰. Vastaa kirjaimilla a, b tai c. Onnea peliin"
							+ "\n");
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
			String vastauss = "kyll‰";

			if (vastauss.equals("ei")) {
				System.out.println("Hyv‰sti!");
				break;
			} else if (vastauss.equals("kyll‰")) {
				System.out.println("Peli alkaa taas " + nimi + ". Seuraavaksi esitet‰‰n 20 kysymyst‰. Vastaa kirjaimilla a, b tai c. Onnea peliin" + "\n");
				peli();
			} else {
				System.out.println("En ymm‰rt‰nyt vastausta. Kokeile uudelleen");
				continue;
			}

		}

	}

	public static void peli() {
		Random random = new Random();
		Scanner lukija = new Scanner(System.in);
		int pisteet = 0;
		int kysytty = 0;

		haeKysymykset quiz = new haeKysymykset();
		for (kysymys kysymys : quiz.getkysymyss()) {
			System.out.println(kysymys);
			System.out.println("Mik‰ on vastauksesi?");
			String pelaajanVastaus = lukija.nextLine();

			if (pelaajanVastaus.equalsIgnoreCase(kysymys.vastaus)) {
				System.out.println("Vastasit oikein" + "\n");
				pisteet = pisteet + 1;
			} else {
				System.out.println("V‰‰r‰ vastaus" + "\n");
			}

			kysytty = kysytty + 1;

			if (kysytty == 3) {
				break;
			}
			System.out.println("Seuraava kysymys:" + "\n");

		}
		lukija.close();
		lopputekstit(pisteet);
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

class haeKysymykset {
	private ArrayList<kysymys> kysymyss = new ArrayList<>();

	public haeKysymykset() {
		try {
			FileInputStream file = new FileInputStream("kys.txt");
			InputStreamReader isr = new InputStreamReader(file, StandardCharsets.UTF_8);
			BufferedReader reader = new BufferedReader(isr);
			Scanner scanner = new Scanner(reader);

			String rivi;
			String kysymys = "";

			String[] vaihtoehdot = new String[4];
			String vastaus = "";
			int apu = 0;

			do {

				do {
					rivi = scanner.nextLine();

					if (rivi.contains("?")) { // etsii ? ja varastoi sen kysymyksen‰
						kysymys = rivi;

					} else if (rivi.contains(")")) { // stores the alternatives
						vaihtoehdot[apu++] = rivi;

					} else if (Character.isAlphabetic(rivi.indexOf(0)) || apu == 4) { // Stores the
																						// vastaus
						vastaus = String.valueOf(rivi);
					}

				}

				while (vastaus == "");
				kysymyss.add(new kysymys(kysymys, vaihtoehdot, vastaus));
				apu = 0;
				vastaus = "";
				vaihtoehdot = new String[4];

			} while (scanner.hasNext());

			file.close();
			reader.close();
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<kysymys> getkysymyss() {
		return kysymyss;
	}
}

class kysymys {

	private String kysymys;
	private String[] vaihtoehdot;
	public String vastaus;

	public kysymys(String kysymys, String[] vaihtoehdot, String vastaus) {
		this.kysymys = kysymys;
		this.vaihtoehdot = vaihtoehdot;
		this.vastaus = vastaus;
	}

	public String getkysymys() {
		return kysymys;
	}

	public String[] getvaihtoehdot() {
		return vaihtoehdot;

	}

	public String getvastaus() {
		return vastaus;
	}

	@Override
	public String toString() {
		String print = kysymys + "\n";
		for (String vaihtoehto : vaihtoehdot) {
			print += vaihtoehto + "\n";
		}

		return print;
	}
}
