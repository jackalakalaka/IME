package model.FuncObjs;

import model.iPixel;

public class ConvertFromIntensity extends AConvertFromAbsolute {
  @Override
  protected int getAbsolute(iPixel p) {
    return p.getIntensity();
  }

  @Override
  public String giveSignature() {
    return "To get a heat map of intensity in the image " +
            "type 'intensity' into the command line.\n\n";
  }
}