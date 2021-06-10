package projekat.util;

import projekat.Main;
import projekat.osoba.Administrator;
import projekat.sistem.PravilaBiblioteke;
import projekat.util.serijalizacija.DataManager;

import java.io.IOException;
import java.util.Scanner;

import static projekat.util.EnumArguments.*;

public class ArgManager {

	private static final Scanner scanner = new Scanner(System.in);
	private final String[] args;

	public ArgManager(String[] args) {
		this.args = args;
		test();
	}

	private void displayHelp() {
		System.out.println("Dostupne komande: ");
		for (EnumArguments argument : EnumArguments.values()) {
			String arg = argument.getArgument();
			String desc;
			if ((desc = argument.getDescription()) == null || desc.equals("")) {
				desc = "Opis argumenta nije dostupan.";
			}
			System.out.printf("\t%-15s\t- %s%n", arg, desc);
		}
	}

	private void resetSystem() {

	}

	private void setupSystem() {
		try {
			DataManager.createFileEntries();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		Administrator masterAdmin = new Administrator();

		masterAdmin.setUUID(masterAdmin.generateUUID());

		masterAdmin.getPassword().setKorisnickiUUID(masterAdmin.getUUID());
		masterAdmin.getDozvole().setUserUUID(masterAdmin.getUUID());

		System.out.print("Unesite vase ime: "); masterAdmin.setIme(scanner.nextLine());
		System.out.print("Unesite vase prezime: "); masterAdmin.setPrezime(scanner.nextLine());
		System.out.print("Unesite vasu adresu: "); masterAdmin.setAdresa(scanner.nextLine());
		System.out.print("Unesite vas broj telefona: "); masterAdmin.setBrTelefona(scanner.nextLine());
		System.out.print("Unesite vas email: "); masterAdmin.setEmail(scanner.nextLine());
		System.out.print("Unesite zeljeno korisnicko ime: "); masterAdmin.setUsername(scanner.nextLine());

		System.out.print("Unesite sifru: "); masterAdmin.getPassword().encryptSifra(scanner.nextLine());
		masterAdmin.getPassword().setKorisnickiUUID(masterAdmin.getUUID());
		masterAdmin.setDozvole(masterAdmin.getDozvole().admin().addAdmins().addBooks().addMembers().deleteAdmins().deleteMembers().deleteBooks().loanBooks().alterRules().masterRule());

		masterAdmin.serialize();

		System.out.println("Master administrator nalog kreiran. Da li zelite da pokrenete sistem? (Y/N): ");
		boolean cycle = true;
		while (cycle) {
			System.out.print("Unos: ");
			switch (scanner.nextLine()) {
				case "Y":
					cycle = false;
					Main.prijavljenAdmin = masterAdmin;
					Main.init();
					break;
				case "N":
					cycle = false;
					System.exit(0);
				default:
					System.out.println("Dozvoljeni unos je Y (Da) ili N (Ne).");
					break;
			}
		}
	}

	public void test() {
		if (args[0].equals(HELP.getArgument())) { displayHelp(); System.exit(0);}
		else if (args[0].equals(DEBUG.getArgument())) { Main.debugMode = true;}

		//TODO: Login potvrda
		else if (args[0].equals(RESET.getArgument()) && args[1].equals("da")) {
			Main.pravila = new PravilaBiblioteke(true);
			DataManager.resetSystem(args[1].equals("da"));
			System.out.println("Resetovanje sistema uspesno. Pokrenite program sa argumentom '--setup' da bi ste podesili program."); System.exit(100);
		}

		//TODO: Provera da li sistem sadrzi podatke
		else if (args[0].equals(SYSTEM_SETUP.getArgument())) {
			setupSystem();
		}

		if (args.length > 1) {
			if (args[0].equals(LOGIN.getArgument())) {

			}
			else if (args[0].equals(RESET.getArgument())) {
				if (args[1].equals("Da")) {
					resetSystem();
				}
			}
		}
	}
}
