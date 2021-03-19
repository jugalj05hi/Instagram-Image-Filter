package view;

//import java.awt.*;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * This interface represents the different kind of displays.
 */
public interface DisplayType {

  public boolean canDisplayImage();

  public void replaceImage(BufferedImage image);

  public String[] read();

  public void displayErrorMessage(String message);

  /**
   * Set the listener for any actions.
   */
  public void setListener(ActionListener listener);

  /**
   * sets the view visible.
   */
  public void shows();

  /**
   * sets the view invisible.
   */
  public void close();
}
