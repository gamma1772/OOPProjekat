package projekat.util;

import projekat.Main;
import projekat.sistem.PravilaBiblioteke;
import projekat.util.debug.Logger;
import projekat.util.fajl.DataManager;

import static projekat.util.EnumArguments.*;

public class ArgManager {

	private static final Logger LOGGER = new Logger("ARGUMENT-MANAGEMENT");

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
		else if (args[0].equals(DEBUG.getArgument())) { Main.debugMode = true; System.out.println(LOGGER.debug("Debug mode: " + Main.debugMode)); }

		//TODO: Login potvrda
		else if (args[0].equals(RESET.getArgument()) && args[1].equals("da")) {
			Main.pravila = new PravilaBiblioteke();
			DataManager.resetSystem(args[1].equals("da"));
			LOGGER.info("Sistem resetovan");
			System.out.println("Resetovanje sistema uspesno. Pokrenite program sa argumentom '--setup' da bi ste podesili program."); System.exit(100);
		}
	}
}
