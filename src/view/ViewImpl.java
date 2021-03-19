package view;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ViewImpl implements View {

  private List<DisplayType> displays;
  private int currDisplay;

  public ViewImpl(int width, int height) {
    this.displays = new ArrayList<>();
    displays.add(new InitialView(width, height));
    displays.add(new GenerateImageView(width, height));
    displays.add(new FilterImage(width, height));
    displays.add(new SaveImage(width, height));
    displays.add(new LoadImage(width, height));
    currDisplay = 0;
    //display();
  }


  @Override
  public void displayImage(BufferedImage image) {
    if (!displays.get(currDisplay).canDisplayImage()) {
      throw new IllegalArgumentException("Muggle");
    }
    displays.get(currDisplay).replaceImage(image);
  }

  @Override
  public void show(int n) {
    if (n > 4 || n < 0) {
      throw new IllegalArgumentException("incorrect display number");
    }
    switch (n) {
      case 0:
        currDisplay = n;
        displays.get(1).close();
        displays.get(2).close();
        displays.get(3).close();
        displays.get(4).close();
        displays.get(0).shows();
        break;
      case 1:
        currDisplay = n;
        displays.get(0).close();
        displays.get(2).close();
        displays.get(3).close();
        displays.get(4).close();
        displays.get(1).shows();
        break;
      case 2:
        currDisplay = n;
        displays.get(0).close();
        displays.get(1).close();
        displays.get(3).close();
        displays.get(4).close();
        displays.get(2).shows();
        break;
      case 3:
        displays.get(3).shows();
        break;
      case 4:
        displays.get(4).shows();
        break;
      default://do nothing
    }
  }

  @Override
  public String[] read(int n) {
    return displays.get(n).read();
  }


  @Override
  public void displayErrorMessage(String message) {
    displays.get(currDisplay).displayErrorMessage(message);
  }

  /**
   * Set the listener for any actions.
   *
   * @param listener
   */
  @Override
  public void setListener(ActionListener listener) {
    for (int i = 0; i < displays.size(); i++) {
      displays.get(i).setListener(listener);
    }

  }


}
