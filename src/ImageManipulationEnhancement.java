import java.io.IOException;

import controller.IMEController;
import controller.IMEControllerCompact;
import factory.IMEModelFactory;

/**
 * The main class for running the controller.
 */
public final class ImageManipulationEnhancement {

  /**
   * The inner main method for running the controller.
   *
   * @param args Arguments to augment how main runs. (Not implemented)
   */
  public static void main(String[] args) {
    if (args.length > 0) {
      throw new IllegalArgumentException("Image manipulation and enhancement suite is not meant " +
              "to be run with arguments.");
    }
    IMEModelFactory modelFac = new IMEModelFactory();

    IMEController ctrl = new IMEControllerCompact();
    ctrl.runIME();
  }
}