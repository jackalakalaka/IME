package model;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageBMP extends ImageJPG {

  public ImageBMP(String filePath) {
    super(filePath);
    this.type = Type.BMP;
  }

  public ImageBMP(int maxValue, IPixel[][] pixelArray) {
    super(maxValue, pixelArray);
    this.type = Type.BMP;
  }

  @Override
  public void saveImageToFile(String filepath) throws IllegalStateException {
    setToCurrent();
    try {
      File outFile = new File(filepath);
      ImageIO.write(this.buff, "bmp", outFile);
    } catch (IOException e) {
      throw new IllegalStateException("The file path does not exist.");
    }
  }
}
