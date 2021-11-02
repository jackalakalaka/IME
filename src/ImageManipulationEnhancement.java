import java.io.IOException;

import controller.IMEController;
import controller.IMEControllerCompact;
import factory.IMEModelFactory;

public final class ImageManipulationEnhancement {
  public static void main(String[] args) throws IOException {
    if (args.length > 0) {
      throw new IllegalArgumentException("Image manipulation and enhancement suite is not meant " +
              "to be run with arguments.");
    }
    IMEModelFactory modelFac = new IMEModelFactory();

    IMEController ctrl = new IMEControllerCompact();
    ctrl.runIME();
  }
}