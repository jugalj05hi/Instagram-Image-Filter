package controller.commands;

import java.io.IOException;
import java.util.Scanner;

import model.createimages.FlagOfFrance;
import model.createimages.GenerateImage;
import model.imageloaders.ImageWriter;
import model.imageloaders.ImageWriterImpl;

/**
 * The class controller.commands.FranceFlagGenerateCommand is used to generate the france flag
 * without. The execute() method contains the logic behind the creation of the flag i.e the
 * commands to be executed in sequence to generate the flag. The class implements
 * controller.commands.Command class. When called from the controller it
 * executes the command in order to achieve the task required.
 */
public class FranceFlagGenerateCommand implements Command {

  private Scanner in = new Scanner(System.in);

  /**
   * The method is implemented from the controller.commands.Command class and is used to generate
   * the required flag by calling the commands in order when called. The method is to be called by
   *  the controller.commands.CommandController object and not directly.
   */
  @Override
  public void execute() {
    /*
     * Asks user about the dimensions of the image/flag.
     */

    System.out.print("\nPlease enter the height of the flag: ");
    int fHeight = in.nextInt();

    System.out.print("\nPlease enter the width of the flag: ");
    int fWidth = in.nextInt();

    /*
     * Asks user for the image format.
     */
    in.nextLine();
    System.out.print("\n Enter the image format: ");
    String imageFormatFrance = in.nextLine();

    GenerateImage image = new FlagOfFrance(fWidth, fHeight);
    ImageWriter writeImage = new ImageWriterImpl();
    try {
      writeImage.writeImage(image.getImage(), "FranceFlag."
              + imageFormatFrance, imageFormatFrance);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
