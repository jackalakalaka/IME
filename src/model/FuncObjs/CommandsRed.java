package model.FuncObjs;

import model.Pixel;
import model.iPixel;

/**
 * A function object for getting the red value of a pixel.
 */
public class CommandsRed extends ACommandsAbsolute {

  @Override
  protected int getAbsolute(iPixel originalPixel) {
    return originalPixel.getColors().get(Pixel.Color.Red);
  }

  @Override
  public String giveSignature() {
    return "- To get a heat map of red in the img type 'red <img_former> <img_new>' into "
            + "the command line.\n";
  }
}