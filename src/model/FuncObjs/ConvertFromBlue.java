package model.FuncObjs;

import model.iPixel;

public class ConvertFromBlue extends AConvertFromAbsolute {
  @Override
  protected int getAbsolute(iPixel p) {
    return p.getColors().get(iPixel.Color.Blue);
  }

  @Override
  public String giveSignature() {
    return "To get a heat map of blue in the image type 'blue' into the command line.\n\n";
  }
}
