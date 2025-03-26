package test;

import JFree.DiscountCalculator;
import org.jfree.data.time.Week;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class DiscountCalculatorTest {

    @Test
    public void testIsTheSpecialWeekWhenFalse() throws Exception {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.MARCH, 22);  // March 22, 2025
        Date date = calendar.getTime();
        Week week = new Week(date);

        System.out.println("Week = " + week);
        DiscountCalculator calculator = new DiscountCalculator(week);
        // Act
        boolean ans = calculator.isTheSpecialWeek();

        // Assert
        assertFalse(ans);
    }

    // Test missing cases ( JUNE, 23 is a date in week 26 )
    @Test
    public void testIsTheSpecialWeekWhenTrue() throws Exception {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JUNE, 23);  // March 22, 2025
        Date date = calendar.getTime();
        Week week = new Week(date);

        System.out.println("Week = " + week);
        DiscountCalculator calculator = new DiscountCalculator(week);
        // Act
        boolean ans = calculator.isTheSpecialWeek();

        // Assert
        assertTrue(ans);
    }

    @Test
    public void testGetDiscountPercentageForEvenWeek() throws Exception {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.MARCH, 15);  // Week 12 (even)
        Date date = calendar.getTime();
        Week week = new Week(date);

        System.out.println("Even week = " + week);
        DiscountCalculator calculator = new DiscountCalculator(week);

        // Act
        int ans = calculator.getDiscountPercentage();

        // Assert
        assertEquals(7, ans);
    }

    @Test
    public void testGetDiscountPercentageForOddWeek() throws Exception {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.MARCH, 10);  // Week 11 (odd)
        Date date = calendar.getTime();
        Week week = new Week(date);


        System.out.println("Odd week = " + week);
        DiscountCalculator calculator = new DiscountCalculator(week);

        // Act
        int ans = calculator.getDiscountPercentage();

        // Assert
        assertEquals(5, ans);
    }
}
