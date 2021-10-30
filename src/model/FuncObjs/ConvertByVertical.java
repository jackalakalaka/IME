package model.FuncObjs;

import model.iImage;
import model.Pixel;

public class ConvertByVertical extends AConvertByDimensions{
  @Override
  protected Pixel getOtherPixel(int i, int j, iImage model) {
    return model.getPixelAt(model.getHeight() - i - 1, j);
  }

  @Override
  public String giveSignature() {
    return "To flip the image vertically type 'vertical' into the command line.\n\n";
  }
}
