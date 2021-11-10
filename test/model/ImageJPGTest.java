
package model;

/**
 * Tests ImageJpg class's utility methods. Also checks that null cannot be input to methods,
 * including the constructor.
 */
public class ImageJPGTest extends ImageTest {
  /**
   * Initialize implementation-specific variables.
   */
  public ImageJPGTest() {
    imgType = Image.Type.JPG;
    setUp();
  }
}