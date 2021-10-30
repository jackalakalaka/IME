package model.FuncObjs;

import java.util.ArrayList;

import model.ImageModel;
import model.ImageModelPpm;
import model.Pixel;
import model.iPixel;

public abstract class AConvertFromMultiple implements IConvertFrom {

  /**
   * Applies this function to the given argument.
   *
   * @param initModel the function argument
   * @return the function result
   */
  public ImageModel apply(ImageModel initModel) {
    int h = initModel.getHeight();
    int w = initModel.getWidth();
    Pixel[][] pixels = new Pixel[h][w];
    int maxValue = initModel.getMaxValue();

    for (int row = 0; row < h; row++) {
      for (int col = 0; col < w; col++) {
        Pixel oldPixel = initModel.getPixelAt(row, col);
        ArrayList<Double> multiple = getMultiple(oldPixel);
        int red = (int) (multiple.get(0) * maxValue);
        int green = (int) (multiple.get(1) * maxValue);
        int blue = (int) (multiple.get(2) * maxValue);
        pixels[row][col] = new Pixel(maxValue, red, green, blue);
      }
    }
    return new ImageModelPpm(pixels, maxValue);
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
