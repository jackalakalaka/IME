import java.io.StringReader;

import controller.IMEController;
import controller.IMEControllerGUI;
import controller.IMEControllerText;
import model.IMEModelImpl;
import view.IMEViewText;

/**
 * The main class for running the controller.
 */
public final class ImageManipulationEnhancement {

  /**
   * The inner main method for running the controller.
   *
   * @param args Arguments to augment how main runs.
   */
  public static void main(String[] args) {
    IMEController ctrlr;

    switch (args.length) {
      case 0:
        ctrlr = new IMEControllerGUI();
        ctrlr.runIME();
        break;
      case 1:
        if (args[0].equals("-text")) {
          ctrlr = new IMEControllerText();
          ctrlr.runIME();
        } else {
          throw new RuntimeException("Argument(s) to IME program not recognized.");
        }
        break;
      case 2:
        if (args[0].equals("-file")) {
          Readable reader = new StringReader(String.format("-file %1$s", args[1]));
          ctrlr = new IMEControllerText(new IMEModelImpl(), new IMEViewText(), reader);
          ctrlr.runIME();
        } else {
          throw new RuntimeException("Argument(s) to IME program not recognized.");
        }
        break;
      default:
        throw new RuntimeException("Argument(s) to IME program not recognized.");
    }
  }
}
