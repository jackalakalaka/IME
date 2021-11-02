package model.FuncObjs;

import model.Pixel;
import model.iPixel;

public class CommandsGreen extends ACommandsAbsolute {

  @Override
  protected int getAbsolute(iPixel p) {
    return p.getColors().get(Pixel.Color.Green);
  }

  @Override
  public String giveSignature() {
    return "- To get a heat map of green in the img type 'green  <img_former> <img_new>' "
            + "into the command line.\n";
  }
}
