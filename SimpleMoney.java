/**
 * This class represents a simple implementation of US Money.
 */
public class SimpleMoney implements Money {
  private int dollars;
  private int cents;

  /**
   * Constructs a SimpleMoney object with the given dollar and cent amounts.
   *
   * @param dollars the dollar amount
   * @param cents the cent amount
   * @throws IllegalArgumentException if dollars or cents are negative
   */
  public SimpleMoney(int dollars, int cents) {
    if (dollars < 0 || cents < 0) {
      throw new IllegalArgumentException("Money cannot have negative values");
    }
    this.dollars = dollars + (cents / 100);
    this.cents = cents % 100;
  }

  /**
   * Adds another Money amount to this Money amount.
   *
   * @param other the Money amount to add
   * @return a new Money object representing the sum
   */
  @Override
  public Money add(Money other) {
    if (other == null) {
      throw new IllegalArgumentException("Cannot add null money");
    }
    double otherValue = other.getDecimalValue();
    int otherDollars = (int) otherValue;
    int otherCents = (int) Math.round((otherValue - otherDollars) * 100);
    int totalDollars = this.dollars + otherDollars;
    int totalCents = this.cents + otherCents;
    if (totalCents >= 100) {
      totalDollars += totalCents / 100;
      totalCents = totalCents % 100;
    }
    return new SimpleMoney(totalDollars, totalCents);
  }

  /**
   * Adds a money amount given as separate dollar and cent values.
   *
   * @param dollars the dollar amount to add
   * @param cents the cent amount to add
   * @return a new Money object representing the sum
   * @throws IllegalArgumentException if dollars or cents are negative
   */
  @Override
  public Money add(int dollars, int cents) {
    if (dollars < 0 || cents < 0) {
      throw new IllegalArgumentException("Money cannot have negative values");
    }
    int totalDollars = this.dollars + dollars;
    int totalCents = this.cents + cents;
    if (totalCents >= 100) {
      totalDollars += totalCents / 100;
      totalCents = totalCents % 100;
    }
    return new SimpleMoney(totalDollars, totalCents);
  }

  /**
   * Returns the decimal value of the money in the format "xx.yy".
   * The cent value is padded with leading zeroes if necessary.
   *
   * @return the decimal value of this Money amount
   */
  @Override
  public double getDecimalValue() {
    return dollars + cents / 100.0;
  }

  /**
   * Returns a string representation of this Money in the format "$xx.yy".
   * There are exactly two digits after the decimal point, padded with leading zeroes.
   *
   * @return a string representation of this Money
   */
  @Override
  public String toString() {
    return String.format("$%d.%02d", dollars, cents);
  }
}