package projekat.util;

import projekat.Main;
import projekat.osoba.Administrator;
import projekat.osoba.EnumPol;
import projekat.sistem.Login;
import projekat.sistem.PravilaBiblioteke;
import projekat.util.serijalizacija.DataManager;

import javax.security.auth.login.CredentialException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
		ArrayList<Administrator> admin = new ArrayList<>();
		Administrator a = new Administrator();
		try {
			admin = DataManager.deserializeAdmins(DataManager.deserializeSifre(), DataManager.deserializeDozvole());
		} catch (IOException | TokProgramaException exception) {
			exception.printStackTrace();
			System.out.println("Greska u deserijalizaciji!");
			System.exit(1);
		}
		try {
			a = Login.login(admin);
		} catch (CredentialException e) {
			e.printStackTrace();
		}

		if (a.getDozvole().hasMasterRule()) {
			Scanner tempScanner = new Scanner(System.in);
			System.out.println("Da li ste sigurni da zelite da resetujete sistem? [Da / Ne]");
			while (true) {
				System.out.print("Unos: ");
				switch (tempScanner.nextLine()) {
					case "Da":
						DataManager.resetSystem(true);
						System.out.println("Sistem je resetovan, da bi ste ponovo podesili sistem, potrebno je da pokrenete program sa opcijom '--setup'");
						System.exit(0);
					case "Ne":
						System.exit(0);
					default:
						System.out.println("Niste uneli pravilnu opciju!");
						break;
				}
			}
		}
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
		System.out.println("Odaberite pol: ");

		boolean petlja = true;

		while (petlja) {
			System.out.println("\n1. Musko\n2. Zensko");
			System.out.println("Unos: ");
			switch (scanner.nextInt()) {
				case 1:
					petlja = false;
					masterAdmin.setPol(EnumPol.MUSKO.getNum());
					break;
				case 2:
					petlja = false;
					masterAdmin.setPol(EnumPol.ZENSKO.getNum());
					break;
				default:
					System.out.println("Molimo unesite jednu od dostupnih opcija!");
			}
		}
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
		if (args.length == 1) {
			if (args[0].equals(HELP.getArgument())) {
				displayHelp(); System.exit(0);
			}
			else if (args[0].equals(DEBUG.getArgument())) {
				Main.debugMode = true;
			}
			else if (args[0].equals(SYSTEM_SETUP.getArgument())) {
				File temp = new File("data//");
				if (temp.exists() && temp.list().length == 0) {
					setupSystem();
				}
				else {
					System.out.println("Sistem je vec podesen i sadrzi fajlove, program se zatvara.");
					System.exit(4);
				}
			}
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
