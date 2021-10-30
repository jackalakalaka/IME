package model.FuncObjs;

import java.util.ArrayList;
import java.util.HashMap;

import model.iPixel;

public class ConvertFromLuma extends AConvertFromMultiple{
  @Override
  protected ArrayList<Double> getMultiple(iPixel p) {
    HashMap<iPixel.Color, Double> luma = p.getLuma();
    ArrayList<Double> lumaDoubles = new ArrayList<>();
    lumaDoubles.add(luma.get(iPixel.Color.Red));
    lumaDoubles.add(luma.get(iPixel.Color.Green));
    lumaDoubles.add(luma.get(iPixel.Color.Blue));
    return lumaDoubles;
  }
}
