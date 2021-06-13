package projekat.sistem;

import projekat.Main;
import projekat.knjiga.Autor;
import projekat.knjiga.Izdavac;
import projekat.knjiga.Knjiga;
import projekat.osoba.*;
import projekat.util.serijalizacija.DataManager;

import java.io.IOException;
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
					AdminManager.addAdmin(adminList);
				}
				break;
			case 2:
				if (prijavljenAdmin.getDozvole().canEditAdmins() || prijavljenAdmin.getDozvole().hasMasterRule()) {
					AdminManager.editAdmin(adminList);
				}
				break;
			case 3:
				if (prijavljenAdmin.getDozvole().canDeleteAdmins() || prijavljenAdmin.getDozvole().hasMasterRule()) {
					AdminManager.deleteAdmin(adminList);
				}
				break;
		}
	}
	public static void initMemberManager(int opcija, ArrayList<Clan> clanList) {
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
					MemberManager.deleteMember(clanList);
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
		//TODO
	}
	private static void editRules(PravilaBiblioteke pravilaBiblioteke) {
		//TODO
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

class AdminManager {
	//TODO: Testiranje, provera
	protected static void addAdmin(ArrayList<Administrator> adminList) {
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
		ArrayList<String> objects = new ArrayList<>();
		String fileName = adminList.get(0).serializedFileName();
		for (Administrator a : adminList) {
			objects.add(a.toStringSerializable());
		}
		try {
			DataManager.serializeString(objects, fileName, false);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	//TODO: Testiranje, provera
	protected static void deleteAdmin(ArrayList<Administrator> adminList) {
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
								adminList.remove(Integer.parseInt(unos) - 1);
								System.out.println("Uspesno obrisano.");
								ArrayList<String> objects = new ArrayList<>();
								String fileName = adminList.get(0).serializedFileName();
								for (Administrator a : adminList) {
									objects.add(a.toStringSerializable());
								}
								try {
									DataManager.serializeString(objects, fileName, false);
								} catch (IOException exception) {
									exception.printStackTrace();
								}
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

class MemberManager {
	//TODO: Testiranje, provera
	protected static void addMember(ArrayList<Clan> clanovi) {
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
	}
	//TODO: Testiranje, provera
	protected static void deleteMember(ArrayList<Clan> clanovi) {
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
								clanovi.remove(Integer.parseInt(unos) - 1);
								System.out.println("Uspesno obrisano.");
								ArrayList<String> objects = new ArrayList<>();
								String fileName = clanovi.get(0).serializedFileName();
								for (Clan c : clanovi) {
									objects.add(c.toStringSerializable());
								}
								try {
									DataManager.serializeString(objects, fileName, false);
								} catch (IOException exception) {
									exception.printStackTrace();
								}
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
					//petlja = false;
					while (true) {
						System.out.println("Unesite redni broj autora (0 za izlaz):"); String unos = scanner.nextLine();
						for (int i = 0; i < autori.size(); i++) {
							System.out.printf("%d. %s%n", i+1, autori.get(i).getId().concat(" " + autori.get(i).getFullName()));
						}
						System.out.print("Unos: ");
						if (unos.equals("0")) {
							break;
						}
						else {
							if (Integer.parseInt(unos) - 1 > autori.size()) {
								System.out.println("Uneli ste nepostojeci redni broj");
							}
							else {
								k.getAutori().add(autori.get(Integer.parseInt(unos) - 1));
							}
						}
					}
					break;
				case 2:
					Autor a = new Autor();
					a.setId(a.generateUUID());
					System.out.print("Unesite ime autora: "); a.setIme(scanner.nextLine());
					System.out.print("Unesite prezime autora: "); a.setPrezime(scanner.nextLine());

					autori.add(a);
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
						a.serialize();
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
			a.serialize();
			autori.add(a);
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
						i.serialize();
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
			i.serialize();
			izdavaci.add(i);
		}
	}

	//TODO: Testiranje, provera
	protected static void editBook(ArrayList<Knjiga> knjige, ArrayList<Autor> autori, ArrayList<Izdavac> izdavaci) {}
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
								ArrayList<String> objects = new ArrayList<>();
								String fileName = autori.get(0).serializedFileName();
								for (Autor a : autori) {
									objects.add(a.toStringSerializable());
								}
								try {
									DataManager.serializeString(objects, fileName, false);
								} catch (IOException exception) {
									exception.printStackTrace();
								}
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
								ArrayList<String> objects = new ArrayList<>();
								String fileName = izdavaci.get(0).serializedFileName();
								for (Izdavac i : izdavaci) {
									objects.add(i.toStringSerializable());
								}
								try {
									DataManager.serializeString(objects, fileName, false);
								} catch (IOException exception) {
									exception.printStackTrace();
								}
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