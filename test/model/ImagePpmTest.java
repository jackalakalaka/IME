package model;

import org.junit.Before;

public class ImagePpmTest {

  @Before
  public void setUp() {
    iPixel pixelZero = new MockPixel();
    iPixel pixelOne = new MockPixel(1);
    iPixel pixelTwo = new MockPixel(2);
    iPixel pixelThree = new MockPixel(3);
    iPixel pixelFour = new MockPixel(4);
    iPixel[][] mockPixels = new iPixel[2][2];
    mockPixels[0][0] = pixelZero;

  }

//  String getName();
//
//  int getHeight();
//
//  int getWidth();
//
//  int getMaxValue();
//
//  Pixel getPixelAt(int row, int col);
//
//  Image convertToViz(IConvertFrom cmd);
//
//  Image changeBrightness(int change, String newName);

}