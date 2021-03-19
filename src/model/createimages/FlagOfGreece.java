package model.createimages;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Class to generate a the flag of Greece image.
 */
public class FlagOfGreece extends Rainbow {
  /**
   * The constructor initializes the flag size and colors. THe flag of greece has 9 stripes so
   * inorder to maintain uniformity, the height must be divisible by 9.
   *
   * @param width  the width of the flag (must be 1.5 times the length of height).
   * @param height the height of the flag (must be 2/3rd times width and divisible by 9).
   * @throws IllegalArgumentException if the dimension is less than 1.
   */
  public FlagOfGreece(int width, int height) throws IllegalArgumentException {
    super(width, height);
    if ((height * 1.5) != width) {
      throw new IllegalArgumentException("Incorrect dimension proportions");
    }
    if (height % 9 != 0) {
      throw new IllegalArgumentException("height must be divisible by 9");
    }
    colors[0] = new Color(0, 85, 164);
    colors[1] = new Color(255, 255, 255);
  }

  @Override
  protected void setColor() {
    for (int i = image.getHeight() - 1; i >= 0; i--) {
      for (int j = image.getWidth() - 1; j >= 0; j--) {
        if (i <= thickness[0]) {
          image.setRGB(j, i, colors[0].getRGB());
        } else if (i <= thickness[1]) {
          image.setRGB(j, i, colors[1].getRGB());
        } else if (i <= thickness[2]) {
          image.setRGB(j, i, colors[0].getRGB());
        } else if (i <= thickness[3]) {
          image.setRGB(j, i, colors[1].getRGB());
        } else if (i <= thickness[4]) {
          image.setRGB(j, i, colors[0].getRGB());
        } else if (i <= thickness[5]) {
          image.setRGB(j, i, colors[1].getRGB());
        } else if (i <= thickness[6]) {
          image.setRGB(j, i, colors[0].getRGB());
        } else if (i <= thickness[7]) {
          image.setRGB(j, i, colors[1].getRGB());
        } else {
          image.setRGB(j, i, colors[0].getRGB());
        }
      }
    }
  }


  protected void reParse() {
    int k = 0;
    int l = 0;
    int side = image.getHeight() / 9 * 5;
    int squareSize = side / 5;
    for (int i = 0; i <= side; i++) {
      if (i % squareSize == 0) {
        k++;
      }
      l = 0;
      for (int j = 0; j <= side; j++) {

        if (j % squareSize == 0) {
          l++;
        }
        if (l == 3 || k == 3) {
          image.setRGB(j, i, colors[1].getRGB());
        } else {
          image.setRGB(j, i, colors[0].getRGB());
        }
      }
    }
  }

  /**
   * This method fetches the generated image.
   *
   * @return The generated image.
   */
  @Override
  public BufferedImage getImage() {
    setThickness(9);
    setColor();
    reParse();
    return image;
  }
}
