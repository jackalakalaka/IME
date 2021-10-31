package model;

import org.junit.Before;
import org.junit.Test;

import model.FuncObjs.ConvertByHorizontal;
import model.FuncObjs.ConvertFromBlue;
import model.FuncObjs.ConvertFromValue;
import model.FuncObjs.IConvertFrom;

import static org.junit.Assert.assertEquals;

public class ImagePpmTest {

  iPixel[][] mockPixels = new iPixel[2][2];
  iPixel[][] mockPixelsWide = new iPixel[2][3];
  Image modelOne = new ImagePpm("modelOne", 255, mockPixels);
  Image modelTwo = new ImagePpm("modelTwo", 255, mockPixelsWide);
  iPixel pixelZero = new MockPixel();
  iPixel pixelOne = new MockPixel(1);
  iPixel pixelTwo = new MockPixel(2);
  iPixel pixelThree = new MockPixel(3);
  iPixel pixelFour = new MockPixel(4);

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

  //Test all the getter functions.
  @Test
  public void testGetters() {
    assertEquals("modelOne", modelOne.getName());
    assertEquals("modelTwo", modelTwo.getName());
    assertEquals(2, this.modelOne.getHeight());
    assertEquals(3, this.modelTwo.getWidth());
    assertEquals(255, this.modelOne.getMaxValue());
  }

  //Test to see if the getPixelAt function works consistently.
  @Test
  public void testPixelFetch() {
    assertEquals(this.pixelOne, this.modelOne.getPixelAt(0, 0));
    assertEquals(this.pixelTwo, this.modelOne.getPixelAt(0, 1));
    Image rotateOne = new ConvertByHorizontal("rotated").apply(this.modelOne);
    assertEquals(this.pixelOne, rotateOne.getPixelAt(0, 1));
    assertEquals(this.pixelTwo, rotateOne.getPixelAt(0, 0));
  }

  @Test
  public void testCommandReceive() {
    Image blue = this.modelOne.convertToViz(new ConvertFromValue("model"));
    assertEquals(1,blue.getPixelAt(0,0).getValue());
  }

  @Test
  public void testChangeBrightness() {
    Image bright = this.modelTwo.changeBrightness(10,"model");
    assertEquals(11,bright.getPixelAt(0,0).getValue());
  }

}