package controller.commands;

import java.io.IOException;
import java.util.Scanner;

import model.createimages.FlagOfSwitzerland;
import model.createimages.GenerateImage;
import model.imageloaders.ImageWriter;
import model.imageloaders.ImageWriterImpl;

/**
 * The class controller.commands.SwitzerlandFlagGenerate is used to generate the Swiss flag.
 * The execute() method contains the logic behind the creation of the flag i.e the commands to be
 * executed in sequence to generate the flag. The class implements controller.commands.Command class
 * When called from the controller it executes the command in order to achieve the task required.
 */
public class SwitzerlandFlagGenerate implements Command {


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

    System.out.print("Enter the size of the side: ");
    int sSide = in.nextInt();

    /*
     * Asks user for the image format.
     */
    in.nextLine();
    System.out.print("\n Enter the image format: ");
    String imageFormatSwiss = in.nextLine();

    GenerateImage image = new FlagOfSwitzerland(sSide);
    ImageWriter writeImage = new ImageWriterImpl();
    try {
      writeImage.writeImage(image.getImage(), "SwissFlag."
              + imageFormatSwiss, imageFormatSwiss);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
