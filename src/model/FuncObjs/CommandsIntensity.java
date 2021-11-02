package model.FuncObjs;

import model.iPixel;

public class CommandsIntensity extends ACommandsAbsolute {

  @Override
  protected int getAbsolute(iPixel p) {
    return p.getIntensity();
  }

  @Override
  public String giveSignature() {
    return "- To get a heat map of intensity in the img " +
            "type 'intensity <img_former> <img_new>' into the command line.\n";
  }
}