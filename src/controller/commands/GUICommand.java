package controller.commands;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import model.imageloaders.ImageReader;
import model.imageloaders.ImageReaderImpl;
import model.processimages.Filter;
import model.processimages.FilterImpl;
import view.View;
import view.ViewImpl;

public class GUICommand implements ActionListener, Command {

  View view;
  BufferedImage image;
  Filter filter;

  public GUICommand() {
    image = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
  }

  @Override
  public void execute() {


    view = new ViewImpl(600, 500);

    view.setListener(this);

    view.show(0);


  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {


    switch (actionEvent.getActionCommand()) {

      case "generate view":
        view.show(1);
        break;
      case "initial view":
        view.show(0);
        break;
      case "filter view":
        try {
          ImageReader imageReader = new ImageReaderImpl(view.read(4)[0]);
          image = imageReader.getImage();
          filter = new FilterImpl(image);
          view.show(2);
          view.displayImage(image);
        } catch (IOException e) {
          e.printStackTrace();
        }

        break;
      case "load view":
        view.show(4);

        break;
      case "save view":
        view.show(3);

        break;
      case "validate": {
        try {
          GenerateImageGUI generateImage = new GenerateImageGUI(view);

          image = generateImage.execute();
          view.show(3);
        } catch (IllegalArgumentException e) {//do nothing
        }
      }
      break;

      case "save": {

        WriteImageCommand writeImageCommand = new WriteImageCommand(image, view.read(3)[0],
                view.read(3)[1]);

        writeImageCommand.execute();
      }
      break;

      case "load": {
        try {
          ImageReader imageReader = new ImageReaderImpl(view.read(4)[0]);
          image = imageReader.getImage();
          filter = new FilterImpl(image);
          view.displayImage(image);
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
      }

      case "dither": {
        image = filter.dither();
        view.displayImage(image);

        break;
      }

      case "sepia": {
        image = filter.sepia();
        view.displayImage(image);
        break;
      }
      case "blur": {
        image = filter.blur();
        view.displayImage(image);
        break;
      }
      case "sharpen": {
        System.out.println("In sharpen");

        image = filter.sharpen();
        view.displayImage(image);
        break;
      }
      case "mosaic": {

        view.displayErrorMessage("Mosaic might take some time, please be patient");
        image = filter.mosaic(Integer.parseInt(view.read(2)[0]));
        view.displayImage(image);
        break;
      }
      case "greyscale":
        image = filter.greyscale();
        view.displayImage(image);
        break;

      default: //do nothing

    }
  }
}