package model.funcobjs;

import model.image.IPixel;
import model.image.Image;

/**
 * A function object for getting the pixel on the other side of the image vertically.
 */
public class ConvertByVertical extends ACommandImageOp {

  @Override
  protected IPixel getOtherPixel(int row, int column, Image image) {
    return image.getPixelAt(image.getHeight() - row - 1, column);
  }

  @Override
  public String giveSignature() {
    return "- To flip the img vertically type 'vertical <img_former> <img_new>' into the " +
            "command line.\n";
  }
}
