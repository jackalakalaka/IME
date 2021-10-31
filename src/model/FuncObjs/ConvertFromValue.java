package model.FuncObjs;

import model.iPixel;

public class ConvertFromValue extends AConvertFromAbsolute {
  public ConvertFromValue(String newName) {
    super(newName);
  }

  @Override
  protected int getAbsolute(iPixel p) {
    return p.getValue();
  }

  @Override
  public String giveSignature() {
    return "- To get a heat map of the max value in the img " +
            "type 'value-component <img_former> <img_new>' into the command line.\n";
  }
}