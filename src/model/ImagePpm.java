package model;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

import model.funcobjs.ICommands;


/**
 * Representation of an image from a PPM file.
 */
public class ImagePpm extends AbstractImage {

  /**
   * 1-arg constructor for an image model using a PPM file.
   *
   * @param filePath The file path to the PPM file.
   * @throws IllegalArgumentException If the file cannot be found.
   */
  public ImagePpm(String filePath) throws IllegalArgumentException {
    super(filePath);
  }

  @Override
  protected void getImageFromFile(String filePath) {
    Objects.requireNonNull(filePath);
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filePath));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File path leads to no file or was mistyped.");
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

  /**
   * 2-arg constructor for making a new ImageModelImpl.
   *
   * @param maxValue   The maximum value carried over from the original model.
   * @param pixelArray An array of pixels for the new model.
   */
  public ImagePpm(int maxValue, IPixel[][] pixelArray) {
    super(maxValue,pixelArray);
  }

  @Override
  public void saveImageToFile(String filePath) throws IllegalStateException {
    Objects.requireNonNull(filePath);
    Appendable ap = new StringBuilder(); //Initialize the string for file creation

    //Start by adding the correct PPM file format (P3 and dimensions)
    try {
      ap.append("P3\n")
              .append("# Created by IME.\n")
              .append(String.valueOf(this.width))
              .append(" ")
              .append(String.valueOf(this.height))
              .append("\n")
              .append(String.valueOf(this.maxValue))
              .append("\n");
    } catch (IOException e) {
      throw new IllegalStateException("Save Image couldn't write to appendable.");
    }

    //Go through the whole array and populate the string with pixel values
    for (int i = 0; i < this.height; i++) {
      for (int j = 0; j < this.width; j++) {
        HashMap<Pixel.Color, Integer> pixelColors = this.getPixelAt(i, j).getColors();
        int red = pixelColors.get(Pixel.Color.Red);
        int green = pixelColors.get(Pixel.Color.Green);
        int blue = pixelColors.get(Pixel.Color.Blue);
        try {
          ap.append(String.valueOf(red)).append("\n")
                  .append(String.valueOf(green)).append("\n")
                  .append(String.valueOf(blue)).append("\n");
        } catch (IOException e) {
          throw new IllegalStateException("Save Image couldn't write to appendable.");
        }
      }
    }
    try {
      BufferedWriter writer = new BufferedWriter((new FileWriter(filePath)));
      String output = ap.toString();
      writer.write(output);
      writer.close();
    } catch (IOException e) {
      throw new IllegalStateException("File could not be saved to path.");
    }
  }
}