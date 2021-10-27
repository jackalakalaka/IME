package model.FuncObjs;

import model.iPixel;

public class ConvertFromValue extends AConvertFromAbsolute {
  @Override
  protected int getAbsolute(iPixel p) {
    return p.getValue();
  }
}