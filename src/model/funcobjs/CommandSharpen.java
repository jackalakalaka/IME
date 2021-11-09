package model.funcobjs;

import model.IPixel;
import model.Image;
import model.Pixel;

/**
 * Function object for sharpening an image pixel by pixel.
 */
public class CommandSharpen extends ACommandImageOp {

  @Override
  protected IPixel getOtherPixel(int row, int column, Image image) {
    int newBlue = getSharpColor(IPixel.Color.Blue, row, column, image);
    int newRed = getSharpColor(IPixel.Color.Red, row, column, image);
    int newGreen = getSharpColor(IPixel.Color.Green, row, column, image);

    return new Pixel(image.getMaxValue(), newRed, newGreen, newBlue);
  }

  @Override
  public String giveSignature() {
    return "- To sharpen an image type 'sharpen <img_former> <img_new>'" +
            "into the command line.\n";
  }

  /**
   * This helper returns the color value for a sharper pixel at the given row/column.
   *
   * @param color  The color value to be returned.
   * @param row    The row where the pixel is.
   * @param column The column where the pixel is.
   * @param image  The image to get the old pixels.
   * @return An integer representing the value of the color.
   */
  private int getSharpColor(IPixel.Color color, int row, int column, Image image) {
    int colorValue = 0;
    for (int i = row - 2; i < row + 3; i++) {
      for (int j = column - 2; j < column + 3; j++) {
        try {
          if (Math.abs(i -row) > 1 || Math.abs(j-column) > 1) {
            colorValue = colorValue + image.getPixelAt(i,j).getColors().get(color)/-8;
          } else if (Math.abs(i - row) == 1 || Math.abs(j - column) == 1) {
            colorValue = colorValue + image.getPixelAt(i,j).getColors().get(color)/4;
          } else if (i == row && column == j) {
            colorValue = colorValue + image.getPixelAt(i,j).getColors().get(color);
          }
        } catch (ArrayIndexOutOfBoundsException ignored) {

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
