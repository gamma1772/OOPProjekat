package projekat.knjiga;

import java.util.HashMap;
import java.util.Map;

public enum EnumKategorija {
	KUHINJA(0),
	HOBIJI(1),
	PROGRAMIRANJE(2),
	EKOLOGIJA(3),
	FIKCIJA(4),
	AKCIJA(5),
	ROMANTIKA(6),
	KLASICIZAM(7),
	STRIP(8),
	ENCIKLOPEDIJA(9);

	private final int redniBroj;

	private static final Map<Integer, EnumKategorija> mapaKategorija = new HashMap<>();

	//Popunjavanje mape enumeratorima. Pokrece se samo jednom tokom rada programa
	static {
		for (EnumKategorija kategorija : EnumKategorija.values()) {
			mapaKategorija.put(kategorija.getRedniBroj(), kategorija);
		}
	}

	EnumKategorija(int redniBroj) {
		this.redniBroj = redniBroj;
	}

	//Vraca enumerator datog rednog broja
	public static EnumKategorija getKategorija(int redniBroj) {
		return mapaKategorija.get(redniBroj);
	}

	public int getRedniBroj() {
		return redniBroj;
	}
}
