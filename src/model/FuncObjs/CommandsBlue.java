package model.FuncObjs;

import model.Pixel;
import model.iPixel;

/**
 * A function object for getting the blue value of a pixel.
 */
public class CommandsBlue extends ACommandsAbsolute {

  @Override
  protected int getAbsolute(iPixel originalPixel) {
    return originalPixel.getColors().get(Pixel.Color.Blue);
  }

  @Override
  public String giveSignature() {
    return "- To get a heat map of blue in the img type 'blue <img_former> <img_new>'" +
            "into the command line.\n";
  }
}