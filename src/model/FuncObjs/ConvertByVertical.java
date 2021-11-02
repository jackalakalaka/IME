package model.FuncObjs;

import model.Image;
import model.iPixel;

/**
 * A function object for getting the pixel on the other side of the image.
 */
public class ConvertByVertical extends ACommandDimension {

  @Override
  protected iPixel getOtherPixel(int row, int column, Image image) {
    return image.getPixelAt(image.getHeight() - row - 1, column);
  }

  @Override
  public String giveSignature() {
    return "- To flip the img vertically type 'vertical <img_former> <img_new>' into the " +
            "command line.\n";
  }
}
