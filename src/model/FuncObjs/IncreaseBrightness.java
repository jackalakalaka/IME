package model.FuncObjs;

import java.util.ArrayList;
import java.util.HashMap;

import model.Pixel;
import model.iPixel;

/**
 * A function object for changing the brightness of a pixel.
 */
public class IncreaseBrightness extends ACommandsMultiple {
  private final int brightness;

  public IncreaseBrightness(int brightness) {
    this.brightness = brightness;
  }

  @Override
  protected ArrayList<Double> getMultiple(iPixel originalPixel) {
    HashMap<iPixel.Color, Integer> colors = originalPixel.getColors();
    ArrayList<Double> changed = new ArrayList<>();
    changed.add(colors.get(Pixel.Color.Red) + brightness * 1.0);
    changed.add(colors.get(Pixel.Color.Green) + brightness * 1.0);
    changed.add(colors.get(Pixel.Color.Blue) + brightness * 1.0);
    return changed;
  }

  @Override
  public String giveSignature() {
    return "- To change brightness of the img, type 'brightness" +
            "<img_former> <integer_change> <img_new>'.\n";
  }
}
