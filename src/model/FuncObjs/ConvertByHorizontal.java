package model.FuncObjs;

import model.Image;
import model.iPixel;

public class ConvertByHorizontal extends AConvertByDimensions {
  public ConvertByHorizontal(String newName) {
    super(newName);
  }

  @Override
  protected iPixel getOtherPixel(int i, int j, Image model) {
    return model.getPixelAt(i, model.getWidth() - j - 1);
  }

  @Override
  public String giveSignature() {
    return "- To flip the img horizontally type 'horizontal-flip  <img_former> <img_new>' into the "
            + "command line.\n";
  }
}
