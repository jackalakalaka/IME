package model;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.*;

public class ImageModelImplTest {

  ImageModel koala = new ImageModelImpl("Koala.ppm");

  public ImageModelImplTest() throws FileNotFoundException {
  }

  @Test
  public void testSaveImage() throws IOException {
    ImageModel darker = koala.changeBrightness(-150);
    ImageModel lost = darker.changeBrightness(150);
    lost.saveImageToFile("LostKoala");
  }

  @Test
  public void testGetColors() {
    int red = this.koala.getPixelAt(0,0).getColors().get(iPixel.Color.Red);
    int green = this.koala.getPixelAt(0,0).getColors().get(iPixel.Color.Green);
    int blue = this.koala.getPixelAt(0,0).getColors().get(iPixel.Color.Blue);
    assertEquals(101, red);
    assertEquals(58, green);
    assertEquals(90, blue);
  }

  @Test
  public void testDimensions() {
    Integer[][] testArray = new Integer[3][7];
    assertEquals(3,testArray.length);
    assertEquals(7,testArray[0].length);
  }

}