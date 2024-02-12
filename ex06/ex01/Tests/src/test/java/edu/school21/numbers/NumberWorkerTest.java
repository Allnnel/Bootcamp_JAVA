package edu.school21.numbers;

import static org.junit.jupiter.api.Assertions.*;

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
  @ValueSource(ints = {4, 6, 10})
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
}
