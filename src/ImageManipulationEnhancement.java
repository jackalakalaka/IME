import controller.IMEController;
import controller.IMEControllerCompact;

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
    IMEController ctrl = new IMEControllerCompact();
    ctrl.runIME();
  }
}
