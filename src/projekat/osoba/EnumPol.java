package projekat.osoba;

public enum EnumPol {
	MUSKO(1),
	ZENSKO(2);

	private int rbr;

	EnumPol(int rbr) {
		this.rbr = rbr;
	}

	public int getNum() {
		return this.rbr;
	}
}
