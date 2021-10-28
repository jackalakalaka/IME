package model;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
    String token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalStateException("Invalid PPM file: plain RAW file should begin with P3");
    }
    this.height = sc.nextInt();
    this.width = sc.nextInt();
    this.maxValue = sc.nextInt();
    this.pixelArray = buildPixelArray(this.height, this.width, this.maxValue, sc);
  }

  /**
   * Builds pixel array from a scanned string.
   * @param h array height
   * @param w array width
   * @param mV max value for color levels
   * @param sc scanner constructed w/ data-containing string
   * @return pixel array corresponding to the modeled image
   */
  private Pixel[][] buildPixelArray(int h, int w, int mV, Scanner sc) {
    Pixel[][] pixels = new Pixel[h][w];
    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        this.pixelArray[i][j] = new Pixel(mV, r, g, b);
      }
    }
    return pixels;
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
  public void saveImageToFile(String fileName) throws IOException {
    Appendable ap = new StringBuilder(); //Initialize the string for file creation

    //Start by adding the correct PPM file format (P3 and dimensions)
    try{
      ap.append("P3\n")
              .append("# Created by IME.\n")
              .append(String.valueOf(this.width))
              .append(" ")
              .append(String.valueOf(this.height))
              .append("\n")
              .append(String.valueOf(this.maxValue))
              .append("\n");
    } catch (IOException e) {
      throw new IllegalArgumentException("Save Image couldn't write to appendable.");
    }

    //Go through the whole array and populate the string with pixel values
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        HashMap<iPixel.Color, Integer> pixelColors = this.getPixelAt(i,j).getColors();
        int red = pixelColors.get(iPixel.Color.Red);
        int green = pixelColors.get(iPixel.Color.Green);
        int blue = pixelColors.get(iPixel.Color.Blue);
        try{
          ap.append(String.valueOf(red)).append("\n")
                  .append(String.valueOf(green)).append("\n")
                  .append(String.valueOf(blue)).append("\n");
        } catch (IOException e) {
          throw new IllegalArgumentException("Save Image couldn't write to appendable.");
        }
      }
    }
    BufferedWriter writer = new BufferedWriter((new FileWriter(fileName+".PPM")));
    String output = ap.toString();
    writer.write(output);
    writer.close();
  }

}
