package view;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * This interface represents the view the design. It will display all the functionalities the model
 * can perform but should only be accessed by the controller.
 */
public interface View {
  /**
   * This method is used to display the given image on the current window.
   *
   * @param image Image to be displayed.
   */
  public void displayImage(BufferedImage image);

  /**
   * Shows a certain kind of display.
   *
   * @param n pass 0 -> initial view, 1 -> generate view, 2 -> filter view, 3->load, 4->save.
   */
  public void show(int n);

  /**
   * this method is used to read the contents entered by user on the given view.
   *
   * @param n pass 0 -> initial view, 1 -> generate view, 2 -> filter view, 3->load, 4->save.
   * @return the contents entered by the user.
   */
  public String[] read(int n);

  /**
   * Displays an error message on the current window.
   *
   * @param message the message to be displayed.
   */
  public void displayErrorMessage(String message);

  /**
   * Set the listener for any actions.
   */
  void setListener(ActionListener listener);

}
