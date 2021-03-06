package model.image;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 * This class represents an immutable pixel.
 */
public class Pixel implements IPixel {
  private final int redValue;
  private final int greenValue;
  private final int blueValue;

  /**
   * Constructor which takes in the max value and grey value and makes a pixel.
   *
   * @param maxValue The maximum value for the pixel.
   * @param grey     The new value that will be shared by all the colors.
   */
  public Pixel(int maxValue, int grey) {
    this(maxValue, grey, grey, grey);
  }

  /**
   * Constructor which takes in a max value and all color fields.
   *
   * @param maxValue The maximum value for the pixel.
   * @param red      The value of red in the pixel.
   * @param green    The value of green in the pixel.
   * @param blue     The value of blue in the pixel.
   */
  public Pixel(int maxValue, int red, int green, int blue) {
    if (maxValue < 1) {
      throw new IllegalArgumentException("Upper limit of RGB values must be at least 1.");
    }
    if (red < 0 || green < 0 || blue < 0) {
      throw new IllegalArgumentException("Pixel cannot be constructed from negative RBG values.");
    }
    if (red > maxValue || green > maxValue || blue > maxValue) {
      throw new IllegalArgumentException("Pixel cannot be constructed from RBG values greater " +
              "than the upper limit.");
    }

    this.redValue = red;
    this.blueValue = blue;
    this.greenValue = green;
  }

  @Override
  public int getIntensity() {
    return (redValue + blueValue + greenValue) / 3;
  }

  @Override
  public Double getLuma() {
    double totalValue = this.redValue + this.blueValue + this.greenValue;
    if (totalValue == 0) {
      return 0.0;
    } else {
      return (this.redValue * this.redValue +
              this.greenValue * this.greenValue +
              this.blueValue * this.blueValue) / totalValue;
    }
  }

  @Override
  public int getValue() {
    Integer[] values = {redValue, greenValue, blueValue};
    return Collections.max(Arrays.asList(values));
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
