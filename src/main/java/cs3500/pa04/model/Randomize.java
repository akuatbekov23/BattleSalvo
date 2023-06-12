package cs3500.pa04.model;

import java.util.Random;

/**
 * a class for randomizing a number between bounds
 */
public class Randomize {
  private Random rand = new Random();

  /**
   * @param leftBound the lower bound to pick a random number between
   * @param rightBound the upper bound to pick a random number between
   * @return integer representing a random number between the left and right bound
   */
  public int randomNumber(int leftBound, int rightBound) {
    return rand.nextInt(leftBound, rightBound);
  }

}
