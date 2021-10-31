package model;

import java.util.HashMap;

public class MockPixel implements iPixel{
  private final int value;

  public MockPixel() {
    this(0);
  }

  public MockPixel(int value){
    this.value = value;
  }

  @Override
  public int getIntensity() {
    return this.value;
  }

  @Override
  public HashMap<Color, Double> getLuma() {
    HashMap<Color,Double> mapOfValue = new HashMap<>();
    mapOfValue.put(Color.Red,value*1.0);
    mapOfValue.put(Color.Green,value*1.0);
    mapOfValue.put(Color.Blue,value*1.0);
    return mapOfValue;
  }

  @Override
  public int getValue() {
    return this.value;
  }

  @Override
  public HashMap<Color, Integer> getColors() {
    HashMap<Color,Integer> mapOfValue = new HashMap<>();
    mapOfValue.put(Color.Red,value);
    mapOfValue.put(Color.Green,value);
    mapOfValue.put(Color.Blue,value);
    return mapOfValue;
  }
}
