package model;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import factory.ImageFactory;
import model.FuncObjs.ConvertByHorizontalVertical;

import static org.junit.Assert.assertEquals;

public class IMEPpmTest extends IMEModelTest {
  ImageFactory imgFac;
  Image koala1;

  public IMEPpmTest() throws FileNotFoundException {
    this.imgFac = new ImageFactory();
    this.koala1 = this.imgFac.createImage("ppm", "koala1", "Koala.ppm");
  }

  @Test
  public void testSaveImage() throws IOException {
    Image darker = koala1.changeBrightness(-150, "darker-koala1");
    Image lost = darker.changeBrightness(150, "info-lost-koala1");
    lost.saveImageToFile("LostKoala");
  }

  @Test
  public void testGetColors() {
    int red = this.koala1.getPixelAt(0, 0).getColors().get(Pixel.Color.Red);
    int green = this.koala1.getPixelAt(0, 0).getColors().get(Pixel.Color.Green);
    int blue = this.koala1.getPixelAt(0, 0).getColors().get(Pixel.Color.Blue);
    assertEquals(101, red);
    assertEquals(58, green);
    assertEquals(90, blue);
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
    Image rotated = koala1.convertToViz(new ConvertByHorizontalVertical("rotated-koala1"));
    rotated.saveImageToFile("rotate");
  }

}