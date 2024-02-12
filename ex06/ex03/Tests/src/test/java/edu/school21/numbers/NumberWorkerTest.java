package edu.school21.numbers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberWorkerTest {
  @ParameterizedTest
  @ValueSource(ints = {2, 5, 7, 11})
  public void isPrimeForPrimes(int prime) {
    try {
      assertTrue(new NumberWorker().isPrime(prime));
    } catch (IllegalNumberException e) {
      fail("Unexpected IllegalNumberException");
    }
  }

  @ParameterizedTest
  @ValueSource(ints = {4, 6, 10, 46340})
  public void isPrimeForNotPrimes(int prime) {
    try {
      assertFalse(new NumberWorker().isPrime(prime));
    } catch (IllegalNumberException e) {
      fail("Unexpected IllegalNumberException");
    }
  }

  @ParameterizedTest
  @ValueSource(ints = {0, 1, -12})
  public void isPrimeForIncorrectNumbers(int incorrectNum) {
    assertThrows(IllegalNumberException.class, () -> new NumberWorker().isPrime(incorrectNum));
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
  public void isSumCorrect(int input, int expected) {
    assertEquals(expected, new NumberWorker().digitsSum(input));
  }

  @Test
  public void testLargeNonPrimeNumber() throws IllegalNumberException {
    final NumberWorker numberWorker = new NumberWorker();
    Assertions.assertTrue(numberWorker.isPrime(2));
    Assertions.assertTrue(numberWorker.isPrime(3));
    Assertions.assertTrue(numberWorker.isPrime(5));
    Assertions.assertTrue(numberWorker.isPrime(7));
    Assertions.assertTrue(numberWorker.isPrime(11));
    Assertions.assertFalse(numberWorker.isPrime(4));
    Assertions.assertFalse(numberWorker.isPrime(6));
    Assertions.assertFalse(numberWorker.isPrime(8));
    Assertions.assertFalse(numberWorker.isPrime(9));
    Assertions.assertFalse(numberWorker.isPrime(10));
    Assertions.assertThrows(IllegalNumberException.class, () -> numberWorker.isPrime(-1));
    Assertions.assertThrows(IllegalNumberException.class, () -> numberWorker.isPrime(0));
    Assertions.assertTrue(numberWorker.isPrime(46349));
    Assertions.assertFalse(numberWorker.isPrime(46350));
  }
}
