package model.funcobjs;

import java.util.ArrayList;
import java.util.HashMap;

import model.IPixel;
import model.Pixel;

/**
 * A function object for getting the luma of a pixel.
 */
public class CommandsLuma extends ACommandsMultiple {

  @Override
  protected ArrayList<Double> getMultiple(IPixel originalPixel) {
    HashMap<Pixel.Color, Double> luma = originalPixel.getLuma();
    ArrayList<Double> lumaDoubles = new ArrayList<>();
    lumaDoubles.add(luma.get(Pixel.Color.Red));
    lumaDoubles.add(luma.get(Pixel.Color.Green));
    lumaDoubles.add(luma.get(Pixel.Color.Blue));
    return lumaDoubles;
  }

  @Override
  public String giveSignature() {
    return "- To get a heat map of luminosity in the img type 'luma <img_former> <img_new>' into" +
            " the command line.\n";
  }
}
