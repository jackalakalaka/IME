package model.FuncObjs;

import model.iPixel;

public class ConvertFromRed extends AConvertFromAbsolute {
  @Override
  protected int getAbsolute(iPixel p) {
    return p.getColors().get(iPixel.Color.Red);
  }

  @Override
  public String giveSignature() {
    return "To get a heat map of red in the image type 'red' into the command line.\n\n";
  }
}