package projekat.util;

import projekat.Main;
import projekat.sistem.PravilaBiblioteke;
import projekat.util.serijalizacija.DataManager;

import static projekat.util.EnumArguments.*;

public class ArgManager {


	private final String[] args;

	public ArgManager(String[] args) {
		this.args = args;
		test();
	}

	public static void displayHelp() {
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

	public void test() {
		if (args[0].equals(HELP.getArgument())) { displayHelp(); System.exit(0);}
		else if (args[0].equals(DEBUG.getArgument())) { Main.debugMode = true;}

		//TODO: Login potvrda
		else if (args[0].equals(RESET.getArgument()) && args[1].equals("da")) {
			Main.pravila = new PravilaBiblioteke();
			DataManager.resetSystem(args[1].equals("da"));
			System.out.println("Resetovanje sistema uspesno. Pokrenite program sa argumentom '--setup' da bi ste podesili program."); System.exit(100);
		}

		//TODO: Provera da li sistem sadrzi podatke
		else if (args[0].equals(SYSTEM_SETUP.getArgument())) {

		}
	}
}
