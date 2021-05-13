package projekat.osoba;

public enum EnumPol {
	MUSKO(0),
	ZENSKO(1);

	private int rbr;

	EnumPol(int rbr) {
		this.rbr = rbr;
	}

	public int getNum() {
		return this.rbr;
	}
}
