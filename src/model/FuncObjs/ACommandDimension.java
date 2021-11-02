package model.FuncObjs;

import model.Image;
import model.ImagePpm;
import model.iPixel;

/**
 * An abstract class for function objects that work with dimensions.
 */
public abstract class ACommandDimension implements ICommands {

  /**
   * Applies this function to the given argument.
   *
   * @param initModel the function argument
   * @return the function result
   */
  public Image apply(Image initModel) {
    int h = initModel.getHeight();
    int w = initModel.getWidth();
    iPixel[][] pixels = new iPixel[h][w];
    int maxValue = initModel.getMaxValue();

    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        iPixel oldPixel = initModel.getPixelAt(i, j);
        pixels[i][j] = getOtherPixel(i, j, initModel);
      }
    }
    return new ImagePpm(maxValue, pixels);
  }

  /**
   * Gets a pixel from the other image at that location.
   *
   * @param row    The row.
   * @param column The column.
   * @param image  The other image.
   * @return A new iPixel.
   */
  protected abstract iPixel getOtherPixel(int row, int column, Image image);


}
