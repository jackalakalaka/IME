package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

import model.FuncObjs.AConvertFromAbsolute;

/**
 * Representation of an image from a PPM file.
 */
public class ImageModelImpl implements ImageModel {
  private final Pixel[][] pixelArray;
  private final int width;
  private final int height;
  private final int maxValue;

  /**
   * 1-arg constructor for an image model using a PPM file.
   *
   * @param filePath The file path to the PPM file.
   * @throws FileNotFoundException If the file cannot be found.
   */
  ImageModelImpl(String filePath) throws FileNotFoundException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filePath));
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("File path leads to no file or was mistyped.");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalStateException("Invalid PPM file: plain RAW file should begin with P3");
    }
    this.width = sc.nextInt();
    this.height = sc.nextInt();
    this.pixelArray = new Pixel[height][width];

    int maxValue = sc.nextInt();
    this.maxValue = maxValue;

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        this.pixelArray[i][j] = new Pixel(maxValue, r, g, b);
      }
    }
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  /**
   * @return the maximum value of this image's pixels, defining white / the color scale.
   */
  @Override
  public int getMaxValue() {
    return this.maxValue;
  }

  /**
   * 2-arg constructor for making a new ImageModelImpl.
   *
   * @param pixelArray An array of pixels for the new model.
   * @param maxValue   The maximum value carried over from the original model.
   */
  public ImageModelImpl(Pixel[][] pixelArray, int maxValue) {
    Objects.requireNonNull(pixelArray);
    this.pixelArray = pixelArray;
    this.height = pixelArray.length;
    this.width = pixelArray[0].length;
    this.maxValue = maxValue;
  }

  @Override
  public ImageModel changeBrightness(int change) {
    Pixel[][] brighterModel = new Pixel[this.height][this.width];
    for (int row = 0; row < height; row++) {
      for (int column = 0; column < width; column++) {
        Pixel oldPixel = this.getPixelAt(row, column);
        brighterModel[row][column] = changePixelBrightness(oldPixel, change);
      }
    }
    return new ImageModelImpl(brighterModel, this.maxValue);
  }

  /**
   * Helper for {@link #changeBrightness(int)} to make brighter pixels.
   *
   * @param oldPixel The original pixel.
   * @param change   The brightness change to be implemented.
   * @return A new brighter or darker pixel.
   */
  private Pixel changePixelBrightness(Pixel oldPixel, int change) {
    HashMap<iPixel.Color, Integer> oldPixelColors = oldPixel.getColors();
    int red = oldPixelColors.get(iPixel.Color.Red);
    int green = oldPixelColors.get(iPixel.Color.Green);
    int blue = oldPixelColors.get(iPixel.Color.Blue);
    return new Pixel(this.maxValue,
            checkBrightness(red + change), checkBrightness(green + change),
            checkBrightness(blue + change));
  }

  /**
   * Helper for {@link #changePixelBrightness(Pixel, int)}.
   *
   * @param colorValue The desired color value after brightening or dimming.
   * @return The acceptable value or max/min.
   */
  private int checkBrightness(int colorValue) {
    if (colorValue < 0) {
      return 0;
    }
    return Math.min(colorValue, this.maxValue);
  }

  @Override
  public Pixel getPixelAt(int row, int col) {
    return pixelArray[row][col];
  }

  @Override
  public ImageModel convertToViz(AConvertFromAbsolute cmd) {
    return cmd.apply(this);
  }

  @Override
  public void saveImageToFile(String filePath) {

  }

}
