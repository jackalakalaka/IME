package model;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class ImageModelImplTest {

  ImageModel koala = new ImageModelImpl("Koala.ppm");

  public ImageModelImplTest() throws FileNotFoundException {
  }

  @Test
  public void testModel() {
    assertEquals(new Pixel(255,1,1,1).toString(),
            this.koala.getPixelAt(240,200).toString());
  }

}