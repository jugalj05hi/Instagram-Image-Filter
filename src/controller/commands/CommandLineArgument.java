package controller.commands;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import model.imageloaders.ImageReaderImpl;
import model.processimages.Filter;
import model.processimages.FilterImpl;

public class CommandLineArgument implements Command {

  private String finalName = "";
  private String imagePath = null;
  private BufferedImage image = null;
  private CommandController commandController;
  private Scanner in = new Scanner(System.in);
  private String argument;
  private Boolean imageLoaded = false;
  private List<String> allLines = null;

  /**
   * The constructor would take in the file path.
   *
   * @param argument the file path.
   * @throws IOException if there is some error.
   */
  public CommandLineArgument(String argument) throws IOException {
    this.argument = argument;

  }


  @Override
  public void execute() {

    parser();

    for (String string : allLines) {


      if (string.toLowerCase().contains("load")) { // When load is read as input.

        loadImage();

      } else if (string.toLowerCase().contains("save")) { //When save is read as input.

        try {
          saveImage();
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }


      } else if (string.toLowerCase().contains("generate")) {

        generateImage(string);

      } else {


        try {
          filter(string);
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
      }

    }

  }

  private void parser() {
    try {
      allLines = Files.readAllLines(Paths.get(argument));  // extracting the text from the file.
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  private void loadImage() {
    imageLoaded = true;
    System.out.println("\n \n \n ");

    System.out.print("Enter the image path/ image location: ");
    imagePath = in.nextLine();
    try {
      image = new ImageReaderImpl(imagePath).getImage();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void saveImage() throws FileNotFoundException {

    if (!imageLoaded) {
      throw new FileNotFoundException("Image is not loaded, please load the image "
              + "first with load command in the input file");
    }
    SaveImageCommand saveImage = new SaveImageCommand(finalName, imagePath, image);
    commandController = new CommandController(saveImage);
    commandController.execute();
  }

  private void generateImage(String string) {
    System.out.println(string.split(" ")[1]);
    //The variables to be used.
    String generate = string.split(" ")[1];

    GenerateImageCommand generateImageCommand = new GenerateImageCommand(generate);
    commandController = new CommandController(generateImageCommand);
    commandController.execute();
  }

  private void filter(String string) throws FileNotFoundException {

    if (!imageLoaded) {
      throw new FileNotFoundException("Image is not loaded, please load the image first "
              + "with load command in the input file");
    }

    Filter filter = new FilterImpl(image);

    switch (string.toLowerCase()) {
      case "blur":
        System.out.println(string);
        image = filter.blur();
        finalName += "-blur";
        break;

      case "sharpen":
        System.out.println(string);
        image = filter.sharpen();
        finalName += "-sharpen";
        break;

      case "sepia":
        System.out.println(string);
        image = filter.sepia();
        finalName += "-sepia";
        break;

      case "greyscale":
        System.out.println(string);
        image = filter.greyscale();
        finalName += "-greyscale";
        break;
      case "dither":
        System.out.println(string);
        image = filter.dither();
        finalName += "-dither";
        break;
      case "mosaic":
        System.out.println(string);
        System.out.println("Enter the number of seeds: ");
        int seeds = in.nextInt();
        System.out.println("The processing of mosaic may take time depending on your pc"
                + ", please be patient. The higher the seeds, more time it'd take.");
        image = filter.mosaic(seeds);
        finalName += "-mosaic";
        break;

      default:
        System.out.println("No such filter exists");
        break;

    }

  }

  /**
   * Method created for testing purpose, has no effect on code if removed.
   */

  public List<String> getArgument() {
    parser();
    return allLines;

  }

}
