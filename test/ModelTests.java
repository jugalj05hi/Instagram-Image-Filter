
import org.junit.Test;
import org.junit.Assert;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import View.View;
import View.ViewImpl;
import View.InitialView;
import controller.Controller;
import model.createimages.CheckerBoard;
import model.createimages.FlagOfFrance;
import model.createimages.FlagOfGreece;
import model.createimages.FlagOfSwitzerland;
import model.createimages.GenerateImage;
import model.createimages.Rainbow;
import model.createimages.RainbowVertical;
import model.imageloaders.ImageReader;
import model.imageloaders.ImageReaderImpl;
import model.imageloaders.ImageWriter;
import model.imageloaders.ImageWriterImpl;
import model.processimages.Filter;
import model.processimages.FilterImpl;

/**
 * Testing class for the model and controller.
 */
public class ModelTests {
  private GenerateImage generateImage;

  @Test(expected = IllegalArgumentException.class)
  public void testIncorrectInputRainbow() {
    generateImage = new Rainbow(-1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIncorrectInputRainbowVertical() {
    generateImage = new RainbowVertical(-1, 0);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testIncorrectInputCheckerBoard() {
    generateImage = new CheckerBoard(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIncorrectInputSwissFlag() {
    generateImage = new FlagOfSwitzerland(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIncorrectInputGreekFlag() {
    generateImage = new FlagOfGreece(-1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIncorrectInputFrenchFlag() {
    generateImage = new FlagOfFrance(-1, 0);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testIncorrectInputReader() throws IOException {
    ImageReader imageReader = new ImageReaderImpl("C:\\Users\\urvak\\IdeaProjects\\PDP"
            + "\\PDP-Group-Assignment-Fall-2019\\Assignment7\\res\\unsupported format testing "
            + "image.tiff");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIncorrectWrite() throws IOException {
    ImageWriter imageWriter = new ImageWriterImpl();
    imageWriter.writeImage(new BufferedImage(10, 10, 1), "tiff",
            "xyz.tiff");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMosaic() throws IOException {
    Filter image = new FilterImpl(ImageIO.read(new File("C:\\Users\\urvak\\IdeaProjects"
            + "\\PDP\\PDP-Group-Assignment-Fall-2019\\Assignment7\\res\\NYC - Original.jpg")));
    ImageWriter imageWriter = new ImageWriterImpl();
    imageWriter.writeImage(image.mosaic(-1), "mosaic.jpg",
            "jpg");
  }

  @Test
  public void testFileInput() throws IOException {
    Controller controller = new Controller("D:\\Assignment-8\\input.txt");

    Assert.assertEquals("[load, sepia, blur, blur, blur, blur, save]",
            controller.getArgument().toString());

  }

  @Test
  public void testView() throws InterruptedException {
    View view = new ViewImpl(600,600);
    view.show(new InitialView());
    System.out.println("");
    Scanner in = new Scanner(System.in);
    in.nextLine();
  }


}