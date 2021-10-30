package model.FuncObjs;

import model.ImageModel;
import model.Pixel;

public class ConvertByHorizontal extends AConvertByDimensions{
  @Override
  protected Pixel getOtherPixel(int i, int j, ImageModel model) {
    return model.getPixelAt(i,model.getWidth() - j - 1);
  }
}
