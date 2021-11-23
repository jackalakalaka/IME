
package model.image;

/**
 * Tests ImageBMP class's utility methods. Also checks that null cannot be input to methods,
 * including the constructor.
 */
public class ImageBMPTest extends ImageTest {
  /**
   * Initialize implementation-specific variables.
   */
  public ImageBMPTest() {
    imgType = Image.Type.BMP;
    setUp();
  }
}