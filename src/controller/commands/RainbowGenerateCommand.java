package controller.commands;

import java.io.IOException;
import java.util.Scanner;

import model.createimages.GenerateImage;
import model.createimages.Rainbow;
import model.createimages.RainbowVertical;
import model.imageloaders.ImageWriter;
import model.imageloaders.ImageWriterImpl;

/**
 * The class controller.commands.RainbowGenerateCommand is used to generate the france flag without.
 * The execute() method contains the logic behind the creation of the flag i.e the commands to be
 * executed in sequence to generate the flag. The class implements controller.commands.Command
 * class. When called from the controller it executes the command in order to achieve
 * the task required.
 */
public class RainbowGenerateCommand implements Command {

  private Scanner in = new Scanner(System.in);

  /**
   * The method is implemented from the controller.commands.Command class and is used to generate
   * required flag by calling the commands in order when called. The method is to be called by the
   * controller.commands.CommandController object and not directly.
   */

  @Override
  public void execute() {

    /*
     * Asks user about the dimensions of the image/flag.
     */
    System.out.print("\nPlease enter the height of the flag: ");
    int height = in.nextInt();

    System.out.print("\nPlease enter the width of the flag: ");
    int width = in.nextInt();
    /*
     * Asks user if the flag is to be generated in vertical or horizontal orientation.
     */
    System.out.println("\nWhat would be the orientation of the flag?");
    System.out.println("1. Vertical");
    System.out.println("2. Horizontal");
    System.out.print("\nEnter your choice: ");


    int rainbowOrientation = in.nextInt();
    switch (rainbowOrientation) {
      case 1:
        in.nextLine();
        /*
         * Asks user for the image format.
         */
        System.out.print("\n Enter the image format: ");
        String imageFormat = in.nextLine();
        GenerateImage image = new RainbowVertical(width, height);
        ImageWriter writeImage = new ImageWriterImpl();
        try {
          writeImage.writeImage(image.getImage(), "RainbowVertical."
                  + imageFormat, imageFormat);
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
      case 2:

        in.nextLine();
        System.out.print("\n Enter the image format: ");
        String imageFormatHorizontal = in.nextLine();
        image = new Rainbow(width, height);
        writeImage = new ImageWriterImpl();
        try {
          writeImage.writeImage(image.getImage(), "RainbowHorizontal."
                  + imageFormatHorizontal, imageFormatHorizontal);
        } catch (IOException e) {
          e.printStackTrace();
        }

        break;
      default:
        System.out.println("Incorrect input");//no action needed.

    }
  }
}
