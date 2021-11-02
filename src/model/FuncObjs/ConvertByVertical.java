package model.FuncObjs;

import model.Image;
import model.iPixel;

public class ConvertByVertical extends ACommandDimension {

  @Override
  protected iPixel getOtherPixel(int i, int j, Image model) {
    return model.getPixelAt(model.getHeight() - i - 1, j);
  }

  @Override
  public String giveSignature() {
    return "- To flip the img vertically type 'vertical <img_former> <img_new>' into the " +
            "command line.\n";
  }
}
