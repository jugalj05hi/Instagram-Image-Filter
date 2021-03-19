package model.createimages;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Class to generate the rainbow image.
 */
public class Rainbow extends AbstractStripes {

  /**
   * Sets up up the images to be generated.
   *
   * @param width  width of the image.
   * @param height height of the image.
   * @throws IllegalArgumentException if the dimension is less than 1.
   */
  public Rainbow(int width, int height) {
    super(width,height);
    colors = new Color[]{new Color(148, 0, 211),
                         new Color(75, 0, 130),
                         new Color(0, 0, 255),
                         new Color(0, 255, 0),
                         new Color(255, 255, 0),
                         new Color(255, 127, 0),
                         new Color(255, 0, 0)};
  }

  /**
   * This method fetches the generated image.
   *
   * @return The generated image.
   */
  @Override
  public BufferedImage getImage() {
    setThickness(7);
    setColor();
    return image;
  }
}
