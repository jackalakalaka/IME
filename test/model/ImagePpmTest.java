package model;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import model.FuncObjs.CommandsBlue;
import model.FuncObjs.CommandsGreen;
import model.FuncObjs.CommandsIntensity;
import model.FuncObjs.CommandsRed;
import model.FuncObjs.ConvertByHorizontal;
import model.FuncObjs.CommandsValue;

import static org.junit.Assert.assertEquals;

public class ImagePpmTest {
  private final iPixel[][] mockPixels = new iPixel[2][2];
  private final iPixel[][] mockPixelsWide = new iPixel[2][3];
  private final Image modelOne = new ImagePpm(255, mockPixels);
  private final Image modelTwo = new ImagePpm(255, mockPixelsWide);
  private final iPixel pixelZero = new MockPixel();
  private final iPixel pixelOne = new MockPixel(1);
  private final iPixel pixelTwo = new MockPixel(2);
  private final iPixel pixelThree = new MockPixel(3);
  private final iPixel pixelFour = new MockPixel(4);

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
   * Test an invalid filepath argument to the 1-arg constructor.
   */
  @Test(expected = FileNotFoundException.class)
  public void constructor1ArgInvalidFilepath() throws FileNotFoundException {
    Image modelOne = new ImagePpm("hehehehehe");
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
   * Test that a command function object is able to be summoned.
   */
  @Test
  public void testCommandReceive() {
    Image red = this.modelOne.convertToViz(new CommandsRed());
    Image blue = this.modelOne.convertToViz(new CommandsBlue());
    Image green = this.modelOne.convertToViz(new CommandsGreen());
    Image value = this.modelOne.convertToViz(new CommandsValue());
    Image intensity = this.modelOne.convertToViz(new CommandsIntensity());

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

    assertEquals(1, same.getPixelAt(0,0).getValue());
    assertEquals(11, bright.getPixelAt(0,0).getValue());
    assertEquals(0, dark.getPixelAt(0,0).getValue());
  }
}