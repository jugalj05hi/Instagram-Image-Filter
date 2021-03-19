package model.processimages;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * class to implement all the filter methods.
 */
public class FilterImpl extends AbstractFilter {
  private int[][] category;
  private int[][] centers;
  private double e;
  private int seeds;

  /**
   * sets of the image to be filtered.
   *
   * @param image then image to be filtered.
   */
  public FilterImpl(BufferedImage image) {
    super(image);
  }

  /**
   * This method uses the given image and blurs it.
   *
   * @return returns the blurred image.
   */
  @Override
  public BufferedImage blur() {
    float[] b = {0.0625f, 0.125f, 0.0625f,
                 0.125f, 0.25f, 0.125f,
                 0.0625f, 0.125f, 0.0625f};
    return modifyPixelDensity(b, 3);
  }

  /**
   * This method uses the given image and sharpens it.
   *
   * @return returns the sharpened image.
   */
  @Override
  public BufferedImage sharpen() {
    float[] b = {-0.125f, -0.125f, -0.125f, -0.125f, -0.125f,
                 -0.125f, 0.25f, 0.25f, 0.25f, -0.125f,
                 -0.125f, 0.25f, 1f, 0.25f, -0.125f,
                 -0.125f, 0.25f, 0.25f, 0.25f, -0.125f,
                 -0.125f, -0.125f, -0.125f, -0.125f, -0.125f};
    return modifyPixelDensity(b, 5);
  }

