package projekat.sistem;

import projekat.Main;
import projekat.knjiga.*;
import projekat.osoba.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SistemManager {
	public static void initPozajmljivanje(int odabir, ArrayList<Clan> clanovi, ArrayList<Pozajmljivanje> pozajmljivanja, ArrayList<Knjiga> knjige) {
		switch (odabir) {
			case 1:

				break;
			case 2:
				break;
		}
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
	public static void initBookManager(int opcija, ArrayList<Knjiga> knjigaList, ArrayList<Autor> autorList, ArrayList<Izdavac> izdavacList) {
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
					BookManager.deleteBook(knjigaList, autorList, izdavacList);
				}
				break;
			default: {

			}
		}
	}
	public static void initAuthorManager(int opcija, ArrayList<Autor> autorList) {
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
					BookManager.deleteAuthor(autorList);
				}
				break;
		}
	}
	public static void initPublisherManager(int opcija, ArrayList<Izdavac> izdavacList) {
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
					BookManager.deletePublisher(izdavacList);
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
			System.out.print("\nDa li zelite da izmenite pravila? [Y/N]: "); String odabir = scanner.nextLine();
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

			System.out.print("\nOdaberite pravilo koje zelite da izmenite (0 za izlaz): "); odabir = scanner.nextLine();
			switch (odabir) {
				case "1":
					Main.cls();
					System.out.println("Menjanje pravila 'Koliko dugo knjiga moze da bude pozajmljena?'");
					System.out.println("Unesite broj: "); pravilaBiblioteke.maxPeriod(scanner.nextInt());
					scanner.nextLine();
					break;
				case "2":
					Main.cls();
					System.out.println("Menjanje pravila 'Koliko puta moze da se produzi pozajmljivanje jedne knjige?'");
					System.out.println("Unesite broj: "); pravilaBiblioteke.maxReloan(scanner.nextInt());
					scanner.nextLine();
					break;
				case "3":
					Main.cls();
					System.out.println("Menjanje pravila 'Koliko puta moze da se produzi pozajmljivanje jedne knjige?'");
					System.out.println("Unesite broj (Sa ili bez decimalnog zareza): "); pravilaBiblioteke.multiplier(scanner.nextDouble());
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
		}
		else if (pol == 2) {
			return EnumPol.ZENSKO.name();
		}
		else {
			return "NEODREDJENO";
		}
	}
}

class PozajmljivanjeManager {
	protected static void showLoans(ArrayList<Pozajmljivanje> pozajmljivanja) {
		for (Pozajmljivanje p : pozajmljivanja) {
			if (!p.isRazreseno()) {
				System.out.println(p.toString());
			}
		}
	}

	protected static void editLoans(ArrayList<Pozajmljivanje> pozajmljivanja) {

	}
}

