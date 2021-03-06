package model.image;

import model.funcobjs.ICommands;

/**
 * Interface for representing images in multiple formats. Can access and modify their data using
 * this data model.
 */
public interface Image {

  /**
   * This enum helps methods and commands know what type of image they are working with.
   */
  enum Type { PPM, JPG, PNG, BMP }

  /**
   * This is a simple getter for the type of the image.
   *
   * @return A Type enum.
   */
  Type getType();

  /**
   * Gets the height of the image.
   *
   * @return Integer image height.
   */
  int getHeight();

  /**
   * Gets the width of the image.
   *
   * @return Integer image width.
   */
  int getWidth();

  /**
   * Gets the maximum value of the image.
   *
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
  IPixel getPixelAt(int row, int col);

  Image convertToViz(ICommands cmd);

  /**
   * Creates a new ImageModel that mirrors the other model but with the brightness changed.
   *
   * @param change The integer value in which the brightness is changed.
   * @return ImageModel with its brightness changed (or not if change == zero).
   */
  Image changeBrightness(int change);

  /**
   * Takes ina  file path and attempts to save the image to a file.
   *
   * @param filepath The path to the file.
   * @throws IllegalStateException If the file path does not exist.
   */
  void saveImageToFile(String filepath) throws IllegalStateException;

}
