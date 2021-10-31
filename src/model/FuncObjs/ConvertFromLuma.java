package model.FuncObjs;

import java.util.ArrayList;
import java.util.HashMap;

import model.Pixel;
import model.iPixel;

public class ConvertFromLuma extends AConvertFromMultiple {
  public ConvertFromLuma(String newName) {
    super(newName);
  }

  @Override
  protected ArrayList<Double> getMultiple(iPixel p) {
    HashMap<Pixel.Color, Double> luma = p.getLuma();
    ArrayList<Double> lumaDoubles = new ArrayList<>();
    lumaDoubles.add(luma.get(Pixel.Color.Red));
    lumaDoubles.add(luma.get(Pixel.Color.Green));
    lumaDoubles.add(luma.get(Pixel.Color.Blue));
    return lumaDoubles;
  }

  @Override
  public String giveSignature() {
    return "- To get a heat map of luminosity in the img type 'luma  <img_former> <img_new>' into" +
            " the command line.\n";
  }
}
