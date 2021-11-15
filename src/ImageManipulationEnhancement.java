import controller.GUIControllerImpl;
import controller.IGUIController;
import controller.IMEController;
import model.IMEModelImpl;
import view.IMEViewGUI;

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
    IGUIController ctrlr = new GUIControllerImpl(new IMEModelImpl(), new IMEViewGUI());
    ctrlr.go();

    // N/A
    // -file path-of-script-file
    // -text
  }
}
