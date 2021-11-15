package model.funcobjs;

import model.IPixel;
import model.Image;
import model.Pixel;

/**
 * This function object creates a blurred version of the pixel.
 */
public class CommandBlur extends ACommandImageOp {

  @Override
  protected IPixel getOtherPixel(int row, int column, Image image) {

    int newBlue = getBlurredColor(IPixel.Color.Blue, row, column, image);
    int newRed = getBlurredColor(IPixel.Color.Red, row, column, image);
    int newGreen = getBlurredColor(IPixel.Color.Green, row, column, image);

    return new Pixel(image.getMaxValue(), newRed, newGreen, newBlue);
  }

  @Override
  public String giveSignature() {
    return "- To blur an image type 'blur <img_former> <img_new>'" +
            "into the command line.\n";
  }

  /**
   * This helper returns the color value for a blurred pixel at the given row/column.
   *
   * @param color  The color value to be returned.
   * @param row    The row where the pixel is.
   * @param column The column where the pixel is.
   * @param image  The image to get the old pixels.
   * @return An integer representing the value of the color.
   */
  private int getBlurredColor(IPixel.Color color, int row, int column, Image image) {
    int colorValue = 0;
    for (int i = row - 1; i < row + 2; i++) {
      for (int j = column - 1; j < column + 2; j++) {
        if (i >= 0 && i <= image.getHeight() - 1 && j >= 0 && j <= image.getWidth() - 1) {
          switch (Math.abs(i - row) + Math.abs(j - column)) {
            case 0:
              colorValue = colorValue + image.getPixelAt(i, j).getColors().get(color) / 4;
              break;
            case 1:
              colorValue = colorValue + image.getPixelAt(i, j).getColors().get(color) / 8;
              break;
            case 2:
              colorValue = colorValue + image.getPixelAt(i, j).getColors().get(color) / 16;
              break;
            default:
          }
        }
      }
    }
    if (colorValue > 255) {
      return 255;
    } else if (colorValue <= 0) {
      return 0;
    }
    return colorValue;
  }
}