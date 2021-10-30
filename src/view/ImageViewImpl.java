package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import model.FuncObjs.ConvertByHorizontal;
import model.FuncObjs.ConvertByHorizontalVertical;
import model.FuncObjs.ConvertByVertical;
import model.FuncObjs.ConvertFromBlue;
import model.FuncObjs.ConvertFromGreen;
import model.FuncObjs.ConvertFromIntensity;
import model.FuncObjs.ConvertFromLuma;
import model.FuncObjs.ConvertFromRed;
import model.FuncObjs.ConvertFromValue;
import model.FuncObjs.IConvertFrom;

/**
 * Implementation of an image viewer.
 */
public class ImageViewImpl implements ImageView {
  private final Appendable appendable;
  private final ArrayList<IConvertFrom> commands;

  /**
   * Default constructor for the view.
   */
  public ImageViewImpl() {
    ArrayList<IConvertFrom> com = new ArrayList<>();
    com.add(new ConvertByHorizontal());
    com.add(new ConvertByVertical());
    com.add(new ConvertByHorizontalVertical());
    com.add(new ConvertFromBlue());
    com.add(new ConvertFromGreen());
    com.add(new ConvertFromRed());
    com.add(new ConvertFromIntensity());
    com.add(new ConvertFromLuma());
    com.add(new ConvertFromValue());
    this.appendable = System.out;
    this.commands = com;
  }

  /**
   * Two input constructor for the view.
   *
   * @param commands The list of commands that are used.
   * @param appendable The appendable for the model or messages.
   */
  public ImageViewImpl(Appendable appendable, ArrayList<IConvertFrom> commands) {
    this.appendable = Objects.requireNonNull(appendable);
    this.commands = Objects.requireNonNull(commands);
  }

  public void printMenu() throws IOException {
    String menu = "Here are the commands for using IME.\n\n" +
            "To see the menu again type 'menu'.\n\n" +
            "To change the brightness of the image type 'brightness x', \n" +
            "where x is the amount you want to change brightness by.\n\n" +
            listCommands();
    appendable.append(menu);
  }

  /**
   * Returns a string builder of all the commands.
   *
   * @return String builder of instructions.
   */
  private StringBuilder listCommands() {
    StringBuilder instructions = new StringBuilder();
    for (IConvertFrom command : commands) {
      instructions.append(command.giveSignature());
    }
    return instructions;
  }

  @Override
  public void createImage(String model) throws IOException {
    appendable.append(model);
  }
}

