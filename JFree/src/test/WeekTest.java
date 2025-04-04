package test;

import org.jfree.data.time.Week;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class WeekTest {
    @Test
    public void testWeekDefaultCtor() {
        // Arrange
        Week week = new Week();

        Calendar cal = Calendar.getInstance();
        int currentWeek = cal.get(Calendar.WEEK_OF_YEAR);
        int currentYear = cal.get(Calendar.YEAR);

        // Assert
        assertEquals(currentYear, week.getYearValue());
        assertEquals(currentWeek, week.getWeek());
    }

    @Test
    public void testWeekCtorWithDate() {
        // Arrange
        Calendar cal = Calendar.getInstance();
        cal.set(2025, Calendar.MARCH, 22); // March 22, 2025
        Date date = cal.getTime();

        // Act
        Week week = new Week(date);

        // Assert
        assertEquals(2025, week.getYearValue());
        assertEquals(12, week.getWeek()); // March 22, 2025 is week 12
    }

    @Test
    public void testYearIsSameAsYearValue() {
        // Arrange
        Calendar cal = Calendar.getInstance();
        cal.set(2025, Calendar.MARCH, 15); // March 15, 2025
        Date date = cal.getTime();

        // Act
        Week week = new Week(date);

        // Assert
        assertEquals(2025, week.getYearValue());
        assertEquals(2025, week.getYear().getYear());
    }

    @Test
    public void testNextAndPrevious() {
        // Arrange
        Week week = new Week(12, 2025);

        // Assert
        assertEquals(new Week(13, 2025), week.next());
        assertEquals(new Week(11, 2025), week.previous());

        assertEquals(week, week.next().previous());
        assertEquals(week, week.previous().next());
    }

    @Test
    public void testNextWrap() {
        // Arrange
        Week week = new Week(52, 2025);

        // Assert
        assertEquals(new Week(1, 2026), week.next());
    }

    @Test
    public void testPreviousWrap() {
        // Arrange
        Week week = new Week(1, 2025);

        // Assert
        assertEquals(new Week(52, 2024), week.previous());
    }

    @Test
    public void testParseWeek() {
        // Arrange
        Week week = new Week(12, 2025);

        // Assert
        assertEquals(week, Week.parseWeek("2025-12"));
        assertEquals(week, Week.parseWeek("2025,12"));
        assertEquals(week, Week.parseWeek("2025 12"));
        assertEquals(week, Week.parseWeek("2025.12"));
    }

    @Test
    public void testMillisecondsFunctions() {
        // Arrange
        Week week = new Week(12, 2025);

        // Assert
        assertEquals(1742076000000L, week.getFirstMillisecond());
        assertEquals(1742680799999L, week.getLastMillisecond());
        assertEquals(7L * 24L * 60L * 60L * 1000L - 1L, week.getLastMillisecond() - week.getFirstMillisecond());
    }
}
