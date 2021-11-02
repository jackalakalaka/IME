package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import model.FuncObjs.CommandsBlue;
import model.FuncObjs.CommandsGreen;
import model.FuncObjs.CommandsIntensity;
import model.FuncObjs.CommandsLuma;
import model.FuncObjs.CommandsRed;
import model.FuncObjs.CommandsValue;
import model.FuncObjs.ConvertByHorizontal;
import model.FuncObjs.ConvertByVertical;
import model.FuncObjs.ICommands;

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
  }

  /**
   * Constructor with both fields represented.
   *
   * @param images   A HashMap of String, Images.
   * @param commands A HashMap of String, ICommands.
   */
  public IMEModelImpl(HashMap<String, Image> images, HashMap<String, ICommands> commands) {
    this.images = Objects.requireNonNull(images);
    this.commands = Objects.requireNonNull(commands);
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
