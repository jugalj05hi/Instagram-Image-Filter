package controller.commands;

import java.awt.image.BufferedImage;

import model.createimages.CheckerBoard;
import model.createimages.FlagOfFrance;
import model.createimages.FlagOfGreece;
import model.createimages.FlagOfSwitzerland;
import model.createimages.Rainbow;
import model.createimages.RainbowVertical;
import view.View;

public class GenerateImageGUI {

  View view;
  BufferedImage image = null;
  String generateType;
  int height;
  int width;

  public GenerateImageGUI(View view) {

    this.view = view;
  }


  public BufferedImage execute() throws IllegalArgumentException {
    try {
      generateType = view.read(1)[0];
      height = Integer.parseInt(view.read(1)[1]);
      if (view.read(1)[2].equals("")) {
        width = 1;
      } else {
        width = Integer.parseInt(view.read(1)[2]);
      }
    } catch (Exception e) {

      view.displayErrorMessage(" Please Input numbers");
    }


    if (generateType.contains("Switzerland")) {

      if (height < 0 || width < 0) {
        view.displayErrorMessage("please add positive values");
        throw new IllegalArgumentException("incorrect dimensions");
      } else {

        image = new FlagOfSwitzerland(height).getImage();
      }


    } else if (generateType.contains("France")) {

      if ((height * 1.5) != width || width < 1 || height < 1) {

        view.displayErrorMessage("The dimensions of flag of france should be positive and height" +
                " * 1.5 = width");
        throw new IllegalArgumentException("incorrect dimensions");
      }

      image = new FlagOfFrance(width, height).getImage();


    } else if (generateType.contains("Greece")) {

      if ((height * 1.5) != width || height < 1 || width < 1 || height % 9 != 0) {
        view.displayErrorMessage("The dimensions of flag of france should be positive and height" +
                " * 1.5 = width and height should be divisible by 9. try 900 and 1350");
        throw new IllegalArgumentException("incorrect dimensions");
      }


      image = new FlagOfGreece(width, height).getImage();
    } else if (generateType.contains("CheckerBoard")) {

      if (height < 0 || width < 0) {
        view.displayErrorMessage("Incorrect dimensions");
        throw new IllegalArgumentException("incorrect dimensions");
      }

      image = new CheckerBoard(height).getImage();

    } else if (generateType.contains("Horizontal Rainbow")) {


      if (height < 0 || width < 0) {
        view.displayErrorMessage("please add positive values");
        throw new IllegalArgumentException("incorrect dimensions");
      }

      image = new Rainbow(width, height).getImage();

    } else if (generateType.contains("Vertical")) {


      if (height < 0 || width < 0) {
        view.displayErrorMessage("please add positive values");
        throw new IllegalArgumentException("incorrect dimensions");
      }

      image = new RainbowVertical(width, height).getImage();
    }

    return image;

  }
}
