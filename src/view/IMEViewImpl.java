package view;


import java.io.IOException;
import java.util.List;
import java.util.Objects;

import model.funcobjs.ICommands;


/**
 * Implementation of an image viewer.
 */
public class IMEViewImpl implements IMEView {
  private final Appendable appendable;


  /**
   * Default constructor for the view.
   */
  public IMEViewImpl() {
    this.appendable = System.out;
  }

  /**
   * Two input constructor for the view.
   *
   * @param appendable The appendable for the model or messages.
   */
  public IMEViewImpl(Appendable appendable) {
    this.appendable = Objects.requireNonNull(appendable);
  }

  @Override
  public void printMenu(List<ICommands> commandsHashMap) {
    Objects.requireNonNull(commandsHashMap);

    StringBuilder menu = new StringBuilder(("Here are the commands for using IME " +
            "(Image Manipulation & Enhancement).\n") +
            ("- To quit type: quit.\n") +
            ("- To load an image type: load <image-name> <file-path>.\n") +
            ("- To save an image type: save <image-name> <file-name>.\n") +
            "- To change the brightness type: brightness <image-name> " +
            "<integer-change> <new-name>.\n");
    for (ICommands commands : commandsHashMap) {
      menu.append(commands.giveSignature());
    }
    this.renderMsg(menu.toString());
  }

  @Override
  public void renderMsg(String str) throws IllegalStateException {
    Objects.requireNonNull(str);

    try {
      this.appendable.append(str);
    } catch (IOException e) {
      throw new IllegalStateException("There is nothing to append to.");
    }
  }

}

