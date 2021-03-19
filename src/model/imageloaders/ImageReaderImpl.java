package model.imageloaders;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * The class ImageReaderImpl implements the interface ImageReader and provides the functionality to
 * read the image where the path is given by the user.
 */

public class ImageReaderImpl implements ImageReader {
  private BufferedImage image;

  /**
   * Creating a constructor that takes in the path/ location of the image as an argument.
   *
   * @param imagePath The path or the location of the image.
   * @throws IOException Throws IllegalArgumentException when the file path is incorrect.
   */
  public ImageReaderImpl(String imagePath) throws IOException {
    try {
      SupportedImageFormats.valueOf(imagePath.substring(imagePath.lastIndexOf('.') + 1));
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid file path");

    }
    image = ImageIO.read(new File(imagePath));

  }


  @Override
  public BufferedImage getImage() {
    return image;
  }

}
