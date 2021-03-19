package controller.commands;

import java.io.IOException;
import java.util.Scanner;

import model.createimages.CheckerBoard;
import model.createimages.GenerateImage;
import model.imageloaders.ImageWriter;
import model.imageloaders.ImageWriterImpl;

/**
 * The method is implemented from the controller.commands.Command class and is used to generate the
 * required pattern by calling the commands in order when called. The method is to be called by the
 * CommandController object and not directly.
 */

public class CheckerboardGenerateCommand implements Command {

  private Scanner in = new Scanner(System.in);

  /**
   * The method is implemented from the controller.commands.Command class and is used to generate
   * required pattern by calling the commands in order when called. The method is to be called by
   * the controller.commands.CommandController object and not directly.
   */
  @Override
  public void execute() {
    System.out.print("Enter the size of the side: ");
    int side = in.nextInt();
    GenerateImage image = new CheckerBoard(side);
    in.nextLine();
    /*
     * Asks user for the image format.
     */
    System.out.print("\n Enter the image format: ");
    String imageFormatCheckered = in.nextLine();
    // in.close();
    ImageWriter writeImage = new ImageWriterImpl();
    try {
      writeImage.writeImage(image.getImage(), "CheckeredBoard."
              + imageFormatCheckered, imageFormatCheckered);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
