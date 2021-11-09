package model;

import java.util.HashMap;

/**
 * An interface for pixels of an image.
 */
public interface IPixel {

  /**
   * Returns the intensity of the pixel.
   *
   * @return An integer that represents the overall intensity of the pixel.
   */
  int getIntensity();

  /**
   * Gets the luma of the pixel.
   *
   * @return HashMap of the colors and their respective luma values.
   */
  Double getLuma();

  /**
   * Returns the value of the pixel.
   *
   * @return An integer that represents the maximum value of the pixel.
   */
  int getValue();

  /**
   * Enums for representing the three primary colors.
   */
  enum Color { Red, Green, Blue }

  /**
   * Returns the HashMap for the pixel's colors and their values.
   *
   * @return A HashMap of Colors and their values.
   */
  HashMap<Color, Integer> getColors();

}
