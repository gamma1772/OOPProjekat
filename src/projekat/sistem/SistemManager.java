package projekat.sistem;

import projekat.Main;
import projekat.knjiga.Autor;
import projekat.knjiga.Izdavac;
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
		adminList.add(a);
	}
	private static void addMember(ArrayList<Clan> clanovi) {
		Scanner scanner = new Scanner(System.in);
		Main.cls();
		System.out.println("Dodavanje novog clana...");
		Clan c = new Clan();

		System.out.print("Unesite ime: "); c.setIme(scanner.nextLine());
		System.out.print("Unesite prezime: "); c.setPrezime(scanner.nextLine());
		System.out.print("Unesite adresu stanovanja: "); c.setAdresa(scanner.nextLine());
		System.out.print("Unesite broj telefona: "); c.setBrTelefona(scanner.nextLine());
		System.out.print("Unesite email: "); c.setEmail(scanner.nextLine());

		boolean petlja = true;

		while (petlja) {
			System.out.println("\n1. Musko\n2. Zensko");
			System.out.println("Unos: ");
			switch (scanner.nextInt()) {
				case 1:
					petlja = false;
					c.setPol(EnumPol.MUSKO.getNum());
					break;
				case 2:
					petlja = false;
					c.setPol(EnumPol.ZENSKO.getNum());
					break;
				default:
					System.out.println("Molimo unesite jednu od dostupnih opcija!");
			}
		}

		c.serialize();
		clanovi.add(c);
	}
	private static void addBook(ArrayList<Knjiga> knjige, ArrayList<Autor> autori, ArrayList<Izdavac> izdavaci) {
		Scanner scanner = new Scanner(System.in);
		Main.cls();
		System.out.println("Dodavanje nove knjige...");
		Knjiga k = new Knjiga();
		k.setId(k.generateUUID());
		System.out.println("Unesite ime knjige: ");
		System.out.println("Unesite ISBN: ");
		System.out.println("Unesite godinu objavljivanja: ");
		System.out.println("Unesite izdanje knjige: ");
		System.out.println("Unesite broj strana: ");
		System.out.println("Unesite kolicinu: ");

		boolean petlja = true;
		System.out.println("Autori: ");
		System.out.println("Odaberite opciju: ");
		while (petlja) {
			System.out.println("1. Odaberite postojece autore\n2. Novi autor/i");
			System.out.println("Unos: ");
			switch (scanner.nextInt()) {
				case 1:
					petlja = false;

					break;
				case 2:
					Autor a = new Autor();
					a.setId(a.generateUUID());
					System.out.print("Unesite ime autora: "); a.setIme(scanner.nextLine());
					System.out.print("Unesite prezime autora: "); a.setPrezime(scanner.nextLine());

					k.getAutori().add(a);

					System.out.print("Da li zelite da dodate jos jednog autora? (Y/N): ");
					if (scanner.nextLine().equalsIgnoreCase("Y")) {
						continue;
					} else {
						petlja = false;
					}
					break;
				default:
					System.out.println("Molimo unesite jednu od dostupnih opcija!");
			}
		}

		petlja = true;
		System.out.println("Izdavac: ");
		System.out.println("Odaberite opciju: ");
		while (petlja) {
			System.out.println("1. Odaberite postojeceg izvodjaca\n2. Novi izdavac/i");
			System.out.println("Unos: ");
			switch (scanner.nextInt()) {
				case 1:
					petlja = false;

					break;
				case 2:
					petlja = false;

					Izdavac i = new Izdavac();
					i.setId(i.generateUUID());
					System.out.print("Unesite naziv izdavaca: "); i.setImeIzdavaca(scanner.nextLine());
					System.out.print("Unesite zemlju izdavaca: "); i.setZemljaPorekla(scanner.nextLine());

					k.setIzdavac(i);
					izdavaci.add(i);

					break;
				default:
					System.out.println("Molimo unesite jednu od dostupnih opcija!");
			}
		}

		k.serialize();
		knjige.add(k);
	}
	private static void addAuthor(ArrayList<Autor> autori) {
		Scanner scanner = new Scanner(System.in);
		Main.cls();
		System.out.println("Dodavanje novog autora...");
		Autor a = new Autor();

		a.setId(a.generateUUID());
		System.out.println("Unesite ime autora: "); a.setIme(scanner.nextLine());
		System.out.println("Unesite prezime autora: "); a.setPrezime(scanner.nextLine());

		a.serialize();
		autori.add(a);
	}
	private static void addPublisher(ArrayList<Izdavac> izdavaci) {
		Scanner scanner = new Scanner(System.in);
		Main.cls();
		System.out.println("Dodavanje novog autora...");
		Izdavac i = new Izdavac();

		i.setId(i.generateUUID());
		System.out.println("Unesite naziv izdavaca: "); i.setImeIzdavaca(scanner.nextLine());
		System.out.println("Unesite zemlju: "); i.setZemljaPorekla(scanner.nextLine());

		i.serialize();
		izdavaci.add(i);
	}

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