  /**
   * This method uses the given image and turns it into a greyscale image.
   *
   * @return returns the greyscale image.
   */
  @Override
  public BufferedImage greyscale() {

    for (int i = image.getHeight() - 1; i >= 0; i--) {
      for (int j = image.getWidth() - 1; j >= 0; j--) {
        float r = new Color(image.getRGB(j, i)).getRed() * 0.2126f;
        float g = new Color(image.getRGB(j, i)).getGreen() * 0.7152f;
        float b = new Color(image.getRGB(j, i)).getBlue() * 0.0722f;
        int greyscale = clamp((int) (r + g + b));
        Color grey = new Color(greyscale, greyscale, greyscale);
        try {
          image.setRGB(j, i, grey.getRGB());

        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    return image;
  }


  /**
   * This method uses the given image and turns it into a sepia image.
   *
   * @return returns the sepia image.
   */
  @Override
  public BufferedImage sepia() {
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        float r = new Color(image.getRGB(j, i)).getRed();
        float g = new Color(image.getRGB(j, i)).getGreen();
        float b = new Color(image.getRGB(j, i)).getBlue();
        int rd = clamp((int) ((r * 0.393) + (g * 0.769) + (b * 0.189)));
        int gd = clamp((int) ((r * 0.349) + (g * 0.686) + (b * 0.168)));
        int bd = clamp((int) ((r * 0.272) + (g * 0.534) + (b * 0.131)));
        Color sepia = new Color(rd, gd, bd);
        image.setRGB(j, i, sepia.getRGB());
      }
    }
    return image;
  }

  /**
   * This method uses the given image and turns it into a dithered image.
   *
   * @return returns the dither image.
   */
  @Override
  public BufferedImage dither() {
    greyscale();
    int height = image.getHeight();
    int width = image.getWidth();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        float r = new Color(image.getRGB(j, i)).getRed();
        int newColor = r > 127.5 ? 255 : 0;
        float error = r - newColor;
        image.setRGB(j, i, new Color(newColor, newColor, newColor).getRGB());
        if (i + 1 < height) {
          Color pixel = new Color(image.getRGB(j, i + 1));
          int r1 = clamp((int) (pixel.getRed() + (7 * error / 16)));
          image.setRGB(j, i + 1, new Color(r1, r1, r1).getRGB());
        }
        if (i - 1 > 0 && j + 1 < width) {
          Color pixel = new Color(image.getRGB(j + 1, i - 1));
          int r1 = clamp((int) (pixel.getRed() + (3 * error / 16)));
          image.setRGB(j + 1, i - 1, new Color(r1, r1, r1).getRGB());
        }
        if (j + 1 < width) {
          Color pixel = new Color(image.getRGB(j + 1, i));
          int r1 = clamp((int) (pixel.getRed() + (5 * error / 16)));
          image.setRGB(j + 1, i, new Color(r1, r1, r1).getRGB());
        }
        if (j + 1 < width && i + 1 < height) {
          Color pixel = new Color(image.getRGB(j + 1, i + 1));
          int r1 = clamp((int) (pixel.getRed() + (1 * error / 16)));
          image.setRGB(j + 1, i + 1, new Color(r1, r1, r1).getRGB());
        }

      }
    }
    return image;
  }

  /**
   * This method uses the given image and turns it into a mosaic image.
   *
   * @param seeds the number of seeds the mosaic must have.
   * @return returns the dither image.
   */
  @Override
  public BufferedImage mosaic(int seeds) {
    if (seeds < 1) {
      throw new IllegalArgumentException("Number of clusters cannot be less than 1");
    }
    this.seeds = seeds;
    e = Double.POSITIVE_INFINITY;
    assignKCentres();

    return image;
  }

  private void assignKCentres() throws IllegalArgumentException {
    int height = image.getHeight();
    int width = image.getWidth();
    centers = new int[seeds][2];
    if (seeds > width * height) {
      throw new IllegalArgumentException("Number of seeds cannot be greater than number of "
              + "pixels");
    }
    Random random = new Random();
    for (int i = 0; i < seeds; i++) {
      centers[i][0] = random.nextInt(height);
      centers[i][1] = random.nextInt(width);
    }
    categorizePixels();
  }

  private void categorizePixels() {
    int height = image.getHeight();
    int width = image.getWidth();
    category = new int[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        double minDist = calculateDistance(i, centers[0][0], j, centers[0][1]);
        int categoryIndex = 0;
        for (int k = 1; k < seeds; k++) {
          double currDist = calculateDistance(i, centers[k][0], j, centers[k][1]);
          if (minDist > currDist) {
            minDist = currDist;
            categoryIndex = k;
          }
        }
        category[i][j] = categoryIndex;
      }
    }
    calculateNewError();
  }

  private double calculateDistance(int x1, int x2, int y1, int y2) {
    return Math.sqrt(Math.pow(x1 - x2, 2) + Math.sqrt(Math.pow(y1 - y2, 2)));
  }

  private void calculateNewError() {
    double distance = 0;
    int height = image.getHeight();
    int width = image.getWidth();
    for (int i = 0; i < seeds; i++) {
      int ctr = 0;
      double x = 0;
      double y = 0;
      for (int j = 0; j < height; j++) {
        for (int k = 0; k < width; k++) {
          if (category[j][k] == i) {
            ctr++;
            x += i;
            y += j;
            distance += calculateDistance(i, centers[i][0], j, centers[i][1]);
          }

        }
      }
      ctr = ctr == 0 ? 1 : ctr;
      centers[i][0] = (int) x / ctr;
      centers[i][1] = (int) y / ctr;
    }

    double ne = distance / seeds;
    double percentageError = (Math.abs(ne - e)) / e;
    e = ne;
    if (percentageError < 0.000001) {
      categorizePixels();
    } else {
      calculateAverageColor();
    }
  }

  private void calculateAverageColor() {
    int height = image.getHeight();
    int width = image.getWidth();
    Color[] avgColor = new Color[seeds];
    for (int i = 0; i < seeds; i++) {
      int r = 0;
      int g = 0;
      int b = 0;
      int ctr = 0;
      for (int j = 0; j < height; j++) {
        for (int k = 0; k < width; k++) {
          if (category[j][k] == i) {
            Color pixel = new Color(image.getRGB(k, j));
            r += pixel.getRed();
            g += pixel.getGreen();
            b += pixel.getBlue();
            ctr++;
          }
        }
      }
      ctr = ctr == 0 ? 1 : ctr;
      r = clamp(r / ctr);
      g = clamp(g / ctr);
      b = clamp(b / ctr);
      avgColor[i] = new Color(r, g, b);
    }
    for (int i = 0; i < seeds; i++) {
      for (int j = 0; j < height; j++) {
        for (int k = 0; k < width; k++) {
          if (category[j][k] == i) {
            image.setRGB(k, j, avgColor[i].getRGB());
          }
        }
      }
    }
  }


}
