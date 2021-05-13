package projekat.util.debug;

import projekat.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Lokalna klasa Logger, sluzi za izbacivanje poruka u terminal u slucaju da je debugMode = true,
 * ili ako je u pitanju greska.
 * */
public class Logger {
	private static final String output = "logs//log";
	private final String loggerIme;
	private Date datumPoruke;
	private final SimpleDateFormat formatDatuma = new SimpleDateFormat("(dd.MM.yyyy HH:mm:ss)");
	private static final SimpleDateFormat outDatum = new SimpleDateFormat("-ddMMyyyyHHmmss");

	private static final StringBuilder localBuffer = new StringBuilder();

	public Logger(String loggerIme) {
		this.loggerIme = loggerIme;
	}

	/**Proverava da li je debugMode aktivan ili ako je greska u pitanju, prikazuje pouku u terminalu.
	 * U zavisnosti od tipa, prikazuje tip poruke u sledecem formatu:
	 * (datum vreme) [IMELOGGERA][TIP]: poruka
	 * Zatim vraca taj isti string za pisanje u fajl.
	 * Ima mogucnost da koristi proizvoljan tip, ali mora da bude enumerator
	 */
	public String poruka(String poruka, Enum tip) {
		datumPoruke = new Date();
		String tipPoruke = tip.name();
		if (Main.debugMode || tipPoruke.toLowerCase().contains("error")) System.out.printf("%s [%s][%s]: %s%n", formatDatuma.format(datumPoruke), loggerIme, tipPoruke, poruka);

		return String.format("%s [%s][%s]: %s%n", formatDatuma.format(datumPoruke), loggerIme, tipPoruke, poruka);
	}

	//Sledece funkcije sluze za direktno upisivanje u log fajl, ali mogu da se koriste u kombinaciji sa println()

	/**Osnovna fja za informacionu poruku*/
	public String info(String poruka) {
		datumPoruke = new Date();
		localBuffer.append(String.format("%s [%s][%s]: %s%n", formatDatuma.format(datumPoruke), loggerIme, "INFO", poruka));
		return String.format("%s [%s][%s]: %s%n", formatDatuma.format(datumPoruke), loggerIme, "INFO", poruka);
	}
	/**Osnovna fja za poruku o izvrsavanju koda*/
	public String debug(String poruka) {
		datumPoruke = new Date();
		localBuffer.append(String.format("%s [%s][%s]: %s%n", formatDatuma.format(datumPoruke), loggerIme, "DEBUG", poruka));
		return String.format("%s [%s][%s]: %s%n", formatDatuma.format(datumPoruke), loggerIme, "DEBUG", poruka);
	}
	/**Osnovna fja za poruku o gresci*/
	public String error(String poruka) {
		datumPoruke = new Date();
		localBuffer.append(String.format("%s [%s][%s]: %s%n", formatDatuma.format(datumPoruke), loggerIme, "ERROR", poruka));
		return String.format("%s [%s][%s]: %s%n", formatDatuma.format(datumPoruke), loggerIme, "ERROR", poruka);
	}

	/**Upisivanje poruka u log fajl*/
	public static void out() {
		PrintWriter pw = null;
		File folder = new File("logs");
		if (!folder.exists()) {
			folder.mkdir();
		}
		try {
			pw = new PrintWriter(output + outDatum.format(new Date()) + ".txt");
			pw.println(localBuffer.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			pw.flush();
			pw.close();
		}
	}
}
