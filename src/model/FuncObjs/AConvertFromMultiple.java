package model.FuncObjs;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import factory.ImageFactory;
import model.Image;
import model.Pixel;
import model.iPixel;

public abstract class AConvertFromMultiple implements IConvertFrom {
  ImageFactory imgFac;
  String newName;

  public AConvertFromMultiple(String newName) {
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
    iPixel[][] pixels = new Pixel[h][w];
    int maxValue = initModel.getMaxValue();

    for (int row = 0; row < h; row++) {
      for (int col = 0; col < w; col++) {
        iPixel oldPixel = initModel.getPixelAt(row, col);
        ArrayList<Double> multiple = getMultiple(oldPixel);
        int red = (int) (multiple.get(0) * maxValue);
        int green = (int) (multiple.get(1) * maxValue);
        int blue = (int) (multiple.get(2) * maxValue);
        pixels[row][col] = new Pixel(maxValue, red, green, blue);
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
   * Gets this implementation of AConvertFromMultiple's specific list to convert into a
   * new pixel.
   *
   * @param p old pixel
   * @return impl-specific list
   */
  protected abstract ArrayList<Double> getMultiple(iPixel p);

}
