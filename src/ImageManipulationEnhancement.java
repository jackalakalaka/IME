import java.io.IOException;

import controller.IMEController;
import controller.IMEControllerImpl;
import factory.IMEModelFactory;

public final class ImageManipulationEnhancement {
  public static void main(String[] args) throws IOException {
    if (args.length > 0) {
      throw new IllegalArgumentException("Image manipulation and enhancement suite is not meant " +
              "to be run with arguments.");
    }
    IMEModelFactory modelFac = new IMEModelFactory();

    IMEController ctrlr = new IMEControllerImpl(modelFac.createIMEModel("standard"));
    ctrlr.runIME();
  }
}