package model.funcobjs;

import model.IPixel;

/**
 * A function object that returns the greyscale version of a pixel.
 */
public class CommandGreyscale extends ACommandsAbsolute {

  @Override
  protected int getAbsolute(IPixel originalPixel) {
    double newColor = 0;
    newColor = newColor + originalPixel.getColors().get(IPixel.Color.Red) * 0.2126
            + originalPixel.getColors().get(IPixel.Color.Green) * 0.7152
            + originalPixel.getColors().get(IPixel.Color.Blue) * 0.0722;
    return (int) newColor;
  }

  @Override
  public String giveSignature() {
    return "- To get the greyscale color transform type 'greyscale <img-former> <img-new>" +
            "into the commandline.\n";
  }
}
