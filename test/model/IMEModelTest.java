package model;

import java.io.FileNotFoundException;

import factory.ImageFactory;

public class IMEModelTest {
  ImageFactory imgFac;
  Image koala1;

  public IMEModelTest() throws FileNotFoundException {
    this.imgFac = new ImageFactory();
    this.koala1 = this.imgFac.createImage("ppm", "koala1", "Koala.ppm");
  }
}
