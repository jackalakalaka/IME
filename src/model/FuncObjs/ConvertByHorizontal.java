package model.FuncObjs;

import model.Image;
import model.iPixel;

/**
 * A function object for getting the pixel on the other side of the image.
 */
public class ConvertByHorizontal extends ACommandDimension {

  @Override
  protected iPixel getOtherPixel(int row, int column, Image image) {
    return image.getPixelAt(row, image.getWidth() - column - 1);
  }

  @Override
  public String giveSignature() {
    return "- To flip the img horizontally type 'horizontal  <img_former> <img_new>' into the "
            + "command line.\n";
  }
}
