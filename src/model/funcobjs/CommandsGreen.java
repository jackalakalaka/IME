package model.FuncObjs;

import model.IPixel;
import model.Pixel;

/**
 * A function object for getting the green value of a pixel.
 */
public class CommandsGreen extends ACommandsAbsolute {

  @Override
  protected int getAbsolute(IPixel originalPixel) {
    return originalPixel.getColors().get(Pixel.Color.Green);
  }

  @Override
  public String giveSignature() {
    return "- To get a heat map of green in the img type 'green <img_former> <img_new>' "
            + "into the command line.\n";
  }
}
