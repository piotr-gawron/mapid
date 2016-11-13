package list.mapid;

import java.util.Comparator;

public class PhylogenyComparator implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		//bacteria is the most important, if it's not bactieria then check if it's archea
		if (o1.startsWith("Bacteria")) {
			return 1;
		} else if (o2.startsWith("Bacteria")) {
			return 2;
		} else if (o1.startsWith("Archaea")) {
			return 1;
		} else if (o2.startsWith("Archaea")) {
			return 2;
		} else {
			//not interesting for now
			return 0;
		}
	}

}
