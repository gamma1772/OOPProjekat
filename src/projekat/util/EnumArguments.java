package projekat.util;

import java.util.HashMap;
import java.util.Map;

/**Enumerator argumenata, sadrzi sve argumente koji su dozvoljeni*/
public enum EnumArguments {

	RESET("--reset", "Resetuje program, brise sve podatke. Zahteva potvrdu i prijavljivanje sa nalogom koji ima 'masterRule' dozvolu", "[Da/Ne]"),
	SYSTEM_SETUP("--setup", "Pokrece program u rezimu za prvo podezavanje. Moze samo da se pokrene ako ne postoji ni jedan korisnik"),
	LOGIN("--login", "Preskace proces prijavljivanja i direktno se prijavljuje na sistem uz pomoc dodeljenih argumenata", "username", "password"),
	HELP("--help", "Prikazuje ovaj ekran");

	private final String argument;
	private final String[] params;
	private final String description;

// --Commented out by Inspection START (14.6.2021. 16:41):
//	EnumArguments(String argument) {
//		this.argument = argument;
//		this.params = null;
//		this.description = null;
//	}
// --Commented out by Inspection STOP (14.6.2021. 16:41)
	EnumArguments(String argument, String description) {
		this.argument = argument;
		this.params = null;
		this.description = description; }
	EnumArguments(String argument, String description, String... params) {
		this.argument = argument;
		this.params = params;
		this.description = description;
	}

	public String getArgument() {
		return this.argument;
	}

	public String getDescription() {
		return this.description;
	}

	// --Commented out by Inspection (14.6.2021. 16:41):public String[] getParams() {return this.params; }

	private static final Map<String, EnumArguments> mapaArgumenata = new HashMap<>();

	static {
		for (EnumArguments argument : EnumArguments.values()) {
			mapaArgumenata.put(argument.getArgument(), argument);
		}
	}

// --Commented out by Inspection START (14.6.2021. 16:41):
//	public static Map<String, EnumArguments> getMap() {
//		return mapaArgumenata;
//	}
// --Commented out by Inspection STOP (14.6.2021. 16:41)
}
