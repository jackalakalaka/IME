package model.FuncObjs;

import model.IPixel;
import model.Image;

/**
 * A function object for getting the pixel on the other side of the image.
 */
public class ConvertByHorizontalVertical extends ACommandDimension {

  @Override
  protected IPixel getOtherPixel(int row, int column, Image image) {
    return image.getPixelAt(image.getHeight() - row - 1, image.getWidth() - column - 1);
  }

  @Override
  public String giveSignature() {
    return "- To flip the img vertically & horizontally " +
            "type 'flip-both' into the command line.\n";
  }
}