import org.junit.Test;

import java.io.FileNotFoundException;

import factory.ImageFactory;
import model.Image;
import model.ImageJPG;

import static org.junit.Assert.assertEquals;

public class scribles {

  @Test
  public void test() throws FileNotFoundException {
    String word = "word.ppm";
    assertEquals(".ppm",word.substring(word.length()-4));
    Image image = new ImageFactory().createImage("res/onePixelImage.ppm");
    Image jpg = new ImageJPG("066.jpg");
    assertEquals(2,2);
  }


}
