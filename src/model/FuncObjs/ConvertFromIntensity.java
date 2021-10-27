package model.FuncObjs;

import model.iPixel;

public class ConvertFromIntensity extends AConvertFromAbsolute {
  @Override
  protected int getAbsolute(iPixel p) {
    return p.getIntensity();
  }
}