package model.FuncObjs;

import model.IPixel;

/**
 * A function object for getting the value of a pixel.
 */
public class CommandsValue extends ACommandsAbsolute {

  @Override
  protected int getAbsolute(IPixel originalPixel) {
    return originalPixel.getValue();
  }

  @Override
  public String giveSignature() {
    return "- To get a heat map of the max value in the img " +
            "type 'value <img_former> <img_new>' into the command line.\n";
  }
}