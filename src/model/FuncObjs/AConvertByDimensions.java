package model.FuncObjs;

import java.util.HashMap;

import model.ImageModel;
import model.ImageModelPpm;
import model.Pixel;

public abstract class AConvertByDimensions implements IConvertFrom {


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

    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        Pixel oldPixel = initModel.getPixelAt(i,j);
        pixels[i][j] = getOtherPixel(i,j,initModel);
      }
    }
    return new ImageModelPpm(pixels, maxValue);
  }

  protected abstract Pixel getOtherPixel(int i, int j, ImageModel model);


}
