package model.funcobjs;

import model.IPixel;
import model.Image;
import model.Pixel;

/**
 * Function object that returns the sepia version of a given pixel.
 */
public class CommandSepia extends ACommandImageOp {

  @Override
  protected IPixel getOtherPixel(int row, int column, Image image) {
    IPixel originalPixel = image.getPixelAt(row,column);
    return new Pixel(255,getSepiaRed(originalPixel),getSepiaGreen(originalPixel),
            getSepiaBlue(originalPixel));
  }

  @Override
  public String giveSignature() {
    return "- To get the sepia color transformation type 'sepia <img_former> <img_new>'" +
            "into the command line.\n";
  }

  private int getSepiaRed(IPixel originalPixel) {
    double newRed = 0;
    newRed = newRed + originalPixel.getColors().get(IPixel.Color.Red) * 0.393;
    newRed = newRed + originalPixel.getColors().get(IPixel.Color.Green) * 0.769;
    newRed = newRed + originalPixel.getColors().get(IPixel.Color.Green) * 0.189;
    if (newRed > 255 ) {
      return 255;
    }
    return ((int) newRed);
  }

  private int getSepiaGreen(IPixel originalPixel) {
    double newGreen = 0;
    newGreen = newGreen + originalPixel.getColors().get(IPixel.Color.Red) * 0.349;
    newGreen = newGreen + originalPixel.getColors().get(IPixel.Color.Green) * 0.686;
    newGreen = newGreen + originalPixel.getColors().get(IPixel.Color.Green) * 0.168;
    if (newGreen > 255 ) {
      return 255;
    }
    return ((int) newGreen);
  }

  private int getSepiaBlue(IPixel originalPixel) {
    double newBlue = 0;
    newBlue = newBlue + originalPixel.getColors().get(IPixel.Color.Red) * 0.272;
    newBlue = newBlue + originalPixel.getColors().get(IPixel.Color.Green) * 0.534;
    newBlue = newBlue + originalPixel.getColors().get(IPixel.Color.Green) * 0.131;
    if (newBlue > 255 ) {
      return 255;
    }
    return ((int) newBlue);
  }
}
