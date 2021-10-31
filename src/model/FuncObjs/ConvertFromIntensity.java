package model.FuncObjs;

import model.iPixel;

public class ConvertFromIntensity extends AConvertFromAbsolute {
  public ConvertFromIntensity(String newName) {
    super(newName);
  }

  @Override
  protected int getAbsolute(iPixel p) {
    return p.getIntensity();
  }

  @Override
  public String giveSignature() {
    return "- To get a heat map of intensity in the img " +
            "type 'intensity-component <img_former> <img_new>' into the command line.\n";
  }
}