class AdminManager {
	//TODO: Testiranje, provera
	protected static void addAdmin(ArrayList<Administrator> adminList, ArrayList<Administrator.Dozvole> dozvole, ArrayList<Sifra> sifre) {
		Scanner scanner = new Scanner(System.in);
		Main.cls();
		System.out.println("Dodavanje novog administratora...");
		Administrator a = new Administrator();

		a.setUUID(a.generateUUID());
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
	//TODO: Testiranje, provera
	protected static void editAdmin(ArrayList<Administrator> adminList) {
		Scanner scanner = new Scanner(System.in);
		Main.cls();
		System.out.println("Odaberite administratora cije podatke zelite da izmenite (0 za izlaz):");
		for (int i = 0; i < adminList.size(); i++) {
			System.out.printf("%d. %s%n", i+1, adminList.get(i).getUUID().concat(" " + adminList.get(i).getUsername()));
		}
		while (true) {
			System.out.println("Unos: "); int unos = scanner.nextInt();
			if (unos == 0) {
				break;
			}
			else {
				if (unos - 1 > adminList.size()) {
					System.out.println("Uneli ste nepostojeci redni broj.");
				}
				else {
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
									System.out.println("Unesite novo ime: "); String tempIme = scanner.nextLine();
									if (tempIme.length() < 2) {
										System.out.println("Ime moze da sardzi samo slova.");
									}
									else {
										petlja = false;
										adminList.get(unos - 1).setIme(tempIme);
										break;
									}
								}
								break;
							case 2:
								while (true) {
									System.out.println("Unesite novo prezime: "); String tempPrezime = scanner.nextLine();
									if (tempPrezime.length() < 2) {
										System.out.println("Prezime moze da sardzi samo slova.");
									}
									else {
										petlja = false;
										adminList.get(unos - 1).setPrezime(tempPrezime);
										break;
									}
								}
								break;
							case 3:
								while (true) {
									System.out.println("Unesite novu adresu: "); String tempAdresa = scanner.nextLine();
									if (tempAdresa.length() < 2) {
										System.out.println("Adresa mora biti duza od 2 karaktera i ne sme da sadrzi specijalne znakove.");
									}
									else {
										petlja = false;
										adminList.get(unos - 1).setAdresa(tempAdresa);
										break;
									}
								}
								break;
							case 4:
								while (true) {
									System.out.println("Unesite novi broj telefona: "); String tempBrTelefona = scanner.nextLine();
									if (tempBrTelefona.length() < 9 || tempBrTelefona.length() > 10) {
										System.out.println("Broj telefona mora da ima 9 ili 10 brojeva");
									}
									else {
										petlja = false;
										adminList.get(unos - 1).setBrTelefona(tempBrTelefona);
										break;
									}
								}
								break;
							case 5:
								while (true) {
									System.out.println("Unesite novu email adresu: "); String tempEmail = scanner.nextLine();
									if (tempEmail.length() < 7 || !tempEmail.contains("@")) {
										System.out.println("Email adresa mora da sardzi znak @, i mora biti duza od 7 karaktera");
									}
									else {
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
							case 7:
								while (true) {
									System.out.println("Unesite novo korisnicko ime: "); String tempUsername = scanner.nextLine();
									if (tempUsername.length() < 6) {
										System.out.println("Korisnicko ime mora biti duze od 6 karaktera");
									}
									else {
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
									}
									else {
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
									System.out.println("Unesite novu lozinku (Unesite 0 za otkazivanje): "); String tempSifra = scanner.nextLine();
									if (tempSifra.equals("0")) {
										break;
									}
									System.out.println("Ponovo unesite lozinku");
									if (scanner.nextLine().equals(tempSifra)) {
										adminList.get(unos - 1).getPassword().encryptSifra(tempSifra);
										System.out.println("Sifra promenjena");
										Main.save(EnumCheckpoints.SIFRE.ordinal());
										break;
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
	//TODO: Testiranje, provera
	protected static void deleteAdmin(ArrayList<Administrator> adminList, ArrayList<Sifra> sifre, ArrayList<Administrator.Dozvole> dozvole) {
		Scanner scanner = new Scanner(System.in);
		Main.cls();
		System.out.println("Odaberite administratora cije podatke zelite da izmenite (0 za izlaz):");
		for (int i = 0; i < adminList.size(); i++) {
			System.out.printf("%d. %s%n", i+1, adminList.get(i).getUUID().concat(" " + adminList.get(i).getUsername()));
		}
		while (true) {
			System.out.println("Unos: "); String unos = scanner.nextLine();
			if (unos.equals("0")) {
				break;
			}
			else {
				if (Integer.parseInt(unos) - 1 > adminList.size()) {
					System.out.println("Uneli ste nepostojeci redni broj.");
				}
				else {
					System.out.printf("Odabran administrator: %s, %s%n", adminList.get((Integer.parseInt(unos) - 1)).getUUID(), adminList.get((Integer.parseInt(unos) - 1)).getUsername());
					System.out.println("Da li ste sigurni da zelite da obrisete ovog administratora? (Y/N)");
					boolean petlja = true;
					while (petlja) {
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
	//TODO: Testiranje, provera
	protected static void addMember(ArrayList<Clan> clanovi) {
		Scanner scanner = new Scanner(System.in);
		Main.cls();
		System.out.println("Dodavanje novog clana...");
		Clan c = new Clan();
		c.setUUID(c.generateUUID());
		System.out.print("Unesite ime: "); c.setIme(scanner.nextLine());
		System.out.print("Unesite prezime: "); c.setPrezime(scanner.nextLine());
		System.out.print("Unesite adresu stanovanja: "); c.setAdresa(scanner.nextLine());
		System.out.print("Unesite broj telefona: "); c.setBrTelefona(scanner.nextLine());
		System.out.print("Unesite email: "); c.setEmail(scanner.nextLine());

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
	//TODO: Testiranje, provera
	protected static void editMember(ArrayList<Clan> clanovi) {
		Scanner scanner = new Scanner(System.in);
		Main.cls();
		System.out.println("Odaberite clana cije podatke zelite da izmenite (0 za izlaz):");
		for (int i = 0; i < clanovi.size(); i++) {
			System.out.printf("%d. %s%n", i+1, clanovi.get(i).getUUID().concat(" " + clanovi.get(i).getPunoIme()));
		}

		while (true) {
			System.out.println("Unos: "); String unos = scanner.nextLine();
			if (unos.equals("0")) {
				break;
			}
			else {
				Main.cls();
				System.out.println("Podaci o odabranom clanu: ");
				System.out.printf("ID: %s%nIme: %s%nPrezime: %s%nBroj telefona %s%nPol: %s%nEmail: %s%n",
						clanovi.get(Integer.parseInt(unos) - 1).getUUID(), clanovi.get(Integer.parseInt(unos) - 1).getIme(), clanovi.get(Integer.parseInt(unos) - 1).getPrezime(),
						clanovi.get(Integer.parseInt(unos) - 1).getBrTelefona(), SistemManager.pol(clanovi.get(Integer.parseInt(unos) - 1).getPol()), clanovi.get(Integer.parseInt(unos) - 1).getEmail());
				System.out.println("Odaberite sta zelite da izmenite (1. Ime, 2. Prezime, 3. Broj telefona, 4. Pol, 5. Email, 6. Pogledaj pozajmljivanja clana). Unesite 0 za izlaz: ");
				switch (scanner.nextLine()) {
					case "1":
						while (true) {
							System.out.println("Unesite novo ime: "); String tempIme = scanner.nextLine();
							if (tempIme.length() < 2) {
								System.out.println("Ime moze da sardzi samo slova.");
							}
							else {
								clanovi.get(Integer.parseInt(unos) - 1).setIme(tempIme);
								break;
							}
						}
						break;
					case "2":
						while (true) {
							System.out.println("Unesite novo prezime: "); String tempPrezime = scanner.nextLine();
							if (tempPrezime.length() < 2) {
								System.out.println("Prezime moze da sardzi samo slova.");
							}
							else {
								clanovi.get(Integer.parseInt(unos) - 1).setPrezime(tempPrezime);
								break;
							}
						}
						break;
					case "3":
						while (true) {
							System.out.println("Unesite novi broj telefona: "); String tempBrTelefona = scanner.nextLine();
							if (tempBrTelefona.length() < 9 || tempBrTelefona.length() > 10) {
								System.out.println("Broj telefona mora da ima 9 ili 10 brojeva");
							}
							else {
								clanovi.get(Integer.parseInt(unos) - 1).setBrTelefona(tempBrTelefona);
								break;
							}
						}
						break;
					case "4":
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
					case "5":
						while (true) {
							System.out.println("Unesite novu email adresu: "); String tempEmail = scanner.nextLine();
							if (tempEmail.length() < 7 || !tempEmail.contains("@")) {
								System.out.println("Email adresa mora da sardzi znak @, i mora biti duza od 7 karaktera");
							}
							else {
								clanovi.get(Integer.parseInt(unos) - 1).setEmail(tempEmail);
								break;
							}
						}
						break;
					case "6":
						//TODO: Prikazivanje pozajmljivanja
					default:
				}
			}
		}
		Main.save(EnumCheckpoints.CLANOVI.ordinal());
	}
	//TODO: Testiranje, provera
	protected static void deleteMember(ArrayList<Clan> clanovi, ArrayList<Pozajmljivanje> pozajmljivanja) {
		Scanner scanner = new Scanner(System.in);
		Main.cls();
		System.out.println("Odaberite clana kojeg zelite da obrisete (0 za izlaz):");
		for (int i = 0; i < clanovi.size(); i++) {
			System.out.printf("%d. %s%n", i+1, clanovi.get(i).getUUID().concat(" " + clanovi.get(i).getPunoIme()));
		}
		while (true) {
			System.out.println("Unos: "); String unos = scanner.nextLine();
			if (unos.equals("0")) {
				break;
			}
			else {
				if (Integer.parseInt(unos) - 1 > clanovi.size()) {
					System.out.println("Uneli ste nepostojeci redni broj.");
				}
				else {
					System.out.printf("Odabran clan: %s, %s%n", clanovi.get((Integer.parseInt(unos) - 1)).getUUID(), clanovi.get((Integer.parseInt(unos) - 1)).getPunoIme());
					System.out.println("Da li ste sigurni da zelite da obrisete ovog clana? (Y/N)");
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
				}
			}
		}
	}
}

class BookManager {

	//TODO: Testiranje, provera
	protected static void addBook(ArrayList<Knjiga> knjige, ArrayList<Autor> autori, ArrayList<Izdavac> izdavaci) {
		Scanner scanner = new Scanner(System.in);
		Main.cls();
		System.out.println("Dodavanje nove knjige...");
		Knjiga k = new Knjiga();
		k.setId(k.generateUUID());

		System.out.print("Unesite ime knjige: "); k.setImeKnjige(scanner.nextLine());
		System.out.print("Unesite ISBN: "); k.setISBN(scanner.nextLine());
		System.out.print("Unesite godinu objavljivanja: "); k.setGodinaObjavljivanja(Integer.parseInt(scanner.nextLine()));
		System.out.print("Unesite izdanje knjige: "); k.setIzdanje(Integer.parseInt(scanner.nextLine()));
		System.out.print("Unesite broj strana: "); k.setBrStrana(Integer.parseInt(scanner.nextLine()));
		System.out.print("Unesite kolicinu: "); k.setKolicina(Integer.parseInt(scanner.nextLine()));

		boolean petlja = true;
		Main.cls();
		System.out.println("Autori: ");
		System.out.println("Odaberite opciju: ");
		while (petlja) {
			System.out.println("1. Odaberite postojece autore\n2. Novi autor/i");
			System.out.print("Unos: ");
			switch (scanner.nextLine()) {
				case "1":
					//petlja = false;
					while (true) {
						for (int i = 0; i < autori.size(); i++) {
							System.out.printf("%d. %s%n", i+1, autori.get(i).getId().concat(" " + autori.get(i).getFullName()));
						}
						System.out.print("Unesite redni broj autora (0 za izlaz): "); String unos = scanner.nextLine();
						if (unos.equals("0")) {
							break;
						}
						else {
							if (Integer.parseInt(unos) - 1 > autori.size()) {
								System.out.println("Uneli ste nepostojeci redni broj");
							}
							else {
								k.getAutori().add(autori.get(Integer.parseInt(unos) - 1));
								break;
							}
						}
					}
					break;
				case "2":
					Autor a = new Autor();
					a.setId(a.generateUUID());
					System.out.print("Unesite ime autora: "); a.setIme(scanner.nextLine());
					System.out.print("Unesite prezime autora: "); a.setPrezime(scanner.nextLine());

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
		Main.cls();
		System.out.println("Izdavac: ");
		System.out.println("Odaberite opciju: ");
		while (petlja) {
			System.out.println("1. Odaberite postojeceg izvodjaca\n2. Novi izdavac/i");
			System.out.print("Unos: ");
			switch (scanner.nextLine()) {
				case "1":
					//petlja = false;
					while (true) {
						for (int i = 0; i < izdavaci.size(); i++) {
							System.out.printf("%d. %s%n", i+1, izdavaci.get(i).getId().concat(" " + izdavaci.get(i).getImeIzdavaca()));
						}
						System.out.print("Unesite redni broj izdavaca (0 za izlaz): "); String unos = scanner.nextLine();
						if (unos.equals("0")) {
							break;
						}
						else {
							if ( Integer.parseInt(unos) - 1 < 0 || Integer.parseInt(unos) - 1 > izdavaci.size()) {
								System.out.println("Uneli ste nepostojeci redni broj");
							}
							else {
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
					System.out.print("Unesite naziv izdavaca: "); i.setImeIzdavaca(scanner.nextLine());
					System.out.print("Unesite zemlju izdavaca: "); i.setZemljaPorekla(scanner.nextLine());

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
				}
				else if (Integer.parseInt(s) < 0 || Integer.parseInt(s) > EnumZanr.getMap().size()) {
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
		System.out.println("Odaberite kategoriju");
		while (true) {
			Main.cls();
			System.out.println("Odaberite novu kategoriju");
			int kategorija;
			for (EnumKategorija zanr : EnumKategorija.getMap().values()) {
				System.out.printf("%d. %s%n", zanr.getRedniBroj(), zanr.name());
			}
			System.out.print("\nOdaberite kategoriju: "); kategorija = Integer.parseInt(scanner.nextLine());
			if (kategorija < 0 || kategorija > EnumKategorija.getMap().size()) {
				System.out.println("Unesite redni broj postojece kategorije!");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else {
				k.setKategorija(kategorija);
				break;
			}
		}
		knjige.add(k);
		Main.save(EnumCheckpoints.KNJIGE.ordinal());
	}
	//TODO: Testiranje, provera
	protected static void addAuthor(ArrayList<Autor> autori) {
		Scanner scanner = new Scanner(System.in);
		Main.cls();
		System.out.println("Dodavanje novog autora...");
		Autor a = new Autor();

		a.setId(a.generateUUID());
		System.out.print("Unesite ime autora: "); a.setIme(scanner.nextLine());
		System.out.print("Unesite prezime autora: "); a.setPrezime(scanner.nextLine());

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
	//TODO: Testiranje, provera
	protected static void addPublisher(ArrayList<Izdavac> izdavaci) {
		Scanner scanner = new Scanner(System.in);
		Main.cls();
		System.out.println("Dodavanje novog autora...");
		Izdavac i = new Izdavac();

		i.setId(i.generateUUID());
		System.out.print("Unesite naziv izdavaca: "); i.setImeIzdavaca(scanner.nextLine());
		System.out.print("Unesite zemlju: "); i.setZemljaPorekla(scanner.nextLine());

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

	//TODO: Testiranje, provera
	protected static void editBook(ArrayList<Knjiga> knjige, ArrayList<Autor> autori, ArrayList<Izdavac> izdavaci) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			Main.cls();
			System.out.println("Odaberite knjigu cije podatke zelite da izmenite (0 za izlaz):");
			for (int i = 0; i < knjige.size(); i++) {
				System.out.printf("%d. %s%n", i+1, knjige.get(i).getId().concat(" " + knjige.get(i).getImeKnjige()));
			}
			System.out.println("Unos: "); String unos = scanner.nextLine();
			if (unos.equals("0")) {
				break;
			}
			else if (unos.equals("")) {
				continue; //Nepotrebno, ali ubaceno zbog citljivosti
			}
			else {
				boolean petlja = true;
				while (petlja) {
					Main.cls();
					System.out.println("Podaci o odabranoj knjizi: ");
					System.out.printf("ID: %s, ISBN: %s, Naziv: %s, Autori: %s, Izdavac: %s, Zanrovi: %s, god. objavljivanja: %d, Izdanje: %d, Broj strana %d, Kategorija: %d, Kolicina: %d",
							knjige.get(Integer.parseInt(unos) - 1).getId(),
							knjige.get(Integer.parseInt(unos) - 1).getISBN(),
							knjige.get(Integer.parseInt(unos) - 1).getImeKnjige(),
							knjige.get(Integer.parseInt(unos) - 1).getAutori(),  //TODO
							knjige.get(Integer.parseInt(unos) - 1).getIzdavac(),
							knjige.get(Integer.parseInt(unos) - 1).getZanrovi(), //TODO
							knjige.get(Integer.parseInt(unos) - 1).getGodinaObjavljivanja(),
							knjige.get(Integer.parseInt(unos) - 1).getIzdanje(),
							knjige.get(Integer.parseInt(unos) - 1).getBrStrana(),
							knjige.get(Integer.parseInt(unos) - 1).getKategorija(), //TODO
							knjige.get(Integer.parseInt(unos) - 1).getKolicina());
					System.out.println("Odaberite sta zelite da izmenite: ");
					System.out.print("1. ISBN, 2. Naziv, 3. Autori, 4. Izdavac, 5. Zanrovi, 6. Godinu objavljivanja, 7. Izdanje, 8. Broj strana, 9. Kategoruja, 10. Kolicina\n" +
							"(0 za izlaz): ");
					switch (scanner.nextLine()) {
						case "1":
							System.out.println("Unesite novi ISBN"); knjige.get(Integer.parseInt(unos) - 1).setISBN(scanner.nextLine());
							break;
						case "2":
							System.out.println("Unesite novi naziv"); knjige.get(Integer.parseInt(unos) - 1).setISBN(scanner.nextLine());
							break;
						case "3":
							boolean petlja2 = true;
							Main.cls();
							System.out.println("Odaberite nove autore");
							System.out.println("Odaberite opciju: ");
							while (petlja2) {
								System.out.println("1. Odaberite postojece autore\n2. Novi autor/i");
								System.out.print("Unos: ");
								switch (scanner.nextLine()) {
									case "1":
										//petlja = false;
										while (true) {
											for (int i = 0; i < autori.size(); i++) {
												System.out.printf("%d. %s%n", i+1, autori.get(i).getId().concat(" " + autori.get(i).getFullName()));
											}
											System.out.print("Unesite redni broj autora (0 za izlaz): "); String unos2 = scanner.nextLine();
											if (unos2.equals("0")) {
												break;
											}
											else {
												if (Integer.parseInt(unos2) - 1 > autori.size()) {
													System.out.println("Uneli ste nepostojeci redni broj");
												}
												else {
													knjige.get(Integer.parseInt(unos) - 1).getAutori().add(autori.get(Integer.parseInt(unos2) - 1));
													break;
												}
											}
										}
										break;
									case "2":
										Autor a = new Autor();
										a.setId(a.generateUUID());
										System.out.print("Unesite ime autora: "); a.setIme(scanner.nextLine());
										System.out.print("Unesite prezime autora: "); a.setPrezime(scanner.nextLine());

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
									}
									else if (Integer.parseInt(s) < 1 || Integer.parseInt(s) > EnumZanr.getMap().size()) {
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
						case "4":
							petlja = true;
							Main.cls();
							System.out.println("Odaberite izdavaca");
							System.out.println("Odaberite opciju: ");
							while (petlja) {
								System.out.println("1. Odaberite postojeceg izvodjaca\n2. Novi izdavac/i");
								System.out.print("Unos: ");
								switch (scanner.nextLine()) {
									case "1":
										petlja = false;
										while (true) {
											for (int i = 0; i < izdavaci.size(); i++) {
												System.out.printf("%d. %s%n", i+1, izdavaci.get(i).getId().concat(" " + izdavaci.get(i).getImeIzdavaca()));
											}
											System.out.print("Unesite redni broj izdavaca (0 za izlaz): "); String unos3 = scanner.nextLine();
											if (unos3.equals("0")) {
												break;
											}
											else {
												if (Integer.parseInt(unos) - 1 > izdavaci.size()) {
													System.out.println("Uneli ste nepostojeci redni broj");
												}
												else {
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
										System.out.print("Unesite naziv izdavaca: "); i.setImeIzdavaca(scanner.nextLine());
										System.out.print("Unesite zemlju izdavaca: "); i.setZemljaPorekla(scanner.nextLine());

										knjige.get(Integer.parseInt(unos) - 1).setIzdavac(i);
										izdavaci.add(i);
										Main.save(EnumCheckpoints.IZDAVACI.ordinal());
										break;
									default:
										System.out.println("Molimo unesite jednu od dostupnih opcija!");
								}
							}
							break;
						case "6":
							System.out.println("Unesite godinu objavljivanja"); knjige.get(Integer.parseInt(unos) - 1).setGodinaObjavljivanja(Integer.parseInt(scanner.nextLine()));
							break;
						case "7":
							System.out.println("Unesite izdanje"); knjige.get(Integer.parseInt(unos) - 1).setIzdanje(Integer.parseInt(scanner.nextLine()));
							break;
						case "8":
							System.out.println("Unesite broj strana"); knjige.get(Integer.parseInt(unos) - 1).setBrStrana(Integer.parseInt(scanner.nextLine()));
							break;
						case "9":
							System.out.println("Odaberite kategoriju");
							while (true) {
								Main.cls();
								System.out.println("Odaberite novu kategoriju");
								int kategorija;
								for (EnumKategorija zanr : EnumKategorija.getMap().values()) {
									System.out.printf("%d. %s%n", zanr.getRedniBroj(), zanr.name());
								}
								System.out.print("\nOdaberite kategoriju: "); kategorija = Integer.parseInt(scanner.nextLine());
								if (kategorija < 0 || kategorija > EnumKategorija.getMap().size()) {
									System.out.println("Unesite redni broj postojece kategorije!");
									try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
								else {
									knjige.get(Integer.parseInt(unos) - 1).setKategorija(kategorija);
									break;
								}
							}
							break;
						case "10":
							System.out.println("Unesite kolicinu"); knjige.get(Integer.parseInt(unos) - 1).setKolicina(Integer.parseInt(scanner.nextLine()));
							break;
						case "0":
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
	//TODO: Testiranje, provera
	protected static void editAuthor(ArrayList<Autor> autori) {
		Scanner scanner = new Scanner(System.in);
		Main.cls();
		System.out.println("Odaberite autora cije podatke zelite da izmenite (0 za izlaz):");
		for (int i = 0; i < autori.size(); i++) {
			System.out.printf("%d. %s%n", i+1, autori.get(i).getId().concat(" " + autori.get(i).getFullName()));
		}

		while (true) {
			System.out.println("Unos: "); String unos = scanner.nextLine();
			if (unos.equals("0")) {
				break;
			}
			else {
				Main.cls();
				System.out.println("Podaci o odabranom autoru: ");
				System.out.printf("ID: %s%nIme: %s%nPrezime: %s%n",autori.get(Integer.parseInt(unos) - 1).getId(), autori.get(Integer.parseInt(unos) - 1).getIme(), autori.get(Integer.parseInt(unos) - 1).getPrezime());
				System.out.println("Odaberite sta zelite da izmenite (1. Ime, 2. Prezime). Unesite 0 za izlaz: ");
				switch (scanner.nextLine()) {
					case "1":
						while (true) {
							System.out.println("Unesite novo ime: "); String tempIme = scanner.nextLine();
							if (tempIme.length() < 2) {
								System.out.println("Ime moze da sardzi samo slova.");
							}
							else {
								autori.get(Integer.parseInt(unos) - 1).setIme(tempIme);
								break;
							}
						}
						break;
					case "2":
						while (true) {
							System.out.println("Unesite novo prezime: "); String tempPrezime = scanner.nextLine();
							if (tempPrezime.length() < 2) {
								System.out.println("Prezime moze da sardzi samo slova.");
							}
							else {
								autori.get(Integer.parseInt(unos) - 1).setPrezime(tempPrezime);
								break;
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
	//TODO: Testiranje, provera
	protected static void editPublisher(ArrayList<Izdavac> izdavaci) {
		Scanner scanner = new Scanner(System.in);
		Main.cls();
		System.out.println("Odaberite autora cije podatke zelite da izmenite (0 za izlaz):");
		for (int i = 0; i < izdavaci.size(); i++) {
			System.out.printf("%d. %s%n", i+1, izdavaci.get(i).getId().concat(" " + izdavaci.get(i).getImeIzdavaca() + " " + izdavaci.get(i).getZemljaPorekla()));
		}

		while (true) {
			System.out.println("Unos: "); String unos = scanner.nextLine();
			if (unos.equals("0")) {
				break;
			}
			else {
				Main.cls();
				System.out.println("Podaci o odabranom autoru: ");
				System.out.printf("ID: %s%nIme izdavaca: %s%nZemlja porekla: %s%n",izdavaci.get(Integer.parseInt(unos) - 1).getId(), izdavaci.get(Integer.parseInt(unos) - 1).getImeIzdavaca(), izdavaci.get(Integer.parseInt(unos) - 1).getZemljaPorekla());
				System.out.println("Odaberite sta zelite da izmenite (1. Ime izdavaca, 2. Zemlja porekla). Unesite 0 za izlaz: ");
				switch (scanner.nextLine()) {
					case "1":
						while (true) {
							System.out.println("Unesite novo ime izdavaca: "); String tempIme = scanner.nextLine();
							if (tempIme.length() < 2) {
								System.out.println("Ime izdavaca moze da sardzi samo slova.");
							}
							else {
								izdavaci.get(Integer.parseInt(unos) - 1).setImeIzdavaca(tempIme);
								break;
							}
						}
						break;
					case "2":
						while (true) {
							System.out.println("Unesite novu zemlju porekla: "); String tempPrezime = scanner.nextLine();
							if (tempPrezime.length() < 2) {
								System.out.println("Zemlja porekla moze da sardzi samo slova.");
							}
							else {
								izdavaci.get(Integer.parseInt(unos) - 1).setZemljaPorekla(tempPrezime);
								break;
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

	protected static void deleteBook(ArrayList<Knjiga> knjige, ArrayList<Autor> autori, ArrayList<Izdavac> izdavaci) {
		//TODO
	}
	//TODO: Testiranje, provera
	protected static void deleteAuthor(ArrayList<Autor> autori) {
		Scanner scanner = new Scanner(System.in);
		Main.cls();
		System.out.println("Odaberite autora kojeg zelite da obrisete (0 za izlaz):");
		for (int i = 0; i < autori.size(); i++) {
			System.out.printf("%d. %s%n", i+1, autori.get(i).getId().concat(" " + autori.get(i).getFullName()));
		}
		while (true) {
			System.out.println("Unos: "); String unos = scanner.nextLine();
			if (unos.equals("0")) {
				break;
			}
			else {
				if (Integer.parseInt(unos) - 1 > autori.size()) {
					System.out.println("Uneli ste nepostojeci redni broj.");
				}
				else {
					System.out.printf("Odabran clan: %s, %s%n", autori.get((Integer.parseInt(unos) - 1)).getId(), autori.get((Integer.parseInt(unos) - 1)).getFullName());
					System.out.println("Da li ste sigurni da zelite da obrisete ovog autora? (Y/N)");
					boolean petlja = true;
					while (petlja) {
						switch (scanner.nextLine().toUpperCase()) {
							case "Y":
								petlja = false;
								autori.remove(Integer.parseInt(unos) - 1);
								System.out.println("Uspesno obrisano.");
								Main.save(EnumCheckpoints.AUTORI.ordinal());
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
	}
	//TODO: Testiranje, provera
	protected static void deletePublisher(ArrayList<Izdavac> izdavaci) {
		Scanner scanner = new Scanner(System.in);
		Main.cls();
		System.out.println("Odaberite autora kojeg zelite da obrisete (0 za izlaz):");
		for (int i = 0; i < izdavaci.size(); i++) {
			System.out.printf("%d. %s%n", i+1, izdavaci.get(i).getId().concat(" " + izdavaci.get(i).getImeIzdavaca() + " " + izdavaci.get(i).getZemljaPorekla()));
		}
		while (true) {
			System.out.println("Unos: "); String unos = scanner.nextLine();
			if (unos.equals("0")) {
				break;
			}
			else {
				if (Integer.parseInt(unos) - 1 > izdavaci.size()) {
					System.out.println("Uneli ste nepostojeci redni broj.");
				}
				else {
					System.out.printf("Odabran clan: %s, %s%n", izdavaci.get((Integer.parseInt(unos) - 1)).getId(), izdavaci.get((Integer.parseInt(unos) - 1)).getImeIzdavaca().concat(" " + izdavaci.get((Integer.parseInt(unos) - 1)).getZemljaPorekla()));
					System.out.println("Da li ste sigurni da zelite da obrisete ovog autora? (Y/N)");
					boolean petlja = true;
					while (petlja) {
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