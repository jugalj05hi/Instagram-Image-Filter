package controller.commands;

import java.awt.image.BufferedImage;

/**
 * The class SaveImageCommand does the job of writing the filtered image to the file systems by
 * calling another class' execute() method i.e WriteImageCommand class' execute() method.
 * It takes in arguments from the controller. The class also implements Command interface.
 * When called from the controller it executes the command in order to achieve the task required.
 */
public class SaveImageCommand implements Command {
  private String finalName;
  private BufferedImage image;
  private String imagePath;

  /**
   * The constructor takes in three arguments in order to write the image to the file system. They
   * are as follows.
   *
   * @param finalName the name of the file.
   * @param imagePath location/ path of the image.
   * @param image     the image to be written.
   */
  public SaveImageCommand(String finalName, String imagePath, BufferedImage image) {
    this.finalName = finalName;
    this.image = image;
    this.imagePath = imagePath;
  }

  @Override
  public void execute() {
    String pathManipulation = imagePath.substring(imagePath.lastIndexOf('\\')
            + 1, imagePath.lastIndexOf('.'));

    finalName = pathManipulation + finalName + "."
            + imagePath.substring(imagePath.lastIndexOf('.') + 1);
    String extension = imagePath.substring(imagePath.lastIndexOf('.') + 1);

    //Using the WriteImageCommand and  commandController to execute the commands.

    WriteImageCommand writeImageCommand = new WriteImageCommand(image, finalName, extension);
    CommandController commandController = new CommandController(writeImageCommand);
    commandController.execute();
  }
}
