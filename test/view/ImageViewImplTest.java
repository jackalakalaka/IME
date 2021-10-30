package view;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ImageViewImplTest {
  ImageViewImpl basic = new ImageViewImpl();

  @Test
  public void testMenu() throws IOException {
    basic.printMenu();
  }

}