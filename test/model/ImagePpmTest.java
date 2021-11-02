package model;

import org.junit.Before;
import org.junit.Test;

import model.funcobjs.CommandsBlue;
import model.funcobjs.ConvertByHorizontal;
import model.funcobjs.CommandsValue;

import static org.junit.Assert.assertEquals;

/**
 * Tests ImagePpm class's utility methods. Also checks that null cannot be input to methods,
 * including the constructor.
 */
public class ImagePpmTest {
  private final IPixel[][] mockPixels = new IPixel[2][2];
  private final IPixel[][] mockPixelsWide = new IPixel[2][3];
  private final Image modelOne = new ImagePpm(255, mockPixels);
  private final Image modelTwo = new ImagePpm(255, mockPixelsWide);
  private final IPixel pixelZero = new MockPixel();
  private final IPixel pixelOne = new MockPixel(1);
  private final IPixel pixelTwo = new MockPixel(2);
  private final IPixel pixelThree = new MockPixel(3);
  private final IPixel pixelFour = new MockPixel(4);

  /**
   * Initialize data.
   */
  @Before
  public void setUp() {
    this.mockPixels[0][0] = pixelOne;
    this.mockPixels[1][0] = pixelThree;
    this.mockPixels[0][1] = pixelTwo;
    this.mockPixels[1][1] = pixelFour;
    this.mockPixelsWide[0][0] = pixelOne;
    this.mockPixelsWide[1][0] = pixelThree;
    this.mockPixelsWide[0][1] = pixelTwo;
    this.mockPixelsWide[1][1] = pixelFour;
    this.mockPixelsWide[0][2] = pixelZero;
    this.mockPixelsWide[1][2] = pixelZero;
  }

  /**
   * Test an invalid null filepath argument to the 1-arg constructor.
   */
  @Test(expected = NullPointerException.class)
  public void constructor1ArgNullFilepath() {
    Image modelOne = new ImagePpm(null);
  }

  /**
   * Test an invalid filepath argument to the 1-arg constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void constructor1ArgInvalidFilepath() {
    Image modelOne = new ImagePpm("hehehehehe");
  }

  /**
   * Test an invalid null pixel array input to the 2-arg constructor.
   */
  @Test(expected = NullPointerException.class)
  public void constructor2ArgPixelsNull() throws IllegalArgumentException {
    Image modelOne = new ImagePpm(255, null);
  }

  //Test all the getter functions.
  @Test
  public void testGetters() {
    assertEquals(2, this.modelOne.getHeight());
    assertEquals(3, this.modelTwo.getWidth());
    assertEquals(255, this.modelOne.getMaxValue());
  }

  //Test to see if the getPixelAt function works consistently.
  @Test
  public void testPixelFetch() {
    assertEquals(this.pixelOne, this.modelOne.getPixelAt(0, 0));
    assertEquals(this.pixelTwo, this.modelOne.getPixelAt(0, 1));
    Image horzFlipOne = new ConvertByHorizontal().apply(this.modelOne);
    assertEquals(this.pixelOne, horzFlipOne.getPixelAt(0, 1));
    assertEquals(this.pixelTwo, horzFlipOne.getPixelAt(0, 0));
  }

  /**
   * Test an invalid null command argument to convertToViz method.
   */
  @Test(expected = NullPointerException.class)
  public void convertToVizNullCmd() {
    modelOne.convertToViz(null);
  }

  /**
   * Test that a command function object can be summoned.
   */
  @Test
  public void testCommandReceive() {
    Image image = this.modelOne.convertToViz(new CommandsValue());
    assertEquals(1, image.getPixelAt(0, 0).getValue());
    Image blue = this.modelOne.convertToViz(new CommandsBlue());
    assertEquals(1,blue.getPixelAt(0,0).getValue());
  }

  /**
   * Test the changeBrightness method.
   */
  @Test
  public void testChangeBrightness() {
    Image same = this.modelTwo.changeBrightness(0);
    Image bright = this.modelTwo.changeBrightness(10);
    Image dark = this.modelTwo.changeBrightness(-1);

    assertEquals(1, same.getPixelAt(0, 0).getValue());
    assertEquals(11, bright.getPixelAt(0, 0).getValue());
    assertEquals(0, dark.getPixelAt(0, 0).getValue());
  }

  /**
   * Test an invalid null filepath argument to saveImageToFile method.
   */
  @Test(expected = NullPointerException.class)
  public void saveImageToFileNullFilepath() {
    modelOne.saveImageToFile(null);
  }
}