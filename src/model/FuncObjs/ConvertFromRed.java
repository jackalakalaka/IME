package model.FuncObjs;

import model.Pixel;
import model.iPixel;

public class ConvertFromRed extends AConvertFromAbsolute {
  public ConvertFromRed(String newName) {
    super(newName);
  }

  @Override
  protected int getAbsolute(iPixel p) {
    return p.getColors().get(Pixel.Color.Red);
  }

  @Override
  public String giveSignature() {
    return "- To get a heat map of red in the img type 'red-component <img_former> <img_new>' into "
            + "the command line.\n";
  }
}