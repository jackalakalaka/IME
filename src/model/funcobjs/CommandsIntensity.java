package model.FuncObjs;

import model.IPixel;

/**
 * A function object for getting the intensity of a pixel.
 */
public class CommandsIntensity extends ACommandsAbsolute {

  @Override
  protected int getAbsolute(IPixel originalPixel) {
    return originalPixel.getIntensity();
  }

  @Override
  public String giveSignature() {
    return "- To get a heat map of intensity in the img " +
            "type 'intensity <img_former> <img_new>' into the command line.\n";
  }
}