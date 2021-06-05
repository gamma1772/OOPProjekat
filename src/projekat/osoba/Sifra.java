package projekat.osoba;

import projekat.util.debug.Logger;

import java.io.CharConversionException;
import java.io.Serializable;

public class Sifra implements Serializable {
	private String korisnickiUUID, sifra;
	private static int UUIDSum = 0;
	private static final Logger LOGGER = new Logger("SIFRA");

	public Sifra(String korisnickiUUID, String sifra) {
		this.setKorisnickiUUID(korisnickiUUID);
		this.UUIDSummary(korisnickiUUID);
		this.setSifra(sifrujLozinku(sifra));
	}

	public Sifra() {
		this.korisnickiUUID = "";
		this.sifra = "";
	}

	public String getKorisnickiUUID() {
		return korisnickiUUID;
	}

	public void setKorisnickiUUID(String korisnickiUUID) {
		this.korisnickiUUID = korisnickiUUID;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
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
	 * @return sifrovana lozinka (String)*/
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
	/**Desifruje lozinku koja je sifrovana Cezarovim sifrovanjem. Princip je isti kao i kod sifrovanja,
	 * samo sto se od ASCII vrednosti oduzima opseg karaktera.
	 * @param sifra Sifra koja treba da se desifruje
	 * @return Desifrovana sifra (String)*/
	public static String desifrujLozinku(String sifra) {
		StringBuilder desifrovanaSifra = new StringBuilder();

		for (int i = 0; i < sifra.length(); i++) {
			if (Character.isUpperCase(sifra.charAt(i))) {
				desifrovanaSifra.append((char)(((int) sifra.charAt(i) + UUIDSum - (65 - 26) % 26 + 65)));
			} else if (Character.isLowerCase(sifra.charAt(i))) {
				desifrovanaSifra.append((char)(((int) sifra.charAt(i) + UUIDSum - (97 - 26) % 26 + 97)));
			} else if (Character.isDigit(sifra.charAt(i))) {
				desifrovanaSifra.append((char)(((int) sifra.charAt(i) + UUIDSum - (48 - 10) % 10 + 48)));
			}
		}

		return desifrovanaSifra.toString();
	}

	public String toStringSerializable() {
		return String.format("%s~%s", getKorisnickiUUID(), getSifra());
	}
}
