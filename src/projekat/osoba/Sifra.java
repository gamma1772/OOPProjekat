package projekat.osoba;

import projekat.debug.Logger;

import java.io.CharConversionException;

public class Sifra {
	private String korisnickiUUID, sifra;
	private int UUIDSum = 0;
	private static final Logger LOGGER = new Logger("SIFRA");

	public Sifra(String korisnickiUUID, String sifra) {
		this.korisnickiUUID = korisnickiUUID;
		this.UUIDSummary(korisnickiUUID);
		this.setSifra(sifrujLozinku(sifra));
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

	private void UUIDSummary(String UUID) {
		char[] UUIDCharArray = UUID.toCharArray();
		for (char c: UUIDCharArray) {
			UUIDSum += (int)c;
		}
	}

	//TODO
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
		return tempSifra.toString();
	}
	//TODO
	public static String desifrujLozinku(String sifra) {
		return "";
	}
}
