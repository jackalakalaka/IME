package model.FuncObjs;

import model.iImage;
import model.Pixel;

public class ConvertByHorizontal extends AConvertByDimensions{
  @Override
  protected Pixel getOtherPixel(int i, int j, iImage model) {
    return model.getPixelAt(i,model.getWidth() - j - 1);
  }

  @Override
  public String giveSignature() {
    return "To flip the image horizontally type 'horizontally' into the command line.\n\n";
  }
}
