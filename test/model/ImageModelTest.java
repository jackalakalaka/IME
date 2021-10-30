package model;

import java.io.FileNotFoundException;

public class ImageModelTest {
  ImageModel koala;

  public ImageModelTest() throws FileNotFoundException {
    this.koala = new ImageModelPpm("Koala.ppm");
  }
}
