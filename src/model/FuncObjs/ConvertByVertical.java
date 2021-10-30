package model.FuncObjs;

import model.ImageModel;
import model.Pixel;

public class ConvertByVertical extends AConvertByDimensions{
  @Override
  protected Pixel getOtherPixel(int i, int j, ImageModel model) {
    return model.getPixelAt(model.getHeight() - i - 1, j);
  }
}
