package model;

/**
 * Tests ImagePng class's utility methods. Also checks that null cannot be input to methods,
 * including the constructor.
 */
public class ImagePNGTest extends ImageTest {
  /**
   * Initialize implementation-specific variables.
   */
  public ImagePNGTest() {
    imgType = Image.Type.PNG;
    setUp();
  }
}
