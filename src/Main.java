import java.io.IOException;

import controller.Controller;
import controller.IController;

/**
 * This is the driver of the mvc design. The main function of the program.
 */
public class Main {
  /**
   * Sets the program in motion.
   *
   * @throws IOException in case of a wrong input.
   */
  public static void main(String[] args) throws IOException {

    IController controller = new Controller(args[0]);

    controller.jumpStart();

  }
}


