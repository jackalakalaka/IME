package model;

import java.util.ArrayList;

import model.FuncObjs.ICommands;

public interface IMEModel {

  Image getImageFromModel(String imageName);

  void addImage(String name, Image Image);

  void applyCommand(String command, String original, String newName);

  ArrayList<ICommands> getCommandList();

  boolean containsCommand(String command);

  boolean containsImage(String imageName);
}
