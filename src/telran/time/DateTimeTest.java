package telran.time;

import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class DateTimeTest {

	@BeforeEach
	void setUp() throws Exception {

	}

	@Test
	@Disabled
	void test() {
		LocalDate birthDateAS = LocalDate.parse("1799-06-06");
		LocalDate barMizvaAs = birthDateAS.plusYears(13);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM,YYYY,d", Locale.CANADA);
		System.out.println(barMizvaAs.format(dtf));
		ChronoUnit unit = ChronoUnit.MONTHS;
		System.out.printf("Number of %s beetwen %s and %s is %d", unit, birthDateAS, barMizvaAs,
				unit.between(birthDateAS, barMizvaAs));
	}

	@Test
	void barMizvaTest() {
		LocalDate current = LocalDate.now();
		assertEquals(current.plusYears(13), current.with(new BarMizvaAdjuster()));
	}

	@Test
	void NextFriday13() {
		LocalDate current = LocalDate.now();
		assertEquals(current.plusDays(239), current.with(new NextFriday13()));
	}

	@Test
	void WorkingDaysTest() {
		LocalDate current = LocalDate.now();
		assertEquals(current.plusDays(8), current.with(new WorkingDays(7, new DayOfWeek[] { DayOfWeek.SATURDAY })));
	}

	@Test
	void displayCurrentDateTimeCanadaTimeZones() {
		String[] timeZonesId = TimeZone.getAvailableIDs();
		Arrays.stream(timeZonesId).filter(x -> x.toLowerCase().contains("canada"))
				.forEach(x -> System.out.printf("%s %s\n", 
				LocalDate.ofInstant(Instant.now(), ZoneId.of(x))
				.format(DateTimeFormatter.ofPattern("MMM-YYYY-dd H:m:s")), x));
	}

}
