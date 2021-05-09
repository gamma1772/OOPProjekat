package projekat.debug;

import projekat.Main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Lokalna klasa Logger, sluzi za izbacivanje poruka u terminal u slucaju da je debugMode = true,
 * ili ako je u pitanju greska.
 * */
public class Logger {
	private final String loggerIme;
	private final Date datumPoruke;
	private final SimpleDateFormat formatDatuma = new SimpleDateFormat("(dd.MM.yyyy HH:mm:ss)");

	public Logger(String loggerIme) {
		this.loggerIme = loggerIme;
		datumPoruke = new Date();
	}

	/**Proverava da li je debugMode aktivan ili ako je greska u pitanju, prikazuje pouku u terminalu.
	 * U zavisnosti od tipa, prikazuje tip poruke u sledecem formatu:
	 * (datum vreme) [IMELOGGERA][TIP]: poruka
	 * Zatim vraca taj isti string za pisanje u fajl.
	 * Ima mogucnost da koristi proizvoljan tip, ali mora da bude enumerator
	 */
	public String poruka(String poruka, Enum tip) {
		String tipPoruke = tip.name();
		/* formatDatuma.format(datumPoruke) + String.format(" [%s]: ", loggerIme) + String.format("[%s] ", tipPoruke) + poruka*/
		if (Main.debugMode || tipPoruke.toLowerCase().contains("error")) System.out.printf("%s [%s][%s]: %s%n", formatDatuma.format(datumPoruke), loggerIme, tipPoruke, poruka);

		return String.format("%s [%s][%s]: %s%n", formatDatuma.format(datumPoruke), loggerIme, tipPoruke, poruka);
	}

	//Sledece funkcije sluze za direktno upisivanje u log fajl, ali mogu da se koriste u kombinaciji sa println()

	/**Osnovna fja za informacionu poruku*/
	public String info(String poruka) {
		return String.format("%s [%s][%s]: %s%n", formatDatuma.format(datumPoruke), loggerIme, "INFO", poruka);
	}
	/**Osnovna fja za poruku o izvrsavanju koda*/
	public String debug(String poruka) {
		return String.format("%s [%s][%s]: %s%n", formatDatuma.format(datumPoruke), loggerIme, "DEBUG", poruka);
	}
	/**Osnovna fja za poruku o gresci*/
	public String error(String poruka) {
		return String.format("%s [%s][%s]: %s%n", formatDatuma.format(datumPoruke), loggerIme, "ERROR", poruka);
	}
}
