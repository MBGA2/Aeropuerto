package Vista.Subsistemas;

import java.util.ArrayList;
import java.util.List;

public class seg_Resources {

	public final static String genericCamPath = "src/Iconos/";
	public final static String extension = ".gif";

	public static String mountPath(List<String> l) {

		StringBuilder strBld = new StringBuilder();

		for (String s : l) {
			strBld.append(s);
		}

		return strBld.toString();

	}

	public static String mountCamPath(String ID) {
		List<String> l = new ArrayList<String>();

		l.add(genericCamPath);
		l.add(ID);
		l.add(extension);

		return mountPath(l);
	}

}
