package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ImageModelImpl implements ImageModel{
  private Pixel[][] model;
  private int width;
  private int height;

  public ImageModelImpl(String filePath) throws FileNotFoundException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filePath));
    }
    catch (FileNotFoundException e) {
      throw new FileNotFoundException("File path leads to no file or was mistyped.");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0)!='#') {
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
    this.model = new Pixel[height][width];

    int maxValue = sc.nextInt();

    for (int i=0;i<height;i++) {
      for (int j=0;j<width;j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        this.model[i][j] = new Pixel(maxValue,r,g,b);
      }
    }
  }

  @Override
  public Pixel getPixelAt(int row, int col) {
    return model[row][col];
  }

  @Override
  public ImageModel imageFromFile(String filePath) {
    return null;
  }

  @Override
  public <T> ImageModel convertToViz(T Viz) {
    return null;
  }

  @Override
  public ImageModel changeBrightness(int change) {
    return null;
  }

  @Override
  public void saveImageToFile(String filepath) {

  }

}
