package model.imageloaders;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * The interface ImageWriter provides with the method to store images according to user choices.
 */
public interface ImageWriter {

  /**
   * The method provides the means to store/write an image according to user preferences. It takes
   * in three arguments.
   *
   * @param image         takes in image to be stored as an argument.
   * @param writePath     the path/location where the data must be stored.
   * @param imageSaveType the extension of the image.
   * @throws IOException              whenever user inputs the wrong value.
   * @throws IllegalArgumentException if the path entered by user is invalid.
   */
  void writeImage(BufferedImage image, String writePath, String imageSaveType)
          throws IOException, IllegalArgumentException;
}
