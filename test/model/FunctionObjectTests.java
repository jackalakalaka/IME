package model;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class for function objects.
 */
public class FunctionObjectTests {
  IMEModel model = new IMEModelImpl();
  Image image = new ImagePPM("res/onePixelImage.ppm");
  Image four;
  Image jpg;
  IPixel[][] fourPixels = new IPixel[2][2];
  int red;
  int green;
  int blue;
  private final IMEModelImpl jpgModel = new IMEModelImpl();

  @Before
  public void setUp() {
    this.model.addImage("pixel",this.image);
    this.red = this.image.getPixelAt(0,0).getColors().get(IPixel.Color.Red);
    this.green = this.image.getPixelAt(0,0).getColors().get(IPixel.Color.Green);
    this.blue = this.image.getPixelAt(0,0).getColors().get(IPixel.Color.Blue);
    this.fourPixels[0][0] = new Pixel(255,50);
    this.fourPixels[0][1] = new Pixel(255,100);
    this.fourPixels[1][0] = new Pixel(255,150);
    this.fourPixels[1][1] = new Pixel(255,250);
    this.four = new ImagePPM(255, this.fourPixels );
    this.jpg = new ImageJPG(255,this.fourPixels);
    this.model.addImage("four",this.four);
    this.jpgModel.addImage("jpg",this.jpg);
  }

  @Test
  public void testCommandBlue() {
    this.model.applyCommand("blue","pixel","blue");
    Image blueImage = this.model.getImageFromModel("blue");
    int newRed = blueImage.getPixelAt(0,0).getColors().get(IPixel.Color.Red);
    int newGreen = blueImage.getPixelAt(0,0).getColors().get(IPixel.Color.Green);
    assertTrue(this.model.containsImage("blue"));
    assertEquals(this.blue,blueImage.getPixelAt(0,0).getValue());
    assertEquals(this.blue,blueImage.getPixelAt(0,0).getIntensity());
    assertEquals(this.blue,newRed);
    assertEquals(this.blue,newGreen);
  }

  @Test
  public void testCommandRed() {
    this.model.applyCommand("red","pixel","red");
    Image redImage = this.model.getImageFromModel("red");
    int newBlue = redImage.getPixelAt(0,0).getColors().get(IPixel.Color.Blue);
    int newGreen = redImage.getPixelAt(0,0).getColors().get(IPixel.Color.Green);
    assertTrue(this.model.containsImage("red"));
    assertEquals(this.red,redImage.getPixelAt(0,0).getValue());
    assertEquals(this.red,redImage.getPixelAt(0,0).getIntensity());
    assertEquals(this.red,newBlue);
    assertEquals(this.red,newGreen);
  }

  @Test
  public void testCommandGreen() {
    this.model.applyCommand("green","pixel","green");
    Image greenImage = this.model.getImageFromModel("green");
    int newBlue = greenImage.getPixelAt(0,0).getColors().get(IPixel.Color.Blue);
    int newRed = greenImage.getPixelAt(0,0).getColors().get(IPixel.Color.Red);
    assertTrue(this.model.containsImage("green"));
    assertEquals(this.green,greenImage.getPixelAt(0,0).getValue());
    assertEquals(this.green,greenImage.getPixelAt(0,0).getIntensity());
    assertEquals(this.green,newRed);
    assertEquals(this.green,newBlue);
  }

  @Test
  public void testCommandLuma() {
    int totalValue = this.red + this.blue + this.green;
    this.model.applyCommand("luma","pixel","luma");
    Image lumaImage = this.model.getImageFromModel("luma");
    assertTrue(this.model.containsImage("luma"));
    int newBlue = lumaImage.getPixelAt(0,0).getColors().get(IPixel.Color.Blue);
    int newRed = lumaImage.getPixelAt(0,0).getColors().get(IPixel.Color.Red);
    int newGreen = lumaImage.getPixelAt(0,0).getColors().get(IPixel.Color.Green);
    assertEquals(75,newRed,1.0);
    assertEquals(75,newGreen,1.0);
    assertEquals(75,newBlue,1.0);

  }

