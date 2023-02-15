package telran.time;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.Locale;


public class NextFriday13 implements TemporalAdjuster {

	@Override
	public Temporal adjustInto(Temporal temporal) {
		Temporal res = temporal;
		while (!compare(res)) {
			res = res.plus(1, ChronoUnit.DAYS);
		}
		return res;
	}

	private boolean compare(Temporal temporal) {
		boolean res = false;
		DateTimeFormatter dayFormater = DateTimeFormatter.ofPattern("EEEE", Locale.CANADA);
		DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("d");
		String str = temporal.toString();
		LocalDate date = LocalDate.parse(str);
		if(date.format(dayFormater).equals("Friday") && date.format(dateFormater).equals("13")) {
			res = true;
		}
		return res;
	}

}
