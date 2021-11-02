package view;


import java.io.IOException;
import java.util.List;
import java.util.Objects;
import model.FuncObjs.ICommands;
import model.FuncObjs.IncreaseBrightness;

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
   * @param appendable     The appendable for the model or messages.
   */
  public IMEViewImpl(Appendable appendable) {
    this.appendable = Objects.requireNonNull(appendable);
  }

  public void printMenu(List<ICommands> commands) throws IOException {
    StringBuilder menu = new StringBuilder(("Here are the commands for using IME " +
            "(Image Manipulation & Enhancement).\n") +
            ("- To quit type: quit.\n") +
            ("- To load an image type: load <image-name> <file-path>.\n") +
            ("- To save an image type: save <image-name> <file-name>.\n") +
            new IncreaseBrightness(0).giveSignature());
            for (ICommands command : commands) {
              menu.append(command.giveSignature());
            }
    this.appendable.append(menu.toString());
  }

  @Override
  public void renderMsg(String str) throws IOException {
    this.appendable.append(str);
  }

}

