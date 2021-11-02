package model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PixelTest {
  private static ColorsHashmapFactory colorsHashmapFac = new ColorsHashmapFactory();
  private static Random rand = new Random();

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

  /**
   * Test invalid max value arg Pixel class's 4-arg constructor.
   */
  @Test
  public void constructor4ArgInvalidMaxValue() {
    String testingMsg = "Testing construction of Pixel w/ maxValue of %1$s: ";
    int[] maxVals = new int[]{-75, -1, 0};
    boolean threwIllegalArgumentException = false;

    for(int maxVal:maxVals) {
      try {
        Pixel pA = new Pixel(maxVal, rand.nextInt(), rand.nextInt(), rand.nextInt());
      } catch (IllegalArgumentException iAE) {
        threwIllegalArgumentException = true;
      }
      assertEquals(String.format(testingMsg, maxVal), true, threwIllegalArgumentException);
      threwIllegalArgumentException = false;
    }
  }

  /**
   * Test invalid max value arg Pixel class's 1-arg constructor.
   */
  @Test
  public void constructor1ArgInvalidMaxValue() {
    String testingMsg = "Testing construction of Pixel w/ maxValue of %1$s: ";
    int[] maxVals = new int[]{-75, -1, 0};
    boolean threwIllegalArgumentException = false;

    for(int invalidMaxVal:maxVals) {
      try {
        Pixel pA = new Pixel(invalidMaxVal, rand.nextInt());
      } catch (IllegalArgumentException iAE) {
        threwIllegalArgumentException = true;
      }
      assertEquals(String.format(testingMsg, invalidMaxVal), true, threwIllegalArgumentException);
      threwIllegalArgumentException = false;
    }
  }

  /**
   * Test invalid, low color args for each color to Pixel class's 4-arg constructor.
   */
  @Test
  public void constructor4ArgInvalidLowColors() {
    String testingMsg = "Testing construction of Pixel w/ maxValue of %1$s & color values of " +
            "R: %2$s, G: %3$s, B: %4$s:";
    int maxVal = 255;
    int[] invalidColorVals = new int[]{-75, -1};
    boolean threwIllegalArgumentException = false;
    HashMap<iPixel.Color, Integer> colorsToVals = new HashMap<>();
    for (iPixel.Color color : iPixel.Color.values()) {
      colorsToVals.put(color, 0);
    }

    for(int invalidColorVal:invalidColorVals) {
      // For each color
      for (Map.Entry<iPixel.Color, Integer> colorAndVal : colorsToVals.entrySet()) {
        iPixel.Color color = colorAndVal.getKey();

        // Set random valid color values
        colorsToVals.replaceAll((a, b) -> rand.nextInt() % maxVal);
        // Set map's value of current iteration's color to invalidColorVal
        colorsToVals.replace(color, invalidColorVal);

        int redVal = colorsToVals.get(iPixel.Color.Red);
        int blueVal = colorsToVals.get(iPixel.Color.Blue);
        int greenVal = colorsToVals.get(iPixel.Color.Green);
        // Test pixel construction where current iteration's color value is invalidColorVal
        try {
          Pixel pA = new Pixel(maxVal, redVal, blueVal, greenVal);
        } catch (IllegalArgumentException iAE) {
          threwIllegalArgumentException = true;
        }
        assertEquals(String.format(testingMsg, maxVal, redVal, blueVal, greenVal),
                true,
                threwIllegalArgumentException);

        threwIllegalArgumentException = false;
      }
    }
  }

  /**
   * Test invalid, low color arg to Pixel class's 1-arg constructor.
   */
  @Test
  public void constructor1ArgInvalidLowColor() {
    String testingMsg = "Testing construction of Pixel w/ maxValue of %1$s & color values of " +
            "R: %2$s, G: %2$s, B: %2$s:";
    int maxVal = 255;
    int[] invalidColorVals = new int[]{-75, -1};
    boolean threwIllegalArgumentException = false;

    for(int invalidColorVal:invalidColorVals) {
        try {
          Pixel pA = new Pixel(maxVal, invalidColorVal);
        } catch (IllegalArgumentException iAE) {
          threwIllegalArgumentException = true;
        }
        assertEquals(String.format(testingMsg, maxVal, invalidColorVal),
                true,
                threwIllegalArgumentException);

        threwIllegalArgumentException = false;
    }
  }

  /**
   * Test invalid, high color args for each color to Pixel class's 4-arg constructor.
   */
  @Test
  public void constructor4ArgInvalidHighColors() {
    String testingMsg = "Testing construction of Pixel w/ maxValue of %1$s & color values of " +
            "R: %2$s, G: %3$s, B: %4$s:";
    int maxVal = 255;
    int[] invalidColorVals = new int[]{
            maxVal + 1,
            maxVal + rand.nextInt()};
    boolean threwIllegalArgumentException = false;
    HashMap<iPixel.Color, Integer> colorsToVals = new HashMap<>();
    for (iPixel.Color color : iPixel.Color.values()) {
      colorsToVals.put(color, 0);
    }

    for(int invalidColorVal:invalidColorVals) {
      // For each color
      for (Map.Entry<iPixel.Color, Integer> colorAndVal : colorsToVals.entrySet()) {
        iPixel.Color color = colorAndVal.getKey();

        // Set random valid color values
        colorsToVals.replaceAll((a, b) -> rand.nextInt() % maxVal);
        // Set map's value of current iteration's color to invalidColorVal
        colorsToVals.replace(color, invalidColorVal);

        int redVal = colorsToVals.get(iPixel.Color.Red);
        int blueVal = colorsToVals.get(iPixel.Color.Blue);
        int greenVal = colorsToVals.get(iPixel.Color.Green);
        // Test pixel construction where current iteration's color value is invalidColorVal
        try {
          Pixel pA = new Pixel(maxVal, redVal, blueVal, greenVal);
        } catch (IllegalArgumentException iAE) {
          threwIllegalArgumentException = true;
        }
        assertTrue(String.format(testingMsg, maxVal, redVal, blueVal, greenVal),
                threwIllegalArgumentException);

        threwIllegalArgumentException = false;
      }
    }
  }

  /**
   * Test invalid, high color arg to Pixel class's 1-arg constructor.
   */
  @Test
  public void constructor1ArgInvalidHighColor() {
    String testingMsg = "Testing construction of Pixel w/ maxValue of %1$s & color values of " +
            "R: %2$s, G: %2$s, B: %2$s:";
    int maxVal = 255;
    int[] invalidColorVals = new int[]{
            maxVal + 1,
            maxVal + rand.nextInt()};
    boolean threwIllegalArgumentException = false;

    for(int invalidColorVal:invalidColorVals) {
      try {
        Pixel pA = new Pixel(maxVal, invalidColorVal);
      } catch (IllegalArgumentException iAE) {
        threwIllegalArgumentException = true;
      }
      assertTrue(String.format(testingMsg, maxVal, invalidColorVal),
              threwIllegalArgumentException);

      threwIllegalArgumentException = false;
    }
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

  /**
   * Test default behavior of Pixel class's getIntensity method.
   */
  @Test
  public void getIntensity() {
    assertEquals(0, p1.getIntensity());
    assertEquals(255, p2.getIntensity());
    assertEquals(0, p3.getIntensity());
    assertEquals((0 + 9129 + 127) / 3, p4.getIntensity());
    assertEquals((120 + 30 + 202) / 3, p5.getIntensity());
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