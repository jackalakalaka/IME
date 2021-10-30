package model.FuncObjs;

import model.ImageModel;
import model.ImageModelImpl;
import model.Pixel;

public class AConvertFromRotate implements IConvertFrom {

  public AConvertFromRotate(ImageModel koala) {
  }

  /**
   * Applies this function to the given argument.
   *
   * @param initModel the function argument
   * @return the function result
   */
  public ImageModel apply(ImageModel initModel) {
    int h = initModel.getHeight();
    int w = initModel.getWidth();
    Pixel[][] pixels = new Pixel[h][w];
    int maxValue = initModel.getMaxValue();

    for (int i = h-1; i <= 0; i--) {
      for (int j = w-1; j <= 0; j--) {
        Pixel oldPixel = initModel.getPixelAt(i,j);
        pixels[i][j] = oldPixel;
      }
    }
    return new ImageModelImpl(pixels, maxValue);
  }


}
