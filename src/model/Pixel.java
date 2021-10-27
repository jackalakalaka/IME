package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * This class represents an immutable pixel.
 */
public class Pixel implements iPixel {
  private final int redValue;
  private final int greenValue;
  private final int blueValue;
  private final int maxValue;

  public Pixel(int maxValue, int grey) {
    this(maxValue, grey, grey, grey);
  }

  public Pixel(int maxValue, int red, int green, int blue) {
    this.maxValue = maxValue;
    this.redValue = red;
    this.blueValue = green;
    this.greenValue = blue;
  }

  @Override
  public int getIntensity() {
    return (redValue + blueValue + greenValue) / 3;
  }

  @Override
  public HashMap<Color, Double> getLuma() {
    double totalValue = redValue + greenValue + blueValue;
    HashMap<Color, Double> pixelColors = new HashMap<>();
    pixelColors.put(Color.Red, this.redValue / totalValue);
    pixelColors.put(Color.Green, this.greenValue / totalValue);
    pixelColors.put(Color.Blue, this.blueValue / totalValue);
    return pixelColors;
  }









  /**
   * Returns the value of the pixel.
   *
   * @return An integer that represents the maximum value of the pixel.
   */
  @Override
  public int getValue() {
//    List<Integer> rgbVals =
    //this.getColors().values().stream().max(Comparator.);
    return 0;
  }








  @Override
  public HashMap<Color, Integer> getColors() {
    HashMap<Color, Integer> pixelColors = new HashMap<>();
    pixelColors.put(Color.Red, this.redValue);
    pixelColors.put(Color.Green, this.greenValue);
    pixelColors.put(Color.Blue, this.blueValue);
    return pixelColors;
  }
}
