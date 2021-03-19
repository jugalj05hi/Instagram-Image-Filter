package model.createimages;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Used to generate a checkerboard image.
 */
public class CheckerBoard extends AbstractSquares {


  /**
   * The constructor expects the size of one square of the checkerboard, and sets the entire image
   * sized base on it.
   *
   * @param squareSide the length of one side of one box on the checkerboard.
   * @throws IllegalArgumentException if the side size is less than 1.
   */
  public CheckerBoard(int squareSide) {
    super(squareSide * 8);
    this.squareSize = squareSide;
    image = new BufferedImage(side, side, BufferedImage.TYPE_INT_RGB);
    color = new int[]{Color.white.getRGB(), Color.black.getRGB()};
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

  /**
   * This method fetches the generated image.
   *
   * @return The generated image.
   */
  @Override
  public BufferedImage getImage() {
    setColor();
    return image;
  }
}
