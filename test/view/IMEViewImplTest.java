package view;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class IMEViewImplTest {
  IMEViewImpl basic = new IMEViewImpl();

  @Test
  public void testMenu() throws IOException {
    basic.printMenu(new ArrayList<>());
  }

}