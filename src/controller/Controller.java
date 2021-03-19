package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import controller.commands.CommandController;
import controller.commands.CommandLineArgument;
import controller.commands.GUICommand;

/**
 * This is the controller class which would communicate with the model from the inputs given to it.
 * Command pattern design is used to implement the controller to make it feel less cluttered and
 * make the code look clean. The controller would communicate use the help of other classes like
 * Command class and other GenerateCommand classes to help implement the command design pattern.
 */

public class Controller implements IController {


  private CommandController commandController;

  private String argument;


  @Override
  public void jumpStart() {

    switch (this.argument) {

      case "input.txt": {
        try {
          startCommandLine();
        } catch (FileNotFoundException e) {
          e.printStackTrace();
        }
      }
      case "interactive": {

        startGUI();

      }
    }

  }

  /**
   * The constructor would take in the file path.
   *
   * @param argument the file path.
   * @throws IOException if there is some error.
   */
  public Controller(String argument) throws IOException {
    this.argument = argument;

  }


  /**
   * The method that can be called from main to give control to the controller.
   */
  public void startCommandLine() throws FileNotFoundException {

    try {
      commandController = new CommandController(new CommandLineArgument(argument));
    } catch (IOException e) {
      e.printStackTrace();
    }

    commandController.execute();

  }

  public void startGUI() {
    commandController = new CommandController(new GUICommand());
    commandController.execute();

  }
}


//____________________________________________________________________________Controller__________________________________________

//    parser();
//
//    for (String string : allLines) {
//
//
//      if (string.toLowerCase().contains("load")) { // When load is read as input.
//
//        loadImage();
//
//      } else if (string.toLowerCase().contains("save")) { //When save is read as input.
//
//        saveImage();
//
//
//      } else if (string.toLowerCase().contains("generate")) {
//
//        generateImage(string);
//
//      } else {
//
//
//        filter(string);
//      }
//
//    }
//  }
//
//  // Making the method public to test.
//  private void parser() {
//    try {
//      allLines = Files.readAllLines(Paths.get(argument));  // extracting the text from the file.
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//  }
//
//
//  private void loadImage() {
//    imageLoaded = true;
//    System.out.println("\n \n \n ");
//
//    System.out.print("Enter the image path/ image location: ");
//    imagePath = in.nextLine();
//    try {
//      image = new ImageReaderImpl(imagePath).getImage();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//  }
//
//  private void saveImage() throws FileNotFoundException {
//
//    if (!imageLoaded) {
//      throw new FileNotFoundException("Image is not loaded, please load the image "
//              + "first with load command in the input file");
//    }
//    SaveImageCommand saveImage = new SaveImageCommand(finalName, imagePath, image);
//    commandController = new CommandController(saveImage);
//    commandController.execute();
//  }
//
//  private void generateImage(String string) {
//    System.out.println(string.split(" ")[1]);
//    //The variables to be used.
//    String generate = string.split(" ")[1];
//
//    GenerateImageCommand generateImageCommand = new GenerateImageCommand(generate);
//    commandController = new CommandController(generateImageCommand);
//    commandController.execute();
//  }
//
//  private void filter(String string) throws FileNotFoundException {
//
//    if (!imageLoaded) {
//      throw new FileNotFoundException("Image is not loaded, please load the image first "
//              + "with load command in the input file");
//    }
//
//    Filter filter = new FilterImpl(image);
//
//    switch (string.toLowerCase()) {
//      case "blur":
//        System.out.println(string);
//        image = filter.blur();
//        finalName += "-blur";
//        break;
//
//      case "sharpen":
//        System.out.println(string);
//        image = filter.sharpen();
//        finalName += "-sharpen";
//        break;
//
//      case "sepia":
//        System.out.println(string);
//        image = filter.sepia();
//        finalName += "-sepia";
//        break;
//
//      case "greyscale":
//        System.out.println(string);
//        image = filter.greyscale();
//        finalName += "-greyscale";
//        break;
//      case "dither":
//        System.out.println(string);
//        image = filter.dither();
//        finalName += "-dither";
//        break;
//      case "mosaic":
//        System.out.println(string);
//        System.out.println("Enter the number of seeds: ");
//        int seeds = in.nextInt();
//        System.out.println("The processing of mosaic may take time depending on your pc"
//                + ", please be patient. The higher the seeds, more time it'd take.");
//        image = filter.mosaic(seeds);
//        finalName += "-mosaic";
//        break;
//
//      default:
//        System.out.println("No such filter exists");
//        break;
//
//    }
//
//  }
//
//  /**
//   * Method created for testing purpose, has no effect on code if removed.
//   */
//
//  public List<String> getArgument() {
//    parser();
//    return allLines;
//
//  }
