package cs3500.pa03.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.controller.InputController;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class InputControllerTest {

  private InputController inputController;

  @BeforeEach
  public void setUp() {
    inputController = new InputController();
  }

  @Test
  public void testGetBoardHeight_ValidInput() {
    String input = "10";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    inputController.getBoardHeight();

    assertEquals(10, inputController.getHeight());
  }



  @Test
  public void testGetBoardWidthValidInput() {
    String input = "8";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    inputController.getBoardWidth();

    assertEquals(8, inputController.getWidth());
  }

  @Test
  public void testGetBoardWidthInvalidInput() {
    String input = "12\n5";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    inputController.getBoardWidth();

    assertEquals(12, inputController.getWidth());
  }

  @Test
  public void testGetMaxShips() {
    inputController.setHeight(9);
    inputController.setWidth(7);

    int maxShips = inputController.getMaxShips();

    assertEquals(7, maxShips);
  }

}
