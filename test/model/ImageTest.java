package model;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

import factory.ImageFactory;
import model.funcobjs.CommandsBlue;
import model.funcobjs.CommandsValue;
import model.funcobjs.ConvertByHorizontal;

import static org.junit.Assert.assertEquals;

public abstract class ImageTest {
  // Factory obj whose methods are customized by image implementation.
  protected static ImageFactory imgFac = new ImageFactory();
  protected static Image.Type imgType;
  protected static String imgTypeStr;

  // Pixels and pixel arrays
  protected static final IPixel[][] mockPixels = new IPixel[2][2];
  protected static final IPixel[][] mockPixelsWide = new IPixel[2][3];
  protected final IPixel pixelZero = new MockPixel(0);
  protected final IPixel pixelOne = new MockPixel(1);
  protected final IPixel pixelTwo = new MockPixel(2);
  protected final IPixel pixelThree = new MockPixel(3);
  protected final IPixel pixelFour = new MockPixel(4);

  // Testing example image models
  protected Image modelOne;
  protected Image modelTwo;

  /**
   * Initialize data.
   */
  public ImageTest() {
  }

  /**
   * Initialize data.
   */
  public void setUp() {
    mockPixels[0][0] = pixelOne;
    mockPixels[1][0] = pixelThree;
    mockPixels[0][1] = pixelTwo;
    mockPixels[1][1] = pixelFour;
    mockPixelsWide[0][0] = pixelOne;
    mockPixelsWide[1][0] = pixelThree;
    mockPixelsWide[0][1] = pixelTwo;
    mockPixelsWide[1][1] = pixelFour;
    mockPixelsWide[0][2] = pixelZero;
    mockPixelsWide[1][2] = pixelZero;

    imgTypeStr = imgType.toString().toLowerCase();
    this.modelOne = imgFac.createImage(255, mockPixels, imgType);
    this.modelTwo = imgFac.createImage(255, mockPixelsWide, imgType);
  }

  /**
   * Test an invalid null filepath argument to the 1-arg constructor.
   */
  @Test(expected = NullPointerException.class)
  public void constructor1ArgNullFilepath() {
    Image modelOne = imgFac.createImage(null);
  }

  /**
   * Test an invalid filepath argument to the 1-arg constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void constructor1ArgInvalidFilepath() {
    Image modelOne = imgFac.createImage("hehehehehe");
  }

  /**
   * Test an invalid null pixel array input to the 2-arg constructor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void constructor2ArgPixelsNull() throws IllegalArgumentException {
    Image modelOne = imgFac.createImage(255, null, imgType);
  }

  /**
   * Test all the getter functions.
   */
  @Test
  public void testGetters() {
    assertEquals(2, this.modelOne.getHeight());
    assertEquals(3, this.modelTwo.getWidth());
    assertEquals(255, this.modelOne.getMaxValue());
  }

  /**
   * Test to see if the getPixelAt function works consistently.
   */
  @Test
  public void testPixelFetch() {
    assertEquals(this.pixelOne.getValue(), this.modelOne.getPixelAt(0, 0).getValue());
    assertEquals(this.pixelTwo.getValue(), this.modelOne.getPixelAt(0, 1).getValue());
    Image horzFlipOne = new ConvertByHorizontal().apply(this.modelOne);
    assertEquals(this.pixelOne.getValue(), horzFlipOne.getPixelAt(0, 1).getValue());
    assertEquals(this.pixelTwo.getValue(), horzFlipOne.getPixelAt(0, 0).getValue());
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
    assertEquals(1, blue.getPixelAt(0, 0).getValue());
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

  /**
   * Tests what should be identical pixel arrays of the same image in different image formats.
   */
  @Test
  public void testLoadingAndSameness() {
    String filepathNoExt = "./res/goat"; // Example image for type-type comparison

    // Create a hashmap of each image type and other types that its type's pixels have been compared
    // with. Before comparison, each type's HashSet should be empty.
    HashMap<Image.Type, HashSet<Image.Type>> typesAndComparedTo = new HashMap<>();
    for (Image.Type type : Image.Type.values()) {
      typesAndComparedTo.put(type, new HashSet<>());
    }

    // For each image format, compare pixels with each other format if the 2 are not the same or
    // haven't already been compared.
    for (Image.Type thisType : Image.Type.values()) {
      for (Image.Type thatType : Image.Type.values()) {
        if (thisType.equals(thatType) || typesAndComparedTo.get(thisType).contains(thatType)) {
          continue;
        } else { // If types still need to be compared, register the comparison for each of them.
          typesAndComparedTo.get(thisType).add(thatType);
          typesAndComparedTo.get(thatType).add(thisType);
        }

        String thisFileExt = "." + thisType.toString().toLowerCase();
        String thatFileExt = "." + thatType.toString().toLowerCase();
        Image thisImg = imgFac.createImage(filepathNoExt + thisFileExt);
        Image thatImg = imgFac.createImage(filepathNoExt + thatFileExt);
        int imgH = thisImg.getHeight();
        int imgW = thisImg.getWidth();

        // Check that each format's pixel channels are identical.
        for (int i = 0; i < imgH; i++) {
          for (int j = 0; j < imgW; j++) {
            assertEquals(thisImg.getPixelAt(i,j).getColors().get(IPixel.Color.Red),
                    thatImg.getPixelAt(i,j).getColors().get(IPixel.Color.Red),20);
            assertEquals(thisImg.getPixelAt(i,j).getColors().get(IPixel.Color.Green),
                    thatImg.getPixelAt(i,j).getColors().get(IPixel.Color.Green),20);
            assertEquals(thisImg.getPixelAt(i,j).getColors().get(IPixel.Color.Blue),
                    thatImg.getPixelAt(i,j).getColors().get(IPixel.Color.Blue),20);
          }
        }
      }
    }
  }

}
