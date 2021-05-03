package projekat.debug;

import projekat.Main;

import java.text.SimpleDateFormat;
import java.util.Date;

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

	/**Generalna funkcija za poruku, proverava tip poruke, i u zavisnosti od tipa vraca formatiran string
	 * Takodje izbacuje poruku u terminal
	 */
	public String poruka(String poruka, int tip) {
		String tipPoruke = "";
		switch (tip) {
			case 0:
				tipPoruke = EnumTipovi.INFO.name();
				break;
			case 1:
				tipPoruke = EnumTipovi.DEBUG.name();
				break;
			case 2:
				tipPoruke = EnumTipovi.ERROR.name();
				break;
			case 3:
				tipPoruke = EnumTipovi.MESSAGE.name();
				break;
			case 4:
				tipPoruke = EnumTipovi.WARNING.name();
				break;
			default:
				return "Nepoznat tip poruke";
		}

		if (Main.debugMode || tip == 2) System.out.println(formatDatuma.format(datumPoruke) + String.format(" [%s]: ", loggerIme) + String.format("[%s] ", tipPoruke) + poruka);

		return formatDatuma.format(datumPoruke) + String.format(" [%s]: ", loggerIme) + String.format("[%s] ", tipPoruke) + poruka;
	}

	//Sledece funkcije sluze za direktno upisivanje u log fajl, ali mogu da se koriste u kombinaciji sa println()

	/**Osnovna fja za informacionu poruku*/
	public String info(String poruka) {
		return formatDatuma.format(datumPoruke) + String.format(" [%s]: ", loggerIme) + "[INFO] " + poruka;
	}
	/**Osnovna fja za poruku o izvrsavanju koda*/
	public String debug(String poruka) {
		return formatDatuma.format(datumPoruke) + String.format(" [%s]: ", loggerIme) + "[DEBUG] " + poruka;
	}
	/**Osnovna fja za poruku o gresci*/
	public String error(String poruka) {
		return formatDatuma.format(datumPoruke) + String.format(" [%s]: ", loggerIme) + "[ERROR] " + poruka;
	}
}
