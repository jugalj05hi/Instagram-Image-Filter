package controller.commands;

import java.awt.image.BufferedImage;
import java.io.IOException;

import model.imageloaders.ImageWriter;
import model.imageloaders.ImageWriterImpl;

/**
 * The controller.commands.WriteImageCommand is used to write images to the file system.
 * It implements the controller.commands.Command interface, so all the commands that are
 * to be called to write the images that are placed in the execute() method in sequential order
 * for the it to run successfully.When called from the controller it executes the command
 * in order to achieve the task required.
 */

public class WriteImageCommand implements Command {

  private BufferedImage image;
  private String fileName;
  private String extension;
  private ImageWriter writeImage;

  /**
   * constructor takes in three arguments to help write the image.
   *
   * @param image     the image to be written.
   * @param fileName  the name of the file.
   * @param extension the file type/ extension.
   */
  public WriteImageCommand(BufferedImage image, String fileName, String extension) {

    this.image = image;
    this.fileName = fileName;
    this.extension = extension;
    writeImage = new ImageWriterImpl();
  }

  /**
   * The method is implemented from the controller.commands.Command class and is used to generate
   * required image by calling the commands in order when called. The method is to be called by the
   * controller.commands.CommandController object and not directly.
   */
  @Override
  public void execute() {
    try {
      writeImage.writeImage(image, fileName, extension);
    } catch (IOException e) {
      e.printStackTrace();
    }


  }
}