package projekat.osoba;

import projekat.util.serijalizacija.DataManager;
import projekat.util.serijalizacija.ISerijalizacija;

import java.io.CharConversionException;
import java.io.IOException;
import java.io.Serializable;

public class Sifra implements Serializable, ISerijalizacija {
	private String korisnickiUUID, sifra;
	private static int UUIDSum = 0;

	public Sifra(String korisnickiUUID, String sifra) {
		this.setKorisnickiUUID(korisnickiUUID);
		this.UUIDSummary(); // Potrebno za sifrovanje lozinke
		this.setSifra(sifrujLozinku(sifra));
	}

	/*Poziva se samo kada je potrebna deserijalizacija, jer tada ne treba da se racuna zbir karaktera IDa*/
	public Sifra() {
		this.setKorisnickiUUID("");
		this.setSifra("");
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

	public void encryptSifra(String sifra) {
		this.setSifra(sifrujLozinku(sifra));
	}

	/**Racuna sumu karaktera stringa korisnickiUUID. Pretvara String korisnickiUUID u niz karaktera UUIDCharArray,
	 * zatim brojnu vrednost svakog karaktera dodaje na promenljivu UUIDSum.*/
	private void UUIDSummary() {
		char[] UUIDCharArray = korisnickiUUID.toCharArray();
		for (char c: UUIDCharArray) {
			UUIDSum += c;
		}
	}

	private static void UUIDSummary(String UUID) {
		char[] UUIDCharArray = UUID.toCharArray();
		for (char c: UUIDCharArray) {
			UUIDSum += c;
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
		UUIDSummary();
		char[] tempChar = sifra.toCharArray();
		try {
			for (char c : tempChar) {
				if (Character.isUpperCase(c)) {
					tempSifra.append((char) (((int) c + UUIDSum - 65) % 26 + 65));
				} else if (Character.isLowerCase(c)) {
					tempSifra.append((char) (((int) c + UUIDSum - 97) % 26 + 97));
				} else if (Character.isDigit(c)) {
					tempSifra.append((char) (((int) c + UUIDSum - 48) % 10 + 48));
				} else {
					throw new CharConversionException();
				}
			}
		}
		catch (CharConversionException charConversionException) {
			//LOGGER.error("Greska u sifrovanju. Pokusaj sifrovanja nedozvoljenog karaktera: '" + errorChar + "'");
			System.out.println(charConversionException.getMessage());
			return "NULL";
		}
		//LOGGER.info("Sifrovana lozinka za UUID " + korisnickiUUID);
		return tempSifra.toString();
	}

	public static String sifrujLozinku(String UUID, String sifra) {

		StringBuilder tempSifra = new StringBuilder();
		UUIDSummary(UUID);
		char[] tempChar = sifra.toCharArray();
		try {
			for (char c : tempChar) {
				if (Character.isUpperCase(c)) {
					tempSifra.append((char) (((int) c + UUIDSum - 65) % 26 + 65));
				} else if (Character.isLowerCase(c)) {
					tempSifra.append((char) (((int) c + UUIDSum - 97) % 26 + 97));
				} else if (Character.isDigit(c)) {
					tempSifra.append((char) (((int) c + UUIDSum - 48) % 10 + 48));
				} else {
					throw new CharConversionException();
				}
			}
		}
		catch (CharConversionException charConversionException) {
			//LOGGER.error("Greska u sifrovanju. Pokusaj sifrovanja nedozvoljenog karaktera: '" + errorChar + "'");
			System.out.println(charConversionException.getMessage());
			return "NULL";
		}
		//LOGGER.info("Sifrovana lozinka za UUID " + korisnickiUUID);
		return tempSifra.toString();
	}

	public String toStringSerializable() {
		return String.format("%s~%s", getKorisnickiUUID(), getSifra());
	}

	@Override
	public String serializedFileName() {
		return "sifra.tdb";
	}

	@Override
	public void serialize() {
		try {
			DataManager.serializeString(toStringSerializable(), serializedFileName(), true);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
