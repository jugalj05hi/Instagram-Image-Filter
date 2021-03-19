package model.processimages;

import java.awt.image.BufferedImage;

/**
 * This interface represents the various filters that can be performed on a image.
 */
public interface Filter {
  /**
   * This method uses the given image and blurs it.
   *
   * @return returns the blurred image.
   */
  public BufferedImage blur();

  /**
   * This method uses the given image and sharpens it.
   *
   * @return returns the sharpened image.
   */
  public BufferedImage sharpen();

  /**
   * This method uses the given image and turns it into a greyscale image.
   *
   * @return returns the greyscale image.
   */
  public BufferedImage greyscale();

  /**
   * This method uses the given image and turns it into a sepia image.
   *
   * @return returns the sepia image.
   */
  public BufferedImage sepia();

  /**
   * This method uses the given image and turns it into a dithered image.
   *
   * @return returns the dither image.
   */
  public BufferedImage dither();


  /**
   * This method uses the given image and turns it into a mosaic image.
   *
   * @param seeds the number of seeds the mosaic must have.
   * @return returns the dither image.
   */
  public BufferedImage mosaic(int seeds);
}
