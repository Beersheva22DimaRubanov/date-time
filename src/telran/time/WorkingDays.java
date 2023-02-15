package telran.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.util.Locale;

public class WorkingDays implements TemporalAdjuster {
	int daysAmount;
	DayOfWeek[] dayOffs;
	
	public WorkingDays(int daysAmount, DayOfWeek[] dayOffs) {
		this.daysAmount = daysAmount;
		this.dayOffs = dayOffs;
	}

	@Override
	public Temporal adjustInto(Temporal temporal) {
		Temporal res = temporal;
		for(int i = 0; i<daysAmount; i++) {
			while(isDayOff(res)) {
				res = res.plus(1, ChronoUnit.DAYS);
			}
			res = res.plus(1, ChronoUnit.DAYS);
		}
		return res;
	}
	
	private boolean isDayOff(Temporal temporal) {
		boolean res = false;
		String str = temporal.toString();
		LocalDate date = LocalDate.parse(str);
		DateTimeFormatter dayFormater = DateTimeFormatter.ofPattern("EEEE", Locale.CANADA);
		int index = 0;
		while(index <dayOffs.length && !res) {
			if(date.format(dayFormater).toUpperCase().equals(dayOffs[index].toString())) {
				res = true;
			}
			index++;
		}
		return res;
	}
}
