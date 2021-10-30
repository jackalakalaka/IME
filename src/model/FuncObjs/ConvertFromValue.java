package model.FuncObjs;

import model.iPixel;

public class ConvertFromValue extends AConvertFromAbsolute {
  @Override
  protected int getAbsolute(iPixel p) {
    return p.getValue();
  }

  @Override
  public String giveSignature() {
    return "To get a heat map of average value in the image " +
            "type 'value' into the command line.\n\n";
  }
}