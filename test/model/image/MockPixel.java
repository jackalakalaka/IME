package model.image;

import java.util.HashMap;

import model.image.IPixel;

/**
 * Simulates a pixel for the more simplistic testing of Image and IMEModel classes. Simplifies pixel
 * construction and assertion.
 */
public class MockPixel implements IPixel {
  private final int value;

  public MockPixel() {
    this(0);
  }

  public MockPixel(int value) {
    this.value = value;
  }

  @Override
  public int getIntensity() {
    return this.value;
  }

  @Override
  public Double getLuma() {
    return value * 1.0;
  }

  @Override
  public int getValue() {
    return this.value;
  }

  @Override
  public HashMap<Color, Integer> getColors() {
    HashMap<Color, Integer> mapOfValue = new HashMap<>();
    mapOfValue.put(Color.Red, value);
    mapOfValue.put(Color.Green, value);
    mapOfValue.put(Color.Blue, value);
    return mapOfValue;
  }
}
