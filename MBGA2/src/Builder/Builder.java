package Builder;

import java.sql.Timestamp;

public class Builder {
	@SuppressWarnings("deprecation")
	public static String parseDate(Timestamp date) {
		if (date == null) {
			return "";
		}
		String h = Integer.toString(date.getHours());
		String m = Integer.toString(date.getMinutes());
		if (date.getHours() < 10) {
			h = "0" + h;
		}
		if (date.getMinutes() < 10) {
			m = "0" + m;
		}

		return h + ":" + m;
	}
}
