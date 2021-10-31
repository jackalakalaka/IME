package model.FuncObjs;

import java.io.FileNotFoundException;

import factory.ImageFactory;
import model.Image;
import model.Pixel;
import model.iPixel;

public abstract class AConvertFromAbsolute implements IConvertFrom {
  ImageFactory imgFac;
  String newName;

  public AConvertFromAbsolute(String newName) {
    this.imgFac = new ImageFactory();
    this.newName = newName;
  }

  /**
   * Applies this function to the given argument.
   *
   * @param initModel the function argument
   * @return the function result
   */
  public Image apply(Image initModel) {
    int h = initModel.getHeight();
    int w = initModel.getWidth();
    Pixel[][] pixels = new Pixel[h][w];
    int maxValue = initModel.getMaxValue();

    for (int row = 0; row < h; row++) {
      for (int col = 0; col < w; col++) {
        iPixel oldPixel = initModel.getPixelAt(row, col);
        int absolute = getAbsolute(oldPixel);
        pixels[row][col] = new Pixel(maxValue, absolute);
      }
    }

    try {
      return this.imgFac.createImage("ppm", this.newName, pixels, maxValue);
    } catch (FileNotFoundException e) {
      System.out.println("Error - file not found using apply method.");
      return null;
    }
  }

  /**
   * Gets this implementation of AConvertFromAbsolute's specific absolute value to convert into a
   * greyed pixel.
   *
   * @param p old pixel
   * @return impl-specific absolute
   */
  protected abstract int getAbsolute(iPixel p);
}
