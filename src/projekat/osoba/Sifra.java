package projekat.osoba;

import projekat.util.debug.Logger;

import java.io.CharConversionException;
import java.io.Serializable;

public class Sifra implements Serializable {
	private String korisnickiUUID, sifra;
	private transient int UUIDSum = 0;
	private transient static final Logger LOGGER = new Logger("SIFRA");

	public Sifra(String korisnickiUUID, String sifra) {
		this.setKorisnickiUUID(korisnickiUUID);
		this.UUIDSummary(korisnickiUUID);
		this.setSifra(sifrujLozinku(sifra));
	}

	public String getKorisnickiUUID() {
		return korisnickiUUID;
	}

	public void setKorisnickiUUID(String korisnickiUUID) {
		this.korisnickiUUID = korisnickiUUID;
	}

	private String getSifra() {
		return sifra;
	}

	private void setSifra(String sifra) {
		this.sifra = sifra;
	}

	/**Racuna sumu karaktera stringa UUID. Pretvara String UUID u niz karaktera UUIDCharArray,
	 * zatim brojnu vrednost svakog karaktera dodaje na promenljivu UUIDSum.
	 * @param UUID Korisnicki jedinstveni identifikator*/
	private void UUIDSummary(String UUID) {
		char[] UUIDCharArray = UUID.toCharArray();
		for (char c: UUIDCharArray) {
			UUIDSum += (int)c;
		}
	}

	/**Sifruje lozinku Cezarovim sifrovanjem. Odstup se racuna sumom vrednosti karaktera jedinstvenog identifikatora {@link Sifra#UUIDSummary(String)},
	 * zatim se u zavisnosti od pozicije na ASCII tabeli, i brojem dostupnih karaktera tog tipa, vrednost osnovnog karaktera povecava za (x+n)%opseg,
	 * gde je:
	 * x - odstup;
	 * n - ASCII vrednost prvog karaktera tog tipa (npr, ako je veliko slovo u putanju, n = 65);
	 * opseg - broj karaktera tog tipa (npr za alfabet, opseg = 26);
	 *
	 * @param sifra Sifra koja treba da se sifruje;
	 * @throws CharConversionException u slucaju da je karakter koji treba da se sifruje znak koji ne moze da se sifruje (svi znakovi osim velikih, malih slova, znakova interpunkcije i brojeva)
	 * @return sifrovana lozinka*/
	private String sifrujLozinku(String sifra) {
		StringBuilder tempSifra = new StringBuilder();
		char errorChar = ' ';
		char[] tempChar = sifra.toCharArray();
		try {
			for (int i = 0; i < tempChar.length; i++) {
				if (Character.isUpperCase(tempChar[i])) {
					tempSifra.append((char) (((int) tempChar[i] + UUIDSum - 65) % 26 + 65));
				} else if (Character.isLowerCase(tempChar[i])) {
					tempSifra.append((char) (((int) tempChar[i] + UUIDSum - 97) % 26 + 97));
				} else if (Character.isDigit(tempChar[i])) {
					tempSifra.append((char) (((int) tempChar[i] + UUIDSum - 48) % 10 + 48));
				} else {
					errorChar = tempChar[i];
					throw new CharConversionException();
				}
			}
		}
		catch (CharConversionException charConversionException) {
			LOGGER.error("Greska u sifrovanju. Pokusaj sifrovanja nedozvoljenog karaktera: '" + errorChar + "'");
			System.out.println(charConversionException.getMessage());
			return "NULL";
		}
		LOGGER.info("Sifrovana lozinka za UUID " + korisnickiUUID);
		return tempSifra.toString();
	}
	//TODO
	public static String desifrujLozinku(String sifra) {
		return "";
	}
}
