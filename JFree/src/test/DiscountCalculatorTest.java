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
        Week week = new Week(20, 2025);
        DiscountCalculator calculator = new DiscountCalculator(week);

        // Assert
        assertFalse(calculator.isTheSpecialWeek());
    }

    @Test
    public void testIsTheSpecialWeekWhenTrue() throws Exception {
        // Arrange
        Week week = new Week(26, 2025);
        DiscountCalculator calculator = new DiscountCalculator(week);

        // Assert
        assertTrue(calculator.isTheSpecialWeek());
    }

    @Test
    public void testGetDiscountPercentageForEvenWeek() throws Exception {
        Week week = new Week(40, 2025);
        DiscountCalculator calculator = new DiscountCalculator(week);

        // Act
        int ans = calculator.getDiscountPercentage();

        // Assert
        assertEquals(7, ans);
    }

    @Test
    public void testGetDiscountPercentageForOddWeek() throws Exception {
        Week week = new Week(41, 2025);
        DiscountCalculator calculator = new DiscountCalculator(week);

        // Act
        int ans = calculator.getDiscountPercentage();

        // Assert
        assertEquals(5, ans);
    }
}
