package model.imageloaders;

import java.awt.image.BufferedImage;

/**
 * The interface ImageReader provide us with method(s) to read an image.
 */

public interface ImageReader {
  /**
   * THis fields fetches the read image.
   *
   * @return the read image.
   */
  BufferedImage getImage();
}
