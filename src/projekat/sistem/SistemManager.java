package projekat.sistem;

import projekat.Main;
import projekat.knjiga.*;
import projekat.osoba.*;

import java.util.*;

public class SistemManager {
	public static void initPozajmljivanje(ArrayList<Clan> clanovi, ArrayList<Pozajmljivanje> pozajmljivanja, ArrayList<Knjiga> knjige) {
		PozajmljivanjeManager.showLoans(pozajmljivanja, clanovi, knjige);
	}

	public static void initAdminManager(Administrator prijavljenAdmin, int opcija, ArrayList<Administrator> adminList, ArrayList<Administrator.Dozvole> dozvole, ArrayList<Sifra> sifre) {
		switch (opcija) {
			case 1:
				if (prijavljenAdmin.getDozvole().canAddAdmins() || prijavljenAdmin.getDozvole().hasMasterRule()) {
					AdminManager.addAdmin(adminList, dozvole, sifre);
				}
				break;
			case 2:
				if (prijavljenAdmin.getDozvole().canEditAdmins() || prijavljenAdmin.getDozvole().hasMasterRule()) {
					AdminManager.editAdmin(adminList);
				}
				break;
			case 3:
				if (prijavljenAdmin.getDozvole().canDeleteAdmins() || prijavljenAdmin.getDozvole().hasMasterRule()) {
					AdminManager.deleteAdmin(adminList, sifre, dozvole);
				}
				break;
		}
	}

	public static void initMemberManager(int opcija, ArrayList<Clan> clanList, ArrayList<Pozajmljivanje> pozajmljivanja) {
		switch (opcija) {
			case 1:
				if (Main.prijavljenAdmin.getDozvole().canAddMembers() || Main.prijavljenAdmin.getDozvole().hasMasterRule()) {
					MemberManager.addMember(clanList);
				}
				break;
			case 2:
				if (Main.prijavljenAdmin.getDozvole().canEditMembers() || Main.prijavljenAdmin.getDozvole().hasMasterRule()) {
					MemberManager.editMember(clanList);
				}
				break;
			case 3:
				if (Main.prijavljenAdmin.getDozvole().canDeleteMembers() || Main.prijavljenAdmin.getDozvole().hasMasterRule()) {
					MemberManager.deleteMember(clanList, pozajmljivanja);
				}
				break;
		}
	}

	public static void initBookManager(int opcija, ArrayList<Knjiga> knjigaList, ArrayList<Autor> autorList, ArrayList<Izdavac> izdavacList, ArrayList<Pozajmljivanje> pozajmljivanja) {
		switch (opcija) {
			case 1:
				if (Main.prijavljenAdmin.getDozvole().canAddBooks() || Main.prijavljenAdmin.getDozvole().hasMasterRule()) {
					BookManager.addBook(knjigaList, autorList, izdavacList);
				}
				break;
			case 2:
				if (Main.prijavljenAdmin.getDozvole().canEditBooks() || Main.prijavljenAdmin.getDozvole().hasMasterRule()) {
					BookManager.editBook(knjigaList, autorList, izdavacList);
				}
				break;
			case 3:
				if (Main.prijavljenAdmin.getDozvole().canDeleteBooks() || Main.prijavljenAdmin.getDozvole().hasMasterRule()) {
					BookManager.deleteBook(knjigaList, pozajmljivanja);
				}
				break;
			default: {

			}
		}
	}

	public static void initAuthorManager(int opcija, ArrayList<Autor> autorList, ArrayList<Knjiga> knjige) {
		switch (opcija) {
			case 1:
				if (Main.prijavljenAdmin.getDozvole().canAddBooks() || Main.prijavljenAdmin.getDozvole().hasMasterRule()) {
					BookManager.addAuthor(autorList);
				}
				break;
			case 2:
				if (Main.prijavljenAdmin.getDozvole().canEditBooks() || Main.prijavljenAdmin.getDozvole().hasMasterRule()) {
					BookManager.editAuthor(autorList);
				}
				break;
			case 3:
				if (Main.prijavljenAdmin.getDozvole().canDeleteBooks() || Main.prijavljenAdmin.getDozvole().hasMasterRule()) {
					BookManager.deleteAuthor(autorList, knjige);
				}
				break;
		}
	}

	public static void initPublisherManager(int opcija, ArrayList<Izdavac> izdavacList, ArrayList<Knjiga> knjige) {
		switch (opcija) {
			case 1:
				if (Main.prijavljenAdmin.getDozvole().canAddBooks() || Main.prijavljenAdmin.getDozvole().hasMasterRule()) {
					BookManager.addPublisher(izdavacList);
				}
				break;
			case 2:
				if (Main.prijavljenAdmin.getDozvole().canEditBooks() || Main.prijavljenAdmin.getDozvole().hasMasterRule()) {
					BookManager.editPublisher(izdavacList);
				}
				break;
			case 3:
				if (Main.prijavljenAdmin.getDozvole().canDeleteBooks() || Main.prijavljenAdmin.getDozvole().hasMasterRule()) {
					BookManager.deletePublisher(izdavacList, knjige);
				}
				break;
		}
	}

	public static void initPravilaManager(PravilaBiblioteke pravila) {
		viewRules(pravila);
	}

	private static void viewRules(PravilaBiblioteke pravilaBiblioteke) {
		Scanner scanner = new Scanner(System.in);
		Main.cls();
		System.out.println("Pravila biblioteke");
		System.out.printf("1. Koliko dugo knjiga moze da bude pozajmljena?                 		 	 %d dana.%n" +
						"2. Koliko puta moze da se produzi pozajmljivanje jedne knjige?  		 	 %d put(a)%n" +
						"3. Mnozilac duga:														 %.2f%n" +
						"4. Da li moze da se pozajmi vise knjiga od jednom? 				 		 %b%n" +
						"5. Da li je potrebno da se vrate prethodne knjige pre pozajmljivanja?     %b%n",
				pravilaBiblioteke.getMaxPeriod(), pravilaBiblioteke.getMaxReloan(), pravilaBiblioteke.getMultiplier(), pravilaBiblioteke.getLoanMultipleAtOnce(), pravilaBiblioteke.getLoanBeforeReturningPrevious());


		boolean petlja = true;
		while (petlja) {
			System.out.print("\nDa li zelite da izmenite pravila? [Y/N]: ");
			String odabir = scanner.nextLine();
			switch (odabir) {
				case "Y":
					petlja = false;
					editRules(pravilaBiblioteke);
					break;
				case "N":
					petlja = false;
					break;
				default: {
					System.out.println("Unesite Y ili N");
				}
			}
		}

	}

