package controller;

import org.junit.Test;

import java.io.IOException;

import model.IMEModel;
import model.IMEModelImpl;
import view.IMEView;
import view.IMEViewImpl;

public class IMEControllerImplTest {
  IMEController ctrlr1;

  public IMEControllerImplTest() {
    IMEModel model1 = new IMEModelImpl();
    IMEView view1 = new IMEViewImpl();
    this.ctrlr1 = new IMEControllerImpl(model1, view1);
  }

  @Test
  public void runIME() throws IOException {
    this.ctrlr1.runIME();
  }
}