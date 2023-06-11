package cs3500.pa04;

import cs3500.pa03.view.SalvoView;

/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) {
    SalvoView view = new SalvoView();
    view.promptBoardSize();
    view.promptFleetSize();
  }
}