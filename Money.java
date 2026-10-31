/**
 * This interface represents US money with dollars and cents.
 * It provides methods for adding money amounts and retrieving decimal values.
 */
public interface Money {
  /**
   * Adds another Money amount to this Money amount.
   *
   * @param other the Money amount to add
   * @return a new Money object representing the sum
   */
  Money add(Money other);

  /**
   * Adds a money amount given as separate dollar and cent values.
   *
   * @param dollars the dollar amount to add
   * @param cents the cent amount to add
   * @return a new Money object representing the sum
   * @throws IllegalArgumentException if the provided money is invalid (negative values)
   */
  Money add(int dollars, int cents);

  /**
   * Returns the decimal value of the money in the format "xx.yy".
   * The cent value is padded with leading zeroes if necessary.
   *
   * @return the decimal value of this Money amount
   */
  double getDecimalValue();
}