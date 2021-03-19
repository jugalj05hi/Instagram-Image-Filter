package model.imageloaders;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * The class ImageWriterImpl implements the interface ImageWriter and provides the functionality to
 * write images at user defined path and user defined  image extensions.
 */
public class ImageWriterImpl implements ImageWriter {
  @Override
  public void writeImage(BufferedImage image, String writePath, String imageSaveType)
          throws IOException, IllegalArgumentException {
    try {
      SupportedImageFormats.valueOf(imageSaveType);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Unsupported format");
    }

    ImageIO.write(image, imageSaveType, new File(writePath));

  }
}
