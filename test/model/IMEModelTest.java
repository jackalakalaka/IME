package model;

import org.junit.Test;

import java.io.FileNotFoundException;

public class IMEModelTest {
  Image koala1;

  @Test
  public void test() throws FileNotFoundException {
    this.koala1 = new ImagePpm("koalaoriginal.ppm");
    this.koala1.getPixelAt(0, 0);
  }

}
