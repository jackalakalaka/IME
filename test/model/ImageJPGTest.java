package model;

import org.junit.Test;

import static org.junit.Assert.*;

public class ImageJPGTest {

  @Test
  public void testLoadingJPG() {
    Image dogJPG = new ImageJPG("res/JPGgoat.jpg");
    Image dogPPM = new ImagePPM("res/PPMgoat.ppm");
    assertEquals(dogJPG.getHeight(),dogPPM.getHeight());
    assertEquals(dogJPG.getWidth(),dogPPM.getWidth());
    for (int i = 0; i < dogJPG.getHeight(); i++) {
      for (int j = 0; j < dogJPG.getWidth(); j++) {
        assertEquals(dogJPG.getPixelAt(i,j).getColors().get(IPixel.Color.Red),
                dogPPM.getPixelAt(i,j).getColors().get(IPixel.Color.Red),20);
        assertEquals(dogJPG.getPixelAt(i,j).getColors().get(IPixel.Color.Green),
                dogPPM.getPixelAt(i,j).getColors().get(IPixel.Color.Green),20);
        assertEquals(dogJPG.getPixelAt(i,j).getColors().get(IPixel.Color.Blue),
                dogPPM.getPixelAt(i,j).getColors().get(IPixel.Color.Blue),20);

      }
    }
  }


}