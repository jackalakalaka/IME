package model;

import org.junit.Test;

import java.util.HashMap;

import model.FuncObjs.CommandsIntensity;
import model.FuncObjs.ICommands;

import static org.junit.Assert.assertEquals;

public class IMEModelTest {
  private static final HashMap<String, Image> mtImages = new HashMap<>();
  private static final HashMap<String, ICommands> mtCommands = new HashMap<>();
  private final IMEModel model1 = new IMEModelImpl();

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
   * Test addImage, getImageFromModel methods.
   */
  @Test
  public void getImageFromModel() {
    assertEquals(new CommandsIntensity(), this.model1.getImageFromModel());
  }

}
