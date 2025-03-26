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
        assertEquals(currentYear, week.getYear().getYear());
        assertEquals(currentWeek, week.getWeek());
    }

    @Test
    public void testWeekCtorWithDate() {
        // Arrange
        Calendar cal = Calendar.getInstance();
        cal.set(2025, Calendar.MARCH, 15); // March 22, 2025
        Date date = cal.getTime();

        // Act
        Week week = new Week(date);

        // Assert
        assertEquals(2025, week.getYear().getYear());
        assertEquals(12, week.getWeek()); // March 15, 2025 is week 12
    }
}
