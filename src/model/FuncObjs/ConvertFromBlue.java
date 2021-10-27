package model.FuncObjs;

import model.iPixel;

public class ConvertFromBlue extends AConvertFromAbsolute {
  @Override
  protected int getAbsolute(iPixel p) {
    return p.getColors().get(iPixel.Color.Blue);
  }
}
