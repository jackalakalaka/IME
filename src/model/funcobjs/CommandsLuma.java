package model.funcobjs;

import java.util.ArrayList;
import java.util.HashMap;

import model.IPixel;
import model.Pixel;

/**
 * A function object for getting the luma of a pixel.
 */
public class CommandsLuma extends ACommandsAbsolute {

  @Override
  protected int getAbsolute(IPixel originalPixel) {
    return originalPixel.getLuma().intValue();
  }

  @Override
  public String giveSignature() {
    return "- To get a heat map of luminosity in the img type 'luma <img_former> <img_new>' into" +
            " the command line.\n";
  }
}
