package model.FuncObjs;

import model.iImage;
import model.Pixel;

public class ConvertByHorizontalVertical extends AConvertByDimensions{
  @Override
  protected Pixel getOtherPixel(int i, int j, iImage model) {
    return model.getPixelAt(model.getHeight() - i - 1, model.getWidth() - j - 1);
  }

  @Override
  public String giveSignature() {
    return "To flip the image vertically & horizontally " +
            "type 'flip both' into the command line.\n\n";
  }
}
