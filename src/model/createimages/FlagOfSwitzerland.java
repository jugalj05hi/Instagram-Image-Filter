package model.createimages;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Class to generate a the flag of France image.
 */
public class FlagOfSwitzerland extends CheckerBoard {

  /**
   * Flag initializes the size of the flag.
   *
   * @param squareSide expects one side of the flag.
   * @throws IllegalArgumentException if the dimension is less than 1.
   */
  public FlagOfSwitzerland(int squareSide) throws IllegalArgumentException {
    super(squareSide);
    side = squareSide;
    super.squareSize = squareSide / 5;
    image = new BufferedImage(side, side, BufferedImage.TYPE_INT_RGB);
    color = new int[]{Color.white.getRGB(), Color.red.getRGB()};
  }

  @Override
  protected void setColor() {
    int k = 0;
    int l = 0;
    for (int i = 0; i < side; i++) {
      if (i % squareSize == 0) {
        k++;
      }
      l = 0;
      for (int j = 0; j < side; j++) {

        if (j % squareSize == 0) {
          l++;
        }
        if ((l == 2 && k == 3) || (l == 3 && k == 3) || (l == 3 && k == 4)
                || (l == 3 && k == 2) || (l == 4 && k == 3)) {
          image.setRGB(j, i, color[0]);
        } else {
          image.setRGB(j, i, color[1]);
        }
      }
    }
  }
}
