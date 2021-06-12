package projekat.sistem;

import projekat.Main;
import projekat.knjiga.Knjiga;
import projekat.osoba.Administrator;
import projekat.osoba.Clan;
import projekat.osoba.EnumPol;
import projekat.osoba.Pozajmljivanje;

import java.util.ArrayList;
import java.util.Scanner;

public class SistemManager {
	public static void initPozajmljivanje(ArrayList<Clan> clanovi, ArrayList<Pozajmljivanje> pozajmljivanja, ArrayList<Knjiga> knjige) {
		//TODO
	}

	public static void initAdminManager(Administrator prijavljenAdmin, int opcija, ArrayList<Administrator> adminList) {
		switch (opcija) {
			case 1:
				if (prijavljenAdmin.getDozvole().canAddAdmins() || prijavljenAdmin.getDozvole().hasMasterRule()) {
					addAdmin(adminList);
				}
				break;
			case 2:
				if (prijavljenAdmin.getDozvole().canEditAdmins() || prijavljenAdmin.getDozvole().hasMasterRule()) {
					editAdmin(adminList);
				}
				break;
			case 3:
				if (prijavljenAdmin.getDozvole().canDeleteAdmins() || prijavljenAdmin.getDozvole().hasMasterRule()) {
					deleteAdmin(adminList);
				}
				break;
		}
	}

	public static void initPravilaManager(PravilaBiblioteke pravila) {

	}

	private static void addAdmin(ArrayList<Administrator> adminList) {
		Scanner scanner = new Scanner(System.in);
		Main.cls();
		System.out.println("Dodavanje novog administratora...");
		Administrator a = new Administrator();

		a.getPassword().setKorisnickiUUID(a.getUUID());
		a.getDozvole().setUserUUID(a.getUUID());

		System.out.print("Unesite ime: "); a.setIme(scanner.nextLine());
		System.out.print("Unesite prezime: "); a.setPrezime(scanner.nextLine());
		System.out.print("Unesite adresu stanovanja: "); a.setAdresa(scanner.nextLine());
		System.out.print("Unesite broj telefona: "); a.setBrTelefona(scanner.nextLine());
		System.out.print("Unesite email: "); a.setEmail(scanner.nextLine());
		System.out.print("Unesite korisnicko ime: "); a.setUsername(scanner.nextLine());
		System.out.print("Unesite sifru: "); a.getPassword().encryptSifra(scanner.nextLine());
		System.out.println("Odaberite pol: ");

		boolean petlja = true;

		while (petlja) {
			System.out.println("\n1. Musko\n2. Zensko");
			System.out.println("Unos: ");
			switch (scanner.nextInt()) {
				case 1:
					petlja = false;
					a.setPol(EnumPol.MUSKO.getNum());
					break;
				case 2:
					petlja = false;
					a.setPol(EnumPol.ZENSKO.getNum());
					break;
				default:
					System.out.println("Molimo unesite jednu od dostupnih opcija!");
			}
		}

		a.getDozvole().admin();
		Main.cls();
		System.out.printf("Podesavanje dozvola za administratora %s%n", a.getUsername());
		System.out.println("Dostupne dozvole: ");
		String pravila;
		String[] pravilaList;

		if (Main.prijavljenAdmin.getDozvole().hasMasterRule()) {

			System.out.println("Upravljanje administratorima:");
			System.out.println("1. Dodavanje\t2. Brisanje\t3. Izmena\t4. Dodeljivanje master naloga\n");
			System.out.println("Upravljanje bibliotekom:");
			System.out.println("5. Dodavanje knjiga\t6. Brisanje knjiga\t7. Izmena knjiga\n8. Pozajmljivanje knjiga\t9. Izmena pravila biblioteke\n");
			System.out.println("Upravljanje clanovima: ");
			System.out.println("10. Dodavanje\t11. Brisanje\t12. Izmena\n");

			System.out.println("Uneti odabir u sledecem formatu: 1 3 10");
			System.out.println("Ako ne zelite da dodelite dozvole, upisati 0");
			System.out.print("Unos: "); pravila = scanner.nextLine();
		}
		else {
			System.out.println("Upravljanje administratorima:");
			System.out.println("1. Dodavanje       \t2. Brisanje       \t3. Izmena\n");
			System.out.println("Upravljanje bibliotekom:");
			System.out.println("5. Dodavanje knjiga\t6. Brisanje knjiga\t7. Izmena knjiga\n8. Pozajmljivanje knjiga\n");
			System.out.println("Upravljanje clanovima: ");
			System.out.println("10. Dodavanje      \t11. Brisanje      \t12. Izmena\n");

			System.out.println("Uneti odabir u sledecem formatu: 1 3 10");
			System.out.println("Ako ne zelite da dodelite dozvole, upisati 0");
			System.out.print("Unos: "); pravila = scanner.nextLine();
		}

		if (pravila.contains(" ")) {
			pravilaList = pravila.split(" ");

			a.getDozvole().setPravilaIndexed(pravilaList);
		}

		a.serialize();
	}
	private static void addMember() {}
	private static void addBook() {}
	private static void addAuthor() {}
	private static void addPublisher() {}

	private static void editAdmin(ArrayList<Administrator> adminList) {}
	private static void editMember() {}
	private static void editBook() {}
	private static void editAuthor() {}
	private static void editPublisher() {}

	private static void deleteAdmin(ArrayList<Administrator> adminList) {}
	private static void deleteMember() {}
	private static void deleteBook() {}
	private static void deleteAuthor() {}
	private static void deletePublisher() {}

	private static void editRules(PravilaBiblioteke pravilaBiblioteke) {}
}
