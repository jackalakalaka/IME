
package model;

/**
 * Tests ImagePpm class's utility methods. Also checks that null cannot be input to methods,
 * including the constructor.
 */
class ImagePPMTest extends ImageTest {
  /**
   * Initialize implementation-specific variables.
   */
  public ImagePPMTest() {
    imgType = Image.Type.PPM;
    setUp();
  }
}