	private static void editRules(PravilaBiblioteke pravilaBiblioteke) {
		Scanner scanner = new Scanner(System.in);
		String odabir;
		boolean petlja = true;
		while (petlja) {
			Main.cls();
			System.out.println("Pravila biblioteke");
			System.out.printf("1. Koliko dugo knjiga moze da bude pozajmljena?                 		 	 %d dana.%n" +
							"2. Koliko puta moze da se produzi pozajmljivanje jedne knjige?  		 	 %d put(a)%n" +
							"3. Mnozilac duga:														 %.2f%n" +
							"4. Da li moze da se pozajmi vise knjiga od jednom? 				 		 %b%n" +
							"5. Da li je potrebno da se vrate prethodne knjige pre pozajmljivanja?     %b%n",
					pravilaBiblioteke.getMaxPeriod(), pravilaBiblioteke.getMaxReloan(), pravilaBiblioteke.getMultiplier(), pravilaBiblioteke.getLoanMultipleAtOnce(), pravilaBiblioteke.getLoanBeforeReturningPrevious());

			System.out.print("\nOdaberite pravilo koje zelite da izmenite (0 za izlaz): ");
			odabir = scanner.nextLine();
			switch (odabir) {
				case "1":
					Main.cls();
					System.out.println("Menjanje pravila 'Koliko dugo knjiga moze da bude pozajmljena?'");
					System.out.println("Unesite broj: ");
					pravilaBiblioteke.maxPeriod(scanner.nextInt());
					scanner.nextLine();
					break;
				case "2":
					Main.cls();
					System.out.println("Menjanje pravila 'Koliko puta moze da se produzi pozajmljivanje jedne knjige?'");
					System.out.println("Unesite broj: ");
					pravilaBiblioteke.maxReloan(scanner.nextInt());
					scanner.nextLine();
					break;
				case "3":
					Main.cls();
					System.out.println("Menjanje pravila 'Koliko puta moze da se produzi pozajmljivanje jedne knjige?'");
					System.out.println("Unesite broj (Sa ili bez decimalnog zareza): ");
					pravilaBiblioteke.multiplier(scanner.nextDouble());
					scanner.nextLine();
					break;
				case "4":
					pravilaBiblioteke.loanMultipleAtOnce();
					break;
				case "5":
					pravilaBiblioteke.loanBeforeReturningPrevious();
					break;
				case "0":
					petlja = false;
					break;
				default: {
					System.out.println("Unesite jedno od dostupnih pravila!");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}
		Main.save(EnumCheckpoints.PRAVILA.ordinal());
	}

	protected static String pol(int pol) {
		if (pol == 1) {
			return EnumPol.MUSKO.name();
		} else if (pol == 2) {
			return EnumPol.ZENSKO.name();
		} else {
			return "NEODREDJENO";
		}
	}
}

class PozajmljivanjeManager {
	protected static void showLoans(ArrayList<Pozajmljivanje> pozajmljivanja, ArrayList<Clan> clanovi, ArrayList<Knjiga> knjige) {
		Scanner scanner = new Scanner(System.in);
		Main.cls();
		System.out.println("ID                  Knjiga                Datum Pozajmljivanja  Datum isteka   Dug     Razreseno");
		if (pozajmljivanja != null && pozajmljivanja.size() > 0) {
			for (Pozajmljivanje p : pozajmljivanja) {
				if (!p.isRazreseno()) {
					p.izracunajDug();
					System.out.println(p);
				}
			}
		} else {
			System.out.println("Lista pozajmljivanja je prazna.");
		}

		boolean petlja = true;
		while (petlja) {
			System.out.println("1. Pozajmi knjigu, 2. Vrati knjigu, 3. Prikazi sva pozajmljivanja, 4. Produzi knjigu, 5. Pocetni meni");
			System.out.print("Unos: ");
			switch (scanner.nextLine()) {
				case "1":
					loanBook(pozajmljivanja, clanovi, knjige);
					break;
				case "2":
					returnBook(pozajmljivanja, clanovi);
					break;
				case "3":
					Main.cls();
					System.out.println("ID                  Knjiga                Datum Pozajmljivanja  Datum isteka   Dug     Razreseno");
					if (pozajmljivanja != null && pozajmljivanja.size() > 0) {
						for (Pozajmljivanje p : pozajmljivanja) {
							System.out.println(p.toString());
						}
					} else {
						System.out.println("Lista pozajmljivanja je prazna.");
					}
					break;
				case "4":
					extendBook(pozajmljivanja, clanovi);
					break;
				case "5":
					petlja = false;
					break;
				default:
					System.out.println("Uneli ste nepostojecu opciju");
					break;
			}
		}
	}

	protected static void loanBook(ArrayList<Pozajmljivanje> pozajmljivanja, ArrayList<Clan> clanovi, ArrayList<Knjiga> knjige) {
		Scanner scanner = new Scanner(System.in);
		Main.cls();
		System.out.println("Odaberite knjigu koju pozajmljujete: ");
		for (int i = 0; i < knjige.size(); i++) {
			System.out.printf("%d. %s%n", i + 1, knjige.get(i).getId().concat(knjige.get(i).getISBN() + " " + knjige.get(i).getAutori().get(0).getFullName() + " " + knjige.get(i).getIzdavac().getImeIzdavaca()));
		}
		Knjiga k;
		while (true) {
			System.out.print("Unos (0 za izlaz): ");
			int unos = Integer.parseInt(scanner.nextLine()) - 1;
			if (unos >= 0 && unos < knjige.size()) {
				if (knjige.get(unos).getKolicina() == 0) {
					System.out.println("Knjiga nije na stanju.");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					continue;
				} else {
					k = knjige.get(unos);
					break;
				}
			} else {
				System.out.println("Unesite jednu od dostupnih opcija!");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (unos == -1) {
				k = null;
				break;
			}
		}

		Clan c;
		if (k == null) {
			c = null;
		} else {
			Main.cls();
			System.out.println("Odaberite clana biblioteke koji pozajmljuje knjigu: ");
			for (int i = 0; i < clanovi.size(); i++) {
				System.out.printf("%d. %s%n", i + 1, clanovi.get(i).getUUID().concat(clanovi.get(i).getPunoIme() + " " + clanovi.get(i).getEmail()));
			}
			while (true) {
				System.out.print("Unos (0 za izlaz): ");
				int unos = Integer.parseInt(scanner.nextLine()) - 1;
				if (unos >= 0 && unos < clanovi.size()) {

					if (!Main.pravila.getLoanBeforeReturningPrevious() && clanovi.get(unos).getPozajmljivanje().stream().allMatch(Pozajmljivanje::isRazreseno)) {
						c = clanovi.get(unos);
					} else if (Main.pravila.getLoanBeforeReturningPrevious()) {
						c = clanovi.get(unos);
					} else {
						System.out.println("Ovaj clan vec ima pozajmljene knjige, po pravilu ne moze da pozajmi vise knjiga dok ne vrati prethodnu.");
						continue;
					}
					break;
				} else if (unos == -1) {
					k = null;
					c = null;
					break;
				} else {
					System.out.println("Unesite jednu od dostupnih opcija!");
				}
			}
		}

		if (c != null && k != null) {
			Calendar calendar = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			System.out.println(cal2.getTime());
			cal2.add(Calendar.DAY_OF_MONTH, Main.pravila.getMaxPeriod());
			System.out.println(cal2.getTime());
			for (Knjiga knjiga : knjige) {
				if (knjiga.getISBN().equals(k.getISBN())) {
					knjiga.setKolicina(knjiga.getKolicina() - 1);
				}
			}
			Pozajmljivanje p = new Pozajmljivanje(c.getUUID(), 0.00D, k, calendar, cal2, 0, false);
			c.getPozajmljivanje().add(p);
			pozajmljivanja.add(p);
			Main.save(EnumCheckpoints.KNJIGE.ordinal());
			Main.save(EnumCheckpoints.POZAJMLJIVANJE.ordinal());
		}

	}

	protected static void returnBook(ArrayList<Pozajmljivanje> pozajmljivanja, ArrayList<Clan> clanovi) {
		Scanner scanner = new Scanner(System.in);
		Main.cls();

		while (true) {
			System.out.println("Odaberite clana: ");
			for (int i = 0; i < clanovi.size(); i++) {
				int finalI = i;
				if (pozajmljivanja.stream().anyMatch(p -> p.getClanUUID().equals(clanovi.get(finalI).getUUID()))) {
					System.out.printf("%d. %s%n", i + 1, clanovi.get(i).getUUID().concat(" " + clanovi.get(i).getPunoIme()));
				}
			}
			System.out.print("Unos (0 za izlaz): ");
			int unos = Integer.parseInt(scanner.nextLine()) - 1;

			if (unos >= 0 && unos < clanovi.size()) {
				Clan c = clanovi.get(unos);
				Pozajmljivanje p;

				while (true) {
					System.out.println("Odaberite pozajmljivanje za razresenje:");
					for (int i = 0; i < c.getPozajmljivanje().size(); i++) {
						if (!c.getPozajmljivanje().get(i).isRazreseno()) {
							System.out.printf("%d. %s%n", i + 1, c.getPozajmljivanje().get(i).getPozajmljenaKnjiga().getImeKnjige().concat(" " + c.getPozajmljivanje().get(i).getDug()));
						} else {
							System.out.println("Lista pozajmljivanja ovog clana je prazna, ili su sva pozajmljivanja razresena.");
						}
					}
					System.out.print("Unos (0 za izlaz): ");
					unos = Integer.parseInt(scanner.nextLine()) - 1;
					if (unos >= 0 && unos < c.getPozajmljivanje().size()) {
						p = c.getPozajmljivanje().get(unos);
						break;
					} else if (unos == -1) {
						p = null;
						break;
					} else {
						System.out.println("Unesite jednu od dostupnih opcija");
					}
				}
				if (p != null) {
					if (!p.isRazreseno()) {
						System.out.printf("%nDug: %.2f%n", p.getDug());
						boolean petlja = true;
						while (petlja) {
							System.out.print("Razresi pozajmljivanje? (Y/N): ");
							switch (scanner.nextLine()) {
								case "Y":
									petlja = false;
									p.vratiKnjigu();
									Main.save(EnumCheckpoints.POZAJMLJIVANJE.ordinal());
									break;
								case "N":
									petlja = false;
									break;
								default:
									System.out.println("Unesite Y ili N!");
									break;
							}
						}
					} else {
						System.out.println("Pozajmljivanje ne postoji ili je razreseno.");
						break;
					}
				}

			} else if (unos == -1) {
				break;
			}
		}
	}

	protected static void extendBook(ArrayList<Pozajmljivanje> pozajmljivanja, ArrayList<Clan> clanovi) {
		Scanner scanner = new Scanner(System.in);
		Main.cls();

		while (true) {
			System.out.println("Odaberite clana: ");
			for (int i = 0; i < clanovi.size(); i++) {
				int finalI = i;
				if (pozajmljivanja.stream().anyMatch(p -> p.getClanUUID().equals(clanovi.get(finalI).getUUID()))) {
					System.out.printf("%d. %s%n", i + 1, clanovi.get(i).getUUID().concat(" " + clanovi.get(i).getPunoIme()));
				}
			}
			System.out.print("Unos (0 za izlaz): ");
			int unos = Integer.parseInt(scanner.nextLine()) - 1;

			if (unos >= 0 && unos < clanovi.size()) {
				Clan c = clanovi.get(unos);
				Pozajmljivanje p;

				while (true) {
					System.out.println("Odaberite pozajmljivanje za produzetak:");
					for (int i = 0; i < c.getPozajmljivanje().size(); i++) {
						if (!c.getPozajmljivanje().get(i).isRazreseno()) {
							System.out.printf("%d. %s%n", i + 1, c.getPozajmljivanje().get(i).getPozajmljenaKnjiga().getImeKnjige().concat(" " + c.getPozajmljivanje().get(i).getDug()));
						} else {
							System.out.println("Lista pozajmljivanja ovog clana je prazna, ili su sva pozajmljivanja razresena.");
						}
					}
					System.out.print("Unos (0 za izlaz): ");
					unos = Integer.parseInt(scanner.nextLine()) - 1;
					if (unos >= 0 && unos < c.getPozajmljivanje().size()) {
						p = c.getPozajmljivanje().get(unos);
						break;
					} else if (unos == -1) {
						p = null;
						break;
					} else {
						System.out.println("Unesite jednu od dostupnih opcija");
					}
				}
				if (p != null) {
					if (!p.isRazreseno()) {
						System.out.printf("Pozajmljena knjiga: %s%n", p.getPozajmljenaKnjiga().getImeKnjige());
						boolean petlja = true;
						while (petlja) {
							System.out.print("Produzi zaduzenje? (Y/N): ");
							switch (scanner.nextLine()) {
								case "Y":
									petlja = false;
									p.produziKnjigu();
									Main.save(EnumCheckpoints.POZAJMLJIVANJE.ordinal());
									break;
								case "N":
									petlja = false;
									break;
								default:
									System.out.println("Unesite Y ili N!");
									break;
							}
						}
					} else {
						System.out.println("Pozajmljivanje ne postoji ili je razreseno.");
						break;
					}
				}

			} else if (unos == -1) {
				break;
			}
		}
	}
}

class AdminManager {
	protected static void addAdmin(ArrayList<Administrator> adminList, ArrayList<Administrator.Dozvole> dozvole, ArrayList<Sifra> sifre) {
		Scanner scanner = new Scanner(System.in);
		Main.cls();
		System.out.println("Dodavanje novog administratora...");
		Administrator a = new Administrator();

		a.setUUID(a.generateUUID());
		a.getPassword().setKorisnickiUUID(a.getUUID());
		a.getDozvole().setUserUUID(a.getUUID());

		while (true) {
			System.out.print("Unesite ime: ");
			String ime = scanner.nextLine();
			if (ime.matches("[a-zA-Z.]+")) {
				a.setIme(ime);
				break;
			}
			else {
				System.out.println("Ime ne sme da sadrzi posebne znakove, osim '.'.");
			}
		}
		while (true) {
			System.out.print("Unesite prezime: ");
			String prezime = scanner.nextLine();
			if (prezime.matches("[a-zA-Z.\\s]+")) {
				a.setPrezime(prezime);
				break;
			}
			else {
				System.out.println("Prezime ne sme da sadrzi posebne znakove, osim '.'.");
			}
		}
		while (true) {
			System.out.print("Unesite adresu stanovanja: ");
			String adresa = scanner.nextLine();
			if (adresa.matches("[a-zA-Z0-9.\\s]+")) {
				a.setAdresa(adresa);
				break;
			}
			else {
				System.out.println("Adresa ne sme da sadrzi posebne znakove, osim '.' i brojeva.");
			}
		}
		while (true) {
			System.out.print("Unesite broj telefona: ");
			String brTelefona = scanner.nextLine();
			if (brTelefona.matches("\\d{9,10}")) {
				a.setBrTelefona(brTelefona);
				break;
			}
			else {
				System.out.println("Broj telefona mora da sadrzi 9 ili 10 brojeva.");
			}
		}
		while (true) {
			System.out.print("Unesite email: ");
			String email = scanner.nextLine();
			if (email.matches("\\w+@[a-zA-Z.]+")) {
				a.setEmail(email.toLowerCase());
				break;
			}
			else {
				System.out.println("Email mora da bude u sledecem formatu: example@email.com.");
			}

		}
		while (true) {
			System.out.print("Unesite korisnicko ime: ");
			String korisnickoIme = scanner.nextLine();
			if (korisnickoIme.matches("[a-zA-Z0-9_]+")) {
				a.setUsername(korisnickoIme.toLowerCase());
				break;
			}
			else {
				System.out.println("Korisnicko ime ne sme da sadrzi posebne znakove, osim '_' i brojeva.");
			}
		}
		while (true) {
			System.out.print("Unesite sifru: ");
			String sifra = scanner.nextLine();
			if (sifra.matches("[a-zA-Z0-9_-]+")) {
				a.getPassword().encryptSifra(sifra);
				break;
			}
			else {
				System.out.println("Ime ne sme da sadrzi posebne znakove, osim '.'.");
			}
		}
		System.out.println("Odaberite pol: ");

		boolean petlja = true;

		while (petlja) {
			System.out.println("\n1. Musko\n2. Zensko");
			System.out.println("Unos: ");
			switch (scanner.nextLine()) {
				case "1":
					petlja = false;
					a.setPol(EnumPol.MUSKO.getNum());
					break;
				case "2":
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

		boolean petlja2 = true;
		while (petlja2) {
			Main.cls();
			System.out.println("Dozvole odabranog administratora:");
			a.getDozvole().printDozvole(true);
			System.out.println("\nOdaberite dozvolu koju zelite da promenite (0 za izlaz): ");
			switch (scanner.nextLine()) {
				case "0":
					petlja2 = false;
					break;
				case "1":
					a.getDozvole().addAdmins();
					break;
				case "2":
					a.getDozvole().addBooks();
					break;
				case "3":
					a.getDozvole().addMembers();
					break;
				case "4":
					a.getDozvole().editAdmins();
					break;
				case "5":
					a.getDozvole().editBooks();
					break;
				case "6":
					a.getDozvole().editMembers();
					break;
				case "7":
					a.getDozvole().deleteAdmins();
					break;
				case "8":
					a.getDozvole().deleteBooks();
					break;
				case "9":
					a.getDozvole().deleteMembers();
					break;
				case "10":
					a.getDozvole().loanBooks();
					break;
				case "11":
					if (Main.prijavljenAdmin.getDozvole().hasMasterRule())
						a.getDozvole().alterRules();
					else {
						System.out.println("Nemate dozvolu za zatrazenu operaciju.");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					break;
				case "12":
					if (Main.prijavljenAdmin.getDozvole().hasMasterRule())
						a.getDozvole().masterRule();
					else {
						System.out.println("Nemate dozvolu za zatrazenu operaciju.");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					break;
				default:
					System.out.println("Unesite redni broj dozvole koja postoji!");
					break;
			}
		}

		adminList.add(a);
		sifre.add(a.getPassword());
		dozvole.add(a.getDozvole());

		Main.save(EnumCheckpoints.ADMINI.ordinal());
		Main.save(EnumCheckpoints.SIFRE.ordinal());
		Main.save(EnumCheckpoints.DOZVOLE.ordinal());
	}

	protected static void editAdmin(ArrayList<Administrator> adminList) {
		Scanner scanner = new Scanner(System.in);
		Main.cls();
		System.out.println("Odaberite administratora cije podatke zelite da izmenite (0 za izlaz):");
		for (int i = 0; i < adminList.size(); i++) {
			System.out.printf("%d. %s%n", i + 1, adminList.get(i).getUUID().concat(" " + adminList.get(i).getUsername()));
		}
		while (true) {
			System.out.println("Unos: ");
			int unos = scanner.nextInt();
			if (unos == 0) {
				break;
			} else {
				if (unos - 1 > adminList.size()) {
					System.out.println("Uneli ste nepostojeci redni broj.");
				} else {
					boolean petlja = true;
					while (petlja) {
						Main.cls();
						System.out.println("Podaci o odabranom administratoru:");
						System.out.printf("ID: %s%nIme: %s%nPrezime: %s%nAdresa: %s%nBroj telefona: %s%nEmail: %s%nPol: %s%nKorisnicko ime: %s%n",
								adminList.get(unos - 1).getUUID(), adminList.get(unos - 1).getIme(), adminList.get(unos - 1).getPrezime(), adminList.get(unos - 1).getAdresa(),
								adminList.get(unos - 1).getBrTelefona(), adminList.get(unos - 1).getEmail(), SistemManager.pol(adminList.get(unos - 1).getPol()), adminList.get(unos - 1).getUsername());
						System.out.print("\nIzaberite sta zelite da izmenite (1. Ime, 2. Prezime, 3. Adresa, 4. Broj telefona, 5. Email, 6. Pol, 7. Korisnicko ime, 8. Sifra, 9. Dozvole). Za otkazivanje unesite 0: ");
						switch (scanner.nextInt()) {
							case 1:
								while (true) {
									System.out.println("Unesite novo ime: ");
									String tempIme = scanner.nextLine();
									if (tempIme.length() < 2 && !tempIme.matches("[a-zA-Z.]+")) {
										System.out.println("Ime moze da sardzi samo slova.");
									} else {
										petlja = false;
										adminList.get(unos - 1).setIme(tempIme);
										break;
									}
								}
								break;
							case 2:
								while (true) {
									System.out.println("Unesite novo prezime: ");
									String tempPrezime = scanner.nextLine();
									if (tempPrezime.length() < 2 && !tempPrezime.matches("[a-zA-Z.\\s]+")) {
										System.out.println("Prezime moze da sardzi samo slova.");
									} else {
										petlja = false;
										adminList.get(unos - 1).setPrezime(tempPrezime);
										break;
									}
								}
								break;
							case 3:
								while (true) {
									System.out.println("Unesite novu adresu: ");
									String tempAdresa = scanner.nextLine();
									if (tempAdresa.length() < 2 && !tempAdresa.matches("[a-zA-Z0-9.\\s]+")) {
										System.out.println("Adresa mora biti duza od 2 karaktera i ne sme da sadrzi specijalne znakove.");
									} else {
										petlja = false;
										adminList.get(unos - 1).setAdresa(tempAdresa);
										break;
									}
								}
								break;
							case 4:
								while (true) {
									System.out.println("Unesite novi broj telefona: ");
									String tempBrTelefona = scanner.nextLine();
									if (!tempBrTelefona.matches("\\d{9,10}")) {
										System.out.println("Broj telefona mora da ima 9 ili 10 brojeva");
									} else {
										petlja = false;
										adminList.get(unos - 1).setBrTelefona(tempBrTelefona);
										break;
									}
								}
								break;
							case 5:
								while (true) {
									System.out.println("Unesite novu email adresu: ");
									String tempEmail = scanner.nextLine();
									if (!tempEmail.matches("\\w+@[a-zA-Z.]+")) {
										System.out.println("Email adresa mora da sardzi znak @, i mora biti duza od 7 karaktera");
									} else {
										petlja = false;
										adminList.get(unos - 1).setEmail(tempEmail);
										break;
									}
								}
								break;
							case 6:
								System.out.println("Odaberite pol: ");
								boolean petljaPol = true;
								while (petljaPol) {
									System.out.println("\n1. Musko\n2. Zensko");
									System.out.println("Unos: ");
									switch (scanner.nextInt()) {
										case 1:
											petlja = false;
											petljaPol = false;
											adminList.get(unos - 1).setPol(EnumPol.MUSKO.getNum());
											break;
										case 2:
											petlja = false;
											petljaPol = false;
											adminList.get(unos - 1).setPol(EnumPol.ZENSKO.getNum());
											break;
										default:
											System.out.println("Molimo unesite jednu od dostupnih opcija!");
									}
								}
								break;
							case 7:
								while (true) {
									System.out.println("Unesite novo korisnicko ime: ");
									String tempUsername = scanner.nextLine();
									if (tempUsername.length() < 6 && !tempUsername.matches("[A-Za-z0-9_]+")) {
										System.out.println("Korisnicko ime mora biti duze od 6 karaktera i ne sme sadrzati specijalne karaktere");
									} else {
										petlja = false;
										adminList.get(unos - 1).setUsername(tempUsername);
										break;
									}
								}
								break;
							case 8:
								Main.cls();
								System.out.print("Unesite trenutnu lozinku odabranog administratora: ");
								int pokusaj = 0;
								while (pokusaj < 3) {
									if (Sifra.sifrujLozinku(adminList.get(unos - 1).getUUID(), scanner.nextLine()).equals(adminList.get(unos - 1).getPassword().getSifra())) {
										break;
									} else {
										System.out.println("Lozinka nije ispravna");
										pokusaj++;
									}
								}
								if (pokusaj == 3) {
									System.out.println("Nije moguce promeniti sifru (3 puta netacna lozinka).");
									petlja = false;
									break;
								}
								while (true) {
									Main.cls();
									System.out.println("Unesite novu lozinku (Unesite 0 za otkazivanje): ");
									String tempSifra = scanner.nextLine();
									if (tempSifra.equals("0")) {
										break;
									}
									else if (!tempSifra.matches("[a-zA-Z0-9-_]+") && tempSifra.length() < 6) {
										System.out.println("Sifra ne sme da sadrzi posebne karaktere (osim - i _) i mora biti duza od 6 karaktera");
										try {
											Thread.sleep(1000);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
									}
									else {
										System.out.println("Ponovo unesite lozinku");
										if (scanner.nextLine().equals(tempSifra)) {
											adminList.get(unos - 1).getPassword().encryptSifra(tempSifra);
											System.out.println("Sifra promenjena");
											Main.save(EnumCheckpoints.SIFRE.ordinal());
											break;
										} else {
											System.out.println("Neispravna ponovna lozinka.");
										}
									}
								}
								break;
							case 9:
								boolean petlja3 = true;
								while (petlja3) {
									Main.cls();
									System.out.println("Dozvole odabranog administratora:");
									adminList.get(unos - 1).getDozvole().printDozvole(true);
									System.out.println("Odaberite dozvolu koju zelite da promenite (0 za izlaz): ");
									switch (scanner.nextLine()) {
										case "0":
											petlja3 = false;
											break;
										case "1":
											adminList.get(unos - 1).getDozvole().addAdmins();
											break;
										case "2":
											adminList.get(unos - 1).getDozvole().addBooks();
											break;
										case "3":
											adminList.get(unos - 1).getDozvole().addMembers();
											break;
										case "4":
											adminList.get(unos - 1).getDozvole().editAdmins();
											break;
										case "5":
											adminList.get(unos - 1).getDozvole().editBooks();
											break;
										case "6":
											adminList.get(unos - 1).getDozvole().editMembers();
											break;
										case "7":
											adminList.get(unos - 1).getDozvole().deleteAdmins();
											break;
										case "8":
											adminList.get(unos - 1).getDozvole().deleteBooks();
											break;
										case "9":
											adminList.get(unos - 1).getDozvole().deleteMembers();
											break;
										case "10":
											adminList.get(unos - 1).getDozvole().loanBooks();
											break;
										case "11":
											adminList.get(unos - 1).getDozvole().alterRules();
											break;
										case "12":
											adminList.get(unos - 1).getDozvole().masterRule();
											break;
										default:
											System.out.println("Unesite redni broj dozvole koja postoji!");
											break;
									}
								}
								Main.save(EnumCheckpoints.DOZVOLE.ordinal());
								break;
							case 0:
								petlja = false;
								break;
							default:
								System.out.println("Unesite jednu od ponudjenih opcija!");
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								break;
						}
					}
				}
			}
		}
		Main.save(EnumCheckpoints.ADMINI.ordinal());
	}

	protected static void deleteAdmin(ArrayList<Administrator> adminList, ArrayList<Sifra> sifre, ArrayList<Administrator.Dozvole> dozvole) {

		while (true) {
			Scanner scanner = new Scanner(System.in);
			Main.cls();
			System.out.println("Odaberite administratora cije podatke zelite da izmenite (0 za izlaz):");
			for (int i = 0; i < adminList.size(); i++) {
				System.out.printf("%d. %s%n", i + 1, adminList.get(i).getUUID().concat(" " + adminList.get(i).getUsername()));
			}
			System.out.println("Unos: ");
			String unos = scanner.nextLine();
			if (unos.equals("0")) {
				break;
			} else {
				if (Integer.parseInt(unos) - 1 > adminList.size()) {
					System.out.println("Uneli ste nepostojeci redni broj.");
				} else {
					boolean petlja = true;
					if (Main.prijavljenAdmin == adminList.get((Integer.parseInt(unos) - 1))) {
						petlja = false;
						System.out.println("Brisanje ovog administratora je blokirano zato sto je trenutno prijavljen!");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					while (petlja) {
						System.out.printf("Odabran administrator: %s, %s%n", adminList.get((Integer.parseInt(unos) - 1)).getUUID(), adminList.get((Integer.parseInt(unos) - 1)).getUsername());
						System.out.println("Da li ste sigurni da zelite da obrisete ovog administratora? (Y/N)");
						switch (scanner.nextLine().toUpperCase()) {
							case "Y":
								petlja = false;

								sifre.removeIf(s -> s.getKorisnickiUUID().equals(adminList.get(Integer.parseInt(unos) - 1).getUUID())); //Brisanje clana neke liste uz pomoc funkcije koja koristi lambde. Zaobilazi koriscenje for petlje
								dozvole.removeIf(d -> d.getUserUUID().equals(adminList.get(Integer.parseInt(unos) - 1).getUUID()));
								adminList.remove(Integer.parseInt(unos) - 1);
								System.out.println("Uspesno obrisano.");
								break;
							case "N":
								petlja = false;
								System.out.println("Brisanje otkazano.");
								break;
							default:
								System.out.println("Unesite Y ili N");
								break;
						}
					}
				}
			}
		}
		Main.save(EnumCheckpoints.SIFRE.ordinal());
		Main.save(EnumCheckpoints.DOZVOLE.ordinal());
		Main.save(EnumCheckpoints.ADMINI.ordinal());
	}
}

class MemberManager {

	protected static void addMember(ArrayList<Clan> clanovi) {
		Scanner scanner = new Scanner(System.in);
		Main.cls();
		System.out.println("Dodavanje novog clana...");
		Clan c = new Clan();
		c.setUUID(c.generateUUID());
		while (true) {
			System.out.print("Unesite ime: ");
			String ime = scanner.nextLine();
			if (ime.matches("[a-zA-Z.]+")) {
				c.setIme(ime);
				break;
			}
			else {
				System.out.println("Ime ne sme da sadrzi posebne znakove, osim '.'.");
			}
		}
		while (true) {
			System.out.print("Unesite prezime: ");
			String prezime = scanner.nextLine();
			if (prezime.matches("[a-zA-Z.\\s]+")) {
				c.setPrezime(prezime);
				break;
			}
			else {
				System.out.println("Prezime ne sme da sadrzi posebne znakove, osim '.'.");
			}
		}
		while (true) {
			System.out.print("Unesite adresu stanovanja: ");
			String adresa = scanner.nextLine();
			if (adresa.matches("[a-zA-Z0-9.\\s]+")) {
				c.setAdresa(adresa);
				break;
			}
			else {
				System.out.println("Adresa ne sme da sadrzi posebne znakove, osim '.' i brojeva.");
			}
		}
		while (true) {
			System.out.print("Unesite broj telefona: ");
			String brTelefona = scanner.nextLine();
			if (brTelefona.matches("\\d{9,10}")) {
				c.setBrTelefona(brTelefona);
				break;
			}
			else {
				System.out.println("Broj telefona mora da sadrzi 9 ili 10 brojeva.");
			}
		}
		while (true) {
			System.out.print("Unesite email: ");
			String email = scanner.nextLine();
			if (email.matches("\\w+@[a-zA-Z.]+")) {
				c.setEmail(email.toLowerCase());
				break;
			}
			else {
				System.out.println("Email mora da bude u sledecem formatu: example@email.com.");
			}

		}

		boolean petlja = true;

		while (petlja) {
			System.out.println("\n1. Musko\n2. Zensko");
			System.out.println("Unos: ");
			switch (scanner.nextLine()) {
				case "1":
					petlja = false;
					c.setPol(EnumPol.MUSKO.getNum());
					break;
				case "2":
					petlja = false;
					c.setPol(EnumPol.ZENSKO.getNum());
					break;
				default:
					System.out.println("Molimo unesite jednu od dostupnih opcija!");
			}
		}

		clanovi.add(c);
		Main.save(EnumCheckpoints.CLANOVI.ordinal());
	}

	protected static void editMember(ArrayList<Clan> clanovi) {
		Scanner scanner = new Scanner(System.in);
		Main.cls();
		System.out.println("Odaberite clana cije podatke zelite da izmenite (0 za izlaz):");
		for (int i = 0; i < clanovi.size(); i++) {
			System.out.printf("%d. %s%n", i + 1, clanovi.get(i).getUUID().concat(" " + clanovi.get(i).getPunoIme()));
		}

		while (true) {
			System.out.println("Unos: ");
			String unos = scanner.nextLine();
			if (unos.equals("0")) {
				break;
			} else {
				Main.cls();
				System.out.println("Podaci o odabranom clanu: ");
				System.out.printf("ID: %s%nIme: %s%nPrezime: %s%nAdresa: %s%nBroj telefona: %s%nPol: %s%nEmail: %s%n",
						clanovi.get(Integer.parseInt(unos) - 1).getUUID(), clanovi.get(Integer.parseInt(unos) - 1).getIme(), clanovi.get(Integer.parseInt(unos) - 1).getPrezime(), clanovi.get(Integer.parseInt(unos) - 1).getAdresa(),
						clanovi.get(Integer.parseInt(unos) - 1).getBrTelefona(), SistemManager.pol(clanovi.get(Integer.parseInt(unos) - 1).getPol()), clanovi.get(Integer.parseInt(unos) - 1).getEmail());
				System.out.println("Odaberite sta zelite da izmenite (1. Ime, 2. Prezime, 3. Adresa, 4. Broj telefona, 5. Pol, 6. Email). Unesite 0 za izlaz: ");
				switch (scanner.nextLine()) {
					case "1":
						while (true) {
							System.out.println("Unesite novo ime: ");
							String tempIme = scanner.nextLine();
							if (tempIme.length() < 2 && !tempIme.matches("[a-zA-Z.]+")) {
								System.out.println("Ime moze da sardzi samo slova.");
							} else {
								clanovi.get(Integer.parseInt(unos) - 1).setIme(tempIme);
								break;
							}
						}
						break;
					case "2":
						while (true) {
							System.out.println("Unesite novo prezime: ");
							String tempPrezime = scanner.nextLine();
							if (tempPrezime.length() < 2 && !tempPrezime.matches("[a-zA-Z.\\s]+")) {
								System.out.println("Prezime moze da sardzi samo slova.");
							} else {
								clanovi.get(Integer.parseInt(unos) - 1).setPrezime(tempPrezime);
								break;
							}
						}
						break;
					case "3":
						while (true) {
							System.out.println("Unesite novi broj telefona: ");
							String tempBrTelefona = scanner.nextLine();
							if (!tempBrTelefona.matches("\\d{9,10}")) {
								System.out.println("Broj telefona mora da ima 9 ili 10 brojeva");
							} else {
								clanovi.get(Integer.parseInt(unos) - 1).setBrTelefona(tempBrTelefona);
								break;
							}
						}
						break;
					case "4":
						while (true) {
							System.out.print("Unesite adresu stanovanja: ");
							String adresa = scanner.nextLine();
							if (adresa.matches("[a-zA-Z0-9.\\s]+")) {
								clanovi.get(Integer.parseInt(unos) - 1).setAdresa(adresa);
								break;
							}
							else {
								System.out.println("Adresa ne sme da sadrzi posebne znakove, osim '.' i brojeva.");
							}
						}
						break;
					case "5":
						System.out.println("Odaberite pol: ");
						boolean petljaPol = true;
						while (petljaPol) {
							System.out.println("\n1. Musko\n2. Zensko");
							System.out.println("Unos: ");
							switch (scanner.nextInt()) {
								case 1:
									petljaPol = false;
									clanovi.get(Integer.parseInt(unos) - 1).setPol(EnumPol.MUSKO.getNum());
									break;
								case 2:
									petljaPol = false;
									clanovi.get(Integer.parseInt(unos) - 1).setPol(EnumPol.ZENSKO.getNum());
									break;
								default:
									System.out.println("Molimo unesite jednu od dostupnih opcija!");
							}
						}
					case "6":
						while (true) {
							System.out.println("Unesite novu email adresu: ");
							String tempEmail = scanner.nextLine();
							if (!tempEmail.matches("\\w+@[a-zA-Z.]+")) {
								System.out.println("Email adresa mora da sardzi znak @, i mora biti duza od 7 karaktera");
							} else {
								clanovi.get(Integer.parseInt(unos) - 1).setEmail(tempEmail);
								break;
							}
						}
						break;
					default:
						System.out.println("Unesite jednu od ponudjenih opcija.");
				}
			}
		}
		Main.save(EnumCheckpoints.CLANOVI.ordinal());
	}

	protected static void deleteMember(ArrayList<Clan> clanovi, ArrayList<Pozajmljivanje> pozajmljivanja) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			Main.cls();
			System.out.println("Odaberite clana kojeg zelite da obrisete (0 za izlaz):");
			for (int i = 0; i < clanovi.size(); i++) {
				System.out.printf("%d. %s%n", i + 1, clanovi.get(i).getUUID().concat(" " + clanovi.get(i).getPunoIme()));
			}
			System.out.println("Unos: ");
			String unos = scanner.nextLine();
			if (unos.equals("0")) {
				break;
			} else {
				if (Integer.parseInt(unos) - 1 > clanovi.size()) {
					System.out.println("Uneli ste nepostojeci redni broj.");
				} else {
					System.out.printf("Odabran clan: %s, %s%n", clanovi.get((Integer.parseInt(unos) - 1)).getUUID(), clanovi.get((Integer.parseInt(unos) - 1)).getPunoIme());
					System.out.println("Da li ste sigurni da zelite da obrisete ovog clana? (Y/N)");
					if (clanovi.get((Integer.parseInt(unos) - 1)).getPozajmljivanje().size() == 0 || clanovi.get((Integer.parseInt(unos) - 1)).getPozajmljivanje().stream().anyMatch(Pozajmljivanje::isRazreseno)) {
						boolean petlja = true;
						while (petlja) {
							switch (scanner.nextLine().toUpperCase()) {
								case "Y":
									petlja = false;
									pozajmljivanja.removeIf(p -> p.getClanUUID().equals(clanovi.get(Integer.parseInt(unos) - 1).getUUID()));
									clanovi.remove(Integer.parseInt(unos) - 1);
									System.out.println("Uspesno obrisano.");

									Main.save(EnumCheckpoints.POZAJMLJIVANJE.ordinal());
									Main.save(EnumCheckpoints.CLANOVI.ordinal());
									break;
								case "N":
									petlja = false;
									System.out.println("Brisanje otkazano.");
									break;
								default:
									System.out.println("Unesite Y ili N");
									break;
							}
						}
					} else {
						System.out.println("clan ima neresena pozajmljivanja. Nemoguce brisanje.");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}

class BookManager {


	protected static void addBook(ArrayList<Knjiga> knjige, ArrayList<Autor> autori, ArrayList<Izdavac> izdavaci) {
		Scanner scanner = new Scanner(System.in);
		Main.cls();
		System.out.println("Dodavanje nove knjige...");
		Knjiga k = new Knjiga();
		k.setId(k.generateUUID());

		while (true) {
			System.out.print("Unesite ime knjige: ");
			String knjiga = scanner.nextLine();
			if (knjiga.matches("[A-Za-z0-9:;'\"\\s]+")) {
				k.setImeKnjige(knjiga);
				break;
			}
			else {
				System.out.println("Naziv knjige ne sme da sadrzi znakove osim slova, brojeva i ';' ':' '\"' '''");
			}
		}
		while (true) {
			System.out.print("Unesite ISBN knjige: ");
			String ISBN = scanner.nextLine();
			if (ISBN.matches("[0-9-]+")) {
				k.setISBN(ISBN);
				break;
			}
			else {
				System.out.println("ISBN sme da sadrzi samo brojeve i crte '-'");
			}
		}
		while (true) {
			System.out.print("Unesite godinu objavljivanja knjige: ");
			String godObjavljivnja = scanner.nextLine();
			if (godObjavljivnja.matches("[0-9]{1,4}")) {
				k.setGodinaObjavljivanja(Integer.parseInt(godObjavljivnja));
				break;
			}
			else {
				System.out.println("Godina izdanja moze da ima izmedju 1 i 4 broja");
			}
		}
		while (true) {
			System.out.print("Unesite izdanje knjige: ");
			String izdanje = scanner.nextLine();
			if (izdanje.matches("[0-9]+")) {
				k.setIzdanje(Integer.parseInt(izdanje));
				break;
			}
			else {
				System.out.println("Izdanje sme da sadrzi samo brojeve");
			}
		}
		while (true) {
			System.out.print("Unesite broj strana knjige: ");
			String brStrana = scanner.nextLine();
			if (brStrana.matches("[0-9]+")) {
				k.setBrStrana(Integer.parseInt(brStrana));
				break;
			}
			else {
				System.out.println("Broj strana moze da bude upisan samo brojevima");
			}
		}
		while (true) {
			System.out.print("Unesite kolicinu ove knjige koja je na raspolaganju: ");
			String godObjavljivnja = scanner.nextLine();
			if (godObjavljivnja.matches("[0-9]+")) {
				k.setKolicina(Integer.parseInt(godObjavljivnja));
				break;
			}
			else {
				System.out.println("Kolicina moze da bude upisana samo brojevima");
			}
		}

		boolean petlja = true;

		while (petlja) {
			Main.cls();
			System.out.println("Autori: ");
			System.out.println("Odaberite opciju: ");
			System.out.println("1. Odaberite postojece autore\n2. Novi autor/i");
			System.out.print("Unos: ");
			switch (scanner.nextLine()) {
				case "1":
					//petlja = false;
					while (true) {
						for (int i = 0; i < autori.size(); i++) {
							System.out.printf("%d. %s%n", i + 1, autori.get(i).getId().concat(" " + autori.get(i).getFullName()));
						}
						System.out.print("Unesite redni broj autora (0 za izlaz): ");
						String unos = scanner.nextLine();
						if (unos.equals("0")) {
							break;
						} else {
							if (Integer.parseInt(unos) - 1 > autori.size()) {
								System.out.println("Uneli ste nepostojeci redni broj");
							} else {
								k.getAutori().add(autori.get(Integer.parseInt(unos) - 1));
								break;
							}
						}
					}
					break;
				case "2":
					Autor a = new Autor();
					a.setId(a.generateUUID());
					while (true) {
						System.out.print("Unesite ime autora: ");
						String ime = scanner.nextLine();
						if (ime.matches("[a-zA-Z.]+")) {
							a.setIme(ime);
							break;
						}
						else {
							System.out.println("Ime ne sme da sadrzi posebne znakove, osim '.'.");
						}
					}
					while (true) {
						System.out.print("Unesite prezime autora: ");
						String prezime = scanner.nextLine();
						if (prezime.matches("[a-zA-Z.\\s]+")) {
							a.setPrezime(prezime);
							break;
						}
						else {
							System.out.println("Prezime ne sme da sadrzi posebne znakove, osim '.'.");
						}
					}

					autori.add(a);
					Main.save(EnumCheckpoints.AUTORI.ordinal());
					k.getAutori().add(a);

					break;
				default:
					System.out.println("Molimo unesite jednu od dostupnih opcija!");
			}

			if (k.getAutori().size() > 0) {
				System.out.print("Da li zelite da dodate jos jednog autora? (Y/N): ");
				boolean petlja2 = true;
				while (petlja2) {
					switch (scanner.nextLine().toUpperCase()) {
						case "Y":
							petlja2 = false;
							break;
						case "N":
							petlja = false;
							petlja2 = false;
							break;
						default:
							System.out.println("Unesite Y ili N.");
							break;
					}
				}
			}
		}

		petlja = true;

		while (petlja) {
			Main.cls();
			System.out.println("Izdavac: ");
			System.out.println("Odaberite opciju: ");
			System.out.println("1. Odaberite postojeceg izvodjaca\n2. Novi izdavac/i");
			System.out.print("Unos: ");
			switch (scanner.nextLine()) {
				case "1":
					//petlja = false;
					while (true) {
						for (int i = 0; i < izdavaci.size(); i++) {
							System.out.printf("%d. %s%n", i + 1, izdavaci.get(i).getId().concat(" " + izdavaci.get(i).getImeIzdavaca()));
						}
						System.out.print("Unesite redni broj izdavaca (0 za izlaz): ");
						String unos = scanner.nextLine();
						if (unos.equals("0")) {
							break;
						} else {
							if (Integer.parseInt(unos) - 1 < 0 || Integer.parseInt(unos) - 1 > izdavaci.size()) {
								System.out.println("Uneli ste nepostojeci redni broj");
							} else {
								petlja = false;
								k.setIzdavac(izdavaci.get(Integer.parseInt(unos) - 1));
								break;
							}
						}
					}
					break;
				case "2":
					petlja = false;

					Izdavac i = new Izdavac();
					i.setId(i.generateUUID());
					while (true) {
						System.out.print("Unesite naziv izdavaca: ");
						String ime = scanner.nextLine();
						if (ime.matches("[a-zA-Z.\\s]+")) {
							i.setImeIzdavaca(ime);
							break;
						}
						else {
							System.out.println("naziv ne sme da sadrzi posebne znakove, osim '.'.");
						}
					}
					while (true) {
						System.out.print("Unesite zemlju porekla: ");
						String zemljaPorekla = scanner.nextLine();
						if (zemljaPorekla.matches("[a-zA-Z.\\s]+")) {
							i.setZemljaPorekla(zemljaPorekla);
							break;
						}
						else {
							System.out.println("Prezime ne sme da sadrzi posebne znakove, osim '.'.");
						}
					}

					k.setIzdavac(i);
					izdavaci.add(i);
					Main.save(EnumCheckpoints.IZDAVACI.ordinal());
					break;
				default:
					System.out.println("Molimo unesite jednu od dostupnih opcija!");
			}
		}
		boolean petlja3 = true;
		while (petlja3) {
			Main.cls();
			System.out.println("Zanrovi...");
			int[] zanrInt;
			for (EnumZanr zanr : EnumZanr.getMap().values()) {
				System.out.printf("%d. %s%n", zanr.getRedniBroj(), zanr.name());
			}
			System.out.print("\nOdaberite jedan ili vise zanrova (odvojeni praznim znakom): ");

			ArrayList<String> zanrList = new ArrayList<>(Arrays.asList(scanner.nextLine().split(" ")));

			boolean hasChar = false;
			for (String s : zanrList) {
				hasChar = s.matches("\".*\\\\c+.*\"");

				if (hasChar) {
					System.out.println("Uneli ste pogresan karakter! Dozvoljeni unosi su samo brojevi.");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				} else if (Integer.parseInt(s) < 0 || Integer.parseInt(s) > EnumZanr.getMap().size()) {
					System.out.printf("Zanr sa rednim brojem %s ne postoji. Bice uklonjen%n", s);
					zanrList.remove(s);
				}

			}
			if (hasChar) {
				continue;
			}
			System.out.println("Potvrdite sledece zanrove: ");
			for (String s : zanrList) {
				System.out.printf("%s ", s);
			}
			boolean petlja4 = true;
			while (petlja4) {
				System.out.print("\n\nUnos (Y/N): ");
				switch (scanner.nextLine()) {
					case "Y":
						petlja3 = false;
						petlja4 = false;
						zanrInt = new int[zanrList.size()];
						for (int i = 0; i < zanrInt.length; i++) {
							zanrInt[i] = Integer.parseInt(zanrList.get(i));
						}
						k.setZanrovi(zanrInt);
						break;
					case "N":
						petlja4 = false;
						break;
					default: {
						System.out.println("Unesite Y ili N!");
					}
				}
			}
		}
		while (true) {
			Main.cls();
			System.out.println("Odaberite kategoriju...");
			int kategorija;
			for (EnumKategorija zanr : EnumKategorija.getMap().values()) {
				System.out.printf("%d. %s%n", zanr.getRedniBroj(), zanr.name());
			}
			System.out.print("\nOdaberite kategoriju: ");
			try {
				kategorija = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException exception) {
				continue;
			}

			if (kategorija < 0 || kategorija > EnumKategorija.getMap().size()) {
				System.out.println("Unesite redni broj postojece kategorije!");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				k.setKategorija(kategorija);
				break;
			}
		}
		knjige.add(k);
		Main.save(EnumCheckpoints.KNJIGE.ordinal());
	}

	protected static void addAuthor(ArrayList<Autor> autori) {
		Scanner scanner = new Scanner(System.in);
		Main.cls();
		System.out.println("Dodavanje novog autora...");
		Autor a = new Autor();
		a.setId(a.generateUUID());
		while (true) {
			System.out.print("Unesite ime autora: ");
			String ime = scanner.nextLine();
			if (ime.matches("[a-zA-Z.]+")) {
				a.setIme(ime);
				break;
			}
			else {
				System.out.println("Ime ne sme da sadrzi posebne znakove, osim '.'.");
			}
		}
		while (true) {
			System.out.print("Unesite prezime autora: ");
			String prezime = scanner.nextLine();
			if (prezime.matches("[a-zA-Z.\\s]+")) {
				a.setPrezime(prezime);
				break;
			}
			else {
				System.out.println("Prezime ne sme da sadrzi posebne znakove, osim '.'.");
			}
		}

		boolean postoji = false;
		for (Autor autor : autori) {
			if (a.getIme().equals(autor.getIme()) && a.getPrezime().equals(autor.getPrezime())) {
				postoji = true;
				break;
			}
		}

		if (postoji) {
			boolean petlja = true;
			while (petlja) {
				System.out.print("Autor sa identicnim informacijama vec postoji. Da li ste sigurni da zelite da dodate novog autora? (Y/N): ");
				switch (scanner.nextLine()) {
					case "Y":
						petlja = false;
						autori.add(a);
						break;
					case "N":
						petlja = false;
						break;
					default:
						System.out.println("Unesite Y ili N!");
				}
			}
		} else {
			autori.add(a);
			Main.save(EnumCheckpoints.AUTORI.ordinal());
		}

	}

	protected static void addPublisher(ArrayList<Izdavac> izdavaci) {
		Scanner scanner = new Scanner(System.in);
		Main.cls();
		System.out.println("Dodavanje novog autora...");
		Izdavac i = new Izdavac();
		i.setId(i.generateUUID());
		while (true) {
			System.out.print("Unesite naziv izdavaca: ");
			String ime = scanner.nextLine();
			if (ime.matches("[a-zA-Z.\\s]+")) {
				i.setImeIzdavaca(ime);
				break;
			}
			else {
				System.out.println("naziv ne sme da sadrzi posebne znakove, osim '.'.");
			}
		}
		while (true) {
			System.out.print("Unesite zemlju porekla: ");
			String zemljaPorekla = scanner.nextLine();
			if (zemljaPorekla.matches("[a-zA-Z.\\s]+")) {
				i.setZemljaPorekla(zemljaPorekla);
				break;
			}
			else {
				System.out.println("Prezime ne sme da sadrzi posebne znakove, osim '.'.");
			}
		}

		boolean postoji = false;
		for (Izdavac izdavac : izdavaci) {
			if (i.getImeIzdavaca().equals(izdavac.getImeIzdavaca()) && i.getZemljaPorekla().equals(izdavac.getZemljaPorekla())) {
				postoji = true;
				break;
			}
		}

		if (postoji) {
			boolean petlja = true;
			while (petlja) {
				System.out.print("Izdavac sa identicnim informacijama ec postoji. Da li ste sigurni da zelite da dodate novog izdavaca? (Y/N): ");
				switch (scanner.nextLine()) {
					case "Y":
						petlja = false;
						izdavaci.add(i);
						break;
					case "N":
						petlja = false;
						break;
					default:
						System.out.println("Unesite Y ili N!");
				}
			}
		} else {
			izdavaci.add(i);
			Main.save(EnumCheckpoints.IZDAVACI.ordinal());
		}
	}

	protected static void editBook(ArrayList<Knjiga> knjige, ArrayList<Autor> autori, ArrayList<Izdavac> izdavaci) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			Main.cls();
			System.out.println("Odaberite knjigu cije podatke zelite da izmenite (0 za izlaz):");
			for (int i = 0; i < knjige.size(); i++) {
				System.out.printf("%d. %s%n", i + 1, knjige.get(i).getId().concat(" " + knjige.get(i).getImeKnjige()));
			}
			System.out.println("Unos: ");
			String unos = scanner.nextLine();
			if (unos.equals("0")) {
				break;
			} else if (unos.equals("")) {
				continue; //Nepotrebno, ali ubaceno zbog citljivosti
			} else {
				boolean petlja = true;
				while (petlja) {
					Main.cls();
					System.out.println("Podaci o odabranoj knjizi: ");
					StringBuilder autoriBuilder = new StringBuilder();
					for (Autor a : knjige.get(Integer.parseInt(unos) -1 ).getAutori()) {
						autoriBuilder.append(a.getFullName()).append(" ");
					}
					System.out.printf("ID: %s, ISBN: %s, Naziv: %s, Autori: %s, Izdavac: %s, Zanrovi: %s, god. objavljivanja: %d, Izdanje: %d, Broj strana %d, Kategorija: %s, Kolicina: %d",
							knjige.get(Integer.parseInt(unos) - 1).getId(),
							knjige.get(Integer.parseInt(unos) - 1).getISBN(),
							knjige.get(Integer.parseInt(unos) - 1).getImeKnjige(),
							autoriBuilder,
							knjige.get(Integer.parseInt(unos) - 1).getIzdavac(),
							Arrays.toString(knjige.get(Integer.parseInt(unos) - 1).getZanrovi()), //TODO
							knjige.get(Integer.parseInt(unos) - 1).getGodinaObjavljivanja(),
							knjige.get(Integer.parseInt(unos) - 1).getIzdanje(),
							knjige.get(Integer.parseInt(unos) - 1).getBrStrana(),
							EnumKategorija.getKategorija(knjige.get(Integer.parseInt(unos) - 1).getKategorija()).name(), //TODO
							knjige.get(Integer.parseInt(unos) - 1).getKolicina());
					System.out.println("Odaberite sta zelite da izmenite: ");
					System.out.print("1. ISBN, 2. Naziv, 3. Autori, 4. Izdavac, 5. Zanrovi, 6. Godinu objavljivanja, 7. Izdanje, 8. Broj strana, 9. Kategoruja, 10. Kolicina\n" +
							"(0 za izlaz): ");
					switch (scanner.nextLine()) {
						case "1":
							while (true) {
								System.out.print("Unesite novi ISBN knjige: ");
								String ISBN = scanner.nextLine();
								if (ISBN.matches("[0-9-]+")) {
									knjige.get(Integer.parseInt(unos) - 1).setISBN(ISBN);
									break;
								}
								else {
									System.out.println("ISBN moze da sadrzi samo brojeve i crticu '-'");
								}
							}
							break;
						case "2":
							while (true) {
								System.out.print("Unesite novi naziv knjige: ");
								String knjiga = scanner.nextLine();
								if (knjiga.matches("[A-Za-z0-9:;'\"\\s]+")) {
									knjige.get(Integer.parseInt(unos) - 1).setImeKnjige(knjiga);
									break;
								}
								else {
									System.out.println("Naziv knjige ne sme da sadrzi znakove osim slova, brojeva i ';' ':' '\"' '''");
								}
							}
							break;
						case "3":
							boolean petlja2 = true;

							while (petlja2) {
								Main.cls();
								System.out.println("Odaberite nove autore");
								System.out.println("Odaberite opciju: ");
								System.out.println("1. Odaberite postojece autore\n2. Novi autor/i");
								System.out.print("Unos: ");
								switch (scanner.nextLine()) {
									case "1":
										//petlja = false;
										while (true) {
											for (int i = 0; i < autori.size(); i++) {
												System.out.printf("%d. %s%n", i + 1, autori.get(i).getId().concat(" " + autori.get(i).getFullName()));
											}
											System.out.print("Unesite redni broj autora (0 za izlaz): ");
											String unos2 = scanner.nextLine();
											if (unos2.equals("0")) {
												break;
											} else {
												if (Integer.parseInt(unos2) - 1 > autori.size()) {
													System.out.println("Uneli ste nepostojeci redni broj");
												} else {
													knjige.get(Integer.parseInt(unos) - 1).getAutori().add(autori.get(Integer.parseInt(unos2) - 1));
													break;
												}
											}
										}
										break;
									case "2":
										Autor a = new Autor();
										a.setId(a.generateUUID());
										while (true) {
											System.out.print("Unesite ime autora: ");
											String ime = scanner.nextLine();
											if (ime.matches("[a-zA-Z.]")) {
												a.setIme(ime);
												break;
											}
											else {
												System.out.println("Ime ne sme da sadrzi posebne znakove, osim '.'.");
											}
										}
										while (true) {
											System.out.print("Unesite prezime autora: ");
											String prezime = scanner.nextLine();
											if (prezime.matches("[a-zA-Z.]")) {
												a.setPrezime(prezime);
												break;
											}
											else {
												System.out.println("Prezime ne sme da sadrzi posebne znakove, osim '.'.");
											}
										}

										autori.add(a);
										Main.save(EnumCheckpoints.AUTORI.ordinal());
										knjige.get(Integer.parseInt(unos) - 1).getAutori().add(a);

										break;
									default:
										System.out.println("Molimo unesite jednu od dostupnih opcija!");
								}

								if (knjige.get(Integer.parseInt(unos) - 1).getAutori().size() > 0) {
									System.out.print("Da li zelite da dodate jos jednog autora? (Y/N): ");
									boolean petlja3 = true;
									while (petlja3) {
										switch (scanner.nextLine().toUpperCase()) {
											case "Y":
												break;
											case "N":
												petlja2 = false;
												petlja3 = false;
												break;
											default:
												System.out.println("Unesite Y ili N.");
												break;
										}
									}
								}
							}
							break;
						case "4":
							petlja = true;
							while (petlja) {
								Main.cls();
								System.out.println("Odaberite izdavaca");
								System.out.println("Odaberite opciju: ");
								System.out.println("1. Odaberite postojeceg izvodjaca\n2. Novi izdavac/i");
								System.out.print("Unos: ");
								switch (scanner.nextLine()) {
									case "1":
										petlja = false;
										while (true) {
											for (int i = 0; i < izdavaci.size(); i++) {
												System.out.printf("%d. %s%n", i + 1, izdavaci.get(i).getId().concat(" " + izdavaci.get(i).getImeIzdavaca()));
											}
											System.out.print("Unesite redni broj izdavaca (0 za izlaz): ");
											String unos3 = scanner.nextLine();
											if (unos3.equals("0")) {
												break;
											} else {
												if (Integer.parseInt(unos) - 1 > izdavaci.size()) {
													System.out.println("Uneli ste nepostojeci redni broj");
												} else {
													knjige.get(Integer.parseInt(unos) - 1).setIzdavac(izdavaci.get(Integer.parseInt(unos3) - 1));
													break;
												}
											}
										}
										break;
									case "2":
										petlja = false;

										Izdavac i = new Izdavac();
										i.setId(i.generateUUID());
										while (true) {
											System.out.print("Unesite naziv izdavaca: ");
											String ime = scanner.nextLine();
											if (ime.matches("[a-zA-Z.]")) {
												i.setImeIzdavaca(ime);
												break;
											}
											else {
												System.out.println("naziv ne sme da sadrzi posebne znakove, osim '.'.");
											}
										}
										while (true) {
											System.out.print("Unesite zemlju porekla: ");
											String zemljaPorekla = scanner.nextLine();
											if (zemljaPorekla.matches("[a-zA-Z.]")) {
												i.setZemljaPorekla(zemljaPorekla);
												break;
											}
											else {
												System.out.println("Prezime ne sme da sadrzi posebne znakove, osim '.'.");
											}
										}

										knjige.get(Integer.parseInt(unos) - 1).setIzdavac(i);
										izdavaci.add(i);
										Main.save(EnumCheckpoints.IZDAVACI.ordinal());
										break;
									default:
										System.out.println("Molimo unesite jednu od dostupnih opcija!");
								}
							}
							break;
						case "5":
							boolean petlja4 = true;
							while (petlja4) {
								Main.cls();
								System.out.println("Odaberite nove zanrove");
								int[] zanrInt;
								for (EnumZanr zanr : EnumZanr.getMap().values()) {
									System.out.printf("%d. %s%n", zanr.getRedniBroj(), zanr.name());
								}
								System.out.print("\nOdaberite jedan ili vise zanrova (odvojeni praznim znakom): ");

								ArrayList<String> zanrList = new ArrayList<>(Arrays.asList(scanner.nextLine().split(" ")));

								boolean hasChar = false;
								for (String s : zanrList) {
									hasChar = s.matches("\".*\\\\c+.*\"");

									if (hasChar) {
										System.out.println("Uneli ste pogresan karakter! Dozvoljeni unosi su samo brojevi.");
										try {
											Thread.sleep(1000);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
										break;
									} else if (Integer.parseInt(s) < 1 || Integer.parseInt(s) > EnumZanr.getMap().size()) {
										System.out.printf("Zanr sa rednim brojem %s ne postoji. Bice uklonjen%n", s);
										zanrList.remove(s);
									}
								}
								if (hasChar) {
									continue;
								}
								System.out.println("Potvrdite sledece zanrove: ");
								for (String s : zanrList) {
									System.out.printf("%s ", s);
								}
								boolean petlja5 = true;
								while (petlja5) {
									System.out.print("\n\nUnos (Y/N): ");
									switch (scanner.nextLine()) {
										case "Y":
											petlja4 = false;
											petlja5 = false;
											zanrInt = new int[zanrList.size()];
											for (int i = 0; i < zanrInt.length; i++) {
												zanrInt[i] = Integer.parseInt(zanrList.get(i));
											}
											knjige.get(Integer.parseInt(unos) - 1).setZanrovi(zanrInt);
											break;
										case "N":
											petlja4 = false;
											break;
										default: {
											System.out.println("Unesite Y ili N!");
										}
									}
								}
							}

						case "6":
							while (true) {
								System.out.print("Unesite godinu objavljivanja knjige: ");
								String godObjavljivnja = scanner.nextLine();
								if (godObjavljivnja.matches("[0-9]{1,4}")) {
									knjige.get(Integer.parseInt(unos) - 1).setGodinaObjavljivanja(Integer.parseInt(godObjavljivnja));
									break;
								}
								else {
									System.out.println("Godina izdanja moze da ima izmedju 1 i 4 broja");
								}
							}
							break;
						case "7":
							while (true) {
								System.out.print("Unesite izdanje knjige: ");
								String izdanje = scanner.nextLine();
								if (izdanje.matches("[0-9]+")) {
									knjige.get(Integer.parseInt(unos) - 1).setIzdanje(Integer.parseInt(izdanje));
									break;
								}
								else {
									System.out.println("Izdanje sme da sadrzi samo brojeve");
								}
							}
							break;
						case "8":
							while (true) {
								System.out.print("Unesite broj strana knjige: ");
								String brStrana = scanner.nextLine();
								if (brStrana.matches("[0-9]+")) {
									knjige.get(Integer.parseInt(unos) - 1).setBrStrana(Integer.parseInt(brStrana));
									break;
								}
								else {
									System.out.println("Broj strana moze da bude upisan samo brojevima");
								}
							}
							break;
						case "9":
							while (true) {
								Main.cls();
								System.out.println("Odaberite novu kategoriju");
								int kategorija;
								for (EnumKategorija zanr : EnumKategorija.getMap().values()) {
									System.out.printf("%d. %s%n", zanr.getRedniBroj(), zanr.name());
								}
								System.out.print("\nOdaberite kategoriju: ");
								kategorija = Integer.parseInt(scanner.nextLine());
								if (kategorija < 0 || kategorija > EnumKategorija.getMap().size()) {
									System.out.println("Unesite redni broj postojece kategorije!");
									try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								} else {
									knjige.get(Integer.parseInt(unos) - 1).setKategorija(kategorija);
									break;
								}
							}
							break;
						case "10":
							while (true) {
								System.out.print("Unesite kolicinu ove knjige koja je na raspolaganju: ");
								String godObjavljivnja = scanner.nextLine();
								if (godObjavljivnja.matches("[0-9]+")) {
									knjige.get(Integer.parseInt(unos) - 1).setKolicina(Integer.parseInt(godObjavljivnja));
									break;
								}
								else {
									System.out.println("Kolicina moze da bude upisana samo brojevima");
								}
							}
							break;
						case "0":
							petlja = false;
							break;
						default:
							System.out.println("Uneli ste nepostojecu opciju.");
							break;
					}
				}
			}
		}
		Main.save(EnumCheckpoints.KNJIGE.ordinal());
	}

	protected static void editAuthor(ArrayList<Autor> autori) {
		Scanner scanner = new Scanner(System.in);
		Main.cls();
		System.out.println("Odaberite autora cije podatke zelite da izmenite (0 za izlaz):");
		for (int i = 0; i < autori.size(); i++) {
			System.out.printf("%d. %s%n", i + 1, autori.get(i).getId().concat(" " + autori.get(i).getFullName()));
		}

		while (true) {
			System.out.println("Unos: ");
			String unos = scanner.nextLine();
			if (unos.equals("0")) {
				break;
			} else {
				Main.cls();
				System.out.println("Podaci o odabranom autoru: ");
				System.out.printf("ID: %s%nIme: %s%nPrezime: %s%n", autori.get(Integer.parseInt(unos) - 1).getId(), autori.get(Integer.parseInt(unos) - 1).getIme(), autori.get(Integer.parseInt(unos) - 1).getPrezime());
				System.out.println("Odaberite sta zelite da izmenite (1. Ime, 2. Prezime). Unesite 0 za izlaz: ");
				switch (scanner.nextLine()) {
					case "1":
						while (true) {
							System.out.print("Unesite novo ime autora: ");
							String ime = scanner.nextLine();
							if (ime.matches("[a-zA-Z.]+")) {
								autori.get(Integer.parseInt(unos) - 1).setIme(ime);
								break;
							}
							else {
								System.out.println("Ime ne sme da sadrzi posebne znakove, osim '.'.");
							}
						}
						break;
					case "2":
						while (true) {
							System.out.print("Unesite novo prezime autora: ");
							String prezime = scanner.nextLine();
							if (prezime.matches("[a-zA-Z.\\s]+")) {
								autori.get(Integer.parseInt(unos) - 1).setPrezime(prezime);
								break;
							}
							else {
								System.out.println("Prezime ne sme da sadrzi posebne znakove, osim '.'.");
							}
						}
						break;
					default:
						break;
				}
				Main.save(EnumCheckpoints.AUTORI.ordinal());
			}
		}
	}

	protected static void editPublisher(ArrayList<Izdavac> izdavaci) {
		Scanner scanner = new Scanner(System.in);
		Main.cls();
		System.out.println("Odaberite izdavaca cije podatke zelite da izmenite (0 za izlaz):");
		for (int i = 0; i < izdavaci.size(); i++) {
			System.out.printf("%d. %s%n", i + 1, izdavaci.get(i).getId().concat(" " + izdavaci.get(i).getImeIzdavaca() + " " + izdavaci.get(i).getZemljaPorekla()));
		}

		while (true) {
			System.out.println("Unos: ");
			String unos = scanner.nextLine();
			if (unos.equals("0")) {
				break;
			} else {
				Main.cls();
				System.out.println("Podaci o odabranom izdavacu: ");
				System.out.printf("ID: %s%nIme izdavaca: %s%nZemlja porekla: %s%n", izdavaci.get(Integer.parseInt(unos) - 1).getId(), izdavaci.get(Integer.parseInt(unos) - 1).getImeIzdavaca(), izdavaci.get(Integer.parseInt(unos) - 1).getZemljaPorekla());
				System.out.println("Odaberite sta zelite da izmenite (1. Ime izdavaca, 2. Zemlja porekla). Unesite 0 za izlaz: ");
				switch (scanner.nextLine()) {
					case "1":
						while (true) {
							System.out.print("Unesite nov naziv izdavaca: ");
							String ime = scanner.nextLine();
							if (ime.matches("[a-zA-Z.\\s]+")) {
								izdavaci.get(Integer.parseInt(unos) - 1).setImeIzdavaca(ime);
								break;
							}
							else {
								System.out.println("Naziv ne sme da sadrzi posebne znakove, osim '.'.");
							}
						}
						break;
					case "2":
						while (true) {
							System.out.print("Unesite novu zemlju porekla: ");
							String zemljaPorekla = scanner.nextLine();
							if (zemljaPorekla.matches("[a-zA-Z\\s]+")) {
								izdavaci.get(Integer.parseInt(unos) - 1).setZemljaPorekla(zemljaPorekla);
								break;
							}
							else {
								System.out.println("Zemlja porekla ne sme da sadrzi posebne znakove.");
							}
						}
						break;
					default:
						break;
				}
				Main.save(EnumCheckpoints.IZDAVACI.ordinal());
			}
		}
	}

	protected static void deleteBook(ArrayList<Knjiga> knjige, ArrayList<Pozajmljivanje> pozajmljivanja) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			Main.cls();
			System.out.println("Odaberite knjigu koju zelite da obrisete (0 za izlaz):");
			for (int i = 0; i < knjige.size(); i++) {
				System.out.printf("%d. %s%n", i + 1, knjige.get(i).getId().concat(" " + knjige.get(i).getImeKnjige() + " " + knjige.get(i).getISBN()));
			}
			System.out.println("Unos: ");
			String unos = scanner.nextLine();
			if (unos.equals("0")) {
				break;
			} else {
				if (Integer.parseInt(unos) - 1 > knjige.size()) {
					System.out.println("Uneli ste nepostojeci redni broj.");
				} else {

					boolean petlja = true;
					boolean postoji = false;
					for (Pozajmljivanje p : pozajmljivanja) {
						if (p.getPozajmljenaKnjiga() == knjige.get(Integer.parseInt(unos) - 1)) {
							postoji = true;
							break;
						}
					}
					if (postoji) {
						petlja = false;
						System.out.println("Postoji knjiga kojoj je ovaj autor dodeljen. Brisanje blokirano.");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					while (petlja) {
						System.out.printf("Odabran clan: %s, %s%n", knjige.get((Integer.parseInt(unos) - 1)).getId(), knjige.get((Integer.parseInt(unos) - 1)).getISBN().concat(" " + knjige.get((Integer.parseInt(unos) - 1)).getImeKnjige()));
						System.out.println("Da li ste sigurni da zelite da obrisete ovu knjigu? (Y/N)");
						switch (scanner.nextLine().toUpperCase()) {
							case "Y":
								petlja = false;
								knjige.remove(Integer.parseInt(unos) - 1);
								System.out.println("Uspesno obrisano.");
								Main.save(EnumCheckpoints.KNJIGE.ordinal());
								break;
							case "N":
								petlja = false;
								System.out.println("Brisanje otkazano.");
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								break;
							default:
								System.out.println("Unesite Y ili N");
								break;
						}
					}
				}
			}
		}
	}

	protected static void deleteAuthor(ArrayList<Autor> autori, ArrayList<Knjiga> knjige) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			Main.cls();
			System.out.println("Odaberite autora kojeg zelite da obrisete (0 za izlaz):");
			for (int i = 0; i < autori.size(); i++) {
				System.out.printf("%d. %s%n", i + 1, autori.get(i).getId().concat(" " + autori.get(i).getFullName()));
			}
			System.out.println("Unos: ");
			String unos = scanner.nextLine();
			if (unos.equals("0")) {
				break;
			} else {
				if (Integer.parseInt(unos) - 1 > autori.size()) {
					System.out.println("Uneli ste nepostojeci redni broj.");
				} else {
					System.out.printf("Odabran autor: %s, %s%n", autori.get((Integer.parseInt(unos) - 1)).getId(), autori.get((Integer.parseInt(unos) - 1)).getFullName());
					System.out.println("Da li ste sigurni da zelite da obrisete ovog autora? (Y/N)");
					boolean petlja = true;
					if (knjige.stream().findAny().get().getAutori().stream().anyMatch(a -> a.getId().equals(autori.get((Integer.parseInt(unos) - 1)).getId()))) {
						petlja = false;
						System.out.println("Postoji knjiga kojoj je ovaj autor dodeljen. Brisanje blokirano.");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					while (petlja) {
						switch (scanner.nextLine().toUpperCase()) {
							case "Y":
								petlja = false;
								autori.remove(Integer.parseInt(unos) - 1);
								System.out.println("Uspesno obrisano.");
								Main.save(EnumCheckpoints.AUTORI.ordinal());
								break;
							case "N":
								petlja = false;
								System.out.println("Brisanje otkazano.");
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								break;
							default:
								System.out.println("Unesite Y ili N");
								break;
						}
					}
				}
			}
		}
	}

	protected static void deletePublisher(ArrayList<Izdavac> izdavaci, ArrayList<Knjiga> knjige) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			Main.cls();
			System.out.println("Odaberite izdavaca kojeg zelite da obrisete (0 za izlaz):");
			for (int i = 0; i < izdavaci.size(); i++) {
				System.out.printf("%d. %s%n", i + 1, izdavaci.get(i).getId().concat(" " + izdavaci.get(i).getImeIzdavaca() + " " + izdavaci.get(i).getZemljaPorekla()));
			}
			System.out.println("Unos: ");
			String unos = scanner.nextLine();
			if (unos.equals("0")) {
				break;
			} else {
				if (Integer.parseInt(unos) - 1 > izdavaci.size()) {
					System.out.println("Uneli ste nepostojeci redni broj.");
				} else {

					boolean petlja = true;
					boolean postoji =false;
					for (Knjiga k : knjige) {
						if (k.getIzdavac() == izdavaci.get(Integer.parseInt(unos) - 1)) {
							postoji = true;
							break;
						}
					}
					if (postoji) {
						petlja = false;
						System.out.println("Postoji knjiga kojoj je ovaj izdavac dodeljen. Brisanje blokirano.");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					while (petlja) {
						System.out.printf("Odabran izdavac: %s, %s%n", izdavaci.get((Integer.parseInt(unos) - 1)).getId(), izdavaci.get((Integer.parseInt(unos) - 1)).getImeIzdavaca().concat(" " + izdavaci.get((Integer.parseInt(unos) - 1)).getZemljaPorekla()));
						System.out.print("Da li ste sigurni da zelite da obrisete ovog izdavaca? (Y/N): ");
						switch (scanner.nextLine().toUpperCase()) {
							case "Y":
								petlja = false;
								izdavaci.remove(Integer.parseInt(unos) - 1);
								System.out.println("Uspesno obrisano.");
								Main.save(EnumCheckpoints.IZDAVACI.ordinal());
								break;
							case "N":
								petlja = false;
								System.out.println("Brisanje otkazano.");
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								break;
							default:
								System.out.println("Unesite Y ili N");
								break;
						}
					}
				}
			}
		}
	}
}