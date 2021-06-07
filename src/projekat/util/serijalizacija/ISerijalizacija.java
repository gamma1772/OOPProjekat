package projekat.util.serijalizacija;

public interface ISerijalizacija {
	/**Kreira String koji sadrzi potrebne podatke objekta potrebne za kasniju deserijalizaciju.*/
	String toStringSerializable();
	/**Ime datoteke gde se cuvaju podaci kreirani uz pomoc toStringSerializable()*/
	String serializedFileName();
	/**Pokrece serijalizaciju objekta.*/
	void serialize();
}
