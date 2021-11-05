package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageJPG extends AbstractImage {
  protected BufferedImage buff;

  /**
   * @param filePath The string representing the path to the file.
   */
  public ImageJPG(String filePath) {
    super(filePath);
  }

  @Override
  protected void getImageFromFile(String filePath) {
    File f = new File(filePath);
    BufferedImage bufferedImage;
    try {
      bufferedImage = ImageIO.read(f);
      this.buff = bufferedImage;
    } catch (IOException e) {
      throw new IllegalArgumentException("File contains nothing.");
    }
    this.height = bufferedImage.getHeight();
    this.width = bufferedImage.getWidth();

    this.pixelArray = new IPixel[width][height];

    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        int RGB = bufferedImage.getRGB(i, j);
        int red = (RGB >> 16) & 255;
        int green = (RGB >> 8) & 255;
        int blue = (RGB) & 255;
        pixelArray[i][j] = new Pixel(255, red, green, blue);
      }
    }
  }

  @Override
  public void saveImageToFile(String filepath) throws IllegalStateException {
    try {
      File outFile = new File(filepath);
      ImageIO.write(this.buff, "png", outFile);
    } catch (IOException e) {
      throw new IllegalStateException("The file path does not exist.");
    }
  }
}
