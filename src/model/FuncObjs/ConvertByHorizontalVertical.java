package model.FuncObjs;

import model.ImageModel;
import model.Pixel;

public class ConvertByHorizontalVertical extends AConvertByDimensions{
  @Override
  protected Pixel getOtherPixel(int i, int j, ImageModel model) {
    return model.getPixelAt(model.getHeight() - i - 1, model.getWidth() - j - 1);
  }
}
