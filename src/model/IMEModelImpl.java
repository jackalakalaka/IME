package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import model.funcobjs.CommandBlur;
import model.funcobjs.CommandGreyscale;
import model.funcobjs.CommandSepia;
import model.funcobjs.CommandSharpen;
import model.funcobjs.CommandsBlue;
import model.funcobjs.CommandsGreen;
import model.funcobjs.CommandsIntensity;
import model.funcobjs.CommandsLuma;
import model.funcobjs.CommandsRed;
import model.funcobjs.CommandsValue;
import model.funcobjs.ConvertByHorizontal;
import model.funcobjs.ConvertByVertical;
import model.funcobjs.ICommands;

/**
 * A representation of an IME model.
 */
public class IMEModelImpl implements IMEModel {
  private final HashMap<String, Image> images;
  private final HashMap<String, ICommands> commands;

  /**
   * Default constructor that creates an empty image list and command list of all the commands.
   */
  public IMEModelImpl() {
    this.images = new HashMap<>();
    this.commands = new HashMap<>();
    this.commands.put("horizontal", new ConvertByHorizontal());
    this.commands.put("vertical", new ConvertByVertical());
    this.commands.put("blue", new CommandsBlue());
    this.commands.put("green", new CommandsGreen());
    this.commands.put("red", new CommandsRed());
    this.commands.put("intensity", new CommandsIntensity());
    this.commands.put("value", new CommandsValue());
    this.commands.put("luma", new CommandsLuma());
    this.commands.put("blur",new CommandBlur());
    this.commands.put("greyscale", new CommandGreyscale());
    this.commands.put("sepia", new CommandSepia());
    this.commands.put("sharpen", new CommandSharpen());
  }

  /**
   * Constructor with both fields represented.
   *
   * @param images   A HashMap of String, Images.
   * @param commands A HashMap of String, ICommands.
   */
  public IMEModelImpl(HashMap<String, Image> images, HashMap<String, ICommands> commands) {
    if (images == null || commands == null) {
      throw new IllegalArgumentException("Model parameters cannot be null.");
    }
    this.images = images;
    this.commands = commands;
  }

  @Override
  public Image getImageFromModel(String imageName) {
    Objects.requireNonNull(imageName);
    return this.images.get(imageName);
  }

  @Override
  public void addImage(String name, Image image) {
    Objects.requireNonNull(name);
    Objects.requireNonNull(image);
    this.images.put(name, image);
  }

  @Override
  public void applyCommand(String command, String original, String newName) {
    Objects.requireNonNull(command);
    Objects.requireNonNull(original);
    Objects.requireNonNull(newName);
    this.addImage(newName, commands.get(command).apply(images.get(original)));
  }

  @Override
  public ArrayList<ICommands> getCommandList() {
    return new ArrayList<>(commands.values());
  }

  @Override
  public boolean containsCommand(String command) {
    Objects.requireNonNull(command);
    return this.commands.containsKey(command);
  }

  @Override
  public boolean containsImage(String imageName) {
    Objects.requireNonNull(imageName);
    return this.images.containsKey(imageName);
  }
}
