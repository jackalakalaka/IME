package model.FuncObjs;

import model.iImage;
import model.ImagePpm;
import model.Pixel;
import model.iPixel;

public abstract class AConvertFromAbsolute implements IConvertFrom {

  /**
   * Applies this function to the given argument.
   *
   * @param initModel the function argument
   * @return the function result
   */
  public iImage apply(iImage initModel) {
    int h = initModel.getHeight();
    int w = initModel.getWidth();
    Pixel[][] pixels = new Pixel[h][w];
    int maxValue = initModel.getMaxValue();

    for (int row = 0; row < h; row++) {
      for (int col = 0; col < w; col++) {
        Pixel oldPixel = initModel.getPixelAt(row, col);
        int absolute = getAbsolute(oldPixel);
        pixels[row][col] = new Pixel(maxValue, absolute);
      }
    }
    return new ImagePpm(pixels, maxValue);
  }

  /**
   * Gets this implementation of AConvertFromAbsolute's specific absolute value to convert into a
   * greyed pixel.
   * @param p old pixel
   * @return impl-specific absolute
   */
  protected abstract int getAbsolute(iPixel p);
}
