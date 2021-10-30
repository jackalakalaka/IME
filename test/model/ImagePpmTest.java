package model;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import model.FuncObjs.ConvertByHorizontalVertical;

import static org.junit.Assert.*;

public class ImagePpmTest extends ImageModelTest {

  iImage koala = new ImagePpm("Koala.ppm");

  public ImagePpmTest() throws FileNotFoundException {
  }

  @Test
  public void testSaveImage() throws IOException {
    iImage darker = koala.changeBrightness(-150);
    iImage lost = darker.changeBrightness(150);
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
  public void testLuma() {
    Pixel pixel = new Pixel(255,50,100,200);
    HashMap<iPixel.Color, Double> luma = pixel.getLuma();
    assertEquals(0.28571,luma.get(iPixel.Color.Green),0.0001);
    assertEquals(0.57143,luma.get(iPixel.Color.Blue),0.0001);
    assertEquals(0.14286,luma.get(iPixel.Color.Red),0.0001);

  }

  @Test
  public void testDimensions() {
    Integer[][] testArray = new Integer[3][7];
    assertEquals(3,testArray.length);
    assertEquals(7,testArray[0].length);
  }

  @Test
  public void testRotation() throws IOException {
    iImage rotated = koala.convertToViz(new ConvertByHorizontalVertical());
    rotated.saveImageToFile("rotate");
  }

}