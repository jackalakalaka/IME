package model.FuncObjs;

import model.iPixel;

public class ConvertFromGreen extends AConvertFromAbsolute {
  @Override
  protected int getAbsolute(iPixel p) {
    return p.getColors().get(iPixel.Color.Green);
  }

  @Override
  public String giveSignature() {
    return "To get a heat map of green in the image type 'green' into the command line.\n\n";
  }
}
