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
   * @param filePath The string representing the path to the file.
   */
  public ImagePNG(String filePath) {
    super(filePath);
  }

  @Override
  public void saveImageToFile(String filepath) throws IllegalStateException {
    try {
      File outFile = new File(filepath);
      ImageIO.write(this.buff, "jpg", outFile);
    } catch (IOException e) {
      throw new IllegalStateException("The file path does not exist.");
    }
  }
}

