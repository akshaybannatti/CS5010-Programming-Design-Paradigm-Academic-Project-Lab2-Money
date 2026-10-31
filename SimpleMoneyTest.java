import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for SimpleMoney.
 */
public class SimpleMoneyTest {
  private Money moneyA;
  private Money moneyB;
  private Money moneyC;
  private Money moneyD;
  private Money moneyE;

  /**
   * Tests for Sets up.
   */
  @Before
  public void setUp() {
    moneyA = new SimpleMoney(1, 50);
    moneyB = new SimpleMoney(2, 50);
    moneyC = new SimpleMoney(3, 50);
    moneyD = new SimpleMoney(4, 50);
    moneyE = new SimpleMoney(5, 50);
  }

  /**
   * Positive values for valid Money objects tests.
   */
  @Test
  public void testPositiveValue() {
    Money m = new SimpleMoney(1, 50);
    assertEquals(1.50, m.getDecimalValue(), 0.001);
  }

  /**
   * Zero value which does valid Money objects tests.
   */
  @Test
  public void testZeroValue() {
    Money m = new SimpleMoney(0, 0);
    assertEquals(0.0, m.getDecimalValue(), 0.001);
  }

  /**
   * Tests that negative dollar.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeDollarValue() {
    new SimpleMoney(-1, 50);
  }

  /**
   * Tests that negative cent values.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeCentValue() {
    new SimpleMoney(1, -50);
  }

  /**
   * Tests that both negative values.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBothNegativeValues() {
    new SimpleMoney(-1, -50);
  }

  /**
   * Tests getDecimalValue.
   */
  @Test
  public void testGetDecimalValue() {
    assertEquals(1.50, moneyA.getDecimalValue(), 0.001);
    assertEquals(2.50, moneyB.getDecimalValue(), 0.001);
    assertEquals(3.50, moneyC.getDecimalValue(), 0.001);
    assertEquals(4.50, moneyD.getDecimalValue(), 0.001);
    assertEquals(5.50, moneyE.getDecimalValue(), 0.001);
  }

  /**
   * Tests getDecimalValue with leading zero padding.
   */
  @Test
  public void testGetDecimalValueWithPadding() {
    Money m = new SimpleMoney(8, 2);
    assertEquals(8.02, m.getDecimalValue(), 0.001);
  }

  /**
   * Tests for toString.
   */
  @Test
  public void testToString() {
    Money m = new SimpleMoney(1, 50);
    assertEquals("$1.50", m.toString());
  }

  /**
   * Tests toString with leading zero padding.
   */
  @Test
  public void testToStringWithPadding() {
    Money m = new SimpleMoney(8, 2);
    assertEquals("$8.02", m.toString());
  }

  /**
   * Tests toString with zero cents.
   */
  @Test
  public void testToStringZeroCents() {
    Money m = new SimpleMoney(10, 0);
    assertEquals("$10.00", m.toString());
  }

  /**
   * Tests add method with Money parameter.
   */
  @Test
  public void testAddMoney() {
    Money result = moneyA.add(moneyB);
    assertEquals(4.00, result.getDecimalValue(), 0.001);
  }

  /**
   * Tests add method with cents overflow.
   */
  @Test
  public void testAddMoneyCentsOverflow() {
    Money m1 = new SimpleMoney(1, 60);
    Money m2 = new SimpleMoney(2, 50);
    Money result = m1.add(m2);
    assertEquals(4.10, result.getDecimalValue(), 0.001);
  }

  /**
   * Tests add method with separate dollar and cent parameters.
   */
  @Test
  public void testAddDollarsAndCents() {
    Money result = moneyA.add(2, 30);
    assertEquals(3.80, result.getDecimalValue(), 0.001);
  }

  /**
   * Tests add method with cents overflow using separate parameters.
   */
  @Test
  public void testAddDollarsAndCentsCentsOverflow() {
    Money m = new SimpleMoney(1, 60);
    Money result = m.add(2, 50);
    assertEquals(4.10, result.getDecimalValue(), 0.001);
  }

  /**
   * Tests add method throws exception for negative dollars.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddNegativeDollars() {
    moneyA.add(-1, 50);
  }

  /**
   * Tests add method throws exception for negative cents.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddNegativeCents() {
    moneyA.add(1, -50);
  }

  /**
   * Tests add method with zero values.
   */
  @Test
  public void testAddZeroValues() {
    Money result = moneyA.add(0, 0);
    assertEquals(1.50, result.getDecimalValue(), 0.001);
  }

  /**
   * Tests for constructor cents overflow.
   */
  @Test
  public void testConstructorCentsOverflow() {
    Money m = new SimpleMoney(1, 150);
    assertEquals(2.50, m.getDecimalValue(), 0.001);
  }

  /**
   * Tests adding Money that does multiple dollar overflow from cents.
   */
  @Test
  public void testAddMultipleDollarOverflow() {
    Money m1 = new SimpleMoney(5, 75);
    Money m2 = new SimpleMoney(3, 80);
    Money result = m1.add(m2);
    assertEquals(9.55, result.getDecimalValue(), 0.001);
  }
}