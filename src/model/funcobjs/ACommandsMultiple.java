package model.FuncObjs;

import java.util.ArrayList;

import model.IPixel;
import model.Image;
import model.ImagePpm;
import model.Pixel;

/**
 * An abstract class for function objects that get multiple values from pixels.
 */
public abstract class ACommandsMultiple implements ICommands {


  /**
   * Applies this function to the given argument.
   *
   * @param initModel the function argument
   * @return the function result
   */
  public Image apply(Image initModel) {
    int h = initModel.getHeight();
    int w = initModel.getWidth();
    IPixel[][] pixels = new Pixel[h][w];
    int maxValue = initModel.getMaxValue();

    for (int row = 0; row < h; row++) {
      for (int col = 0; col < w; col++) {
        IPixel oldPixel = initModel.getPixelAt(row, col);
        ArrayList<Double> multiple = getMultiple(oldPixel);
        int red = (int) (multiple.get(0) * maxValue);
        int green = (int) (multiple.get(1) * maxValue);
        int blue = (int) (multiple.get(2) * maxValue);
        pixels[row][col] = new Pixel(maxValue, red, green, blue);
      }
    }
    return new ImagePpm(maxValue, pixels);
  }

  /**
   * Gets this implementation of AConvertFromMultiple's specific list to convert into a
   * new pixel.
   *
   * @param originalPixel The original pixel.
   * @return List of values from the old pixel.
   */
  protected abstract ArrayList<Double> getMultiple(IPixel originalPixel);

}
