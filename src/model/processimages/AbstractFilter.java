package model.processimages;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 * Abstract class to perform certain abstract functions.
 */
public abstract class AbstractFilter implements Filter {
  protected BufferedImage image;

  /**
   * Constructor initializes the class.
   *
   * @param image the image on which the functions must be performed.
   */
  AbstractFilter(BufferedImage image) {
    this.image = image;
  }

  /**
   * This modifies the pixels of the given image.
   *
   * @param pixelDensityArray the given image in pixel density of each element.
   * @param kernelSize        the size of the Kernel, it is suggested that it should be an odd
   *                          matrix.
   * @return the images witht he changed pixels.
   */
  BufferedImage modifyPixelDensity(float[] pixelDensityArray, int kernelSize) {
    Kernel kernel = new Kernel(kernelSize, kernelSize, pixelDensityArray);
    BufferedImageOp op = new ConvolveOp(kernel);
    BufferedImage dst = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
    return op.filter(image, dst);
  }

  int clamp(int value) {
    return value > 255 ? 255 : value < 0 ? 0 : value;
  }

}
