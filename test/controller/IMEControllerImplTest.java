package controller;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import model.IMEModel;
import model.IMEModelImpl;
import view.IMEView;
import view.IMEViewImpl;

public class IMEControllerImplTest {
  IMEController controller;

  public IMEControllerImplTest() {
    IMEModel model1 = new IMEModelImpl();
    IMEView view1 = new IMEViewImpl();
    Readable readable = new StringReader("");
       this.controller = new IMEControllerCompact(model1, view1, readable);
  }

  @Test (expected = IllegalStateException.class)
  public void testBadReadable() {
    this.controller.runIME();
  }
}