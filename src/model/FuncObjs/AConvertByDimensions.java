package model.FuncObjs;

import java.io.FileNotFoundException;

import factory.ImageFactory;
import model.Image;
import model.ImagePpm;
import model.Pixel;

public abstract class AConvertByDimensions implements IConvertFrom {
  ImageFactory imgFac;
  String newName;

  public AConvertByDimensions(String newName) {
    this.imgFac = new ImageFactory();
    this.newName = newName;
  }

  /**
   * Applies this function to the given argument.
   *
   * @param initModel the function argument
   * @return the function result
   */
  public Image apply(Image initModel) {
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

    try {
      return this.imgFac.createImage("ppm", this.newName, pixels, maxValue);
    } catch (FileNotFoundException e) {
      System.out.println("Error - file not found using apply method.");
      return null;
    }
  }

  protected abstract Pixel getOtherPixel(int i, int j, Image model);


}
