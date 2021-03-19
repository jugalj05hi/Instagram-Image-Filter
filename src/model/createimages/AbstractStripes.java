package model.createimages;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * This is an abstract class that generates stripes in an image.
 */
public abstract class AbstractStripes implements GenerateImage {

  protected BufferedImage image;
  Color[] colors;
  int[] thickness;

  /**
   * Abstract constructor is used to validate the parameters.
   *
   * @param width  the width of the image.
   * @param height the height of the image.
   */
  AbstractStripes(int width, int height) {
    if (height < 1 || width < 1) {
      throw new IllegalArgumentException("dimensions cannot eb less than 1");
    }
    image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

  }

  protected void setThickness(int numberOfStripes) {
    thickness = new int[numberOfStripes];
    int mod = image.getHeight() % numberOfStripes;
    int increment = image.getHeight() / numberOfStripes;
    int prev = 0;
    for (int i = 0; i < numberOfStripes; i++) {
      thickness[i] = prev + increment;
      if (mod > 0) {
        thickness[i]++;
        mod--;
      }
      prev = thickness[i];
    }
  }

  protected void setColor() {
    for (int i = image.getHeight() - 1; i >= 0; i--) {
      for (int j = image.getWidth() - 1; j >= 0; j--) {
        if (i <= thickness[0]) {
          image.setRGB(j, i, colors[0].getRGB());
        } else if (i <= thickness[1]) {
          image.setRGB(j, i, colors[1].getRGB());
        } else if (i <= thickness[2]) {
          image.setRGB(j, i, colors[2].getRGB());
        } else if (i <= thickness[3]) {
          image.setRGB(j, i, colors[3].getRGB());
        } else if (i <= thickness[4]) {
          image.setRGB(j, i, colors[4].getRGB());
        } else if (i <= thickness[5]) {
          image.setRGB(j, i, colors[5].getRGB());
        } else {
          image.setRGB(j, i, colors[6].getRGB());
        }
      }
    }
  }
}
