package model.FuncObjs;

import java.util.ArrayList;
import java.util.HashMap;

import model.Pixel;
import model.iPixel;

public class IncreaseBrightness extends ACommandsMultiple {
  private final int brightness;

  public IncreaseBrightness(int brightness) {
    this.brightness = brightness;
  }

  @Override
  protected ArrayList<Double> getMultiple(iPixel p) {
    HashMap<iPixel.Color, Integer> colors = p.getColors();
    ArrayList<Double> changed = new ArrayList<>();
    changed.add(colors.get(Pixel.Color.Red) + brightness * 1.0);
    changed.add(colors.get(Pixel.Color.Green) + brightness * 1.0);
    changed.add(colors.get(Pixel.Color.Blue) + brightness * 1.0);
    return changed;
  }

  @Override
  public String giveSignature() {
    return "- To chg brightness of the img, type 'brightness <value_chg>" +
            "<img_former> <img_new>'.\n";
  }
}
