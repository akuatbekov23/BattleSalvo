package cs3500.pa03.controller;

import cs3500.pa04.controller.ValidatorController;
import cs3500.pa04.model.Board;
import cs3500.pa04.model.Coord;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ValidatorControllerTest {

  private ValidatorController validatorController;
  private Board seenAiBoard;

  @BeforeEach
  public void setUp() {
    validatorController = new ValidatorController();
    seenAiBoard = new Board(10, 10);
  }

  @Test
  public void testValidateSizeValidSize() {
    int size = 8;
    boolean isValid = validatorController.validateSize(size);
    Assertions.assertTrue(isValid);
  }

  @Test
  public void testValidateSizeInvalidSize() {
    int size = 4;
    boolean isValid = validatorController.validateSize(size);
    Assertions.assertFalse(isValid);
  }

  @Test
  public void testValidateFleetSizeInvalidFleetSize() {
    int maxShips = 10;
    int[] shipAmounts = {2, 3, 2, 1};
    boolean isValid = validatorController.validateFleetSize(maxShips, shipAmounts);
    Assertions.assertFalse(isValid);
  }

  @Test
  public void testValidateFleetSizeValidFleetSizeExceedsMaxShips() {
    int maxShips = 10;
    int[] shipAmounts = {3, 4, 2, 1};
    boolean isValid = validatorController.validateFleetSize(maxShips, shipAmounts);
    Assertions.assertFalse(isValid);
  }

  @Test
  public void testValidateFleetSizeInvalidFleetSizeZeroShips() {
    int maxShips = 10;
    int[] shipAmounts = {2, 0, 2, 1};
    boolean isValid = validatorController.validateFleetSize(maxShips, shipAmounts);
    Assertions.assertTrue(isValid);
  }

  @Test
  public void testInvalidSpotInBoundsAndNotHit() {
    Coord coord = new Coord(2, 3);
    boolean isInvalid = validatorController.invalidSpot(coord, seenAiBoard);
    Assertions.assertFalse(isInvalid);
  }

  @Test
  public void testInvalidSpotOutOfBounds() {
    Coord coord = new Coord(12, 8);
    boolean isInvalid = validatorController.invalidSpot(coord, seenAiBoard);
    Assertions.assertTrue(isInvalid);
  }

  @Test
  public void testBeenHitNotHit() {
    Coord coord = new Coord(4, 5);
    boolean beenHit = validatorController.beenHit(coord, seenAiBoard);
    Assertions.assertFalse(beenHit);
  }

  @Test
  public void testBeenHitAlreadyHit() {
    Coord coord = new Coord(2, 2);
    seenAiBoard.addHitSpot(coord);
    boolean beenHit = validatorController.beenHit(coord, seenAiBoard);
    Assertions.assertTrue(beenHit);
  }

  @Test
  public void testIsDupeNotDuplicate() {
    Coord coord = new Coord(1, 2);
    ArrayList<Coord> pendingShots = new ArrayList<>();
    pendingShots.add(new Coord(3, 4));
    pendingShots.add(new Coord(2, 1));

    boolean isDupe = validatorController.isDupe(coord, pendingShots);
    Assertions.assertFalse(isDupe);
  }

  @Test
  public void testIsDupeDuplicate() {
    Coord coord = new Coord(1, 2);
    ArrayList<Coord> pendingShots = new ArrayList<>();
    pendingShots.add(new Coord(1, 2));
    pendingShots.add(new Coord(3, 4));

    boolean isDupe = validatorController.isDupe(coord, pendingShots);
    Assertions.assertTrue(isDupe);
  }
}
