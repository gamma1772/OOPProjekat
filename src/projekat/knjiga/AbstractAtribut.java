package projekat.knjiga;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AbstractAtribut  implements IJedinstveniIdentifikator{
	private int id;
	private String imeAutora;

	@Override
	public int generisiJedinstveniIdentifikator(ArrayList<AbstractAtribut> list, int limiter) {
		int id = 0;
		int[] idArray = new int[list.size()];
		Random random = new Random();
		id = random.nextInt(limiter);
		for (AbstractAtribut a : list) {
			if (a.id == this.id) {
				id = random.nextInt(limiter);
			}
		}

		return id;
	}
}
