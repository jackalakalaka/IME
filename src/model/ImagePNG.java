package model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Image class for PNGs that uses the same construction method to import the image.
 * But the image is saved differently.
 */
public class ImagePNG extends ImageJPG {
  /**
   * Constructor that builds the image from a file.
   *
   * @param filePath The string representing the path to the file.
   */
  public ImagePNG(String filePath) {
    super(filePath);
    this.type = Type.PNG;
  }

  /**
   * Constructor made for creating PNG images with a known pixel array.
   * Alternative constructor that is used in tests and conversions.
   *
   * @param maxValue The maximum value for image.
   * @param pixelArray The pixel arrray that makes up the image.
   */
  public ImagePNG(int maxValue, IPixel[][] pixelArray) {
    super(maxValue,pixelArray);
    this.type = Type.PNG;
  }

  @Override
  public void saveImageToFile(String filepath) throws IllegalStateException {
    setToCurrent();
    try {
      File outFile = new File(filepath);
      ImageIO.write(this.buff, "png", outFile);
    } catch (IOException e) {
      throw new IllegalStateException("The file path does not exist.");
    }
  }

  @Override
  public Type getType() {
    return this.type;
  }
}

