package model;

import java.io.IOException;

import model.FuncObjs.ICommands;


public interface Image {

  /**
   * @return height of image in pixels
   */
  int getHeight();

  /**
   * @return width of image in pixels
   */
  int getWidth();

  /**
   * @return the maximum value of this image's pixels, defining white / the color scale.
   */
  int getMaxValue();

  /**
   * Retrieves the pixel at the given location in the Array.
   *
   * @param row The row in the array where the pixel is located.
   * @param col The column in the array where the pixel is located.
   * @return A Pixel.
   */
  iPixel getPixelAt(int row, int col);

  Image convertToViz(ICommands cmd);

  /**
   * Creates a new ImageModel that mirrors the other model but with the brightness changed.
   *
   * @param change The integer value in which the brightness is changed.
   * @return ImageModel with its brightness changed (or not if change == zero).
   */
  Image changeBrightness(int change);

  void saveImageToFile(String filepath) throws IllegalStateException;

}
