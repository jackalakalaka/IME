package model;

import org.junit.Before;
import org.junit.Test;

public class IMEModelTest {
  Image mockImage;
  IMEModel model;

  @Before
  public void setUp()  {
    iPixel[][] pixels = new iPixel[1][1];
    pixels[0][0] = new MockPixel(50);
    this.mockImage = new ImagePpm(255,pixels);
    this.model = new IMEModelImpl();
    this.model.addImage("onePixel",this.mockImage);
  }

  @Test
  public void testAddImage() {
    this.model.addImage("mockImage",this.mockImage);
  }


}
