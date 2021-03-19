package model.createimages;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Class to generate a the flag of France image.
 */
public class FlagOfFrance extends RainbowVertical {

  /**
   * The constructor initializes the flag size and colors.
   *
   * @param width  the width of the flag (must be 1.5 times the length of height).
   * @param height the height of the flag (must be 2/3rd times the length of width).
   * @throws IllegalArgumentException if the dimensions are incorrect
   */
  public FlagOfFrance(int width, int height) {
    super(width, height);
    if ((height * 1.5) != width || width < 1 || height < 1) {
      throw new IllegalArgumentException("Incorrect dimension proportions");
    }
    colors[0] = new Color(0, 85, 164);
    colors[1] = new Color(255, 255, 255);
    colors[2] = new Color(239, 65, 53);
  }

  @Override
  public BufferedImage getImage() {
    setThickness(3);
    setColor();
    return image;
  }
}
