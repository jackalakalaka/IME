package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class ImageModelImpl implements ImageModel {
  private Pixel[][] pixelArray;
  private int width;
  private int height;

  public ImageModelImpl(String filePath) throws FileNotFoundException {
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

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        this.pixelArray[i][j] = new Pixel(maxValue, r, g, b);
      }
    }
  }

  private ImageModelImpl(int height, int width) {
    this.pixelArray = new Pixel[height][width];
  }

  private ImageModelImpl(Pixel[][] pixelArray){
    Objects.requireNonNull(pixelArray);
    this.pixelArray = pixelArray;
    this.height = pixelArray.length;
    this.width = pixelArray[0].length;
  }

  @Override
  public Pixel getPixelAt(int row, int col) {
    return pixelArray[row][col];
  }

  @Override
  public <T> ImageModel convertToViz(T Viz) {
    return null;
  }

  @Override
  public ImageModel changeBrightness(int change) {
    Pixel[][] brighterModel = new Pixel[this.height][this.width];
    for (int row = 0; row < height; row++) {
      for (int column = 0; column < width; column++) {
        Pixel oldPixel = this.getPixelAt(row, column);
        HashMap<iPixel.Color, Integer> oldPixelColors = oldPixel.getColors();
        int red = oldPixelColors.get(iPixel.Color.Red);
        int green = oldPixelColors.get(iPixel.Color.Green);
        int blue = oldPixelColors.get(iPixel.Color.Blue);
        brighterModel[row][column] = new Pixel(255,
                checkBrightness(red + change), checkBrightness(green + change),
                checkBrightness(blue + change));
      }
    }
    return new ImageModelImpl(brighterModel);
  }

  /**
   * Helper for {@link #changeBrightness(int)}.
   *
   * @param colorValue The desired color value after brightening or dimming.
   * @return The acceptable value or max/min.
   */
  private int checkBrightness(int colorValue) {
    if (colorValue < 0) {
      return 0;
    }
    return Math.min(colorValue, 255);
  }

  /**
   * Populates this current board with another boards contents.
   * Assumes the boards are the same size.
   *
   * @param other ImageModel that this board is copying.
   */
  private void populateModel(ImageModel other) {
    for (int row = 0; row < height; row++) {
      for (int column = 0; column < width; ) {
        this.pixelArray[row][column] = other.getPixelAt(row, column);
      }
    }
  }

  @Override
  public void saveImageToFile(String filePath) {

  }

}
