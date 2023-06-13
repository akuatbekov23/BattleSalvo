package cs3500.pa04;

import cs3500.pa04.controller.ProxyController;
import cs3500.pa04.model.Ai;
import cs3500.pa04.model.Board;
import cs3500.pa04.model.User;
import cs3500.pa04.view.SalvoView;
import java.io.IOException;
import java.net.Socket;

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

    if (args.length == 0) {

      SalvoView view = new SalvoView();
      view.promptBoardSize();
      view.promptFleetSize();
    }

    else if (args.length == 2) {
      try {
        Socket server = new Socket(args[0], Integer.parseInt(args[1]));
        Player player = new Ai();
        ProxyController proxyController = new ProxyController(server, player);
        proxyController.run();
        server.close();
      } catch (IOException e) {
        System.out.println("Invalid arguments! Please put in the server host and port.");
      }
    }
  }
}