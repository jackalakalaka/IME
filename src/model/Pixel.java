package model;

import java.util.HashMap;

/**
 * This class represents an immutable pixel.
 */
public class Pixel implements iPixel {
  private final int redValue;
  private final int blueValue;
  private final int greenValue;
  private final int maxValue;

  public Pixel(int maxValue, int grey) {
    this(maxValue, grey, grey, grey);
  }

  public Pixel(int maxValue, int red, int blue, int green) {
    if (red > maxValue || blue > maxValue || green > maxValue
            || red < 0 || blue < 0 || green < 0) {
      throw new IllegalArgumentException("Color values cannot exceed Maximum value " +
              "or be less than zero.");
    }
    this.maxValue = maxValue;
    this.redValue = red;
    this.blueValue = blue;
    this.greenValue = green;
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

  @Override
  public HashMap<Color, Integer> getColors() {
    HashMap<Color, Integer> pixelColors = new HashMap<>();
    pixelColors.put(Color.Red, this.redValue);
    pixelColors.put(Color.Green, this.greenValue);
    pixelColors.put(Color.Blue, this.blueValue);
    return pixelColors;
  }
}
