package model.funcobjs;

import factory.ImageFactory;
import model.IPixel;
import model.Image;
import model.ImagePpm;
import model.Pixel;

/**
 * An abstract class for function objects that get a single value from pixels.
 */
public abstract class ACommandsAbsolute implements ICommands {

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
        IPixel oldPixel = initModel.getPixelAt(row, col);
        int absolute = getAbsolute(oldPixel);
        pixels[row][col] = new Pixel(maxValue, absolute);
      }
    }
    return new ImageFactory().createImage(initModel.getMaxValue(), pixels, initModel.getType());
  }

  /**
   * Gets this implementation of AConvertFromAbsolute's specific absolute value to convert into a
   * greyed pixel.
   *
   * @param originalPixel The original pixel.
   * @return An integer.
   */
  protected abstract int getAbsolute(IPixel originalPixel);
}
