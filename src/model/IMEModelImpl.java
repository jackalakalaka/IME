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
import model.FuncObjs.IncreaseBrightness;

public class IMEModelImpl implements IMEModel {
  private final HashMap<String, Image> images;
  private final HashMap<String, ICommands> commands;

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

  public IMEModelImpl(HashMap<String, Image> images, HashMap<String, ICommands> commands) {
    this.images = Objects.requireNonNull(images);
    this.commands = Objects.requireNonNull(commands);
  }

  @Override
  public Image getImageFromModel(String imageName) {
    return images.get(imageName);
  }

  @Override
  public void addImage(String name,Image image) {
    this.images.put(name,image);
  }

  @Override
  public void applyCommand(String command,String original, String newName) {
    this.addImage(newName, commands.get(command).apply(images.get(original)));
  }

  @Override
  public ArrayList<ICommands> getCommandList() {
    return new ArrayList<>(commands.values());
  }

  @Override
  public boolean containsCommand(String command) {
    return commands.containsKey(command);
  }

  @Override
  public boolean containsImage(String imageName) {
    return this.images.containsKey(imageName);
  }


}
