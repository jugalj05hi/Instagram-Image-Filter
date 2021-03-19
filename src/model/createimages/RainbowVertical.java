package model.createimages;

/**
 * Class to generate the rainbow image with vertical stripes.
 */
public class RainbowVertical extends Rainbow {
  public RainbowVertical(int width, int height) {
    super(width, height);
  }

  @Override
  protected void setThickness(int numberOfStripes) {
    thickness = new int[numberOfStripes];
    int mod = image.getWidth() % numberOfStripes;
    int increment = image.getWidth() / numberOfStripes;
    int prev = 0;
    for (int i = 0; i < numberOfStripes; i++) {
      thickness[i] = prev + increment;
      if (mod > 0) {
        thickness[i]++;
        mod--;
      }
      prev = thickness[i];
    }
  }

  @Override
  protected void setColor() {
    for (int i = image.getHeight() - 1; i >= 0; i--) {
      for (int j = image.getWidth() - 1; j >= 0; j--) {
        if (j <= thickness[0]) {
          image.setRGB(j, i, colors[0].getRGB());
        } else if (j <= thickness[1]) {
          image.setRGB(j, i, colors[1].getRGB());
        } else if (j <= thickness[2]) {
          image.setRGB(j, i, colors[2].getRGB());
        } else if (j <= thickness[3]) {
          image.setRGB(j, i, colors[3].getRGB());
        } else if (j <= thickness[4]) {
          image.setRGB(j, i, colors[4].getRGB());
        } else if (j <= thickness[5]) {
          image.setRGB(j, i, colors[5].getRGB());
        } else {
          image.setRGB(j, i, colors[6].getRGB());
        }
      }
    }
  }
}
