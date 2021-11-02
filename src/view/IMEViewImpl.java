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

  public void printMenu(List<ICommands> commandsHashMap) throws IOException {
    StringBuilder menu = new StringBuilder(("Here are the commands for using IME " +
            "(Image Manipulation & Enhancement).\n") +
            ("- To quit type: quit.\n") +
            ("- To load an image type: load <image-name> <file-path>.\n") +
            ("- To save an image type: save <image-name> <file-name>.\n") +
            new IncreaseBrightness(0).giveSignature());
            for (ICommands commands : commandsHashMap) {
              menu.append(commands.giveSignature());
            }
    this.appendable.append(menu.toString());
  }

//  /**
//   * Returns a string builder of all the default commands' instructions.
//   *
//   * @return String builder of instructions.
//   */
//  private StringBuilder listDefaultInstr() {
//    StringBuilder instructions = new StringBuilder();
//    for (Map.Entry<String, String> commandPair : this.twoArgCmds.entrySet()) {
//      instructions.append(commandPair.getValue());
//    }
//    for (Map.Entry<String, String> commandPair : this.threeArgCmds.entrySet()) {
//      instructions.append(commandPair.getValue());
//    }
//    return instructions;
//  }

//  /**
//   * Returns a string builder of all the conversion commands' instructions.
//   *
//   * @return String builder of instructions.
//   */
//  private StringBuilder listConversionInstr() throws FileNotFoundException {
//    StringBuilder instructions = new StringBuilder();
//    for (Map.Entry<String, ConvertFactory> commandPair : this.conversionCmds.entrySet()) {
//      instructions.append(commandPair.getValue().createConverter("").giveSignature());
//    }
//    return instructions;
//  }

  @Override
  public void renderMsg(String str) throws IOException {
    this.appendable.append(str);
  }

}