  @Test
  public void testCommandIntensity() {
    int intensity = (this.red + this.blue + this.green) / 3;
    this.model.applyCommand("intensity","pixel","intensity");
    Image intenseImage = this.model.getImageFromModel("intensity");
    assertTrue(this.model.containsImage("intensity"));
    int newBlue = intenseImage.getPixelAt(0,0).getColors().get(IPixel.Color.Blue);
    int newRed = intenseImage.getPixelAt(0,0).getColors().get(IPixel.Color.Red);
    int newGreen = intenseImage.getPixelAt(0,0).getColors().get(IPixel.Color.Green);
    assertEquals(intensity,newRed);
    assertEquals(intensity,newGreen);
    assertEquals(intensity,newBlue);


  }

  @Test
  public void testCommandValue() {
    Integer[] values = {this.red, this.green, this.blue};
    int value = Collections.max(Arrays.asList(values));
    this.model.applyCommand("value","pixel","value");
    Image valueImage = this.model.getImageFromModel("value");
    assertTrue(this.model.containsImage("value"));
    int newBlue = valueImage.getPixelAt(0,0).getColors().get(IPixel.Color.Blue);
    int newRed = valueImage.getPixelAt(0,0).getColors().get(IPixel.Color.Red);
    int newGreen = valueImage.getPixelAt(0,0).getColors().get(IPixel.Color.Green);
    assertEquals(value,newRed);
    assertEquals(value,newGreen);
    assertEquals(value,newBlue);
  }

  @Test
  public void testCommandHorizontal() {
    this.model.applyCommand("horizontal","four","horizontal");
    Image horizontal = this.model.getImageFromModel("horizontal");
    assertEquals(100,horizontal.getPixelAt(0,0).getValue());
    assertEquals(50,horizontal.getPixelAt(0,1).getValue());
    assertEquals(250,horizontal.getPixelAt(1,0).getValue());
    assertEquals(150,horizontal.getPixelAt(1,1).getValue());
  }

  @Test
  public void testCommandVertical() {
    this.model.applyCommand("vertical","four","vertical");
    Image vertical = this.model.getImageFromModel("vertical");
    assertEquals(150,vertical.getPixelAt(0,0).getValue());
    assertEquals(250,vertical.getPixelAt(0,1).getValue());
    assertEquals(50,vertical.getPixelAt(1,0).getValue());
    assertEquals(100,vertical.getPixelAt(1,1).getValue());
  }

  @Test
  public void testCommandBlur() {
    this.jpgModel.applyCommand("blur","jpg","blur");
    Image blur = this.jpgModel.getImageFromModel("blur");
    assertEquals(57,blur.getPixelAt(0,0).getValue());
    assertEquals(71,blur.getPixelAt(0,1).getValue());
    assertEquals(80,blur.getPixelAt(1,0).getValue());
    assertEquals(95,blur.getPixelAt(1,1).getValue());
  }

  @Test
  public void testCommandSharpen() {
    this.jpgModel.applyCommand("sharpen","jpg","sharpen");
    Image sharpen = this.jpgModel.getImageFromModel("sharpen");
    assertEquals(174,sharpen.getPixelAt(0,0).getValue());
    assertEquals(211,sharpen.getPixelAt(0,1).getValue());
    assertEquals(249,sharpen.getPixelAt(1,0).getValue());
    assertEquals(255,sharpen.getPixelAt(1,1).getValue());
  }

  @Test
  public void testCommandGreyscale() {
    this.jpgModel.applyCommand("greyscale","jpg","greyscale");
    Image greyscale = this.jpgModel.getImageFromModel("greyscale");
    assertEquals(50,greyscale.getPixelAt(0,0).getValue());
    assertEquals(100,greyscale.getPixelAt(0,1).getValue());
    assertEquals(150,greyscale.getPixelAt(1,0).getValue());
    assertEquals(250,greyscale.getPixelAt(1,1).getValue());
  }

  @Test
  public void testCommandSepia() {
    this.jpgModel.applyCommand("sepia","jpg","sepia");
    Image sepia = this.jpgModel.getImageFromModel("sepia");
    assertEquals(67,sepia.getPixelAt(0,0).getValue());
    assertEquals(135,sepia.getPixelAt(0,1).getValue());
    assertEquals(202,sepia.getPixelAt(1,0).getValue());
    assertEquals(255,sepia.getPixelAt(1,1).getValue());
  }

}
