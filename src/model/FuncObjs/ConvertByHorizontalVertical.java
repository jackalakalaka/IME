package model.FuncObjs;

import model.Image;
import model.Pixel;

public class ConvertByHorizontalVertical extends AConvertByDimensions{
  public ConvertByHorizontalVertical(String newName) {
    super(newName);
  }

  @Override
  protected Pixel getOtherPixel(int i, int j, Image model) {
    return model.getPixelAt(model.getHeight() - i - 1, model.getWidth() - j - 1);
  }

  @Override
  public String giveSignature() {
    return "- To flip the img vertically & horizontally " +
            "type 'flip-both' into the command line.\n";
  }
}
