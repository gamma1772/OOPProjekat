package projekat.util;

import projekat.knjiga.EnumZanr;

import java.util.HashMap;
import java.util.Map;

/**Enumerator argumenata, sadrzi sve argumente koji su dozvoljeni*/
public enum EnumArguments {

	DEBUG("--debug", "Pokrece program u debug rezimu rada. Sve poruke ce biti prikazane na displeju"),
	NOLOGIN("--nologin", "Pokrece program bez prijavljivanja. U ovom rezimu rada je moguc samo pregled odredjenih podataka"),
	RESET("--reset", "Resetuje program, brise sve podatke. Zahteva potvrdu i prijavljivanje sa nalogom koji ima 'masterRule' dozvolu"),
	SYSTEM_SETUP("--setup", "Pokrece program u rezimu za prvo podezavanje. Moze samo da se pokrene ako ne postoji ni jedan korisnik"),
	HELP("--help", "Prikazuje ovaj ekran");

	private String argument, description;
	EnumArguments(String argument) { this.argument = argument;}
	EnumArguments(String argument, String description) { this.argument = argument; this.description = description; }

	public String getArgument() {
		return this.argument;
	}

	public String getDescription() {
		return this.description;
	}

	private static final Map<String, EnumArguments> mapaArgumenata = new HashMap<>();

	static {
		for (EnumArguments argument : EnumArguments.values()) {
			mapaArgumenata.put(argument.getArgument(), argument);
		}
	}

	public static Map<String, EnumArguments> getMap() {
		return mapaArgumenata;
	}
}
