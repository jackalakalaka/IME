package model.funcobjs;

import factory.ImageFactory;
import model.IPixel;
import model.Image;

/**
 * An abstract class for function objects that require the whole image to work.
 */
public abstract class ACommandImageOp implements ICommands {

  /**
   * Applies this function to the given argument.
   *
   * @param initModel the function argument
   * @return the function result
   */
  public Image apply(Image initModel) {
    int h = initModel.getHeight();
    int w = initModel.getWidth();
    IPixel[][] pixels = new IPixel[h][w];

    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        pixels[i][j] = getOtherPixel(i, j, initModel);
      }
    }
    return new ImageFactory().createImage(initModel.getMaxValue(), pixels, initModel.getType());
  }

  /**
   * Gets a pixel from the other image at that location.
   *
   * @param row    The row.
   * @param column The column.
   * @param image  The other image.
   * @return A new IPixel.
   */
  protected abstract IPixel getOtherPixel(int row, int column, Image image);


}
