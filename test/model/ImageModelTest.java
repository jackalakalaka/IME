package model;

import java.io.FileNotFoundException;

public class ImageModelTest {
  iImage koala;

  public ImageModelTest() throws FileNotFoundException {
    this.koala = new ImagePpm("Koala.ppm");
  }
}
