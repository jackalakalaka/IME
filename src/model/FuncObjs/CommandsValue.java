package model.FuncObjs;

import model.iPixel;

public class CommandsValue extends ACommandsAbsolute {

  @Override
  protected int getAbsolute(iPixel p) {
    return p.getValue();
  }

  @Override
  public String giveSignature() {
    return "- To get a heat map of the max value in the img " +
            "type 'value <img_former> <img_new>' into the command line.\n";
  }
}