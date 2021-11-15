package model.funcobjs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.Objects;
import java.util.Scanner;

import controller.IMEControllerText;
import model.IMEModelImpl;
import view.IMEView;
import view.IMEViewText;

/**
 * A function object that takes a file path to a text document and then runs an instance of IME.
 */
public class CommandScript {
  IMEView view;

  /**
   * Sets IO variables and prepares to read in a script.
   * @param scanner scanner
   * @param view IME view
   */
  public CommandScript(Scanner scanner, IMEView view) {
    this.view = Objects.requireNonNull(view);
    String filePath = scanner.next();
    Appendable appendable = new StringBuilder();
    new IMEControllerText(new IMEModelImpl(), new IMEViewText(appendable),
            getReadable(filePath)).runIME();
  }

  /**
   * Helper for turning script files for the program into readable.
   *
   * @param arg The file path to the text document.
   * @return A readable constructed from the file.
   */
  private Readable getReadable(String arg) {
    File file = new File(arg);
    Scanner sc = new Scanner(" ");
    if (file.exists()) {
      try {
        sc = new Scanner(new FileInputStream(arg));
      } catch (FileNotFoundException e) {
        this.view.renderMsg("The file given was not valid.");
      }
      StringBuilder builder = new StringBuilder();
      int inputCount = 0;
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        builder.append(s).append(" ");
        inputCount++;
        if (inputCount == 3) {
          builder.append(System.lineSeparator());
          inputCount = 0;
        }
      }
      return new StringReader(builder.toString());
    } else {
      this.view.renderMsg("The file given was not valid.");
      return new StringReader("quit");
    }
  }
}

