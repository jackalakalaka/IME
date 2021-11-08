package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageJPG extends AbstractImage {
  protected BufferedImage buff;

  /**
   * Constructor that builds the image from a file.
   *
   * @param filePath The string representing the path to the file.
   */
  public ImageJPG(String filePath) {
    super(filePath);
  }

  @Override
  protected void getImageFromFile(String filePath) {
    this.type = Type.JPG;
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
    this.maxValue = 255;

    this.pixelArray = new IPixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int RGB = bufferedImage.getRGB(j, i);//note that the position call is inverted for JPG/PNG.
        int red = (RGB >> 16) & 255;
        int green = (RGB >> 8) & 255;
        int blue = (RGB) & 255;
        pixelArray[i][j] = new Pixel(255, red, green, blue);
      }
    }
  }

  /**
   * Constructor made for creating JPG images with a known pixel array.
   * Alternative constructor that is used in tests and conversions.
   *
   * @param maxValue The maximum value for image.
   * @param pixelArray The pixel array that makes up the image.
   */
  public ImageJPG(int maxValue, IPixel[][] pixelArray) {
    super(maxValue,pixelArray);
    this.type = Type.JPG;
  }

  @Override
  public Type getType() {
    return this.type;
  }

  @Override
  public void saveImageToFile(String filepath) throws IllegalStateException {
    setToCurrent();
    try {
      File outFile = new File(filepath);
      ImageIO.write(this.buff, "jpg", outFile);
    } catch (IOException e) {
      throw new IllegalStateException("The file path does not exist.");
    }
  }

  /**
   * Helper that sets the buffered image field to the current state of the pixel array.
   */
  protected void setToCurrent() {
    this.buff = new BufferedImage(this.width, this.height,
            BufferedImage.TYPE_3BYTE_BGR);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int alpha = 255;
        int red = this.pixelArray[i][j].getColors().get(IPixel.Color.Red);
        int green = this.pixelArray[i][j].getColors().get(IPixel.Color.Green);
        int blue = this.pixelArray[i][j].getColors().get(IPixel.Color.Blue);
        int RGB = (alpha << 24);
        RGB = RGB | (red << 16);
        RGB = RGB | (green << 8);
        RGB = RGB | (blue);
        this.buff.setRGB(j,i,RGB);//note that the position call is inverted for JPG/PNG.
      }
    }
  }


}
