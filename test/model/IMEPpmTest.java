package model;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import model.FuncObjs.ConvertByHorizontalVertical;

import static org.junit.Assert.assertEquals;

public class IMEPpmTest extends IMEModelTest {

  Image koala1;

  @Before
  public void setUp() {
    this.koala1 = new ImagePpm("koalaoriginal.ppm");
  }

  @Test
  public void testSaveImage() throws IOException {
    Image darker = koala1.changeBrightness(-150);
    Image lost = darker.changeBrightness(150);
    lost.saveImageToFile("LostKoala");
  }

  @Test
  public void testGetColors() {
    int red = this.koala1.getPixelAt(0, 0).getColors().get(Pixel.Color.Red);
    int green = this.koala1.getPixelAt(0, 0).getColors().get(Pixel.Color.Green);
    int blue = this.koala1.getPixelAt(0, 0).getColors().get(Pixel.Color.Blue);
    assertEquals(101, red);
    assertEquals(90, green);
    assertEquals(58, blue);
  }

  @Test
  public void testLuma() {
    Pixel pixel = new Pixel(255, 50, 100, 200);
    HashMap<Pixel.Color, Double> luma = pixel.getLuma();
    assertEquals(0.28571, luma.get(Pixel.Color.Green), 0.0001);
    assertEquals(0.57143, luma.get(Pixel.Color.Blue), 0.0001);
    assertEquals(0.14286, luma.get(Pixel.Color.Red), 0.0001);

  }

  @Test
  public void testDimensions() {
    Integer[][] testArray = new Integer[3][7];
    assertEquals(3, testArray.length);
    assertEquals(7, testArray[0].length);
  }

  @Test
  public void testRotation() throws IOException {
    Image rotated = koala1.convertToViz(new ConvertByHorizontalVertical());
    rotated.saveImageToFile("rotate");
  }

}