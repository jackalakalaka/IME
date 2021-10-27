package model.FuncObjs;

import model.iPixel;

public class ConvertFromRed extends AConvertFromAbsolute {
  @Override
  protected int getAbsolute(iPixel p) {
    return p.getColors().get(iPixel.Color.Red);
  }
}