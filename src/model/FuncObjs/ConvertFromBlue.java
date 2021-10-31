package model.FuncObjs;

import model.Pixel;
import model.iPixel;

public class ConvertFromBlue extends AConvertFromAbsolute {
  public ConvertFromBlue(String newName) {
    super(newName);
  }

  @Override
  protected int getAbsolute(iPixel p) {
    return p.getColors().get(Pixel.Color.Blue);
  }

  @Override
  public String giveSignature() {
    return "- To get a heat map of blue in the img type 'blue-component <img_former> <img_new>'" +
            "into the command line.\n";
  }
}
