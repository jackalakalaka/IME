package model;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class PixelTest {
  private static ColorsHashmapFactory colorsHashmapFac = new ColorsHashmapFactory();
  /**
   * {@link #constructor4ArgDefault()} data.
   */
  private static final Pixel p1 = new Pixel(255, 0, 0, 0);
  private static final Pixel p2 = new Pixel(255, 255, 255, 255);
  private static final Pixel p3 = new Pixel(1, 1, 0, 1);
  private static final Pixel p4 = new Pixel(100000, 0, 9129, 127);
  private static final Pixel p5 = new Pixel(255, 120, 30, 202);
  /**
   * {@link #constructor1ArgDefault()} data.
   */
  private static final Pixel p6 = new Pixel(255, 0);
  private static final Pixel p7 = new Pixel(255, 255);
  private static final Pixel p8 = new Pixel(1, 1);
  private static final Pixel p9 = new Pixel(100000, 0);
  private static final Pixel p10 = new Pixel(255, 122);

  //TODO

  /**
   * Test default constructor of Pixel class.
   */
  @Test
  public void constructorInvalidMaxValue() {
    Pixel pA = new Pixel(-75, 120, 30, 202);
  }

  /**
   * Test default behavior of Pixel class's 4-arg constructor.
   */
  @Test
  public void constructor4ArgDefault() {
    HashMap<Pixel.Color, Integer> p1Colors =
            colorsHashmapFac.createColorsHashmap(0, 0, 0);
    HashMap<Pixel.Color, Integer> p2Colors =
            colorsHashmapFac.createColorsHashmap(255, 255, 255);
    HashMap<Pixel.Color, Integer> p3Colors =
            colorsHashmapFac.createColorsHashmap(1, 0, 1);
    HashMap<Pixel.Color, Integer> p4Colors =
            colorsHashmapFac.createColorsHashmap(0, 9129, 127);
    HashMap<Pixel.Color, Integer> p5Colors =
            colorsHashmapFac.createColorsHashmap(120, 30, 202);

    assertEquals(p1Colors, p1.getColors());
    assertEquals(p2Colors, p2.getColors());
    assertEquals(p3Colors, p3.getColors());
    assertEquals(p4Colors, p4.getColors());
    assertEquals(p5Colors, p5.getColors());
  }

  /**
   * Test default behavior of Pixel class's 1-arg constructor.
   */
  @Test
  public void constructor1ArgDefault() {
    HashMap<Pixel.Color, Integer> p6Colors =
            colorsHashmapFac.createColorsHashmap(0, 0, 0);
    HashMap<Pixel.Color, Integer> p7Colors =
            colorsHashmapFac.createColorsHashmap(255, 255, 255);
    HashMap<Pixel.Color, Integer> p8Colors =
            colorsHashmapFac.createColorsHashmap(1, 1, 1);
    HashMap<Pixel.Color, Integer> p9Colors =
            colorsHashmapFac.createColorsHashmap(0, 0, 0);
    HashMap<Pixel.Color, Integer> p10Colors =
            colorsHashmapFac.createColorsHashmap(122, 122, 122);

    assertEquals(p6Colors, p6.getColors());
    assertEquals(p7Colors, p7.getColors());
    assertEquals(p8Colors, p8.getColors());
    assertEquals(p9Colors, p9.getColors());
    assertEquals(p10Colors, p10.getColors());
  }

  @Test
  public void getIntensity() {
  }

  @Test
  public void getLuma() {
  }

  @Test
  public void getValue() {
  }

  @Test
  public void getColors() {
  }

  public static class ColorsHashmapFactory {
    public HashMap<Pixel.Color, Integer> createColorsHashmap(int red, int green, int blue) {
      HashMap<Pixel.Color, Integer> hashmap = new HashMap<>();
      hashmap.put(Pixel.Color.Red, red);
      hashmap.put(Pixel.Color.Green, green);
      hashmap.put(Pixel.Color.Blue, blue);
      return hashmap;
    }
  }
}