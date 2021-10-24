package model;

public interface ImageModel {

  Pixel getPixelAt(int row, int col);

  ImageModel imageFromFile(String filePath);

  <T> ImageModel convertToViz(T Viz);

  ImageModel changeBrightness(int change);

  void saveImageToFile(String filepath);

}
