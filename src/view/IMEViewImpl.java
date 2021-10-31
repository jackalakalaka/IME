package view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import factory.ConvertFactory;

/**
 * Implementation of an image viewer.
 */
public class IMEViewImpl implements IMEView {
  private final Appendable appendable;
  private final HashMap<String, String> twoArgCmds;
  private final HashMap<String, String> threeArgCmds;
  private final HashMap<String, ConvertFactory> conversionCmds;

  /**
   * Default constructor for the view.
   */
  public IMEViewImpl() {
    this.twoArgCmds = new HashMap<>();
    String loadInstr = "- To load an img from a file, type 'load <file_path> <img_name>'.\n";
    this.twoArgCmds.put("load", loadInstr);
    String saveInstr = "- To save an img to a file, type 'save <file_path> <img_name>'.\n";
    this.twoArgCmds.put("save", saveInstr);

    this.threeArgCmds = new HashMap<>();
    String brightnessInstr = "- To chg brightness of the img, type 'brightness <value_chg>" +
            "<img_former> <img_new>'.\n";
    this.threeArgCmds.put("brightness", brightnessInstr);

    this.conversionCmds = new HashMap<>();
    this.conversionCmds.put("horizontal-flip", new ConvertFactory("horizontal-flip"));
    this.conversionCmds.put("vertical-flip", new ConvertFactory("vertical-flip"));
//    this.conversionCmds.put("flip-both", new ConvertFactory("flip-both"));
    this.conversionCmds.put("blue-component", new ConvertFactory("blue-component"));
    this.conversionCmds.put("green-component", new ConvertFactory("green-component"));
    this.conversionCmds.put("red-component", new ConvertFactory("red-component"));
    this.conversionCmds.put("intensity-component", new ConvertFactory("intensity-component"));
    this.conversionCmds.put("value-component", new ConvertFactory("value-component"));
    this.conversionCmds.put("luma", new ConvertFactory("luma"));

    this.appendable = System.out;
  }

  public IMEViewImpl(HashMap<String, String> twoArgCmds,
                     HashMap<String, String> threeArgCmds,
                     HashMap<String, ConvertFactory> conversionCmds) {
    this.appendable = System.out;
    this.twoArgCmds = twoArgCmds;
    this.threeArgCmds = threeArgCmds;
    this.conversionCmds = conversionCmds;
  }

  /**
   * Two input constructor for the view.
   *
   * @param conversionCmds The list of commands that are used.
   * @param appendable     The appendable for the model or messages.
   */
  public IMEViewImpl(Appendable appendable,
                     HashMap<String, String> twoArgCmds,
                     HashMap<String, String> threeArgCmds,
                     HashMap<String, ConvertFactory> conversionCmds) {
    this.appendable = Objects.requireNonNull(appendable);
    this.twoArgCmds = twoArgCmds;
    this.threeArgCmds = threeArgCmds;
    this.conversionCmds = Objects.requireNonNull(conversionCmds);
  }

  public void printMenu() throws IOException {
    String menu = "Here are the commands for using IME (Image Manipulation & Enhancement).\n" +
            "To see the menu again type 'menu'.\n" +
            "To quit IME, type 'quit'.\n\n" +
            this.listDefaultInstr() +
            this.listConversionInstr();
    this.appendable.append(menu);
  }

  /**
   * Returns a string builder of all the default commands' instructions.
   *
   * @return String builder of instructions.
   */
  private StringBuilder listDefaultInstr() {
    StringBuilder instructions = new StringBuilder();
    for (Map.Entry<String, String> commandPair : this.twoArgCmds.entrySet()) {
      instructions.append(commandPair.getValue());
    }
    for (Map.Entry<String, String> commandPair : this.threeArgCmds.entrySet()) {
      instructions.append(commandPair.getValue());
    }
    return instructions;
  }

  /**
   * Returns a string builder of all the conversion commands' instructions.
   *
   * @return String builder of instructions.
   */
  private StringBuilder listConversionInstr() throws FileNotFoundException {
    StringBuilder instructions = new StringBuilder();
    for (Map.Entry<String, ConvertFactory> commandPair : this.conversionCmds.entrySet()) {
      instructions.append(commandPair.getValue().createConverter("").giveSignature());
    }
    return instructions;
  }

  @Override
  public void renderMsg(String str) throws IOException {
    this.appendable.append(str);
  }

  @Override
  public void createImage(String model) throws IOException {
    this.appendable.append(model);
  }
}

