package model.createimages;

import java.awt.image.BufferedImage;

/**
 * Generic interface to generate images. Classes implementing this are expected to take the
 * dimensions of the image in the constructor.
 */
public interface GenerateImage {
  /**
   * This method fetches the generated image.
   *
   * @return The generated image.
   */
  BufferedImage getImage();
}
