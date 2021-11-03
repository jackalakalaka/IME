package model;

import java.util.HashMap;

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
  public HashMap<Color, Double> getLuma() {
    HashMap<Color, Double> mapOfValue = new HashMap<>();
    mapOfValue.put(Color.Red, value * 1.0);
    mapOfValue.put(Color.Green, value * 1.0);
    mapOfValue.put(Color.Blue, value * 1.0);
    return mapOfValue;
  }

  @Override
  public int getValue() {
    return this.value;
  }

  @Override
  public int getMaxValue() {
    return 255;
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
