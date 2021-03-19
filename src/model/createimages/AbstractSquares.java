package model.createimages;

import java.awt.image.BufferedImage;

/**
 * This class abstracts the process of generating squares in an image.
 */
public abstract class AbstractSquares implements GenerateImage {
  protected BufferedImage image;
  int side;
  int[] color;
  int squareSize;

  /**
   * Abstract constructor is used to validate the parameters.
   *
   * @param side one side length of th entire image.
   */
  public AbstractSquares(int side) {
    if (side < 1) {
      throw new IllegalArgumentException("Size cannot be less than 1");
    }
    this.side = side;
  }

  protected void setColor() {
    int k = 0;
    int l = 0;
    for (int i = 0; i < side; i++) {
      if (i % squareSize == 0) {
        k++;
      }
      for (int j = 0; j < side; j++) {

        if (j % squareSize == 0) {
          l++;
        }
        if ((l + k) % 2 == 0) {
          image.setRGB(j, i, color[0]);
        } else {
          image.setRGB(j, i, color[1]);
        }
      }
    }
  }

}
