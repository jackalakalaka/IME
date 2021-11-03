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
  protected ArrayList<Integer> getMultiple(IPixel originalPixel) {
    HashMap<Pixel.Color, Double> luma = originalPixel.getLuma();
    ArrayList<Integer> lumaIntegers = new ArrayList<>();
    lumaIntegers.add(luma.get(Pixel.Color.Red).intValue());
    lumaIntegers.add(luma.get(Pixel.Color.Green).intValue());
    lumaIntegers.add(luma.get(Pixel.Color.Blue).intValue());

    return lumaIntegers;
  }

  @Override
  public String giveSignature() {
    return "- To get a heat map of luminosity in the img type 'luma <img_former> <img_new>' into" +
            " the command line.\n";
  }
}
