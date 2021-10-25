package model;

public interface ImageModel {

  /**
   * Retrieves the pixel at the given location in the Array.
   *
   * @param row The row in the array where the pixel is located.
   * @param col The column in the array where the pixel is located.
   * @return A Pixel.
   */
  Pixel getPixelAt(int row, int col);

  <T> ImageModel convertToViz(T Viz);

  /**
   * Creates a new ImageModel that mirrors the other model but with the brightness changed.
   *
   * @param change The integer value in which the brightness is changed.
   * @return ImageModel with its brightness changed (or not if change == zero).
   */
  ImageModel changeBrightness(int change);

  void saveImageToFile(String filepath);

}
