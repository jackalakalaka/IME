package model.image;

/**
 * Tests ImagePpm class's utility methods. Also checks that null cannot be input to methods,
 * including the constructor.
 */
public class ImagePPMTest extends ImageTest {
  /**
   * Initialize implementation-specific variables.
   */
  public ImagePPMTest() {
    imgType = Image.Type.PPM;
    setUp();
  }
}