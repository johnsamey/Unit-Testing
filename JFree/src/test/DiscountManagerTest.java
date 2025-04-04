package test;
import JFree.DiscountManager;
import JFree.IDiscountCalculator;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;
import static org.junit.Assert.*;

public class DiscountManagerTest {

    @Test
    public void testCalculatePriceWhenDiscountsSeasonIsFalse() throws Exception {
        // Arrange
        boolean isDiscountsSeason = false;
        double originalPrice = 100.0;
        double expectedPrice = 100.0;

        Mockery mockingContext = new Mockery();
        IDiscountCalculator mockedDependency = mockingContext.mock(IDiscountCalculator.class);
        mockingContext.checking(new Expectations(){{
            never(mockedDependency).isTheSpecialWeek();
            never(mockedDependency).getDiscountPercentage();
        }});

        DiscountManager discountManager = new DiscountManager(isDiscountsSeason, mockedDependency);

        // Act
        double ans = discountManager.calculatePriceAfterDiscount(originalPrice);

        // Assert
        mockingContext.assertIsSatisfied();;
        assertEquals(expectedPrice, ans, 0);
    }

    @Test
    public void testCalculatePriceWhenDiscountsSeasonIsTrueAndSpecialWeekIsTrue() throws Exception {
        // Arrange
        boolean isDiscountsSeason = true;
        double originalPrice = 100.0;
        double expectedPrice = 80.0;

        Mockery mockingContext = new Mockery();
        IDiscountCalculator mockedDependency = mockingContext.mock(IDiscountCalculator.class);
        mockingContext.checking(new Expectations(){{
            oneOf(mockedDependency).isTheSpecialWeek(); will(returnValue(true));
        }});

        DiscountManager discountManager = new DiscountManager(isDiscountsSeason, mockedDependency);

        // Act
        double ans = discountManager.calculatePriceAfterDiscount(originalPrice);

        // Assert
        assertEquals(expectedPrice, ans, 0);
        mockingContext.assertIsSatisfied();
    }

    @Test
    public void testCalculatePriceWhenDiscountsSeasonIsTrueAndNormalDiscount() throws Exception {
        // Arrange
        boolean isDiscountsSeason = true;
        double originalPrice = 100.0;
        double expectedPrice = 95.0;

        Mockery mockingContext = new Mockery();
        IDiscountCalculator mockedDependency = mockingContext.mock(IDiscountCalculator.class);
        mockingContext.checking(new Expectations(){{
            oneOf(mockedDependency).isTheSpecialWeek(); will(returnValue(false));
            oneOf(mockedDependency).getDiscountPercentage(); will(returnValue(5));
        }});

        DiscountManager discountManager = new DiscountManager(isDiscountsSeason, mockedDependency);

        // Act
        double ans = discountManager.calculatePriceAfterDiscount(originalPrice);

        // Assert
        assertEquals(expectedPrice, ans, 0);
        mockingContext.assertIsSatisfied();
    }

    @Test
    public void testCalculatePriceWhenDiscountsSeasonIsTrueAndSpecialWeekAndNormalDiscount() throws Exception {
        // Arrange
        boolean isDiscountsSeason = true;
        double originalPrice = 100.0;
        double expectedPrice = 72.0;

        Mockery mockingContext = new Mockery();
        IDiscountCalculator mockedDependency = mockingContext.mock(IDiscountCalculator.class);
        mockingContext.checking(new Expectations(){{
            oneOf(mockedDependency).isTheSpecialWeek(); will(returnValue(true));
            oneOf(mockedDependency).getDiscountPercentage(); will(returnValue(10));
        }});

        DiscountManager discountManager = new DiscountManager(isDiscountsSeason, mockedDependency);

        // Act
        double ans = discountManager.calculatePriceAfterDiscount(originalPrice);

        // Assert
        assertEquals(expectedPrice, ans, 0);
        mockingContext.assertIsSatisfied();
    }
}
