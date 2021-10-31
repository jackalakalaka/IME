package model.FuncObjs;

import model.Image;
import model.Pixel;

public class ConvertByVertical extends AConvertByDimensions{
  public ConvertByVertical(String newName) {
    super(newName);
  }

  @Override
  protected Pixel getOtherPixel(int i, int j, Image model) {
    return model.getPixelAt(model.getHeight() - i - 1, j);
  }

  @Override
  public String giveSignature() {
    return "- To flip the img vertically type 'vertical-flip <img_former> <img_new>' into the " +
            "command line.\n";
  }
}
