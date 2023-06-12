package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import cs3500.pa04.model.Randomize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests for the Randomize class
 */
class RandomizeTest {
  Randomize randomize;

  /**
   * set up before each random test
   */
  @BeforeEach
      public void setup() {
    randomize = new Randomize();
  }

  /**
   * tests randomizer
   */
  @Test
  public void testRandom() {
    int random1 = randomize.randomNumber(0, 1000);
    int random2 = randomize.randomNumber(0, 1000);
    assertNotEquals(random1, random2);
  }
}