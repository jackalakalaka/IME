package model;

import java.io.FileNotFoundException;

public interface ImageModel {

  Pixel getPixelAt(int row, int col);

  <T> ImageModel convertToViz(T Viz);

  ImageModel changeBrightness(int change);

  void saveImageToFile(String filepath);

}
