package model;

import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;

import model.funcobjs.CommandsBlue;
import model.funcobjs.CommandsGreen;
import model.funcobjs.CommandsIntensity;
import model.funcobjs.CommandsLuma;
import model.funcobjs.CommandsRed;
import model.funcobjs.CommandsValue;
import model.funcobjs.ConvertByHorizontal;
import model.funcobjs.ConvertByVertical;
import model.funcobjs.ICommands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests IMEModelImpl class using standard assertions as well as multiple method calls in a single
 * test method. Uses mock pixels for instantiating an image.
 */
public class IMEModelTest {
  private final IPixel[][] mockPixels = new IPixel[2][2];
  private final Image img1 = new ImagePpm(255, mockPixels);

  private static final HashMap<String, Image> mtImages = new HashMap<>();
  private static final HashMap<String, ICommands> mtCommands = new HashMap<>();
  private static final IMEModel mtModel = new IMEModelImpl(mtImages, mtCommands);
  private final IMEModel model1;
  private final HashMap<String, ICommands> model1Cmds;

  /**
   * Initialize test variables.
   */
  public IMEModelTest() {
    IPixel pixelOne = new MockPixel(1);
    this.mockPixels[0][0] = pixelOne;
    IPixel pixelThree = new MockPixel(3);
    this.mockPixels[1][0] = pixelThree;
    IPixel pixelTwo = new MockPixel(2);
    this.mockPixels[0][1] = pixelTwo;
    IPixel pixelFour = new MockPixel(4);
    this.mockPixels[1][1] = pixelFour;

    this.model1Cmds = new HashMap<>();
    this.model1Cmds.put("horizontal", new ConvertByHorizontal());
    this.model1Cmds.put("vertical", new ConvertByVertical());
    this.model1Cmds.put("blue", new CommandsBlue());
    this.model1Cmds.put("green", new CommandsGreen());
    this.model1Cmds.put("red", new CommandsRed());
    this.model1Cmds.put("intensity", new CommandsIntensity());
    this.model1Cmds.put("value", new CommandsValue());
    this.model1Cmds.put("luma", new CommandsLuma());
    this.model1 = new IMEModelImpl(new HashMap<>(), this.model1Cmds);
  }

  /**
   * Test an invalid null images argument to the 2-arg constructor.
   */
  @Test(expected = NullPointerException.class)
  public void constructor2ArgNullImages() {
    IMEModel model = new IMEModelImpl(null, mtCommands);
  }

  /**
   * Test an invalid null commands argument to the 2-arg constructor.
   */
  @Test(expected = NullPointerException.class)
  public void constructor2ArgNullCommands() {
    IMEModel model = new IMEModelImpl(mtImages, null);
  }

  /**
   * Test invalid null imageName for getImageFromModel method.
   */
  @Test(expected = NullPointerException.class)
  public void getImageFromModelNullImagename() {
    Image invalidImg = this.model1.getImageFromModel(null);
  }

  /**
   * Test invalid null name for addImage method.
   */
  @Test(expected = NullPointerException.class)
  public void getImageFromModelNullName() {
    this.model1.addImage(null, this.img1);
  }

  /**
   * Test invalid null name for addImage method.
   */
  @Test(expected = NullPointerException.class)
  public void getImageFromModelNullImage() {
    this.model1.addImage("img1", null);
  }

  /**
   * Test default behavior of addImage, getImageFromModel methods.
   */
  @Test
  public void checkImages() {
    this.model1.addImage("img1", this.img1);
    assertEquals(this.img1, this.model1.getImageFromModel("img1"));

    assertNull(this.model1.getImageFromModel("unregistered image name"));

    assertNull(this.mtModel.getImageFromModel(""));
  }

  /**
   * Test invalid null command for applyCommand method.
   */
  @Test(expected = NullPointerException.class)
  public void applyCommandNullCommand() {
    this.model1.applyCommand(null, "img1", "img2");
  }

  /**
   * Test invalid null original for applyCommand method.
   */
  @Test(expected = NullPointerException.class)
  public void applyCommandNullOriginal() {
    this.model1.applyCommand("horizontal", null, "img2");
  }

  /**
   * Test invalid null newName for applyCommand method.
   */
  @Test(expected = NullPointerException.class)
  public void applyCommandNullNewname() {
    this.model1.applyCommand("horizontal", "img1", null);
  }

  /**
   * Test getCommandList method.
   */
  @Test
  public void getCommandList() {
    assertEquals(new ArrayList<>(mtCommands.values()), this.mtModel.getCommandList());
    assertEquals(new ArrayList<>(this.model1Cmds.values()), this.model1.getCommandList());
  }

  /**
   * Test invalid null command name to containsCommand method.
   */
  @Test(expected = NullPointerException.class)
  public void containsCommandNullName() {
    assertTrue(this.model1.containsCommand(null));
  }

  /**
   * Test containsCommand method.
   */
  @Test
  public void containsCommand() {
    assertFalse(this.mtModel.containsCommand("horizontal"));
    assertFalse(this.model1.containsCommand("diagonal"));
    assertTrue(this.model1.containsCommand("horizontal"));
  }

  /**
   * Test invalid null command name to containsImage method.
   */
  @Test(expected = NullPointerException.class)
  public void containsImageNullName() {
    assertTrue(this.model1.containsImage(null));
  }

  /**
   * Test containsImage method.
   */
  @Test
  public void containsImage() {
    this.model1.addImage("img1", this.img1);
    assertFalse(mtModel.containsImage("img1"));
    assertFalse(this.model1.containsImage("img2"));
    assertTrue(this.model1.containsImage("img1"));

  }


}
