package model.FuncObjs;

import model.Pixel;
import model.iPixel;

public class ConvertFromGreen extends AConvertFromAbsolute {
  public ConvertFromGreen(String newName) {
    super(newName);
  }

  @Override
  protected int getAbsolute(iPixel p) {
    return p.getColors().get(Pixel.Color.Green);
  }

  @Override
  public String giveSignature() {
    return "- To get a heat map of green in the img type 'green-component  <img_former> <img_new>' "
            + "into the command line.\n";
  }
}
