package twitter.utils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

	public static LocalDate pastDate(int days) {
		return today().minusDays(days);
	}

	public static LocalDate today() {
		return LocalDate.now();
	}

	public static String formatter(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    return date.format(formatter);
	}

	public static String formatter(Date date) {
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}
}
